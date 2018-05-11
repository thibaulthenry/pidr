package main.graphics.path;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import main.graphics.entities.Drone;
import main.graphics.entities.Entity;
import main.graphics.entities.Rotor;
import main.graphics.entities.TrajectorySphere;
import main.graphics.renderer.DisplayRenderer;
import main.graphics.terrains.Terrain;
import main.parameters.TrajectoryManager;

public class CSVConverter {

	private static String[][] trajectory = CSVConverter.convert("resources/simul/" + TrajectoryManager.CSV_FILENAME + ".csv");
	public static int trajectorySize;
	public static int trajectoryStep = 0;
	
	private static int currentIndex = 0;
	private static int sphereIndex = 0;

	private static String[][] convert(String path){
		String tab[][] = new String[7][];
		int i = 1;

		try{
			BufferedReader fichier_source = null;
			fichier_source = new BufferedReader(new FileReader(path));
			String chaine;
			
			try {
				while((chaine = fichier_source.readLine())!= null)
				{
					tab[i] = chaine.split(",");
					trajectorySize = tab[i].length - 1;
					i++;
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			fichier_source.close();

		}
		catch (FileNotFoundException e)
		{
			System.out.println("Le fichier trajectoire est introuvable !");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tab;
	}
	
	public static Vector3f getPosition(int index) {
		return new Vector3f(
				Float.parseFloat(trajectory[1][index]) * TrajectoryManager.SIMULATION_SIZE_RATIO + Terrain.TERRAIN_CENTER,
				Float.parseFloat(trajectory[3][index]) * TrajectoryManager.SIMULATION_SIZE_RATIO,
				Float.parseFloat(trajectory[2][index]) * TrajectoryManager.SIMULATION_SIZE_RATIO + Terrain.TERRAIN_CENTER);
	}
	
	public static Vector3f getPositionRotor(int index,int numrotor) {
		double psi=-Float.parseFloat(trajectory[4][index])*Math.PI/2;
		double phi=-Float.parseFloat(trajectory[5][index])*Math.PI/2;
		double theta;
		double a3;
		double dist= Math.sqrt(315*315+163*163)*0.1;
		switch(numrotor) {
		case 1:
			theta=Float.parseFloat(trajectory[6][index])*Math.PI/2+0.52;
			a3=dist*((Math.sin(psi)));
			break;
		case 2:
			theta=Float.parseFloat(trajectory[6][index])*Math.PI/2+2.65;
			a3=dist*((Math.sin(psi)));
			break;
		case 3:
			theta=Float.parseFloat(trajectory[6][index])*Math.PI/2+3.78;
			a3=dist*(-(Math.sin(psi)));
			break;
		default:
			theta=Float.parseFloat(trajectory[6][index])*Math.PI/2+5.53;
			a3=dist*((Math.sin(psi)));
			break;
		}
		
		
		
		double a1=dist*(Math.cos(psi)*Math.cos(theta));
		float b1= (float) a1;
		
		double a2=dist*( Math.cos(psi)*Math.sin(theta));
		float b2= (float) a2;
		
		
		float b3= (float) a3;

		
		return new Vector3f(
				(Float.parseFloat(trajectory[1][index])) * TrajectoryManager.SIMULATION_SIZE_RATIO -b1+ Terrain.TERRAIN_CENTER,
				(Float.parseFloat(trajectory[3][index])) * TrajectoryManager.SIMULATION_SIZE_RATIO +b3 +7 ,
				(Float.parseFloat(trajectory[2][index])) * TrajectoryManager.SIMULATION_SIZE_RATIO +b2 + Terrain.TERRAIN_CENTER);
		
		
		
	}
	
	public static Vector3f getRotation(int index) {
		return new Vector3f(
				Float.parseFloat(trajectory[4][index]) * 90,
				Float.parseFloat(trajectory[6][index]) * 90,
				Float.parseFloat(trajectory[5][index]) * 90);
	}
	
	public static Vector3f getRotationRotor(int index) {
		return new Vector3f(
				Float.parseFloat(trajectory[4][index]) * 90,
				Float.parseFloat(trajectory[6][index]) * 90*100,
				Float.parseFloat(trajectory[5][index]) * 90);
	}
	
	
	
	public static void calculateStep() {
		trajectoryStep = (int) (1000 / (1/ DisplayRenderer.getFrameTimeSeconds()));
	}
	
	public static void update(Drone drone, List<Entity> entities,Rotor rotor1,Rotor rotor2,Rotor rotor3,Rotor rotor4) {
		drone.followSimulation(currentIndex);
		rotor1.followSimulation(currentIndex,1);
		rotor2.followSimulation(currentIndex,2);
		rotor3.followSimulation(currentIndex,3);
		rotor4.followSimulation(currentIndex,4);

		if (sphereIndex == 0 || ((currentIndex - sphereIndex) == ((1 / TrajectoryManager.SPHERE_SPAWN_FREQ) * trajectoryStep))) {
			entities.add(new TrajectorySphere(currentIndex));
			sphereIndex = currentIndex;
		}
		
		if (currentIndex >= trajectorySize - trajectoryStep * TrajectoryManager.SIMULATION_SPEEDFACTOR - 1) {
			currentIndex = 0;
			sphereIndex = 0;
			drone.clearAllWithoutDrone(entities);
		} else {
			currentIndex += trajectoryStep * TrajectoryManager.SIMULATION_SPEEDFACTOR;
		}
	}

}





package main.graphics.path;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import main.graphics.entities.Drone;
import main.graphics.entities.Entity;
import main.graphics.entities.TrajectorySphere;
import main.graphics.renderer.DisplayRenderer;
import main.graphics.terrains.Terrain;
import main.parameters.TrajectoryManager;

public class CSVConverter {

	private static String[][] trajectory = CSVConverter.convert("resources\\simul\\" + TrajectoryManager.CSV_FILENAME + ".csv");
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
	
	public static Vector3f getRotation(int index) {
		return new Vector3f(
				Float.parseFloat(trajectory[4][index]) * 90,
				Float.parseFloat(trajectory[6][index]) * 90,
				Float.parseFloat(trajectory[5][index]) * 90);
	}
	
	public static void calculateStep() {
		trajectoryStep = (int) (1000 / (1/ DisplayRenderer.getFrameTimeSeconds()));
	}
	
	public static void update(Drone drone, List<Entity> entities) {
		drone.followSimulation(currentIndex);

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





package main.graphics.path;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import main.graphics.entities.Entity;
import main.graphics.entities.TrajectorySphere;
import main.graphics.guis.GuiTexture;
import main.graphics.recorder.SequenceEncoder;
import main.graphics.renderer.DisplayRenderer;
import main.graphics.terrains.Terrain;
import main.parameters.DisplayManager;
import main.parameters.EntityManager;
import main.parameters.TextureManager;
import main.parameters.TrajectoryManager;

public class CSVConverter {

	private static String[][] trajectory = CSVConverter.convert("resources/simul/" + TrajectoryManager.CSV_FILENAME + ".csv");
	public static int trajectorySize;
	public static int trajectoryStep = 0;
	
	public static int currentIndex = 0;
	private static int sphereIndex = 0;
	
	private static List<Integer> fpsRange = new ArrayList<Integer>();
	private static float startAnalyseTime = 0;

	public static String[][] convert(String path){
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
	
	public static Vector3f getPositionRotor(int index) {
		
		return new Vector3f(
				(Float.parseFloat(trajectory[1][index])) * TrajectoryManager.SIMULATION_SIZE_RATIO + Terrain.TERRAIN_CENTER,
				(Float.parseFloat(trajectory[3][index])) * TrajectoryManager.SIMULATION_SIZE_RATIO  ,
				(Float.parseFloat(trajectory[2][index])) * TrajectoryManager.SIMULATION_SIZE_RATIO + Terrain.TERRAIN_CENTER);
		
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
				Float.parseFloat(trajectory[6][index]) * 90,
				Float.parseFloat(trajectory[5][index]) * 90);
	}
	
	public static void update(List<Entity> entities) {
		Entity.getEntityById(entities, EntityManager.droneID).followSimulation(currentIndex);
		Entity.getEntityById(entities, EntityManager.rotor1ID).followSimulation(currentIndex);
		Entity.getEntityById(entities, EntityManager.rotor2ID).followSimulation(currentIndex);
		Entity.getEntityById(entities, EntityManager.rotor3ID).followSimulation(currentIndex);
		Entity.getEntityById(entities, EntityManager.rotor4ID).followSimulation(currentIndex);

	
		if (sphereIndex == 0 || ((currentIndex - sphereIndex) >= ((1 / TrajectoryManager.SPHERE_SPAWN_FREQ) * trajectoryStep))) {
			if (TrajectoryManager.ACTIVATE_SPHERE) entities.add(TrajectorySphere.createSphere(currentIndex));
			sphereIndex = currentIndex;
		}
		if (currentIndex >= trajectorySize - trajectoryStep * TrajectoryManager.SIMULATION_SPEEDFACTOR - 1) {
			currentIndex = 0;
			sphereIndex = 0;
			Entity.clearAllWithoutDrone(entities);
		} else {
			currentIndex += trajectoryStep * TrajectoryManager.SIMULATION_SPEEDFACTOR;
		}
		
		//Easter egg
		if (TrajectorySphere.EASTER_EGG) {
			for (Entity entity : entities) if (entity instanceof TrajectorySphere) entity.rotate(((TrajectorySphere) entity).getEasterEggRotation());
		}
	}
	
	public static int analysePerformance(Boolean affectTrajectoryStep) {
		float sum = 0;
		for (Integer fps : fpsRange) {
			if (fps < DisplayRenderer.FPS_CAP) sum += fps;
		}

		int averageFPS = (int) (sum / fpsRange.size());
		if (affectTrajectoryStep) trajectoryStep = (int) (TrajectoryManager.dataEntryPerSimulationSecond / averageFPS);
		SequenceEncoder.averageFPS = averageFPS;
		fpsRange.clear();
		return trajectoryStep;
	}
	
	public static boolean mustCalculateCurrentFPS() {
		return trajectoryStep == 0;
	}
	
	public static boolean canAnalysePerformance() {
		return (DisplayRenderer.getTimeSinceStart() - startAnalyseTime) > DisplayManager.PERF_ANALYSE_DURATION;  
	}
	
	public static void calculateCurrentFPS() {
		if (startAnalyseTime == 0) startAnalyseTime = DisplayRenderer.getTimeSinceStart();
		fpsRange.add(DisplayRenderer.getCurrentFPS());
	}
	
	public static GuiTexture analysingGui() {
		return new GuiTexture(TextureManager.analysingTexture, new Vector2f(0f,0f), new Vector2f(1f,1f));
	}

}





package main.parameters;

public class TrajectoryManager {
	
	public final static String CSV_FILENAME= "simulation";
	public final static String CSV_ROTORS= "Rotors";
	public final static int dataEntryPerSimulationSecond = 1000;
	
	
	public static int SIMULATION_SPEEDFACTOR = 1;
	public static float SIMULATION_SIZE_RATIO = 100;
	
	public static boolean ACTIVATE_SPHERE = false;

	public static float DroneSize = 4f;
	public static float SPHERE_SCALE = 20 * DroneSize/2;
	public static double SPHERE_SPAWN_FREQ = 0.1f;

}

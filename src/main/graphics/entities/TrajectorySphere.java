package main.graphics.entities;

import java.util.Random;

import org.lwjgl.util.vector.Vector3f;

import main.graphics.models.TexturedModel;
import main.graphics.path.CSVConverter;
import main.parameters.EntityManager;
import main.parameters.TrajectoryManager;

public class TrajectorySphere extends Entity {
	
	public static boolean EASTER_EGG = true;
	private Vector3f easterEggRotation = new Vector3f(randomEgg(), randomEgg(), randomEgg());

	public static TrajectorySphere createSphere(int index) {
		if (EASTER_EGG) {
			return new TrajectorySphere(index, EntityManager.eggTexturedModel, TrajectoryManager.DroneSize/2);
		} else {
			return new TrajectorySphere(index, EntityManager.sphereTexturedModel, TrajectoryManager.SPHERE_SCALE);
		}
	}
	
	public TrajectorySphere(int index, TexturedModel model, float scale) {
		super(model, CSVConverter.getPosition(index), 0, 0, 0, scale);
	}
	
	public static float randomEgg() {
	    Random random = new Random();
	    boolean sign = random.nextBoolean();
	    float speed = random.nextFloat() * 10;
	    if (sign) return (random.nextFloat() * speed);
	    else return (-random.nextFloat() * speed);
	}	
	
	public Vector3f getEasterEggRotation() {
		return easterEggRotation;
	}

}

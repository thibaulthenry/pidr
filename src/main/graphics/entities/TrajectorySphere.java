package main.graphics.entities;

import java.util.Random;

import org.lwjgl.util.vector.Vector3f;

import main.graphics.path.CSVConverter;
import main.parameters.EntityManager;
import main.parameters.TrajectoryManager;

public class TrajectorySphere extends Entity {
	
	public static boolean EASTER_EGG = true;
	private Vector3f easterEggRotation = new Vector3f(randomEgg(), randomEgg(), randomEgg());

	public TrajectorySphere(int index) {
		super(EntityManager.sphereTexturedModel, CSVConverter.getPosition(index), 0, 0, 0, TrajectoryManager.SPHERE_SCALE);
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

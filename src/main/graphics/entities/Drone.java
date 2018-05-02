package main.graphics.entities;

import org.lwjgl.util.vector.Vector3f;

import main.graphics.models.TexturedModel;
import main.graphics.path.CSVConverter;

public class Drone extends Entity {

	public Drone(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
	}
	
	public void followSimulation(int index) {
		this.setPosition(CSVConverter.getPosition(index));
		this.setRotation(CSVConverter.getRotation(index));
	} 

}

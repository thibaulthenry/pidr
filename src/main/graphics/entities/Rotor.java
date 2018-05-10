package main.graphics.entities;

import org.lwjgl.util.vector.Vector3f;

import main.graphics.models.TexturedModel;
import main.graphics.path.CSVConverter;

public class Rotor extends Entity {

	public Rotor(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
	}
	
	public void followSimulation(int index) {
		this.setPosition(CSVConverter.getPositionRotor(index,1));
		this.setRotation(CSVConverter.getRotationRotor(index));
	} 

}

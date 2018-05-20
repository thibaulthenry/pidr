package main.graphics.entities;

import org.lwjgl.util.vector.Vector3f;

import main.graphics.models.TexturedModel;

public class Rotor extends Entity {
	
	public Rotor(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale, int id) {
		super(model, position, rotX, rotY, rotZ, scale);
		this.setId(id);
	}
	
}

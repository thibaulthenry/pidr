package main.graphics.entities;

import org.lwjgl.util.vector.Vector3f;

import main.graphics.models.TexturedModel;

public class Boule extends Entity {

	public Boule(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
	}

}

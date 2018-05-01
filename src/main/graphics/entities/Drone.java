package main.graphics.entities;

import org.lwjgl.util.vector.Vector3f;

import main.graphics.models.TexturedModel;

public class Drone extends Entity {

	public Drone(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
	}
	
	public void sim(String[][] A, int i) {
		this.setPosition(new Vector3f(Float.parseFloat(A[1][i])*1000+4000, Float.parseFloat(A[3][i])*1000, Float.parseFloat(A[2][i])*1000+4000));
		this.setRotX(Float.parseFloat(A[4][i])*90);
		this.setRotY(Float.parseFloat(A[6][i])*90);
		this.setRotZ(Float.parseFloat(A[5][i])*90);	
	} 

}

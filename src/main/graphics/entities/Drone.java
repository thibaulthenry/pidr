package main.graphics.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import main.graphics.models.TexturedModel;
import main.graphics.renderer.DisplayManager;
import main.graphics.terrains.Terrain;

public class Drone extends Entity {
	
	private static final float MOUVEMENT_SPEED = 200;
	private static final float ROTATE_SPEED = 160;
	private static final float FLY_SPEED = 30;
	
	private static final float GRAVITY = 50;
	
	private float currentSpeed = 0;
	private float currentTurnSpeed = 0;
	private float upwardsSpeed = 0;
	
	private int square=0;
	private int seg = 0;

	public Drone(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
	}
	
	public void square() {
		switch (square) {
		case 0:
			if (seg < 100) {
				seg+=1;
				this.getPosition().x -= 10f;
			} else {
				square = 1;
				seg = 0;
			}
			break;
		case 1:
			if (seg < 100) {
				seg+=1;
				this.getPosition().z -= 10f;
			} else {
				square = 2;
				seg = 0;
			}
			break;
		case 2:
			if (seg < 100) {
				seg+=1;
				this.getPosition().x += 10f;
			} else {
				square = 3;
				seg = 0;
			}
			break;
		case 3:
			if (seg < 100) {
				seg+=1;
				this.getPosition().z += 10f;
			} else {
				square = 0;
				seg = 0;
			}
			break;
		}
			
	}
	
	public void sim(String[][] A) {

		for(int a=3;a<10000;a++) {
			
			System.out.println(A[1][a]);
		this.getPosition().x = Float.parseFloat(A[1][a])*100f+4000f;
		this.getPosition().y = Float.parseFloat(A[2][a])*100f;
		this.getPosition().z = Float.parseFloat(A[3][a])*100f+4000f;
		}
			
	}
	
	
	
	public void inputs(Terrain terrain) {
		checkInputs();
		super.rotate(0, currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
		float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
		float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
		super.move(dx, 0, dz);
		upwardsSpeed -= GRAVITY * DisplayManager.getFrameTimeSeconds();
		super.move(0, upwardsSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		float terrainHeight = terrain.getTerrainHeight(super.getPosition().x, super.getPosition().z);
		if (super.getPosition().y < terrainHeight + 2 * super.getScale()) {
			upwardsSpeed = 0;
			super.getPosition().y = terrainHeight + 2 * super.getScale();
		}
	}
	
	public void jump() {
		this.upwardsSpeed = FLY_SPEED;
	}
	
	private void checkInputs() {
		if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
			this.currentSpeed = MOUVEMENT_SPEED;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.currentSpeed = -MOUVEMENT_SPEED;
		} else {
			this.currentSpeed = 0;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			this.currentTurnSpeed = ROTATE_SPEED;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.currentTurnSpeed = -ROTATE_SPEED;
		} else {
			this.currentTurnSpeed = 0;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			jump();
		}
	}

}

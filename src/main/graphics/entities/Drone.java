package main.graphics.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import main.graphics.models.TexturedModel;
import main.graphics.renderer.DisplayManager;

public class Drone extends Entity {
	
	private static final float MOUVEMENT_SPEED = 200;
	private static final float ROTATE_SPEED = 160;
	private static final float FLY_SPEED = 30;
	
	private static final float GRAVITY = 50;
	
	private float currentSpeed = 0;
	private float currentTurnSpeed = 0;
	private float upwardsSpeed = 0;

	public Drone(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
	}
	
	public void move() {
		checkInputs();
		super.rotate(0, currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
		float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
		float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
		super.move(dx, 0, dz);
		upwardsSpeed -= GRAVITY * DisplayManager.getFrameTimeSeconds();
		super.move(0, upwardsSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		if (super.getPosition().y < 0) {
			super.getPosition().y = 0;
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

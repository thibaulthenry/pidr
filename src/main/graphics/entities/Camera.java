package main.graphics.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private static final float CAMERA_SPEED = 400;
	private static final float CAMERA_TURN_SPEED = 200;
	private static final float CAMERA_UP_SPEED = 50;

	private float currentCameraSpeed = 0;
	private float currentCameraTurnSpeed = 0;
	private float currentUpwardSpeed = 0;
	private float distanceFromCameraPointer = 50;
	private float angleAroundCameraPointer = 0;
	private float horizontalDistance;
	private float verticalDistance;
	private float camZoomSensitivity = 0.2f;
	private float camTurnSensitivity = 0.5f;
	private float camSlideSensitivity = 0.5f;
	private float camPitchSensitivity = 0.5f;
	
	private Vector3f position;
	private float pitch;
	private float yaw = 180;
	private float roll;
	
	private Entity cameraPointer;

	public Camera(Vector3f position) {
		this.position = position;
		//this.cameraPointer = cameraPointer;
	}

	public void move() {
		//calculateZoom();
		if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
			position.z+=5f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.z-=5f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			position.x+=5f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.x-=5f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			position.y+=5f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			position.y-=5f;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
			pitch-=2f;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_Y)) {
			yaw-=2f;
		}
		/*calculatePitch();
		calculateAngleAroundCameraPointer();
		//calculateCameraSlide();
		horizontalDistance = calculateHorizontalDistance();
		verticalDistance = calculateVerticalDistance();
		calculateCameraPosition(horizontalDistance, verticalDistance);
		this.yaw = 180 - (cameraPointer.getRotY() + angleAroundCameraPointer);*/
	}

	/*public void movePointer(Terrain terrain) {
		checkInputs();
		float distance = currentCameraSpeed * DisplayManager.getFrameTimeSeconds();
		float dx = (float) (distance * Math.sin(Math.toRadians(angleAroundCameraPointer)));
		float dz = (float) (distance * Math.cos(Math.toRadians(angleAroundCameraPointer)));
		if (cameraPointer.getPosition().y > 300) {
			cameraPointer.increasePosition(dx, 0, dz);
		} else {
			cameraPointer.increasePosition(dx, currentUpwardSpeed * DisplayManager.getFrameTimeSeconds(), dz);
		}

		float terrainHeight = terrain.getHeightOfTerrain(cameraPointer.getPosition().x, cameraPointer.getPosition().z);
		if (cameraPointer.getPosition().y < terrainHeight) {
			currentUpwardSpeed = 0;
			cameraPointer.getPosition().y = terrainHeight;
		}
	}*/

	private void calculateCameraPosition(float horizDistance, float verticDistance) {
		float theta = cameraPointer.getRotY() + angleAroundCameraPointer;
		float offsetX = (float) (horizDistance * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float) (horizDistance * Math.cos(Math.toRadians(theta)));
		position.x = cameraPointer.getPosition().x - offsetX;
		position.y = cameraPointer.getPosition().y + verticDistance;
		position.z = cameraPointer.getPosition().z - offsetZ;
	}

	private float calculateHorizontalDistance() {
		return (float) (distanceFromCameraPointer * Math.cos(Math.toRadians(pitch)));
	}

	private float calculateVerticalDistance() {
		return (float) (distanceFromCameraPointer * Math.sin(Math.toRadians(pitch)));
	}

	/*private void calculateZoom() {
		if (!MousePicker.isGrabbingEntity()) {
			float zoomLevel = Mouse.getDWheel() * camZoomSensitivity;
			distanceFromCameraPointer -= zoomLevel;
			if (distanceFromCameraPointer < 5) {
				distanceFromCameraPointer = 5;
			}
			if (distanceFromCameraPointer > 600) {
				distanceFromCameraPointer = 600;
			}
		}
	}*/

	private void calculatePitch() {
		if (Mouse.isButtonDown(1)) {
			float pitchChange = Mouse.getDY() * camPitchSensitivity;
			pitch -= pitchChange;
		}
		if (pitch > 90) {
			pitch = 90;
		}
		if (pitch < 5) {
			pitch = 5;
		}
	}
	
	public void invertPitch() {
		pitch = -pitch;
	}

	private void calculateAngleAroundCameraPointer() {
		if (Mouse.isButtonDown(0)) {
			float angleChange = Mouse.getDX() * camTurnSensitivity;
			angleAroundCameraPointer -= angleChange;

			float slideYChange = Mouse.getDY() * camSlideSensitivity;
			pitch -= slideYChange;
			if (pitch > 90) {
				pitch = 90;
			}
			if (pitch < 5) {
				pitch = 5;
			}
		}

	}

	/*private void calculateCameraSlide() {
		if (Mouse.isButtonDown(2)) {
			float slideChange = Mouse.getDX() * camSlideSensitivity;
			float distance = slideChange * distanceFromCameraPointer * DisplayManager.getFrameTimeSeconds();
			float dx = (float) (distance * Math.sin(Math.toRadians(angleAroundCameraPointer + 90)));
			float dz = (float) (distance * Math.cos(Math.toRadians(angleAroundCameraPointer + 90)));
			cameraPointer.getPosition().x += dx;
			cameraPointer.getPosition().z += dz;

		}
	}*/

	private void checkInputs() {/*
		if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
			currentCameraSpeed = CAMERA_SPEED;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			currentCameraSpeed = -CAMERA_SPEED;
		} else {
			currentCameraSpeed = 0;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			currentCameraTurnSpeed = -CAMERA_TURN_SPEED;
			angleAroundCameraPointer += currentCameraTurnSpeed * DisplayManager.getFrameTimeSeconds();
		} else if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			currentCameraTurnSpeed = CAMERA_TURN_SPEED;
			angleAroundCameraPointer += currentCameraTurnSpeed * DisplayManager.getFrameTimeSeconds();
		} else {
			currentCameraTurnSpeed = 0;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			currentUpwardSpeed = CAMERA_UP_SPEED;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			currentUpwardSpeed = -CAMERA_UP_SPEED;
		} else {
			currentUpwardSpeed = 0;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {

			camZoomSensitivity += 0.1f;
			camTurnSensitivity += 0.1f;
			camSlideSensitivity += 0.1f;
			camPitchSensitivity += 0.1f;
			if (camSlideSensitivity > 1) {
				camZoomSensitivity = 0.1f;
				camTurnSensitivity = 0.1f;
				camSlideSensitivity = 0.1f;
				camPitchSensitivity = 0.1f;

			}
		}
*/
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
}

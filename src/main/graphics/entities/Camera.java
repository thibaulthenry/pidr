package main.graphics.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private static final float CAMERA_SPEED = 400;
	private static final float CAMERA_TURN_SPEED = 200;
	private static final float CAMERA_UP_SPEED = 50;
	private static final float CAMERA_ZOOM_SENSITIVITY = 0.2f;
	private static final float CAMERA_TURN_SENSITIVITY = 0.5f;
	private static final float CAMERA_SLIDE_SENSITIVITY = 0.5f;
	private static final float CAMERA_PITCH_SENSITIVITY = 0.5f;
	
	private float distanceFromDrone = 50;
	private float angleAroundDrone = 0;
	private float horizontalDistance;
	private float verticalDistance;

	private float currentCameraSpeed = 0;
	private float currentCameraTurnSpeed = 0;
	private float currentUpwardSpeed = 0;
	
	private Vector3f position = new Vector3f(0,0,0);
	private float pitch = 20;
	private float yaw = 0;
	private float roll;
	
	private Drone drone;

	public Camera(Drone drone) {
		this.drone = drone;
	}
	
	public void move() {
		calculateZoom();
		calculatePitch();
		calculateAngleAroundDrone();
		float horizontalDistance = calculateHorizontalDistance();
		float verticalDistance = calculateVerticalDistance();
		calculateCameraPosition(horizontalDistance, verticalDistance);
		this.yaw = 180 - (drone.getRotY() + angleAroundDrone);
	}

	/*private void calculateCameraPosition(float horizDistance, float verticDistance) {
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
	}*/

	/*
	}*/



	/*private void calculateAngleAroundCameraPointer() {
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

	}*/

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

	/*private void checkInputs() {
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
	
	
	private void calculateZoom() {
		float zoomLevel = Mouse.getDWheel() * CAMERA_ZOOM_SENSITIVITY;
		distanceFromDrone -= zoomLevel;
		if (distanceFromDrone < 0.2f) {
			distanceFromDrone = 0.2f;
		}
		if (distanceFromDrone > 600) {
			distanceFromDrone = 600;
		}
	}
	
	private void calculatePitch() {
		if (Mouse.isButtonDown(1)) {
			float pitchChange = Mouse.getDY() * CAMERA_PITCH_SENSITIVITY;
			pitch -= pitchChange;
		}
	}
	
	private void calculateAngleAroundDrone() {
		if (Mouse.isButtonDown(0)) {
			float angleChange = Mouse.getDX() * CAMERA_TURN_SENSITIVITY;
			angleAroundDrone -= angleChange;
		}	
	}
	
	private void calculateCameraPosition(float horizDistance, float verticDistance) {
		float theta = drone.getRotY() + angleAroundDrone;
		float offsetX = (float) (horizDistance * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float) (horizDistance * Math.cos(Math.toRadians(theta)));
		position.x = drone.getPosition().x - offsetX;
		position.y = drone.getPosition().y + verticDistance;
		position.z = drone.getPosition().z - offsetZ;
	}
	
	private float calculateHorizontalDistance() {
		return (float) (distanceFromDrone * Math.cos(Math.toRadians(pitch)));
	}

	private float calculateVerticalDistance() {
		return (float) (distanceFromDrone * Math.sin(Math.toRadians(pitch)));
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

package main.graphics.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import main.graphics.renderer.DisplayRenderer;
import main.parameters.CameraManager;
import main.parameters.EntityManager;
import main.parameters.TerrainManager;

public class Camera {
	
	private float distanceFromDrone = 100;
	private float angleAroundDrone = 0;
	
	private float currentCameraSpeed = 0;
	private float currentUpwardSpeed = 0;
	private float currentCameraTurnSpeed = 0;
	
	private Vector3f position = new Vector3f(0,0,0);
	private float pitch = 5;
	private float yaw = 0;
	private float roll;
	
	private Entity pointer;
	
	public Camera(Entity pointer) {
		this.pointer = pointer;
	}

	public Camera() {
		this.pointer = new Entity(EntityManager.freeCameraTexturedModel, TerrainManager.terrainCenter, 0, 0, 0, 1);
	}

	public void move() {
		calculateZoom();
		calculateAngleAroundDrone();
		float horizontalDistance = calculateHorizontalDistance();
		float verticalDistance = calculateVerticalDistance();
		calculateCameraPosition(horizontalDistance, verticalDistance);
		this.yaw = 180 - (pointer.getRotY() + angleAroundDrone);
	}	
	
	private void calculateZoom() {
		float zoomLevel = Mouse.getDWheel() * CameraManager.CAMERA_ZOOM_SENSITIVITY;
		distanceFromDrone -= zoomLevel;
		if (distanceFromDrone < 1) {
			distanceFromDrone = 1;
		}
		if (distanceFromDrone > 1000) {
			distanceFromDrone = 1000;
		}
	}
	
	private void calculateAngleAroundDrone() {
		if (Mouse.isButtonDown(0)) {
			float angleChange = Mouse.getDX() * CameraManager.CAMERA_HORIZONTAL_SENSITIVITY;
			angleAroundDrone -= angleChange;
			
			float slideYChange = Mouse.getDY() * CameraManager.CAMERA_VERTICAL_SENSITIVITY;
			pitch -= slideYChange;
			
			if (pitch > 90) {
				pitch = 90;
			}
			if (pitch < 5) {
				pitch = 5;
			}
		}	
	}
	
	private void calculateCameraPosition(float horizDistance, float verticDistance) {
		float theta = pointer.getRotY() + angleAroundDrone;
		float offsetX = (float) (horizDistance * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float) (horizDistance * Math.cos(Math.toRadians(theta)));
		position.x = pointer.getPosition().x - offsetX;
		position.y = pointer.getPosition().y + verticDistance;
		position.z = pointer.getPosition().z - offsetZ;
	}
	
	private float calculateHorizontalDistance() {
		return (float) (distanceFromDrone * Math.cos(Math.toRadians(pitch)));
	}

	private float calculateVerticalDistance() {
		return (float) (distanceFromDrone * Math.sin(Math.toRadians(pitch)));
	}
	
	public void movePointer() {
		checkInputs();
		float distance = currentCameraSpeed * DisplayRenderer.getFrameTimeSeconds();
		float dx = (float) (distance * Math.sin(Math.toRadians(angleAroundDrone)));
		float dz = (float) (distance * Math.cos(Math.toRadians(angleAroundDrone)));
		pointer.move(dx, currentUpwardSpeed * DisplayRenderer.getFrameTimeSeconds(), dz);

		if (pointer.getPosition().y < 0) {
			currentUpwardSpeed = 0;
			pointer.getPosition().y = 0;
		}
	}
	
	private void checkInputs() {
		if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
			currentCameraSpeed = CameraManager.CAMERA_SPEED;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			currentCameraSpeed = -CameraManager.CAMERA_SPEED;
		} else {
			currentCameraSpeed = 0;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			currentCameraTurnSpeed = -CameraManager.CAMERA_TURN_SPEED;
			angleAroundDrone += currentCameraTurnSpeed * DisplayRenderer.getFrameTimeSeconds();
		} else if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			currentCameraTurnSpeed = CameraManager.CAMERA_TURN_SPEED;
			angleAroundDrone += currentCameraTurnSpeed * DisplayRenderer.getFrameTimeSeconds();
		} else {
			currentCameraTurnSpeed = 0;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			currentUpwardSpeed = CameraManager.CAMERA_UP_SPEED;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			currentUpwardSpeed = -CameraManager.CAMERA_UP_SPEED;
		} else {
			currentUpwardSpeed = 0;
		}
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

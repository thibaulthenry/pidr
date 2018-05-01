package main.graphics.entities;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import main.parameters.CameraManager;

public class Camera {
	
	private float distanceFromDrone = 50;
	private float angleAroundDrone = 0;
	
	private Vector3f position = new Vector3f(0,0,0);
	private float pitch = 5;
	private float yaw = 0;
	private float roll;
	
	private Drone drone;

	public Camera(Drone drone) {
		this.drone = drone;
	}
	
	public void move() {
		calculateZoom();
		calculateAngleAroundDrone();
		float horizontalDistance = calculateHorizontalDistance();
		float verticalDistance = calculateVerticalDistance();
		calculateCameraPosition(horizontalDistance, verticalDistance);
		this.yaw = 180 - (drone.getRotY() + angleAroundDrone);
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

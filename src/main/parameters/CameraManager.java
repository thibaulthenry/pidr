package main.parameters;

import java.util.List;

import main.graphics.entities.Camera;
import main.graphics.entities.Entity;

public class CameraManager {
	
	private static Camera fixedCamera;
	private static Camera freeCamera = new Camera();
	
	public static boolean IS_FREE_CAMERA = false;
	
	public static final float CAMERA_ZOOM_SENSITIVITY = 0.2f;
	public static final float CAMERA_HORIZONTAL_SENSITIVITY = 0.2f;
	public static final float CAMERA_VERTICAL_SENSITIVITY = 0.2f;
	public static final float CAMERA_SPEED = 400;
	public static final float CAMERA_TURN_SPEED = 150;
	public static final float CAMERA_UP_SPEED = 150;
	
	public static Camera getCamera(List<Entity> entities) {
		if (IS_FREE_CAMERA) {
			return freeCamera;
		} else {
			if (fixedCamera == null) fixedCamera = new Camera(Entity.getEntityById(entities, EntityManager.droneID));
			return fixedCamera;
		}
	}
	
	public static Camera getCamera() {
		return freeCamera;
	}

}

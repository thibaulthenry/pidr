package main.graphics.entities;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import main.graphics.models.TexturedModel;

public class Entity {
	
	private TexturedModel model;
	private Vector3f position;
	private float rotX, rotY, rotZ;
	private float scale;
	
	public Entity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super();
		this.model = model;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
	}
	
	public void clearAllWithoutDrone(List<Entity> entities) {
		List<Entity> copy = new ArrayList<Entity>(entities);
		for (Entity entity : copy) if (!(entity instanceof Drone) && !( entity instanceof Rotor)) entities.remove(entity);
	}
	
	public void move(float dx, float dy, float dz) {
		this.position.x+=dx;
		this.position.y+=dy;
		this.position.z+=dz;
	}
	
	public void rotate(float dx, float dy, float dz) {
		this.rotX+=dx;
		this.rotY+=dy;
		this.rotZ+=dz;
	}
	
	public TexturedModel getModel() {
		return model;
	}
	
	public void setModel(TexturedModel model) {
		this.model = model;
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public void setRotation(Vector3f rotation) {
		this.rotX = rotation.x;
		this.rotY = rotation.y;
		this.rotZ = rotation.z;
	}
	
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	public float getRotX() {
		return rotX;
	}
	
	public void setRotX(float rotX) {
		this.rotX = rotX;
	}
	
	public float getRotY() {
		return rotY;
	}
	
	public void setRotY(float rotY) {
		this.rotY = rotY;
	}
	
	public float getRotZ() {
		return rotZ;
	}
	
	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}
	
	public float getScale() {
		return scale;
	}
	
	public void setScale(float scale) {
		this.scale = scale;
	}

}

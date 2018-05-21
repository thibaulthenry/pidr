package main.parameters;

import java.util.List;

import main.graphics.entities.Drone;
import main.graphics.entities.Entity;
import main.graphics.entities.Rotor;
import main.graphics.models.RawModel;
import main.graphics.models.TexturedModel;
import main.graphics.objConverter.ModelData;
import main.graphics.objConverter.OBJFileLoader;
import main.graphics.renderer.Loader;
import main.graphics.textures.ModelTexture;

public class EntityManager {
	
	private final static Loader loader = new Loader();
	
	public static void createDrone(List<Entity> entities) {
		entities.add(new Drone(droneTexturedModel, TerrainManager.terrainCenter, 0, 0, 0, TrajectoryManager.DroneSize, droneID));
		entities.add(new Rotor(rotorTexturedModel, TerrainManager.terrainCenter, 0, 0, 0, TrajectoryManager.DroneSize, rotor1ID));
		entities.add(new Rotor(rotorTexturedModel, TerrainManager.terrainCenter, 0, 0, 0, TrajectoryManager.DroneSize, rotor2ID));
		entities.add(new Rotor(rotorTexturedModel, TerrainManager.terrainCenter, 0, 0, 0, TrajectoryManager.DroneSize, rotor3ID));
		entities.add(new Rotor(rotorTexturedModel, TerrainManager.terrainCenter, 0, 0, 0, TrajectoryManager.DroneSize, rotor4ID));
	}
	
	/* Drone */

	public final static int droneID = 0;
	
	private final static ModelData droneModelData = OBJFileLoader.loadOBJ("baseDroneModel");
	private final static RawModel droneRawModel = loader.loadToVAO(droneModelData.getVertices(), droneModelData.getTextureCoords(),droneModelData.getNormals(),  droneModelData.getIndices());
	public final static TexturedModel droneTexturedModel = new TexturedModel(droneRawModel, new ModelTexture(TextureManager.droneTexture));
	
	/* Rotor */
	
	public final static int rotor1ID = 1;
	public final static int rotor2ID = 2;
	public final static int rotor3ID = 3;
	public final static int rotor4ID = 4;
	
	private final static ModelData rotorModelData = OBJFileLoader.loadOBJ("rotorsModel");
	private final static RawModel rotorRawModel = loader.loadToVAO(rotorModelData.getVertices(), rotorModelData.getTextureCoords(),rotorModelData.getNormals(),  rotorModelData.getIndices());
	public final static TexturedModel rotorTexturedModel = new TexturedModel(rotorRawModel, new ModelTexture(TextureManager.rotorTexture));

	/* Trajectory Sphere */
	
	private final static ModelData sphereModelData = OBJFileLoader.loadOBJ("sphereModel");
	private final static RawModel sphereRawModel = loader.loadToVAO(sphereModelData.getVertices(),sphereModelData.getTextureCoords(),sphereModelData.getNormals(),  sphereModelData.getIndices());
	public final static TexturedModel sphereTexturedModel = new TexturedModel(sphereRawModel, new ModelTexture(TextureManager.sphereTexture));

	/* Free Camera */
	
	private final static ModelData freeCameraModelData = OBJFileLoader.loadOBJ("freeCamera");
	private final static RawModel freeCameraRawModel = loader.loadToVAO(freeCameraModelData.getVertices(),freeCameraModelData.getTextureCoords(),freeCameraModelData.getNormals(),  freeCameraModelData.getIndices());
	public final static TexturedModel freeCameraTexturedModel = new TexturedModel(freeCameraRawModel, new ModelTexture(TextureManager.voidTexture));
	
	/* Easter Egg */
	
	private final static ModelData eggModelData = OBJFileLoader.loadOBJ("angry");
	private final static RawModel eggRawModel = loader.loadToVAO(eggModelData.getVertices(),eggModelData.getTextureCoords(),eggModelData.getNormals(),  eggModelData.getIndices());
	public final static TexturedModel eggTexturedModel = new TexturedModel(eggRawModel, new ModelTexture(TextureManager.sphereTexture));
	
}

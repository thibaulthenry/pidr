package main.parameters;

import main.graphics.models.RawModel;
import main.graphics.models.TexturedModel;
import main.graphics.objConverter.ModelData;
import main.graphics.objConverter.OBJFileLoader;
import main.graphics.renderer.Loader;
import main.graphics.textures.ModelTexture;

public class EntityManager {
	
	private final static Loader loader = new Loader();
	
	/* Drone */

	private final static ModelData droneModelData = OBJFileLoader.loadOBJ("droneModel");
	private final static RawModel droneRawModel = loader.loadToVAO(droneModelData.getVertices(), droneModelData.getTextureCoords(),droneModelData.getNormals(),  droneModelData.getIndices());
	public final static TexturedModel droneTexturedModel = new TexturedModel(droneRawModel, new ModelTexture(GuiManager.droneTexture));

	/* Trajectory Sphere */
	
	private final static ModelData sphereModelData = OBJFileLoader.loadOBJ("sphereModel");
	private final static RawModel sphereRawModel = loader.loadToVAO(sphereModelData.getVertices(),sphereModelData.getTextureCoords(),sphereModelData.getNormals(),  sphereModelData.getIndices());
	public final static TexturedModel sphereTexturedModel = new TexturedModel(sphereRawModel, new ModelTexture(GuiManager.sphereTexture));

}

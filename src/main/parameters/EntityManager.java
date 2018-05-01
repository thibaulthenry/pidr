package main.parameters;

import main.graphics.models.RawModel;
import main.graphics.models.TexturedModel;
import main.graphics.objConverter.ModelData;
import main.graphics.objConverter.OBJFileLoader;
import main.graphics.renderer.Loader;
import main.graphics.textures.ModelTexture;

public class EntityManager {
	
	private static Loader loader = new Loader();

	ModelData Modeldrone = OBJFileLoader.loadOBJ("droneModel");
	RawModel RawModeldrone = loader.loadToVAO(Modeldrone.getVertices(), Modeldrone.getTextureCoords(),Modeldrone.getNormals(),  Modeldrone.getIndices());
	TexturedModel TexModeldrone = new TexturedModel(RawModeldrone, new ModelTexture(loader.loadTexture("white")));

	ModelData Modelboule = OBJFileLoader.loadOBJ("petiteboule");
	RawModel RawModelboule = loader.loadToVAO(Modelboule.getVertices(),
			Modelboule.getTextureCoords(),Modelboule.getNormals(),  Modelboule.getIndices());
	TexturedModel TexModelboule = new TexturedModel(RawModelboule,
			new ModelTexture(loader.loadTexture("white")));

}

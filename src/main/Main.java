package main;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import main.graphics.entities.Camera;
import main.graphics.entities.Drone;
import main.graphics.entities.Entity;
import main.graphics.entities.Light;
import main.graphics.models.RawModel;
import main.graphics.models.TexturedModel;
import main.graphics.objConverter.ModelData;
import main.graphics.objConverter.OBJFileLoader;
import main.graphics.renderer.DisplayManager;
import main.graphics.renderer.Loader;
import main.graphics.renderer.MasterRenderer;
import main.graphics.renderer.EntityRenderer;
import main.graphics.shaders.StaticShader;
import main.graphics.terrains.Terrain;
import main.graphics.textures.ModelTexture;
import main.graphics.textures.TerrainTexture;
import main.graphics.textures.TerrainTexturePack;

public class Main {

	public static void main(String[] args) {


		DisplayManager.createDisplay();
		MasterRenderer renderer = new MasterRenderer();
		Loader loader = new Loader();
		  
		ModelData cameraPointerModel = OBJFileLoader.loadOBJ("dragon");
		RawModel cameraPointerRawModel = loader.loadToVAO(cameraPointerModel.getVertices(),
					cameraPointerModel.getTextureCoords(),cameraPointerModel.getNormals(),  cameraPointerModel.getIndices());
		TexturedModel cameraPointerTexModel = new TexturedModel(cameraPointerRawModel,
					new ModelTexture(loader.loadTexture("white")));

		  Entity entity = new Entity(cameraPointerTexModel, new Vector3f(400,1,400),0,0,0,1);
		  Light light = new Light(new Vector3f(4000,10000,4000), new Vector3f(1,1,1));
		  
			ModelData Model = OBJFileLoader.loadOBJ("bunny");
			RawModel RawModel = loader.loadToVAO(Model.getVertices(),
					Model.getTextureCoords(),Model.getNormals(),  Model.getIndices());
			TexturedModel TexModel = new TexturedModel(RawModel,
						new ModelTexture(loader.loadTexture("white")));
		  
		  Drone drone = new Drone(TexModel, new Vector3f(4000,0,4000),0,0,0,1);
		  Camera camera = new Camera(drone);
		  
		  
		  //terrain
		  TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("1"));
		  TerrainTexture r = new TerrainTexture(loader.loadTexture("2"));
		  TerrainTexture g = new TerrainTexture(loader.loadTexture("3"));
		  TerrainTexture b = new TerrainTexture(loader.loadTexture("4"));
		  TerrainTexturePack tP= new TerrainTexturePack(backgroundTexture, r, g, b);
		  TerrainTexture blend = new TerrainTexture(loader.loadTexture("blendmap"));
		  //
		  
		  
		  Terrain terrain = new Terrain(0,0,loader, tP, blend);
		  
		  
		while(!Display.isCloseRequested()) {
			camera.move();
			drone.move();
			renderer.processEntity(drone);
			
			renderer.processTerrain(terrain);
			renderer.processEntity(entity);
			
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}
		
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}

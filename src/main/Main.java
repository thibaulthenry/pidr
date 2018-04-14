package main;

import java.util.ArrayList;

import java.util.List;

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
import main.graphics.terrains.Terrain;
import main.graphics.textures.ModelTexture;
import main.graphics.textures.TerrainTexture;
import main.graphics.textures.TerrainTexturePack;
import main.csvConv;

public class Main {

	public static void main(String[] args) {

		String[][] A= csvConv.csvConverter("/Users/matta/Documents/MATLAB/PIDRstat.csv",3);
		
		DisplayManager.createDisplay();
		MasterRenderer renderer = new MasterRenderer();
		Loader loader = new Loader();
		  
		ModelData cameraPointerModel = OBJFileLoader.loadOBJ("dragon");
		RawModel cameraPointerRawModel = loader.loadToVAO(cameraPointerModel.getVertices(),
					cameraPointerModel.getTextureCoords(),cameraPointerModel.getNormals(),  cameraPointerModel.getIndices());
		TexturedModel cameraPointerTexModel = new TexturedModel(cameraPointerRawModel,
					new ModelTexture(loader.loadTexture("white")));

		  Entity entity = new Entity(cameraPointerTexModel, new Vector3f(400,1,400),0,0,0,1f);
		  Light light0 = new Light(new Vector3f(4000,1500,4000), new Vector3f(1,1,1));
		  Light light1 = new Light(new Vector3f(0,1500,0), new Vector3f(1,1,1));
		  Light light2 = new Light(new Vector3f(0,1500,4000), new Vector3f(1,1,1));
		  Light light3 = new Light(new Vector3f(4000,1500,0), new Vector3f(1,1,1));
		  Light light4 = new Light(new Vector3f(4000,1500,4000), new Vector3f(1,1,1));
		  List<Light> lights = new ArrayList<Light>();
		  lights.add(light0);
		  lights.add(light1);
		  lights.add(light2);
		  lights.add(light3);
		  lights.add(light4);
		  
		  
			ModelData Model = OBJFileLoader.loadOBJ("testobj");
			RawModel RawModel = loader.loadToVAO(Model.getVertices(),
					Model.getTextureCoords(),Model.getNormals(),  Model.getIndices());
			TexturedModel TexModel = new TexturedModel(RawModel,
						new ModelTexture(loader.loadTexture("white")));
		  
		  Drone drone = new Drone(TexModel, new Vector3f(4000,0,4000),0,0,0,1f);
		  Camera camera = new Camera(drone);
		  
		  
		  //terrain
		  TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("3"));
		  TerrainTexture r = new TerrainTexture(loader.loadTexture("2"));
		  TerrainTexture g = new TerrainTexture(loader.loadTexture("3"));
		  TerrainTexture b = new TerrainTexture(loader.loadTexture("4"));
		  TerrainTexturePack tP= new TerrainTexturePack(backgroundTexture, r, g, b);
		  TerrainTexture blend = new TerrainTexture(loader.loadTexture("blendmap"));
		  //
		  
		  
		  Terrain terrain = new Terrain(0,0,loader, tP, blend, "heightmap");
		  int i = 0;
		  
		while(!Display.isCloseRequested()) {
			
			camera.move();
			drone.inputs(terrain);
			drone.move(0f,0f,0);
			//drone.rotate(0, 0.1f, 0);
			
			
			drone.square();
			
			renderer.processEntity(drone);
			
			renderer.processTerrain(terrain);
			renderer.processEntity(entity);
			
			renderer.render(light0, camera);
			
			DisplayManager.updateDisplay();
		}
		
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}

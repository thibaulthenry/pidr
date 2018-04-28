package main;

import java.util.ArrayList;

import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import main.graphics.entities.Camera;
import main.graphics.entities.Drone;
import main.graphics.entities.Light;
import main.graphics.guis.ButtonManager;
import main.graphics.guis.buttons.Button;
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

		String[][] A= csvConv.csvConverter("C:\\Users\\thiba\\Documents\\Programmation\\Eclipse\\3DroneSimulator\\resources\\simul\\PIDRstatrot.csv",10000);

		DisplayManager.createDisplay();
		MasterRenderer renderer = new MasterRenderer();
		Loader loader = new Loader();

		Light light0 = new Light(new Vector3f(4000,2000,4000), new Vector3f(1,1,1));
		List<Light> lights = new ArrayList<Light>();
		lights.add(light0);


		ModelData Model = OBJFileLoader.loadOBJ("droneModel");
		RawModel RawModel = loader.loadToVAO(Model.getVertices(),
				Model.getTextureCoords(),Model.getNormals(),  Model.getIndices());
		TexturedModel TexModel = new TexturedModel(RawModel,
				new ModelTexture(loader.loadTexture("white")));

		Drone drone = new Drone(TexModel, new Vector3f(4000,0,4000),0,0,0,0.5f);
		Camera camera = new Camera(drone);
		ButtonManager ib = new ButtonManager();

		//terrain
		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("2"));
		TerrainTexture r = new TerrainTexture(loader.loadTexture("3"));
		TerrainTexture g = new TerrainTexture(loader.loadTexture("4"));
		TerrainTexture b = new TerrainTexture(loader.loadTexture("1"));
		TerrainTexturePack tP= new TerrainTexturePack(backgroundTexture, r, g, b);
		TerrainTexture blend = new TerrainTexture(loader.loadTexture("blendmap"));

		boolean bool = true;
		int plus = 1; 
		Terrain terrain = new Terrain(0,0,loader, tP, blend, "heightmap");
		int i = 0;
		while(!Display.isCloseRequested()) {
			if (bool) {
				double disp = DisplayManager.getFrameTimeSeconds();
				if (disp > 0.015 && disp < 0.025) {
					plus = (int) (1000 / (1/ DisplayManager.getFrameTimeSeconds()));

					bool = false;
				}
			}
			drone.sim(A, i);
			camera.move();
			renderer.processEntity(drone);
			renderer.processTerrain(terrain);

			Button.update();
			renderer.render(light0, camera);
			//System.out.println(plus);
			DisplayManager.updateDisplay();
			if (i>=80000-plus-1) {
				i = 0;
			}
			i+=plus;
			//System.out.println(Mouse.getX() + ":" +  Mouse.getY());
		}
		
		
		
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}

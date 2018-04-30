package main;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import main.graphics.entities.Boule;
import main.graphics.entities.Camera;
import main.graphics.entities.Drone;
import main.graphics.entities.Light;
import main.graphics.guis.ButtonManager;
import main.graphics.guis.buttons.Button;
import main.graphics.models.RawModel;
import main.graphics.models.TexturedModel;
import main.graphics.objConverter.ModelData;
import main.graphics.objConverter.OBJFileLoader;
import main.graphics.recorder.FrameBuffers;
import main.graphics.recorder.SequenceEncoder;
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

		String[][] A= csvConv.csvConverter("/Users/matta/Downloads/PIDRstat.csv",10000);

		DisplayManager.createDisplay();
		MasterRenderer renderer = new MasterRenderer();
		Loader loader = new Loader();

		Light light0 = new Light(new Vector3f(4000,2000,4000), new Vector3f(1,1,1));
		List<Light> lights = new ArrayList<Light>();
		lights.add(light0);


		ModelData Modeldrone = OBJFileLoader.loadOBJ("droneModel");
		RawModel RawModeldrone = loader.loadToVAO(Modeldrone.getVertices(),
				Modeldrone.getTextureCoords(),Modeldrone.getNormals(),  Modeldrone.getIndices());
		TexturedModel TexModeldrone = new TexturedModel(RawModeldrone,
				new ModelTexture(loader.loadTexture("white")));

		ModelData Modelboule = OBJFileLoader.loadOBJ("petiteboule");
		RawModel RawModelboule = loader.loadToVAO(Modelboule.getVertices(),
				Modelboule.getTextureCoords(),Modelboule.getNormals(),  Modelboule.getIndices());
		TexturedModel TexModelboule = new TexturedModel(RawModelboule,
				new ModelTexture(loader.loadTexture("white")));
		
		Boule boule= new Boule(TexModelboule, new Vector3f(4000,0,4000),0,0,0,10f);
		
		Drone drone = new Drone(TexModeldrone, new Vector3f(4000,0,4000),0,0,0,40f);
		Camera camera = new Camera(drone);
		ButtonManager ib = new ButtonManager();

		//terrain
		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("2"));
		TerrainTexture r = new TerrainTexture(loader.loadTexture("3"));
		TerrainTexture g = new TerrainTexture(loader.loadTexture("4"));
		TerrainTexture b = new TerrainTexture(loader.loadTexture("1"));
		TerrainTexturePack tP= new TerrainTexturePack(backgroundTexture, r, g, b);
		TerrainTexture blend = new TerrainTexture(loader.loadTexture("blendmap"));

		FrameBuffers fbo = new FrameBuffers();


		boolean bool = true;
		int plus = 0; 
		Terrain terrain = new Terrain(0,0,loader, tP, blend, "heightmap");
		int i = 0;
		int k = 0;
		boolean stop = true;
		while(!Display.isCloseRequested() && stop) {
			if (bool) {
				if (k > 20) {

					plus = (int) (1000 / (1/ DisplayManager.getFrameTimeSeconds()));
					bool = false;
				}
				k++;
			}
			drone.sim(A, i);

			camera.move();


			
			renderer.processEntity(drone);
			for(int j=0;j<i;j=j + plus) {
				renderer.processEntity(new Boule(TexModelboule, new Vector3f(Float.parseFloat(A[1][j])*1000+4000, Float.parseFloat(A[3][j])*1000, Float.parseFloat(A[2][j])*1000+4000),0,0,0,100f));
			}
			
			renderer.processTerrain(terrain);
			renderer.render(light0, camera);

			Button.update();

			//System.out.println(plus);
			DisplayManager.updateDisplay();
			if (i>=80000-plus-1) {
				stop=false;
				i = 0;
			}
			i+=plus;
		}

		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();


	}

}

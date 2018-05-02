package main;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import main.graphics.entities.Boule;
import main.graphics.entities.Camera;
import main.graphics.entities.Drone;
import main.graphics.guis.buttons.Button;
import main.graphics.path.csvConverter;
import main.graphics.renderer.DisplayManager;
import main.graphics.renderer.MasterRenderer;
import main.parameters.EntityManager;
import main.parameters.TerrainManager;
import main.parameters.TrajectoryManager;

public class Main {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		MasterRenderer renderer = new MasterRenderer();

		Drone drone = new Drone(EntityManager.droneTexturedModel, new Vector3f(4000,0,4000),0,0,0,4);
		Camera camera = new Camera(drone);

		//terrain

		boolean bool = true;
		int plus = 0; 
		
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
			drone.sim(csvConverter.A, i);

			camera.move();

			renderer.processEntity(drone);
			for(int j=0;j<i;j=j + 10*plus) {
				renderer.processEntity(new Boule(EntityManager.sphereTexturedModel, new Vector3f(Float.parseFloat(csvConverter.A[1][j])*1000+4000, Float.parseFloat(csvConverter.A[3][j])*1000, Float.parseFloat(csvConverter.A[2][j])*1000+4000),0,0,0,50));
			}
			
			renderer.processTerrain(TerrainManager.terrain);
			renderer.render(camera);

			Button.update();

			//System.out.println(plus);
			DisplayManager.updateDisplay();
			if (i>=80000-plus*TrajectoryManager.SIMULATION_SPEEDFACTOR-1) {
				stop=false;
				i = 0;
			}
			i+=plus*TrajectoryManager.SIMULATION_SPEEDFACTOR;
		}

		renderer.cleanUp();
		DisplayManager.closeDisplay();
	}

}

package main;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import main.graphics.entities.Camera;
import main.graphics.entities.Drone;
import main.graphics.entities.Entity;
import main.graphics.entities.Rotor;
import main.graphics.path.CSVConverter;
import main.graphics.recorder.SequenceEncoder;
import main.graphics.renderer.DisplayRenderer;
import main.graphics.renderer.Loader;
import main.graphics.renderer.MasterRenderer;
import main.graphics.renderer.State;
import main.parameters.ButtonManager;
import main.parameters.DisplayParameters;
import main.parameters.EntityManager;
import main.parameters.RecordManager;

public class Main {

	public static void main(String[] args) {

		DisplayRenderer.createDisplay();
		
		MasterRenderer renderer = new MasterRenderer();
		List<Entity> entities = new ArrayList<Entity>();
		
		Drone drone = new Drone(EntityManager.droneTexturedModel, new Vector3f(4000,0,4000),0,0,0,4);
		Rotor rotor1 = new Rotor(EntityManager.rotorTexturedModel, new Vector3f(4000,0,4000),0,0,0,4);
		Rotor rotor2 = new Rotor(EntityManager.rotorTexturedModel, new Vector3f(4000,0,4000),0,0,0,4);
		Rotor rotor3 = new Rotor(EntityManager.rotorTexturedModel, new Vector3f(4000,0,4000),0,0,0,4);
		Rotor rotor4 = new Rotor(EntityManager.rotorTexturedModel, new Vector3f(4000,0,4000),0,0,0,4);
		entities.add(drone);
		entities.add(rotor1);
		entities.add(rotor2);
		entities.add(rotor3);
		entities.add(rotor4);
		Camera camera = new Camera(drone);
		
		while(!Display.isCloseRequested()) {
			switch(DisplayRenderer.state) {
			case MENU:
				if (DisplayParameters.ACTIVATE_MENU) {
					ButtonManager.showStartMenuButtons();
					
					renderer.renderMenu();
					
					ButtonManager.hideStartMenuButtons();
					DisplayRenderer.updateDisplay();
				} else {
					DisplayRenderer.state = State.SIMULATION;
				}
				break;
			case SIMULATION:
				renderer.renderScene(camera, entities);
				CSVConverter.update(drone, entities, rotor1,rotor2,rotor3,rotor4);

				
				if (RecordManager.ACTIVATE_RECORD) SequenceEncoder.screenShot();
				
				if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
					Loader.test();
				}

				DisplayRenderer.updateDisplay();
				System.out.println(CSVConverter.trajectoryStep + "->" + (int) (1000 / (1/ DisplayRenderer.getFrameTimeSeconds())));
				break;
			}
		}
		
		if (RecordManager.ACTIVATE_RECORD) SequenceEncoder.makeVideo();
		renderer.cleanUp();
		DisplayRenderer.closeDisplay();
	}

}

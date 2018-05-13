package main;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import main.graphics.entities.Camera;
import main.graphics.entities.Drone;
import main.graphics.entities.Entity;
import main.graphics.entities.Rotor;
import main.graphics.guis.GuiTexture;
import main.graphics.path.CSVConverter;
import main.graphics.recorder.SequenceEncoder;
import main.graphics.renderer.DisplayRenderer;
import main.graphics.renderer.MasterRenderer;
import main.graphics.renderer.State;
import main.parameters.ButtonManager;
import main.parameters.DisplayParameters;
import main.parameters.EntityManager;
import main.parameters.GuiManager;
import main.parameters.RecordManager;
import main.parameters.TerrainManager;

public class Main {

	public static void main(String[] args) {

		DisplayRenderer.createDisplay();
		MasterRenderer renderer = new MasterRenderer();
		List<Entity> entities = new ArrayList<Entity>();
		
		Drone drone = new Drone(EntityManager.droneTexturedModel, TerrainManager.terrainCenter, 0, 0, 0, 4);
		Rotor rotor = new Rotor(EntityManager.rotorTexturedModel, TerrainManager.terrainCenter, 0, 0, 0, 4);
		entities.add(drone);
		entities.add(rotor);
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
				if (SequenceEncoder.isEncodingNeeded()) {
					renderer.processGui(new GuiTexture(GuiManager.loadingTexture, new Vector2f(0,0), new Vector2f(1,1)));
					DisplayRenderer.state = State.ENCODING;
				} else {
					if (RecordManager.ACTIVATE_RECORD) SequenceEncoder.screen();
				}
				
				renderer.renderScene(camera, entities);
				CSVConverter.update(drone, entities, rotor);
				
				//FPS//System.out.println(CSVConverter.trajectoryStep + "->" + (int) (1000 / (1/ DisplayRenderer.getFrameTimeSeconds())));
				DisplayRenderer.updateDisplay();
				break;
			case ENCODING:
				SequenceEncoder.partitionEncoding();
				DisplayRenderer.state = State.SIMULATION;
				break;
			}
		}

		
		if (RecordManager.ACTIVATE_RECORD) SequenceEncoder.makeVideo();
		renderer.cleanUp();
		DisplayRenderer.closeDisplay();
	}

}

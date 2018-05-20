package main;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;

import main.graphics.entities.Entity;
import main.graphics.guis.GuiScreens;
import main.graphics.path.CSVConverter;
import main.graphics.recorder.SequenceEncoder;
import main.graphics.renderer.DisplayRenderer;
import main.graphics.renderer.MasterRenderer;
import main.graphics.renderer.State;
import main.parameters.ButtonManager;
import main.parameters.CameraManager;
import main.parameters.DisplayManager;
import main.parameters.EntityManager;
import main.parameters.RecordManager;


public class Main {

	public static void main(String[] args) {

		DisplayRenderer.createDisplay();
		
		MasterRenderer renderer = new MasterRenderer();
		List<Entity> entities = new ArrayList<Entity>();
		EntityManager.createDrone(entities);
		
		while(!Display.isCloseRequested()) {
			switch(DisplayRenderer.state) {
			case MENU:
				if (DisplayManager.ACTIVATE_MENU) {
					ButtonManager.showStartMenuButtons();
					
					GuiScreens.processMainMenu(renderer);
					renderer.renderOnlyGuis();
					
					ButtonManager.hideStartMenuButtons();
					DisplayRenderer.updateDisplay();
				} else {
					DisplayRenderer.state = State.SIMULATION;
				}
				break;
			case SETTINGS:
				if (DisplayManager.ACTIVATE_MENU) {
					ButtonManager.showSettingsMenuButtons();
					
					GuiScreens.processSettingsMenu(renderer);
					renderer.renderOnlyGuis();
					
					ButtonManager.hideSettingsMenuButtons();
					DisplayRenderer.updateDisplay();
				} else {
					DisplayRenderer.state = State.SIMULATION;
				}
				break;
			case SIMULATION:
				
				if (SequenceEncoder.isEncodingNeeded()) {
					renderer.processGui(SequenceEncoder.encodingGui());
					DisplayRenderer.state = State.ENCODING;
				} else {
					if (CSVConverter.mustCalculateCurrentFPS()) {
						renderer.processGui(CSVConverter.analysingGui());
						CSVConverter.calculateCurrentFPS();
						if (CSVConverter.canAnalysePerformance()) {
							CSVConverter.analysePerformance(true);
						}
					}
				}
				
				renderer.renderScene(CameraManager.getCamera(entities), entities);
				if (!CSVConverter.mustCalculateCurrentFPS()) {
					if (CameraManager.IS_FREE_CAMERA) CameraManager.getCamera().movePointer();
					CSVConverter.update(entities);
				}

				if (RecordManager.ACTIVATE_RECORD && !SequenceEncoder.isEncodingNeeded()) SequenceEncoder.screenshot();
				
				DisplayRenderer.updateDisplay();
				break;
			case PAUSE:
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

package main.graphics.guis;

import org.lwjgl.input.Mouse;

import main.graphics.path.CSVConverter;
import main.graphics.recorder.SequenceEncoder;
import main.graphics.renderer.MasterRenderer;
import main.parameters.ButtonManager;
import main.parameters.DisplayManager;
import main.parameters.RecordManager;
import main.parameters.TextureManager;
import main.parameters.TrajectoryManager;

public class GuiScreens {
	
	public static Boolean isLateralMenuOpened = false;
	
	public static void initLateralButtonTextures() {
		if (TrajectoryManager.ACTIVATE_SPHERE) {
			ButtonManager.beaconButton.setClicked(false);
			ButtonManager.beaconButton.setPressed(true);
			ButtonManager.beaconButton.playOnClickAnimation();
		}
		
		if (RecordManager.ACTIVATE_RECORD) {
			ButtonManager.recordButton.setClicked(false);
			ButtonManager.recordButton.setPressed(true);
			ButtonManager.recordButton.playOnClickAnimation();
		}
	}
	
	public static Boolean canOpenLateralMenu() {
		if (!CSVConverter.mustCalculateCurrentFPS() && !SequenceEncoder.isEncodingNeeded()) {
			if (Mouse.getX() > DisplayManager.WINDOW_WIDTH - 0.0390625 * DisplayManager.WINDOW_WIDTH && !isLateralMenuOpened) {
				isLateralMenuOpened = true;
				return true;
			}
			if (Mouse.getX() > (DisplayManager.WINDOW_WIDTH - 0.0625 * DisplayManager.WINDOW_WIDTH)   && isLateralMenuOpened) {
				return true;
			}
		}
		ButtonManager.hideLateralMenuButtons();
		isLateralMenuOpened = false;
		return false;
	}
	
	public static void processMainMenu(MasterRenderer renderer) {
		switch(DisplayManager.WINDOW_WIDTH) {
		case 1920:
			renderer.processGui(new GuiTexture(TextureManager.menuBack1920, 2048));
			renderer.processGui(new GuiTexture(TextureManager.menuHeaderFooter1920, 2048));
			break;
		case 1280:
			renderer.processGui(new GuiTexture(TextureManager.menuBack1280, 2048));
			renderer.processGui(new GuiTexture(TextureManager.menuHeaderFooter1280, 2048));
			break;
		case 640:
			renderer.processGui(new GuiTexture(TextureManager.menuBack640, 1024));
			renderer.processGui(new GuiTexture(TextureManager.menuHeaderFooter640, 1024));
			break;		
		}
	}
	
	public static void processSettingsMenu(MasterRenderer renderer) {
		processMainMenu(renderer);
		switch(DisplayManager.WINDOW_WIDTH) {
		case 1920:
			renderer.processGui(new GuiTexture(TextureManager.settingsBack1920, 2048));
			break;
		case 1280:
			renderer.processGui(new GuiTexture(TextureManager.settingsBack1280, 2048));
			break;
		case 640:
			renderer.processGui(new GuiTexture(TextureManager.settingsBack640, 1024));
			break;		
		}
	}
	
	public static void processLateralMenu(MasterRenderer renderer) {
		ButtonManager.showLateralMenuButtons();
		switch(DisplayManager.WINDOW_WIDTH) {
		case 1920:
			renderer.processGui(new GuiTexture(TextureManager.lateralBar1920, 2048));
			break;
		case 1280:
			renderer.processGui(new GuiTexture(TextureManager.lateralBar1280, 2048));
			break;
		case 640:
			renderer.processGui(new GuiTexture(TextureManager.lateralBar640, 1024));
			break;		
		}
	}
}

package main.graphics.guis;

import org.lwjgl.input.Mouse;

import main.graphics.path.CSVConverter;
import main.graphics.recorder.SequenceEncoder;
import main.graphics.renderer.MasterRenderer;
import main.parameters.ButtonManager;
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
			if (Mouse.getX() > 1230 && !isLateralMenuOpened) {
				isLateralMenuOpened = true;
				return true;
			}
			if (Mouse.getX() > 1200 && isLateralMenuOpened) {
				return true;
			}
		}
		ButtonManager.hideLateralMenuButtons();
		isLateralMenuOpened = false;
		return false;
	}
	
	public static void processMainMenu(MasterRenderer renderer) {
		renderer.processGui(new GuiTexture(TextureManager.Main_menu_back, 2048));
		renderer.processGui(new GuiTexture(TextureManager.Main_menu, 2048));
	}
	
	public static void processSettingsMenu(MasterRenderer renderer) {
		processMainMenu(renderer);
		renderer.processGui(new GuiTexture(TextureManager.settings_no_buttons, 2048));
	}
	
	public static void processLateralMenu(MasterRenderer renderer) {
		ButtonManager.showLateralMenuButtons();
		renderer.processGui(new GuiTexture(TextureManager.lateral_Panel, 2048));
	}
}

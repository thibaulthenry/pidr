package main.parameters;

import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import main.graphics.guis.buttons.Button;
import main.graphics.guis.buttons.ButtonCreation;
import main.graphics.guis.buttons.PressButton;

public class ButtonManager {
	
	private static Button startButton = ButtonCreation.createStartButton(TextureManager.startTexture, TextureManager.startHoverTexture, 
			TextureManager.startClickTexture, new Vector2f(-0.5f, -0.6f), new Vector2f(0.2f,0.2f));
	
	private static Button settingsButton = ButtonCreation.createSettingsButton(TextureManager.settingsTexture, TextureManager.settingsHoverTexture, 
			TextureManager.settingsClickTexture, new Vector2f(0.5f, -0.6f), new Vector2f(0.2f,0.2f));
	
	private static List<PressButton> onoffRecord = ButtonCreation.createRecordSettingButton(TextureManager.offTexture, TextureManager.offHoverTexture, 
			TextureManager.onTexture, new Vector2f(0.11f, 0.155f), new Vector2f(0.04f,0.04f), new Vector2f(0.31f, 0.155f), new Vector2f(0.04f,0.04f));

	private static List<PressButton> onoffSpeed = ButtonCreation.createSpeedSettingButton(TextureManager.offTexture, TextureManager.offHoverTexture, 
			TextureManager.onTexture, new Vector2f(0.11f, 0.045f), new Vector2f(0.04f,0.04f), new Vector2f(0.31f, 0.045f), new Vector2f(0.04f,0.04f));

	private static List<PressButton> onoffSphere = ButtonCreation.createSphereSettingButton(TextureManager.offTexture, TextureManager.offHoverTexture, 
			TextureManager.onTexture, new Vector2f(0.11f, -0.07f), new Vector2f(0.04f,0.04f), new Vector2f(0.31f, -0.07f), new Vector2f(0.04f,0.04f));

	private static List<PressButton> onoffMap = ButtonCreation.createMapSettingButton(TextureManager.offTexture, TextureManager.offHoverTexture, 
			TextureManager.onTexture, new Vector2f(0.11f, -0.185f), new Vector2f(0.04f,0.04f), new Vector2f(0.31f, -0.185f), new Vector2f(0.04f,0.04f));

	private static List<PressButton> onoffCamera = ButtonCreation.createCameraSettingButton(TextureManager.offTexture, TextureManager.offHoverTexture, 
			TextureManager.onTexture, new Vector2f(0.11f, -0.30f), new Vector2f(0.04f,0.04f), new Vector2f(0.31f, -0.30f), new Vector2f(0.04f,0.04f));

	private static void showList(List<PressButton> addedButtons) {
		for (Button button : addedButtons) if (!Button.getButtons().contains(button)) Button.getButtons().add(button);
	}
	
	private static void hideList(List<PressButton> removedButtons) {
		for (Button button : removedButtons) Button.getButtons().remove(button);
	}
	
	public static void showRightMenuButtons() {
		//if (!Button.getButtons().contains(recordButton)) Button.getButtons().add(recordButton);
	}
	
	public static void hideRightMenuButtons() {
		//Button.getButtons().remove(recordButton);
	}
	
	public static void showStartMenuButtons() {
		if (!Button.getButtons().contains(startButton)) Button.getButtons().add(startButton);
		if (!Button.getButtons().contains(settingsButton)) Button.getButtons().add(settingsButton);
	}
	
	public static void hideStartMenuButtons() {
		Button.getButtons().remove(startButton);
		Button.getButtons().remove(settingsButton);
	}
	
	public static void showSettingsMenuButtons() {
		showStartMenuButtons();
		showList(onoffRecord);
		showList(onoffSpeed);
		showList(onoffSphere);
		showList(onoffMap);
		showList(onoffCamera);
	}
	
	public static void hideSettingsMenuButtons() {
		hideStartMenuButtons();
		hideList(onoffRecord);
		hideList(onoffSpeed);
		hideList(onoffSphere);
		hideList(onoffMap);
		hideList(onoffCamera);
	}

}

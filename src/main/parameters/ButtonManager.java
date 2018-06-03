package main.parameters;

import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import main.graphics.guis.buttons.Button;
import main.graphics.guis.buttons.ButtonCreation;
import main.graphics.guis.buttons.ClickButton;
import main.graphics.guis.buttons.PressButton;

public class ButtonManager {
	
	/* Menu */
	
	private static Button startButton = ButtonCreation.createStartButton(TextureManager.startTexture, TextureManager.startHoverTexture, 
			TextureManager.startClickTexture, new Vector2f(-0.5f, -0.8f), new Vector2f(0.2f,0.2f));
	
	private static Button settingsButton = ButtonCreation.createSettingsButton(TextureManager.settingsTexture, TextureManager.settingsHoverTexture, 
			TextureManager.settingsClickTexture, new Vector2f(0.5f, -0.8f), new Vector2f(0.2f,0.2f));
	
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

	/* Lateral */
	
	public static PressButton recordButton = ButtonCreation.createRecordButton(TextureManager.recordTexture, TextureManager.recordHoverTexture,
			TextureManager.recordClickTexture, new Vector2f(0.935f, 0.8f), new Vector2f(0.06f,0.06f));
	
	public static ClickButton upSpeedButton = ButtonCreation.createUpSpeedButton(TextureManager.upSpeedTexture, TextureManager.upSpeedHoverTexture,
			TextureManager.upSpeedClickTexture, new Vector2f(0.935f, 0.6f), new Vector2f(0.06f,0.06f));
	
	public static ClickButton to1SpeedButton = ButtonCreation.createto1SpeedButton(TextureManager.to1speedTexture, TextureManager.to1speedHoverTexture,
			TextureManager.to1speedClickTexture, new Vector2f(0.935f, 0.6f), new Vector2f(0.06f,0.06f));
	
	public static ClickButton mountainButton = ButtonCreation.createMountainButton(TextureManager.mountainTexture, TextureManager.mountainHoverTexture,
			TextureManager.mountainClickTexture, new Vector2f(0.935f, 0.4f), new Vector2f(0.06f,0.06f));
	
	public static ClickButton flatButton = ButtonCreation.createFlatButton(TextureManager.flatTexture, TextureManager.flatHoverTexture,
			TextureManager.flatClickTexture, new Vector2f(0.935f, 0.4f), new Vector2f(0.06f,0.06f));
	
	private static PressButton pauseButton = ButtonCreation.createPauseButton(TextureManager.pauseTexture, TextureManager.pauseHoverTexture,
			TextureManager.pauseClickTexture, new Vector2f(0.935f, 0.2f), new Vector2f(0.06f,0.06f));
	
	public static ClickButton cameraButton = ButtonCreation.createCameraButton(TextureManager.cameraTexture, TextureManager.cameraHoverTexture,
			TextureManager.cameraClickTexture, new Vector2f(0.935f, 0f), new Vector2f(0.06f,0.06f));
	
	public static ClickButton cameraLockedButton = ButtonCreation.createCameraLockedButton(TextureManager.cameraLockedTexture, TextureManager.cameraLockedHoverTexture,
			TextureManager.cameraLockedClickTexture, new Vector2f(0.935f, 0f), new Vector2f(0.06f,0.06f));
	
	public static PressButton beaconButton = ButtonCreation.createBeaconButton(TextureManager.beaconTexture, TextureManager.beaconHoverTexture,
			TextureManager.beaconClickTexture, new Vector2f(0.935f, -0.2f), new Vector2f(0.06f,0.06f));
	
	private static ClickButton resetButton = ButtonCreation.createResetButton(TextureManager.resetTexture, TextureManager.resetHoverTexture,
			TextureManager.resetClickTexture, new Vector2f(0.935f, -0.4f), new Vector2f(0.06f,0.06f));
	
	public static PressButton easterEggButton = ButtonCreation.createEasterEggButton(TextureManager.voidTexture, TextureManager.voidTexture,
			TextureManager.voidTexture, new Vector2f(0.935f, -0.8f), new Vector2f(0.06f,0.06f));
	
	private static void showList(List<PressButton> addedButtons) {
		for (Button button : addedButtons) if (!Button.getButtons().contains(button)) Button.getButtons().add(button);
	}
	
	private static void hideList(List<PressButton> removedButtons) {
		for (Button button : removedButtons) Button.getButtons().remove(button);
	}
	
	public static void showLateralMenuButtons() {
		if (!Button.getButtons().contains(recordButton)) Button.getButtons().add(recordButton);
		
		if (TrajectoryManager.SIMULATION_SPEEDFACTOR == 2 || TrajectoryManager.SIMULATION_SPEEDFACTOR == 1) if (!Button.getButtons().contains(upSpeedButton)) Button.getButtons().add(upSpeedButton);
		if (TrajectoryManager.SIMULATION_SPEEDFACTOR == 5) if (!Button.getButtons().contains(to1SpeedButton)) Button.getButtons().add(to1SpeedButton);
		
		if (TerrainManager.IS_HEIGHT) if (!Button.getButtons().contains(flatButton)) Button.getButtons().add(flatButton);
		if (!TerrainManager.IS_HEIGHT) if (!Button.getButtons().contains(mountainButton)) Button.getButtons().add(mountainButton);
		
		if (!Button.getButtons().contains(pauseButton)) Button.getButtons().add(pauseButton);
		
		if (CameraManager.IS_FREE_CAMERA) if (!Button.getButtons().contains(cameraLockedButton)) Button.getButtons().add(cameraLockedButton);
		if (!CameraManager.IS_FREE_CAMERA) if (!Button.getButtons().contains(cameraButton)) Button.getButtons().add(cameraButton);
		
		if (!Button.getButtons().contains(beaconButton)) Button.getButtons().add(beaconButton);
		
		if (!Button.getButtons().contains(resetButton)) Button.getButtons().add(resetButton);
		
		if (!Button.getButtons().contains(easterEggButton)) Button.getButtons().add(easterEggButton);
	}
	
	public static void hideLateralMenuButtons() {
		Button.getButtons().remove(recordButton);
		Button.getButtons().remove(upSpeedButton);
		Button.getButtons().remove(to1SpeedButton);
		Button.getButtons().remove(flatButton);
		Button.getButtons().remove(mountainButton);
		Button.getButtons().remove(pauseButton);
		Button.getButtons().remove(cameraLockedButton);
		Button.getButtons().remove(cameraButton);
		Button.getButtons().remove(beaconButton);
		Button.getButtons().remove(resetButton);
		Button.getButtons().remove(easterEggButton);
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

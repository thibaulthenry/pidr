package main.parameters;

import org.lwjgl.util.vector.Vector2f;

import main.graphics.guis.buttons.Button;
import main.graphics.guis.buttons.ButtonCreation;

public class ButtonManager {
	
	private static Button recordButton = ButtonCreation.createRecordButton(GuiManager.recordTexture, GuiManager.recordHoverTexture, 
			new Vector2f(0f, 0f), new Vector2f(0.15f, 0.15f), new Vector2f(0.2f, 0.2f));
	
	private static Button startButton = ButtonCreation.createStartButton(GuiManager.recordTexture, GuiManager.recordHoverTexture, 
			new Vector2f(0f, 0f), new Vector2f(0.15f, 0.15f), new Vector2f(0.2f, 0.2f));

	public static void showRightMenuButtons() {
		if (!Button.getButtons().contains(recordButton)) Button.getButtons().add(recordButton);
	}
	
	public static void hideRightMenuButtons() {
		Button.getButtons().remove(recordButton);
	}
	
	public static void showStartMenuButtons() {
		if (!Button.getButtons().contains(startButton)) Button.getButtons().add(startButton);
	}
	
	public static void hideStartMenuButtons() {
		Button.getButtons().remove(startButton);
	}

}

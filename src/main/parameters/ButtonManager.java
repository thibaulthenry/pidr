package main.parameters;

import org.lwjgl.util.vector.Vector2f;

import main.graphics.guis.buttons.Button;
import main.graphics.guis.buttons.ButtonCreation;

public class ButtonManager {
	
	private static Button recordButton = ButtonCreation.createClickButtonRightMenu(GuiManager.recordTexture, GuiManager.recordHoverTexture, 
			new Vector2f(0f, 0f), new Vector2f(1f, 1f), new Vector2f(0.2f, 0.2f));

}

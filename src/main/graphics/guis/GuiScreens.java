package main.graphics.guis;

import main.graphics.renderer.MasterRenderer;
import main.parameters.TextureManager;

public class GuiScreens {
	
	public static void processMainMenu(MasterRenderer renderer) {
		renderer.processGui(new GuiTexture(TextureManager.Main_menu_back, 2048));
		renderer.processGui(new GuiTexture(TextureManager.Main_menu, 2048));
	}
	
	public static void processSettingsMenu(MasterRenderer renderer) {
		processMainMenu(renderer);
		renderer.processGui(new GuiTexture(TextureManager.settings_no_buttons, 2048));
	}
}

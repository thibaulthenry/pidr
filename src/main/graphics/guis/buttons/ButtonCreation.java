package main.graphics.guis.buttons;

import java.util.Arrays;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import main.graphics.renderer.DisplayRenderer;
import main.graphics.renderer.State;
import main.graphics.terrains.Terrain;
import main.parameters.DisplayParameters;
import main.parameters.RecordManager;
import main.parameters.TerrainManager;
import main.parameters.TextureManager;
import main.parameters.TrajectoryManager;

public class ButtonCreation {
	
	public static PressButton createRecordButton(int texture, int hoverTexture,  Vector2f position, Vector2f baseScale, Vector2f hoverScale) {
		PressButton press = new PressButton(texture, position, baseScale) {
			
			@Override
			public void playOnStopHoverAnimation() {
				guiTexture.setScale(baseScale);
				guiTexture.setTexture(texture);
			}
			
			@Override
			public void playOnStartHoverAnimation() {
				guiTexture.setScale(hoverScale);
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void playOnClickAnimation() {}
			
			@Override
			public void playAfterClickAnimation() {}
			
			@Override
			public void onClick() {}
			
			@Override
			public void afterClick() {}

			@Override
			public void whilePressed() {}

			@Override
			public void afterPressed() {}
		};
		
		return press;
	}
	
	public static ClickButton createStartButton(int texture, int hoverTexture, int clickTexture, Vector2f position, Vector2f scale) {
		ClickButton click = new ClickButton(texture, position, scale) {
			
			@Override
			public void playOnStopHoverAnimation() {
				guiTexture.setTexture(texture);
			}
			
			@Override
			public void playOnStartHoverAnimation() {
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void playOnClickAnimation() {
				guiTexture.setTexture(clickTexture);
			}
			
			@Override
			public void playAfterClickAnimation() {
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void onClick() {
				DisplayRenderer.state = State.SIMULATION;
				playOnClickAnimation();
			}
			
			@Override
			public void afterClick() {
				playAfterClickAnimation();
			}
		};
		
		return click;
	}
	
	public static PressButton createSettingsButton(int texture, int hoverTexture, int clickTexture, Vector2f position, Vector2f scale) {
		PressButton click = new PressButton(texture, position, scale) {
			
			@Override
			public void playOnStopHoverAnimation() {
				guiTexture.setTexture(texture);
			}
			
			@Override
			public void playOnStartHoverAnimation() {
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void playOnClickAnimation() {
				guiTexture.setTexture(clickTexture);
			}
			
			@Override
			public void playAfterClickAnimation() {
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void onClick() {
				DisplayRenderer.state = State.SETTINGS;
				playOnClickAnimation();
			}
			
			@Override
			public void afterClick() {
				playAfterClickAnimation();
			}

			@Override
			public void whilePressed() {
				if (isPressed && isOn() && checkLeftClick()) isPressed = false;
			}

			@Override
			public void afterPressed() {
				DisplayRenderer.state = State.MENU;
			}
		};
		
		return click;
	}
	
	public static List<PressButton> createRecordSettingButton(int texture, int hoverTexture, int clickTexture, Vector2f position1, Vector2f scale1, Vector2f position2, Vector2f scale2) {
		PressButton click1 = new PressButton(texture, position1, scale1) {
			
			@Override
			public void playOnStopHoverAnimation() {
				guiTexture.setTexture(texture);
			}
			
			@Override
			public void playOnStartHoverAnimation() {
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void playOnClickAnimation() {
				guiTexture.setTexture(clickTexture);
			}
			
			@Override
			public void playAfterClickAnimation() {
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void onClick() {
				playOnClickAnimation();
				RecordManager.ACTIVATE_RECORD = true;
			}
			
			@Override
			public void afterClick() {
				playAfterClickAnimation();
			}

			@Override
			public void whilePressed() {
				if (isPressed && isOn() && checkLeftClick()) isPressed = false;
				for (PressButton pressButton : isLinkedTo) if (pressButton.isPressed) isPressed = false;
			}

			@Override
			public void afterPressed() {
			}
		};
		
		PressButton click2 = new PressButton(texture, position2, scale2) {
			
			@Override
			public void playOnStopHoverAnimation() {
				guiTexture.setTexture(texture);
			}
			
			@Override
			public void playOnStartHoverAnimation() {
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void playOnClickAnimation() {
				guiTexture.setTexture(clickTexture);
			}
			
			@Override
			public void playAfterClickAnimation() {
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void onClick() {
				playOnClickAnimation();
				RecordManager.ACTIVATE_RECORD = false;
			}
			
			@Override
			public void afterClick() {
				playAfterClickAnimation();
			}

			@Override
			public void whilePressed() {
				if (isPressed && isOn() && checkLeftClick()) isPressed = false;
				for (PressButton pressButton : isLinkedTo) if (pressButton.isPressed) isPressed = false;
			}

			@Override
			public void afterPressed() {
			}
		};
		
		click2.isPressed = true;
		click2.playOnClickAnimation();
		click1.isLinkedTo.add(click2);
		click2.isLinkedTo.add(click1);

		PressButton[] buttons = {click1, click2};
		return Arrays.asList(buttons);
	}
	
	public static List<PressButton> createSpeedSettingButton(int texture, int hoverTexture, int clickTexture, Vector2f position1, Vector2f scale1, Vector2f position2, Vector2f scale2) {
		PressButton click1 = new PressButton(texture, position1, scale1) {
			
			@Override
			public void playOnStopHoverAnimation() {
				guiTexture.setTexture(texture);
			}
			
			@Override
			public void playOnStartHoverAnimation() {
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void playOnClickAnimation() {
				guiTexture.setTexture(clickTexture);
			}
			
			@Override
			public void playAfterClickAnimation() {
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void onClick() {
				playOnClickAnimation();
				TrajectoryManager.SIMULATION_SPEEDFACTOR = 2;
			}
			
			@Override
			public void afterClick() {
				playAfterClickAnimation();
			}

			@Override
			public void whilePressed() {
				Boolean nonePressed = true;
				
				if (isPressed && isOn() && checkLeftClick()) isPressed = false;
				for (PressButton pressButton : isLinkedTo) if (pressButton.isPressed) {
					isPressed = false;
					nonePressed = false;
				}
				if (nonePressed) TrajectoryManager.SIMULATION_SPEEDFACTOR = 1;
			}

			@Override
			public void afterPressed() {
			}
		};
		
		PressButton click2 = new PressButton(texture, position2, scale2) {
			
			@Override
			public void playOnStopHoverAnimation() {
				guiTexture.setTexture(texture);
			}
			
			@Override
			public void playOnStartHoverAnimation() {
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void playOnClickAnimation() {
				guiTexture.setTexture(clickTexture);
			}
			
			@Override
			public void playAfterClickAnimation() {
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void onClick() {
				playOnClickAnimation();
				TrajectoryManager.SIMULATION_SPEEDFACTOR = 5;
			}
			
			@Override
			public void afterClick() {
				playAfterClickAnimation();
			}

			@Override
			public void whilePressed() {
				if (isPressed && isOn() && checkLeftClick()) isPressed = false;
				for (PressButton pressButton : isLinkedTo) if (pressButton.isPressed) isPressed = false;
			}

			@Override
			public void afterPressed() {
			}
		};
		
		click1.isLinkedTo.add(click2);
		click2.isLinkedTo.add(click1);
		PressButton[] buttons = {click1, click2};
		return Arrays.asList(buttons);
	}
	
	public static List<PressButton> createSphereSettingButton(int texture, int hoverTexture, int clickTexture, Vector2f position1, Vector2f scale1, Vector2f position2, Vector2f scale2) {
		PressButton click1 = new PressButton(texture, position1, scale1) {
			
			@Override
			public void playOnStopHoverAnimation() {
				guiTexture.setTexture(texture);
			}
			
			@Override
			public void playOnStartHoverAnimation() {
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void playOnClickAnimation() {
				guiTexture.setTexture(clickTexture);
			}
			
			@Override
			public void playAfterClickAnimation() {
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void onClick() {
				playOnClickAnimation();
				TrajectoryManager.ACTIVATE_SPHERE = true;
			}
			
			@Override
			public void afterClick() {
				playAfterClickAnimation();
			}

			@Override
			public void whilePressed() {
				if (isPressed && isOn() && checkLeftClick()) isPressed = false;
				for (PressButton pressButton : isLinkedTo) if (pressButton.isPressed) isPressed = false;
			}

			@Override
			public void afterPressed() {
			}
		};
		
		PressButton click2 = new PressButton(texture, position2, scale2) {
			
			@Override
			public void playOnStopHoverAnimation() {
				guiTexture.setTexture(texture);
			}
			
			@Override
			public void playOnStartHoverAnimation() {
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void playOnClickAnimation() {
				guiTexture.setTexture(clickTexture);
			}
			
			@Override
			public void playAfterClickAnimation() {
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void onClick() {
				playOnClickAnimation();
				TrajectoryManager.ACTIVATE_SPHERE = false;
			}
			
			@Override
			public void afterClick() {
				playAfterClickAnimation();
			}

			@Override
			public void whilePressed() {
				if (isPressed && isOn() && checkLeftClick()) isPressed = false;
				for (PressButton pressButton : isLinkedTo) if (pressButton.isPressed) isPressed = false;
			}

			@Override
			public void afterPressed() {
			}
		};
		
		click2.isPressed = true;
		click2.playOnClickAnimation();
		click1.isLinkedTo.add(click2);
		click2.isLinkedTo.add(click1);
		
		PressButton[] buttons = {click1, click2};
		return Arrays.asList(buttons);
	}
	
	public static List<PressButton> createMapSettingButton(int texture, int hoverTexture, int clickTexture, Vector2f position1, Vector2f scale1, Vector2f position2, Vector2f scale2) {
		PressButton click1 = new PressButton(texture, position1, scale1) {
			
			@Override
			public void playOnStopHoverAnimation() {
				guiTexture.setTexture(texture);
			}
			
			@Override
			public void playOnStartHoverAnimation() {
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void playOnClickAnimation() {
				guiTexture.setTexture(clickTexture);
			}
			
			@Override
			public void playAfterClickAnimation() {
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void onClick() {
				playOnClickAnimation();
				TerrainManager.HEIGHTMAP_FILENAME = "flatmap";
				//TerrainManager.terrain = new Terrain(0,0, TextureManager.terrainTexturePack, TextureManager.blendmap, "terrain/" + TerrainManager.HEIGHTMAP_FILENAME);
			}
			
			@Override
			public void afterClick() {
				playAfterClickAnimation();
			}

			@Override
			public void whilePressed() {
				if (isPressed && isOn() && checkLeftClick()) isPressed = false;
				for (PressButton pressButton : isLinkedTo) if (pressButton.isPressed) isPressed = false;
			}

			@Override
			public void afterPressed() {
			}
		};
		
		PressButton click2 = new PressButton(texture, position2, scale2) {
			
			@Override
			public void playOnStopHoverAnimation() {
				guiTexture.setTexture(texture);
			}
			
			@Override
			public void playOnStartHoverAnimation() {
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void playOnClickAnimation() {
				guiTexture.setTexture(clickTexture);
			}
			
			@Override
			public void playAfterClickAnimation() {
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void onClick() {
				playOnClickAnimation();
				TerrainManager.HEIGHTMAP_FILENAME = "heightmap";
				//TerrainManager.terrain = new Terrain(0,0, TextureManager.terrainTexturePack, TextureManager.blendmap, "terrain/" + TerrainManager.HEIGHTMAP_FILENAME);
			}
			
			@Override
			public void afterClick() {
				playAfterClickAnimation();
			}

			@Override
			public void whilePressed() {
				if (isPressed && isOn() && checkLeftClick()) isPressed = false;
				for (PressButton pressButton : isLinkedTo) if (pressButton.isPressed) isPressed = false;
			}

			@Override
			public void afterPressed() {
			}
		};
		
		click2.isPressed = true;
		click2.playOnClickAnimation();
		click1.isLinkedTo.add(click2);
		click2.isLinkedTo.add(click1);

		PressButton[] buttons = {click1, click2};
		return Arrays.asList(buttons);
	}
	
	public static List<PressButton> createCameraSettingButton(int texture, int hoverTexture, int clickTexture, Vector2f position1, Vector2f scale1, Vector2f position2, Vector2f scale2) {
		PressButton click1 = new PressButton(texture, position1, scale1) {
			
			@Override
			public void playOnStopHoverAnimation() {
				guiTexture.setTexture(texture);
			}
			
			@Override
			public void playOnStartHoverAnimation() {
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void playOnClickAnimation() {
				guiTexture.setTexture(clickTexture);
			}
			
			@Override
			public void playAfterClickAnimation() {
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void onClick() {
				playOnClickAnimation();
			}
			
			@Override
			public void afterClick() {
				playAfterClickAnimation();
			}

			@Override
			public void whilePressed() {
				if (isPressed && isOn() && checkLeftClick()) isPressed = false;
				for (PressButton pressButton : isLinkedTo) if (pressButton.isPressed) isPressed = false;
			}

			@Override
			public void afterPressed() {
			}
		};
		
		PressButton click2 = new PressButton(texture, position2, scale2) {
			
			@Override
			public void playOnStopHoverAnimation() {
				guiTexture.setTexture(texture);
			}
			
			@Override
			public void playOnStartHoverAnimation() {
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void playOnClickAnimation() {
				guiTexture.setTexture(clickTexture);
			}
			
			@Override
			public void playAfterClickAnimation() {
				guiTexture.setTexture(hoverTexture);
			}
			
			@Override
			public void onClick() {
				playOnClickAnimation();
			}
			
			@Override
			public void afterClick() {
				playAfterClickAnimation();
			}

			@Override
			public void whilePressed() {
				if (isPressed && isOn() && checkLeftClick()) isPressed = false;
				for (PressButton pressButton : isLinkedTo) if (pressButton.isPressed) isPressed = false;
			}

			@Override
			public void afterPressed() {
			}
		};
		
		click1.isPressed = true;
		click1.playOnClickAnimation();
		click1.isLinkedTo.add(click2);
		click2.isLinkedTo.add(click1);

		PressButton[] buttons = {click1, click2};
		return Arrays.asList(buttons);
	}

}

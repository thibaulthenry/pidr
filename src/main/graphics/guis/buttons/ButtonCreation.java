package main.graphics.guis.buttons;

import java.util.Arrays;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import main.graphics.entities.TrajectorySphere;
import main.graphics.guis.GuiScreens;
import main.graphics.renderer.DisplayRenderer;
import main.graphics.renderer.State;
import main.parameters.ButtonManager;
import main.parameters.CameraManager;
import main.parameters.RecordManager;
import main.parameters.TerrainManager;
import main.parameters.TrajectoryManager;

public class ButtonCreation {
	
	/* Menu */
	
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
				GuiScreens.initLateralButtonTextures();
				DisplayRenderer.state = State.SIMULATION;
				playOnClickAnimation();
			}
			
			@Override
			public void afterClick() {}
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
				for (Button pressButton : isLinkedTo) if (pressButton.isPressed) pressButton.isPressed = false;
			}
			
			@Override
			public void afterClick() {}

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
	
	/* Settings */
	
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
				for (Button pressButton : isLinkedTo) if (pressButton.isPressed) {
					pressButton.playOnStopHoverAnimation();
					pressButton.isPressed = false;
				}
				RecordManager.ACTIVATE_RECORD = true;
			}
			
			@Override
			public void afterClick() {}

			@Override
			public void whilePressed() {}

			@Override
			public void afterPressed() {}
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
				for (Button pressButton : isLinkedTo) if (pressButton.isPressed) {
					pressButton.playOnStopHoverAnimation();
					pressButton.isPressed = false;
				}
				RecordManager.ACTIVATE_RECORD = false;
			}
			
			@Override
			public void afterClick() {}

			@Override
			public void whilePressed() {}

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
				for (Button pressButton : isLinkedTo) if (pressButton.isPressed) {
					pressButton.playOnStopHoverAnimation();
					pressButton.isPressed = false;
				}
				TrajectoryManager.SIMULATION_SPEEDFACTOR = 2;
			}
			
			@Override
			public void afterClick() {}

			@Override
			public void whilePressed() {
				if (isPressed && isOn() && checkLeftClick()) isPressed = false;
			}

			@Override
			public void afterPressed() {
				Boolean nonePressed = true;
				for (Button pressButton : isLinkedTo) if (pressButton.isPressed) nonePressed = false; 
				if (nonePressed) TrajectoryManager.SIMULATION_SPEEDFACTOR = 1;
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
				for (Button pressButton : isLinkedTo) if (pressButton.isPressed) {
					pressButton.playOnStopHoverAnimation();
					pressButton.isPressed = false;
				}
				TrajectoryManager.SIMULATION_SPEEDFACTOR = 5;
			}
			
			@Override
			public void afterClick() {}

			@Override
			public void whilePressed() {
				if (isPressed && isOn() && checkLeftClick()) isPressed = false;
			}

			@Override
			public void afterPressed() {
				Boolean nonePressed = true;
				for (Button pressButton : isLinkedTo) if (pressButton.isPressed) nonePressed = false; 
				if (nonePressed) TrajectoryManager.SIMULATION_SPEEDFACTOR = 1;
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
			public void playAfterClickAnimation() {}
			
			@Override
			public void onClick() {
				playOnClickAnimation();
				for (Button pressButton : isLinkedTo) if (pressButton.isPressed) {
					pressButton.playOnStopHoverAnimation();
					pressButton.isPressed = false;
				}
				TrajectoryManager.ACTIVATE_SPHERE = true;
			}
			
			@Override
			public void afterClick() {}

			@Override
			public void whilePressed() {}

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
				for (Button pressButton : isLinkedTo) if (pressButton.isPressed) {
					pressButton.playOnStopHoverAnimation();
					pressButton.isPressed = false;
				}
				TrajectoryManager.ACTIVATE_SPHERE = false;
			}
			
			@Override
			public void afterClick() {}

			@Override
			public void whilePressed() {}

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
				for (Button pressButton : isLinkedTo) if (pressButton.isPressed) {
					pressButton.playOnStopHoverAnimation();
					pressButton.isPressed = false;
				}
				TerrainManager.IS_HEIGHT = false;
			}
			
			@Override
			public void afterClick() {}

			@Override
			public void whilePressed() {}

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
				for (Button pressButton : isLinkedTo) if (pressButton.isPressed) {
					pressButton.playOnStopHoverAnimation();
					pressButton.isPressed = false;
				}
				TerrainManager.IS_HEIGHT = true;
			}
			
			@Override
			public void afterClick() {}

			@Override
			public void whilePressed() {}

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
			public void playAfterClickAnimation() {}
			
			@Override
			public void onClick() {
				playOnClickAnimation();
				for (Button pressButton : isLinkedTo) if (pressButton.isPressed) {
					pressButton.playOnStopHoverAnimation();
					pressButton.isPressed = false;
				}
				CameraManager.IS_FREE_CAMERA = true;
			}
			
			@Override
			public void afterClick() {}

			@Override
			public void whilePressed() {}

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
			public void playAfterClickAnimation() {}
			
			@Override
			public void onClick() {
				playOnClickAnimation();
				for (Button pressButton : isLinkedTo) if (pressButton.isPressed) {
					pressButton.playOnStopHoverAnimation();
					pressButton.isPressed = false;
				}
				CameraManager.IS_FREE_CAMERA = false;
			}
			
			@Override
			public void afterClick() {}

			@Override
			public void whilePressed() {}

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

	/* Lateral */
	
	public static PressButton createRecordButton(int texture, int hoverTexture, int clickTexture, Vector2f position, Vector2f scale) {
		PressButton press = new PressButton(texture, position, scale) {
			
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
			}

			@Override
			public void afterPressed() {
				RecordManager.ACTIVATE_RECORD = false;
			}
			
		};
		
		press.buttonType = "cycle";

		return press;
	}

	public static ClickButton createUpSpeedButton(int texture, int hoverTexture, int clickTexture, Vector2f position, Vector2f scale) {
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
				playOnClickAnimation();
				if (TrajectoryManager.SIMULATION_SPEEDFACTOR == 1) {
					TrajectoryManager.SIMULATION_SPEEDFACTOR = 2;
				} else if (TrajectoryManager.SIMULATION_SPEEDFACTOR == 2) {
					TrajectoryManager.SIMULATION_SPEEDFACTOR = 5;
				} else {
					ButtonManager.to1SpeedButton.show();
					hide();	
				}
			}
			
			@Override
			public void afterClick() {
				playAfterClickAnimation();
			}
		};
		
		return click;
	}
	
	public static ClickButton createto1SpeedButton(int texture, int hoverTexture, int clickTexture, Vector2f position, Vector2f scale) {
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
				TrajectoryManager.SIMULATION_SPEEDFACTOR = 1;
				playOnClickAnimation();
				ButtonManager.upSpeedButton.show();
				hide();
			}
			
			@Override
			public void afterClick() {
				playAfterClickAnimation();
			}
		};
		
		return click;
	}
	
	public static ClickButton createMountainButton(int texture, int hoverTexture, int clickTexture, Vector2f position, Vector2f scale) {
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
				playOnClickAnimation();
				TerrainManager.IS_HEIGHT = true;
				ButtonManager.flatButton.show();
				hide();	
			}
			
			@Override
			public void afterClick() {
				playAfterClickAnimation();
			}
		};
		
		return click;
	}
	
	public static ClickButton createFlatButton(int texture, int hoverTexture, int clickTexture, Vector2f position, Vector2f scale) {
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
				playOnClickAnimation();
				TerrainManager.IS_HEIGHT = false;
				ButtonManager.mountainButton.show();
				hide();	
			}
			
			@Override
			public void afterClick() {
				playAfterClickAnimation();
			}
		};
		
		return click;
	}
	
	public static PressButton createPauseButton(int texture, int hoverTexture, int clickTexture, Vector2f position, Vector2f scale) {
		PressButton press = new PressButton(texture, position, scale) {
			
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
				DisplayRenderer.state = State.PAUSE;
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
				DisplayRenderer.state = State.SIMULATION;
			}
			
		};
		
		press.buttonType = "cycle";

		return press;
	}
	
	public static ClickButton createCameraLockedButton(int texture, int hoverTexture, int clickTexture, Vector2f position, Vector2f scale) {
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
				playOnClickAnimation();
				CameraManager.IS_FREE_CAMERA = false;
				ButtonManager.cameraButton.show();
				hide();	
			}
			
			@Override
			public void afterClick() {
				playAfterClickAnimation();
			}
		};
		
		return click;
	}
	
	public static ClickButton createCameraButton(int texture, int hoverTexture, int clickTexture, Vector2f position, Vector2f scale) {
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
				playOnClickAnimation();
				CameraManager.IS_FREE_CAMERA = true;
				ButtonManager.cameraLockedButton.show();
				hide();	
			}
			
			@Override
			public void afterClick() {
				playAfterClickAnimation();
			}
		};
		
		return click;
	}

	public static PressButton createBeaconButton(int texture, int hoverTexture, int clickTexture, Vector2f position, Vector2f scale) {
		PressButton press = new PressButton(texture, position, scale) {
			
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
			}

			@Override
			public void afterPressed() {
				TrajectoryManager.ACTIVATE_SPHERE = false;
			}
			
		};
		
		press.buttonType = "cycle";

		return press;
	}
	
	public static ClickButton createResetButton(int texture, int hoverTexture, int clickTexture, Vector2f position, Vector2f scale) {
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
				playOnClickAnimation();
				DisplayRenderer.state = State.RESET;
			}
			
			@Override
			public void afterClick() {
				playAfterClickAnimation();
			}
		};
		
		return click;
	}
	
	public static PressButton createEasterEggButton(int texture, int hoverTexture, int clickTexture, Vector2f position, Vector2f scale) {
		PressButton press = new PressButton(texture, position, scale) {
			
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
				TrajectorySphere.EASTER_EGG = true;
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
				TrajectorySphere.EASTER_EGG = false;
			}
			
		};
		
		press.buttonType = "cycle";

		return press;
	}
	
}

package main.graphics.guis.buttons;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

abstract public class PressButton extends Button {
	
	public PressButton(String textureStr, Vector2f position, Vector2f scale) {
		super(textureStr, position, scale);
	}

	public abstract void whilePressed();
	public abstract void afterPressed();

	public void updateState() {
		if (!isHidden) {
			if (!isPressed) {
				if (isOn()) {
					if (!isHovered) {
						isHovered = true;
						onStartHover();
					}
					
					while (Mouse.next()) {
						if (Mouse.isButtonDown(0) && !isClicked) {
							onClick();
							isPressed = true;
						} else if (isClicked) {
							afterClick();
						}
						isClicked = Mouse.isButtonDown(0);
					}
					
				} else {
					if (isHovered) {
						isHovered = false;
						onStopHover();
					}
				}
				
			} else {
				whilePressed();
				if (!isPressed) {
					afterPressed();
				}
			}
		}
	
	}
	
}

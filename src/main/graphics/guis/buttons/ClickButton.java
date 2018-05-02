package main.graphics.guis.buttons;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

abstract public class ClickButton extends Button {
	
	public ClickButton(Integer texture, Vector2f position, Vector2f scale) {
		super(texture, position, scale);
	}
	
	public void updateState() {
		if (!isHidden) {
			if (isOn()) {
				if (!isHovered) {
					isHovered = true;
					onStartHover();
				}
				
				while(Mouse.next()) {
					if (Mouse.isButtonDown(0) && !isClicked) {
						onClick();
					} else {
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
		}
	}

}

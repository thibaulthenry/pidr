package main.graphics.guis.buttons;

import org.lwjgl.util.vector.Vector2f;

import main.graphics.renderer.DisplayRenderer;
import main.graphics.renderer.State;

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
	
	public static ClickButton createStartButton(int texture, int hoverTexture,  Vector2f position, Vector2f baseScale, Vector2f hoverScale) {
		ClickButton click = new ClickButton(texture, position, baseScale) {
			
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
			public void onClick() {
				DisplayRenderer.state = State.SIMULATION;
			}
			
			@Override
			public void afterClick() {}
		};
		
		return click;
	}
	
	

}

package main.graphics.guis.buttons;

import org.lwjgl.util.vector.Vector2f;

public class ButtonCreation {
	
	public static PressButton createPressButtonRightMenu() {
		PressButton press = new PressButton(null, null, null) {
			
			@Override
			public void playOnStopHoverAnimation() {}
			
			@Override
			public void playOnStartHoverAnimation() {}
			
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
	
	public static ClickButton createClickButtonRightMenu(int texture, int hoverTexture,  Vector2f position, Vector2f baseScale, Vector2f hoverScale) {
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
			public void onClick() {}
			
			@Override
			public void afterClick() {}
		};
		
		return click;
	}
	
	

}

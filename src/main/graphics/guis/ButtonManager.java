package main.graphics.guis;

import org.lwjgl.util.vector.Vector2f;

import main.graphics.guis.buttons.ClickButton;

public class ButtonManager {

	private ClickButton click = new ClickButton("4", new Vector2f(-0.5f,0.5f), new Vector2f(0.25f,0.25f)) {
		
		private int clickTexID = loader.loadTexture("3"); 
		private GuiTexture backTex = guiTexture;
		
		@Override
		public void onClick() {
			guiTexture = new GuiTexture(clickTexID, position, scale);
			setActive(true);
		}

		@Override
		public void afterClick() {
			setActive(false);
			guiTexture = backTex;
		}

		@Override
		public void onStartHover() {
			playOnStartHoverAnimation();
		}

		@Override
		public void onStopHover() {
			playOnStopHoverAnimation();
		}

		@Override
		public void playOnClickAnimation() {
			
		}

		@Override
		public void playAfterClickAnimation() {
			
		}

		@Override
		public void playOnStartHoverAnimation() {
			
		}

		@Override
		public void playOnStopHoverAnimation() {

		}

	};

}

package main.graphics.guis;

import org.lwjgl.util.vector.Vector2f;

import main.parameters.DisplayParameters;

public class GuiTexture {
	
	private int texture;
	private Vector2f position;
	private Vector2f scale;
	private Boolean isDisplayed;
	private Boolean isFullScreen;
	
	public GuiTexture(int texture, Vector2f position, Vector2f scale) {
		super();
		this.texture = texture;
		this.position = position;
		this.scale = scale;
		this.isDisplayed = true;
		this.isFullScreen = false;
	}
	
	public GuiTexture(int texture, Vector2f position, int powerOfTwo) {
		super();
		this.texture = texture;
		this.position = position;
		this.isDisplayed = true;
		this.scale = new Vector2f((float) powerOfTwo / DisplayParameters.WINDOW_WIDTH, (float) powerOfTwo / DisplayParameters.WINDOW_HEIGHT);
		this.isFullScreen = true;
	}
	
	public GuiTexture(int texture, int powerOfTwo) {
		super();
		this.texture = texture;
		this.position = new Vector2f(0,0);
		this.isDisplayed = true;
		this.scale = new Vector2f((float) powerOfTwo / DisplayParameters.WINDOW_WIDTH, (float) powerOfTwo / DisplayParameters.WINDOW_HEIGHT);
		this.isFullScreen = true;
	}

	public int getTexture() {
		return texture;
	}

	public void setTexture(int texture) {
		this.texture = texture;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public Vector2f getScale() {
		return scale;
	}

	public void setScale(Vector2f scale) {
		this.scale = scale;
	}

	public Boolean isDisplayed() {
		return isDisplayed;
	}

	public void setDisplayed(Boolean isDisplayed) {
		this.isDisplayed = isDisplayed;
	}

	public Boolean isFullScreen() {
		return isFullScreen;
	}

	public void setFullScreen(Boolean isFullScreen) {
		this.isFullScreen = isFullScreen;
	}
	
	

}

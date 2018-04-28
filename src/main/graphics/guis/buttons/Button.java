package main.graphics.guis.buttons;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import main.graphics.entities.Entity;
import main.graphics.guis.GuiTexture;
import main.graphics.renderer.Loader;

abstract public class Button implements IButton {

	protected static List<Button> activeButtons = new ArrayList<Button>();
	protected static List<Button> buttons = new ArrayList<Button>();
	protected static List<Button> addedButtons = new ArrayList<Button>();
	protected static List<Button> removedButtons = new ArrayList<Button>();

	protected static final Loader loader = new Loader();
	protected int resetTexID;
	protected Vector2f position;
	protected float xCenter;
	protected float yCenter;
	protected Vector2f scale;
	protected GuiTexture guiTexture;
	protected Entity entityOnClick;
	protected String onHoveringTexture;
	protected String onClickingTexture;
	protected float onHoveringAddScale;
	protected float onClickingAddScale;

	protected boolean isClicked = false;
	protected boolean isHovered = false;
	protected boolean isPressed = false;
	protected boolean isPanelLinked = false;
	protected boolean isHidden = false;

	private float w;
	private float h;
	private float x;
	private float y;

	public Button(String textureStr, Vector2f position, Vector2f scale) {
		this.position = position;
		this.scale = scale;
		this.resetTexID = loader.loadTexture(textureStr);
		this.guiTexture = new GuiTexture(resetTexID, position, scale);
		this.xCenter = guiTexture.getPosition().x;
		this.yCenter = guiTexture.getPosition().y;

		buttons.add(this);
	}

	public boolean isOn() {
		boolean isOn = false;
		w = ((float) (scale.x) * Display.getWidth()) / 2;
		h = ((float) (scale.y) * Display.getHeight()) / 2;
		x = ((float) xCenter * Display.getWidth()) / 2;
		y = ((float) yCenter * Display.getHeight()) / 2;
		Vector2f mouseCoords = new Vector2f(Mouse.getX() - Display.getWidth() / 2,
				Mouse.getY() - Display.getHeight() / 2);
		float ratioHW = (float) Display.getHeight() / Display.getWidth();

		if (x - (ratioHW * w) < mouseCoords.x && x + (ratioHW * w) > mouseCoords.x && y - h < mouseCoords.y
				&& y + h > mouseCoords.y) {
			isOn = true;
		}
		return isOn;
	}
	
	public boolean isOnAnyOther() {
		for (Button b : buttons) {
			if (b != this) {
				if (b.isOn()) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean checkLeftClick() {
		if (Mouse.getEventButtonState()) {
			if (Mouse.getEventButton() == 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public static void update() {
		buttons.addAll(Button.getAddedButtons());
		addedButtons.clear();
		for (Button b : buttons) {
			b.updateState();
		}
		buttons.removeAll(Button.getRemovedButtons());
		removedButtons.clear();
	}

	public void reset() {
		guiTexture.setTexture(this.resetTexID);
		guiTexture.setScale(this.getScale());
		isClicked = false;
		isHovered = false;
		isPressed = false;
	}
	
	public void reset(List<Button> buttons) {
		for (Button b : buttons) {
			b.reset();
		}
	}
	
	public void hide() {
		removedButtons.add(this);
		isHidden = true;
		guiTexture.setDisplayed(false);
	}

	public void hide(List<Button> buttons) {
		for (Button b : buttons) {
			b.hide();
		}
	}

	public void show() {
		addedButtons.add(this);
		isHidden = false;
		guiTexture.setDisplayed(true);
	}

	public void show(List<Button> buttons) {
		for (Button b : buttons) {
			b.show();
		}
	}
	
	public void setActive(boolean bool) {
		if (bool) {
			reset(activeButtons);
			activeButtons.clear();
			activeButtons.add(this);
		} else {
			reset(activeButtons);
			activeButtons.clear();
		}
	}

	public void setActive(boolean bool, boolean severalButtons) {
		if (bool) {
			reset(activeButtons);
			activeButtons.clear();
			if (!severalButtons) {
				activeButtons.add(this);
			}
		} else {
			reset(activeButtons);
			activeButtons.clear();
		}
	}

	public void setActive(boolean bool, List<Button> buttons) {
		for (Button b : buttons) {
			if (bool) {
				b.setActive(true, true);
			} else {
				b.setActive(false, true);
			}
		}
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}

	public boolean isPressed() {
		return isPressed;
	}

	public void setPressed(boolean isPressed) {
		this.isPressed = isPressed;
	}

	public boolean isHovered() {
		return isHovered;
	}

	public void setHovered(boolean isHovered) {
		this.isHovered = isHovered;
	}

	public String getOnHoveringTexture() {
		return onHoveringTexture;
	}

	public void setOnHoveringTexture(String onHoveringTexture) {
		this.onHoveringTexture = onHoveringTexture;
	}

	public String getOnClickingTexture() {
		return onClickingTexture;
	}

	public void setOnClickingTexture(String onClickingTexture) {
		this.onClickingTexture = onClickingTexture;
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

	public GuiTexture getGuiTexture() {
		return guiTexture;
	}

	public void setGuiTexture(GuiTexture guiTexture) {
		this.guiTexture = guiTexture;
	}
	

	public Entity getEntityOnClick() {
		return entityOnClick;
	}

	public void setEntityOnClick(Entity entityOnClick) {
		this.entityOnClick = entityOnClick;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public boolean isPanelLinked() {
		return isPanelLinked;
	}

	public void setPanelLinked(boolean isPanelLinked) {
		this.isPanelLinked = isPanelLinked;
	}

	public float getOnHoveringAddScale() {
		return onHoveringAddScale;
	}

	public void setOnHoveringAddScale(float onHoveringAddScale) {
		this.onHoveringAddScale = onHoveringAddScale;
	}

	public float getOnClickingAddScale() {
		return onClickingAddScale;
	}

	public void setOnClickingAddScale(float onClickingAddScale) {
		this.onClickingAddScale = onClickingAddScale;
	}

	public static List<Button> getButtons() {
		return buttons;
	}

	public static List<Button> getAddedButtons() {
		return addedButtons;
	}

	public static List<Button> getRemovedButtons() {
		return removedButtons;
	}

}

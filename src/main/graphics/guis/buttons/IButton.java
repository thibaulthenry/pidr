package main.graphics.guis.buttons;

public interface IButton {

	public void onClick();
	public void afterClick();
	public void onStartHover();
	public void onStopHover();
	public void playOnStartHoverAnimation();
	public void playOnStopHoverAnimation();
	public void playOnClickAnimation();
	public void playAfterClickAnimation();
	public void updateState();
	public boolean isOn();
	
}

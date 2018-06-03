package main.parameters;

import java.awt.Dimension;
import java.awt.Toolkit;

public class DisplayManager {
	
	public final static String WINDOW_TITLE = "3Drone Simulator";
	public final static String WINDOW_ICON16_FILENAME = "icon16.png";
	public final static String WINDOW_ICON32_FILENAME = "icon32.png";
	public static final boolean ACTIVATE_MENU = true;
	
	private static int[] availableWindow1920 = {1920, 1080};
	private static int[] availableWindow1280 = {1280, 720};
	private static int[] availableWindow640 = {640, 320};
	
	public static int WINDOW_WIDTH = getWindowSize()[0];
	public static int WINDOW_HEIGHT =  getWindowSize()[1];
	
	private static int[] getWindowSize() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screensize = toolkit.getScreenSize();
		if (screensize.getWidth() >= 1920 && screensize.getHeight() >= 1080) {
			return availableWindow1920;
		} else if (screensize.getWidth() >= 1280 && screensize.getHeight() >= 720) {
			return availableWindow1280;
		} else {
			return availableWindow640;
		}
	}
	
	public static int PERF_ANALYSE_DURATION = 3;
	
}

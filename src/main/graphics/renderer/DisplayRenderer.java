package main.graphics.renderer;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;
import org.newdawn.slick.opengl.ImageIOImageData;

import main.parameters.DisplayManager;

public class DisplayRenderer {
	
	public final static int FPS_CAP = 1000; 
	
	public static long startTime = getCurrentTime();
	private static long lastFrameTime;
	private static float delta;
	
	
	private static DisplayMode mode = new DisplayMode(DisplayManager.WINDOW_WIDTH, DisplayManager.WINDOW_HEIGHT);
	public static State state = State.MENU;

	public static void createDisplay() {
		
		ContextAttribs attribs = new ContextAttribs(3,2)
		.withForwardCompatible(true)
		.withProfileCore(true);
		
		ByteBuffer[] buffer = null;
		try {
			buffer = new ByteBuffer[] {
			        new ImageIOImageData().imageToByteBuffer(ImageIO.read(new File("resources/textures/icon/" + DisplayManager.WINDOW_ICON16_FILENAME)), false, true, null),
			        new ImageIOImageData().imageToByteBuffer(ImageIO.read(new File("resources/textures/icon/" + DisplayManager.WINDOW_ICON16_FILENAME)), false, true, null)
			        };
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			Display.setDisplayMode(mode);
			Display.setResizable(true);
			Display.setFullscreen(false);
			Display.setVSyncEnabled(true);
			Display.setTitle(DisplayManager.WINDOW_TITLE);
			Display.setIcon(buffer);
			Display.create(new PixelFormat(), attribs);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		GL11.glViewport(0, 0, DisplayManager.WINDOW_WIDTH, DisplayManager.WINDOW_HEIGHT);
		lastFrameTime = getCurrentTime();
	}
	
	public static void updateDisplay() {
		Display.sync(FPS_CAP);
		Display.update();
		long currentFrameTime = getCurrentTime();
		delta = (currentFrameTime - lastFrameTime)/1000f;
		lastFrameTime = currentFrameTime;
	}

	public static float getFrameTimeSeconds() {
		return delta;
	}
	
	public static int getCurrentFPS() {
		return (int) (1 / delta);
	}
	
	public static void closeDisplay() {
		Display.destroy();
	}
	
	private static long getCurrentTime() {
		return Sys.getTime()*1000/Sys.getTimerResolution();
	}
	
	public static float getTimeSinceStart() {
		return (getCurrentTime() - startTime)/1000f;
	}
	
	 

}
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

import main.parameters.DisplayParameters;

public class DisplayManager {
	
	private final static int FPS_CAP = 1000; 
	
	private static long lastFrameTime;
	private static float delta;
	
	private static DisplayMode mode = new DisplayMode(DisplayParameters.WINDOW_WIDTH, DisplayParameters.WINDOW_HEIGHT);

	public static void createDisplay() {
		
		ContextAttribs attribs = new ContextAttribs(3,2)
		.withForwardCompatible(true)
		.withProfileCore(true);
		
		ByteBuffer[] buffer = null;
		try {
			buffer = new ByteBuffer[] {
			        new ImageIOImageData().imageToByteBuffer(ImageIO.read(new File("resources/textures/icon/" + DisplayParameters.WINDOW_ICON16_FILENAME)), false, true, null),
			        new ImageIOImageData().imageToByteBuffer(ImageIO.read(new File("resources/textures/icon/" + DisplayParameters.WINDOW_ICON16_FILENAME)), false, true, null)
			        };
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			Display.setDisplayMode(mode);
			Display.setResizable(false);
			Display.setFullscreen(false);
			Display.setVSyncEnabled(true);
			Display.setTitle(DisplayParameters.WINDOW_TITLE);
			Display.setIcon(buffer);
			Display.create(new PixelFormat(), attribs);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		GL11.glViewport(0, 0, DisplayParameters.WINDOW_WIDTH, DisplayParameters.WINDOW_HEIGHT);
		lastFrameTime = getCurrentTime();
	}
	
	public static void updateDisplay() {
		Display.sync(FPS_CAP);
		//GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		Display.update();
		long currentFrameTime = getCurrentTime();
		delta = (currentFrameTime - lastFrameTime)/1000f;
		lastFrameTime = currentFrameTime;
	}

	public static float getFrameTimeSeconds() {
		return delta;
	}
	
	public static void closeDisplay() {
		Display.destroy();
	}
	
	private static long getCurrentTime() {
		return Sys.getTime()*1000/Sys.getTimerResolution();
	}

}
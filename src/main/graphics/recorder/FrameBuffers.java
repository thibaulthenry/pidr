package main.graphics.recorder;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;


public class FrameBuffers {

	protected static final int WINDOW_WIDTH = 1280;
	private static final int WINDOW_HEIGHT = 720;
	
	protected static final int RESOLUTION_WIDTH = 512;
	private static final int RESOLUTION_HEIGHT = 288;

	public static List<BufferedImage> record = new ArrayList<BufferedImage>();
	
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  

	public void screenShot(int k){
		//Creating an rbg array of total pixels
		int[] pixels = new int[WINDOW_WIDTH * WINDOW_HEIGHT];
		int bindex;
		// allocate space for RBG pixels
		ByteBuffer fb = ByteBuffer.allocateDirect(WINDOW_WIDTH * WINDOW_HEIGHT * 3);

		// grab a copy of the current frame contents as RGB
		GL11.glReadPixels(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, fb);

		BufferedImage imageIn = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT,BufferedImage.TYPE_INT_RGB);
		// convert RGB data in ByteBuffer to integer array
		for (int i=0; i < pixels.length; i++) {
			bindex = i * 3;
			pixels[i] =
					((fb.get(bindex) << 16))  +
					((fb.get(bindex+1) << 8))  +
					((fb.get(bindex+2) << 0));
		}
		//Allocate colored pixel to buffered Image
		imageIn.setRGB(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, pixels, 0 , WINDOW_WIDTH);

		//Creating the transformation direction (horizontal)
		AffineTransform at =  AffineTransform.getScaleInstance(1, -1);
		at.translate(0, -imageIn.getHeight(null));

		//Applying transformation
		AffineTransformOp opRotated = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		BufferedImage imageOut = opRotated.filter(imageIn, null);

		record.add(resize(imageOut, RESOLUTION_WIDTH, RESOLUTION_HEIGHT));
	}


}

package main.graphics.recorder;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.jcodec.api.awt.AWTSequenceEncoder;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import main.graphics.guis.GuiTexture;
import main.graphics.path.CSVConverter;
import main.parameters.DisplayParameters;
import main.parameters.TextureManager;
import main.parameters.RecordManager;

public class SequenceEncoder {
	
	public static int averageFPS;
	private static int screenshotIndex;
	
	private static AWTSequenceEncoder encoder = null;

	public static List<ByteBuffer> byteBuffers = new ArrayList<ByteBuffer>();
	public static GuiTexture screenGui;

	private static BufferedImage scale(BufferedImage bi, double scaleValue) {
		AffineTransform tx = new AffineTransform();
		tx.scale(scaleValue, scaleValue);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);
		BufferedImage biNew = new BufferedImage( (int) (bi.getWidth() * scaleValue),
				(int) (bi.getHeight() * scaleValue),
				bi.getType());
		return op.filter(bi, biNew);

	}
	
	public static boolean canScreenshot() {
		Boolean canScreenshot = false;
		if ((screenshotIndex % RecordManager.SCREEENSHOT_MODULO) == 0) {
			screenshotIndex = 1;
			canScreenshot = true;
		}
		screenshotIndex++;
		return canScreenshot;
	}
	
	public static void screenshot() {
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(DisplayParameters.WINDOW_WIDTH * DisplayParameters.WINDOW_HEIGHT * 3);
		GL11.glReadPixels(0, 0, DisplayParameters.WINDOW_WIDTH, DisplayParameters.WINDOW_HEIGHT, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, byteBuffer);
		if (!CSVConverter.mustCalculateCurrentFPS()) byteBuffers.add(byteBuffer);
	}
	
	public static void partitionEncoding() {
		int index = 1;
		if (encoder == null) {
			File file = new File("resources/recording/record.mp4");
			while (file.exists() && !file.isDirectory()) {
				file = new File("resources/recording/record" + index + ".mp4");
				index++;
			}
			try {
				encoder = AWTSequenceEncoder.createSequenceEncoder(file, averageFPS / RecordManager.SCREEENSHOT_MODULO);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		int[] pixels = new int[DisplayParameters.WINDOW_WIDTH * DisplayParameters.WINDOW_HEIGHT];
		BufferedImage screenShot = new BufferedImage(DisplayParameters.WINDOW_WIDTH, DisplayParameters.WINDOW_HEIGHT,BufferedImage.TYPE_INT_RGB);
		int bindex;
		
		for (ByteBuffer byteBuffer : byteBuffers) {
			for (int i=0; i < pixels.length; i++) {
				bindex = i * 3;
				pixels[i] =
						((byteBuffer.get(bindex) << 16))  +
						((byteBuffer.get(bindex+1) << 8))  +
						((byteBuffer.get(bindex+2) << 0));
			}

			screenShot.setRGB(0, 0, DisplayParameters.WINDOW_WIDTH, DisplayParameters.WINDOW_HEIGHT, pixels, 0 , DisplayParameters.WINDOW_WIDTH);
			
			AffineTransform affineTransform =  AffineTransform.getScaleInstance(1, -1);
			affineTransform.translate(0, -screenShot.getHeight(null));
			AffineTransformOp opRotated = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BILINEAR);
			BufferedImage finalImage = opRotated.filter(screenShot, null);
			
			try {
				encoder.encodeImage(scale(finalImage, RecordManager.RESOLUTION_SCALE));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		byteBuffers.clear();
		System.gc();
	}
	
	public static void makeVideo() {
		if (encoder != null) {
			try {
				encoder.finish();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static GuiTexture encodingGui() {
		return new GuiTexture(TextureManager.encodingTexture, new Vector2f(0f,0f), new Vector2f(1f,1f));
	}
	
	public static boolean isEncodingNeeded() {
		return (byteBuffers.size() > RecordManager.SCREENSHOT_NUMBER_BEFORE_ENCODING);
	}


}

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

import main.parameters.DisplayParameters;
import main.parameters.RecordParameters;

public class SequenceEncoder {

	public static List<BufferedImage> record = new ArrayList<BufferedImage>();

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

	public void screenShot(){
		int[] pixels = new int[DisplayParameters.WINDOW_WIDTH * DisplayParameters.WINDOW_HEIGHT];
		int bindex;
		
		ByteBuffer fb = ByteBuffer.allocateDirect(DisplayParameters.WINDOW_WIDTH * DisplayParameters.WINDOW_HEIGHT * 3);
		BufferedImage imageIn = new BufferedImage(DisplayParameters.WINDOW_WIDTH, DisplayParameters.WINDOW_HEIGHT,BufferedImage.TYPE_INT_RGB);
		
		GL11.glReadPixels(0, 0, DisplayParameters.WINDOW_WIDTH, DisplayParameters.WINDOW_HEIGHT, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, fb);

		for (int i=0; i < pixels.length; i++) {
			bindex = i * 3;
			pixels[i] =
					((fb.get(bindex) << 16))  +
					((fb.get(bindex+1) << 8))  +
					((fb.get(bindex+2) << 0));
		}

		imageIn.setRGB(0, 0, DisplayParameters.WINDOW_WIDTH, DisplayParameters.WINDOW_HEIGHT, pixels, 0 , DisplayParameters.WINDOW_WIDTH);

		AffineTransform at =  AffineTransform.getScaleInstance(1, -1);
		at.translate(0, -imageIn.getHeight(null));

		AffineTransformOp opRotated = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		BufferedImage imageOut = opRotated.filter(imageIn, null);

		record.add(scale(imageOut, RecordParameters.RESOLUTION_SCALE));
	}


	public static void makeVideo(int fps) throws IOException {

		AWTSequenceEncoder enc = AWTSequenceEncoder.createSequenceEncoder(new File("resources/recording/record.mp4"), fps);

		for(int i=0;i< record.size();++i)
		{
			BufferedImage image = record.get(i);
			enc.encodeImage(image);
		}

		enc.finish();
	}
}

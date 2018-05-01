package main.graphics.recorder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.jcodec.api.awt.AWTSequenceEncoder;

public class SequenceEncoder {
	
   public static void makeVideo(int fps) throws IOException {

       AWTSequenceEncoder enc = AWTSequenceEncoder.createSequenceEncoder(new File("resources/recording/record.mp4"), fps);

       for(int i=0;i< FrameBuffers.record.size();++i)
       {
           BufferedImage image = FrameBuffers.record.get(i);
           enc.encodeImage(image);
       }

       enc.finish();
   }
}

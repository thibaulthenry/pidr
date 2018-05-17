package main.graphics.renderer;

import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;

import main.graphics.guis.GuiTexture;
import main.graphics.guis.buttons.Button;
import main.graphics.models.RawModel;
import main.graphics.shaders.GuiShader;
import main.graphics.toolbox.Maths;

public class GuiRenderer {
	
	private final RawModel quad;
	private GuiShader shader;

	public GuiRenderer(GuiShader shader, Loader loader) {
		float[] positions = { -1, 1, -1, -1, 1, 1, 1, -1 };

		this.quad = loader.loadToVAO(positions, 2);
		this.shader = shader;
	}
	
	public void render(List<GuiTexture> guis) {
		shader.start();
		GL30.glBindVertexArray(quad.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		
		for (Button button : Button.getButtons()) {
			if (!guis.contains(button.getGuiTexture())) guis.add(button.getGuiTexture());
		}

		for (GuiTexture gui : guis) {
				GL13.glActiveTexture(GL13.GL_TEXTURE0);
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, gui.getTexture());
				Matrix4f matrix = null;
				if (!gui.isFullScreen()) {
					matrix = Maths.createTransformationMatrix(gui.getPosition(), new Vector2f(((float) Display.getHeight() / Display.getWidth()) * gui.getScale().x, gui.getScale().y));
				} else {
					matrix = Maths.createTransformationMatrix(gui.getPosition(), gui.getScale());
				}
 				shader.loadTransformation(matrix);
				GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, quad.getVertexCount());
		}
	
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_BLEND);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		shader.stop();
	}

	public void cleanUp() {
		shader.cleanUp();
	}

}

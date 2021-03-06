package main.graphics.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import main.graphics.entities.Camera;
import main.graphics.models.RawModel;
import main.graphics.shaders.SkyShader;
import main.parameters.TerrainManager;

public class SkyRenderer {
	
	private static final float[] VERTICES = {-TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE,  TerrainManager.SKY_SIZE, TerrainManager.SKY_SIZE, -TerrainManager.SKY_SIZE, TerrainManager.SKY_SIZE};
	private static final String[] TEXTURE_FILES = {"right", "left", "top", "bottom", "back", "front"};
	
	private RawModel cube;
	private int texture;
	private SkyShader shader;
	
	public SkyRenderer(SkyShader shader, Matrix4f projectionMatrix, Loader loader) {
		this.cube = loader.loadToVAO(VERTICES, 3);
		this.texture = loader.loadCubeMap(TEXTURE_FILES);
		this.shader = shader;
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}
	
	public void render(Camera camera, Vector3f skyColor) {
		shader.start();
		shader.loadViewMatrix(camera);
		shader.loadFogColour(skyColor.x, skyColor.y, skyColor.z);
		GL30.glBindVertexArray(cube.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, texture);
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, cube.getVertexCount());
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		shader.stop();
	}
	
	public void cleanUp() {
		shader.cleanUp();
	}
	
}

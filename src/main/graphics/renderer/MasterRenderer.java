package main.graphics.renderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import main.graphics.entities.Camera;
import main.graphics.entities.Entity;
import main.graphics.entities.Light;
import main.graphics.guis.GuiTexture;
import main.graphics.models.TexturedModel;
import main.graphics.shaders.GuiShader;
import main.graphics.shaders.SkyShader;
import main.graphics.shaders.StaticShader;
import main.graphics.shaders.TerrainShader;
import main.graphics.terrains.Terrain;

public class MasterRenderer {
	
	private static final float FOV = 70;
	public static final float NEAR_PLANE = 0.1f;
	public static final float FAR_PLANE = 8000f;
	
	public static final float FOG_DENSITY = 0.0003f;
	public static final float FOG_GRADIENT = 1.5f;
	
	public static final Vector3f SKY_COLOR = new Vector3f(0.29f,0.65f,0.9f);
	
	private Matrix4f projectionMatrix;
	private Loader loader;
	
	private StaticShader shader;
	private TerrainShader terrainShader;
	private GuiShader guiShader;
	private SkyShader skyShader;
	
	private EntityRenderer entityRenderer;
	private TerrainRenderer terrainRenderer;
	private GuiRenderer guiRenderer;
	private SkyRenderer skyRenderer;
	
	private Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();
	private List<Terrain> terrains = new ArrayList<Terrain>();
	private List<GuiTexture> guis = new ArrayList<GuiTexture>();
	private Light sun = new Light(new Vector3f(4000,2000,4000), new Vector3f(1,1,1));
	
	public MasterRenderer() {
		enableCulling();
		createProjectionMatrix();
		this.loader = new Loader();
		
		this.shader = new StaticShader();
		this.terrainShader = new TerrainShader();
		this.guiShader = new GuiShader();
		this.skyShader = new SkyShader();
		
		this.entityRenderer = new EntityRenderer(shader, projectionMatrix);
		this.terrainRenderer = new TerrainRenderer(terrainShader, projectionMatrix);
		this.guiRenderer = new GuiRenderer(guiShader, loader);
		this.skyRenderer = new SkyRenderer(skyShader, projectionMatrix, loader);
	}

	public void render(Camera camera) {
		prepare();
		
		shader.start();
		shader.loadSkyColour(SKY_COLOR);
		shader.loadFogDensity(FOG_DENSITY);
		shader.loadFogGradient(FOG_GRADIENT);
		shader.loadLights(sun);
		shader.loadViewMatrix(camera);
		entityRenderer.render(entities);
		shader.stop();
		
		terrainShader.start();
		terrainShader.loadSkyColour(SKY_COLOR);
		terrainShader.loadFogDensity(FOG_DENSITY);
		terrainShader.loadFogGradient(FOG_GRADIENT);
		terrainShader.loadLights(sun);
		terrainShader.loadViewMatrix(camera);
		terrainRenderer.render(terrains);
		terrainShader.stop();
		
		skyRenderer.render(camera, SKY_COLOR.x, SKY_COLOR.y, SKY_COLOR.z);
		
		guiRenderer.render(guis);
		
		guis.clear();
		terrains.clear();
		entities.clear();
	}
	
	public static void enableCulling() {
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}
	
	public static void disableCulling() {
		GL11.glDisable(GL11.GL_CULL_FACE);
	}
	
	public void processEntity(Entity entity) {
		TexturedModel entityModel = entity.getModel();
		List<Entity> batch = entities.get(entityModel);
		if (batch != null) {
			batch.add(entity);
		} else {
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			entities.put(entityModel, newBatch);
		}
	}

	public void processTerrain(Terrain terrain) {
		terrains.add(terrain);
	}
	
	public void processGui(GuiTexture gui) {
		guis.add(gui);
	}
	
	public void cleanUp() {
		shader.cleanUp();
		terrainShader.cleanUp();
		skyShader.cleanUp();
		guiShader.cleanUp();
		loader.cleanUp();
	}
	
	public void prepare() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(SKY_COLOR.x, SKY_COLOR.y, SKY_COLOR.z, 1);
	}
	
	private void createProjectionMatrix() {
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;

		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		projectionMatrix.m33 = 0;
	}
}

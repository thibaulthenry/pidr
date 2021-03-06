package main.graphics.shaders;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import main.graphics.entities.Camera;
import main.graphics.entities.Light;
import main.graphics.toolbox.Maths;

public class TerrainShader extends ShaderProgram {

	private static final String VERTEX_FILE = "/main/graphics/shaders/terrainVertexShader.txt";
	private static final String FRAGMENT_FILE = "/main/graphics/shaders/terrainFragmentShader.txt";

	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	private int location_shineDamper;
	private int location_reflectivity;
	private int location_lightColour;
	private int location_lightPosition;
	private int location_fogDensity;
	private int location_fogGradient;
	private int location_skyColour;
	private int location_backgroundTexture;
	private int location_rTexture;
	private int location_gTexture;
	private int location_bTexture;
	private int location_blendMap;

	public TerrainShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoordinates");
		super.bindAttribute(2, "normal");
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_lightPosition = super.getUniformLocation("lightPosition");
		location_lightColour = super.getUniformLocation("lightColour");
		location_shineDamper = super.getUniformLocation("shineDamper");
		location_reflectivity = super.getUniformLocation("reflectivity");
		location_fogDensity = super.getUniformLocation("fogDensity");
		location_fogGradient = super.getUniformLocation("fogGradient");
		location_skyColour = super.getUniformLocation("skyColour");
		location_backgroundTexture = super.getUniformLocation("backgroundTexture");
		location_rTexture = super.getUniformLocation("rTex");
		location_gTexture = super.getUniformLocation("gTex");
		location_bTexture = super.getUniformLocation("bTex");
		location_blendMap = super.getUniformLocation("blendMap");
		
		/*location_hasEntityColour = super.getUniformLocation("hasEntityColour");
		location_entityColour = super.getUniformLocation("entityColour");

		location_numberOfRows = super.getUniformLocation("numberOfRows");
		location_offset = super.getUniformLocation("offset");
		location_plane = super.getUniformLocation("plane");
		
		location_lightPosition = new int[MAX_LIGHTS];
		location_lightColour = new int[MAX_LIGHTS];
		location_attenuation = new int[MAX_LIGHTS];
		location_lightPosition = super.getUniformLocation("lightPosition[" + i + "]");
		location_lightColour = super.getUniformLocation("lightColour[" + i + "]");*/
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	public void loadProjectionMatrix(Matrix4f matrix) {
		super.loadMatrix(location_projectionMatrix, matrix);
	}
	
	public void loadViewMatrix(Camera camera) {
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}
	
	public void loadLights(Light light) {
		super.loadVector(location_lightPosition, light.getPosition());
		super.loadVector(location_lightColour, light.getColour());
	}
	
	public void loadShineVariables(float damper, float reflectivity) {
		super.loadFloat(location_shineDamper, damper);
		super.loadFloat(location_reflectivity, reflectivity);
	}
	
	public void loadFogDensity(float d) {
		super.loadFloat(location_fogDensity, d);
	}
	
	public void loadFogGradient(float g) {
		super.loadFloat(location_fogGradient, g);
	}
	
	public void loadSkyColour(Vector3f rgb) {
		super.loadVector(location_skyColour, rgb);
	}
	
	public void connectTextureUnits() {
		super.loadInt(location_backgroundTexture, 0);
		super.loadInt(location_rTexture, 1);
		super.loadInt(location_gTexture, 2);
		super.loadInt(location_bTexture, 3);
		super.loadInt(location_blendMap, 4);
	}
	
	
	/*
	public void loadClipPlane(Vector4f plane) {
		super.loadVector(location_plane, plane);
	}

	public void loadLights(List<Light> lights) {
		for (int i=0;i<MAX_LIGHTS;i++) {
			if(i<lights.size()) {
				super.loadVector(location_lightPosition[i], lights.get(i).getPosition());
				super.loadVector(location_lightColour[i], lights.get(i).getColour());
				super.loadVector(location_attenuation[i], lights.get(i).getAttenuation());
			} else {
				super.loadVector(location_lightPosition[i], new Vector3f(0, 0, 0));
				super.loadVector(location_lightColour[i], new Vector3f(0, 0, 0));
				super.loadVector(location_attenuation[i], new Vector3f(1, 0, 0));
			}
		}
	}

	public void loadFakeUseLightingVariable(boolean useFake) {
		super.loadBoolean(location_useFakeLighting, useFake);
	}


	
	public void loadHasEntityColour(boolean useEntityColour) {
		super.loadBoolean(location_hasEntityColour, useEntityColour);
	}

	public void loadEntityColour(float r, float g, float b, float a) {
		super.loadVector(location_entityColour, new Vector4f(r,g,b,a));
	}
	

	
	public void loadNumberOfRows(int numberOfRows) {
		super.loadFloat(location_numberOfRows, numberOfRows);
	}
	
	public void loadOffset(float x, float y) {
		super.loadVector(location_offset, new Vector2f(x, y));
	}
	
	*/
		

}

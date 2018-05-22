package main.graphics.shaders;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import main.graphics.entities.Camera;
import main.graphics.toolbox.Maths;

public class SkyShader extends ShaderProgram {

	private static final String VERTEX_FILE = "/main/graphics/shaders/skyVertexShader.txt";
	private static final String FRAGMENT_FILE = "/main/graphics/shaders/skyFragmentShader.txt";
	
	private int location_projectionMatrix;
	private int location_viewMatrix;
	private int location_fogColour;
	
	public SkyShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}
	
	public void loadProjectionMatrix(Matrix4f matrix){
		super.loadMatrix(location_projectionMatrix, matrix);
	}

	public void loadViewMatrix(Camera camera){
		Matrix4f matrix = Maths.createViewMatrix(camera);
        matrix.m30 = 0;
        matrix.m31 = 0;
        matrix.m32 = 0;
		super.loadMatrix(location_viewMatrix, matrix);
	}
	
	public void loadFogColour(float r, float g, float b) {
		super.loadVector(location_fogColour, new Vector3f(r, g, b));
	}
	
	@Override
	protected void getAllUniformLocations() {
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_fogColour = super.getUniformLocation("fogColour");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}

}

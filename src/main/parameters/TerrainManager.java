package main.parameters;

import org.lwjgl.util.vector.Vector3f;

import main.graphics.terrains.Terrain;

public class TerrainManager {
	
	public static String HEIGHTMAP_FILENAME= "heightmap";

	public static final float TERRAIN_SIZE = 6000f;
	public static final float TERRAIN_MAX_HEIGHT = 150f;
	
	public static final float SKY_SIZE = 4000f;
	
	public static final float NEAR_PLANE = 0.1f;
	public static final float FAR_PLANE = 6000f;
	
	public static final Vector3f SKY_COLOR = new Vector3f(0.29f,0.65f,0.9f);
	public static final Vector3f SUN_COLOR = new Vector3f(1, 1, 1);
	
	
	public static Terrain terrain = new Terrain(0,0, TextureManager.terrainTexturePack, TextureManager.blendmap, "terrain/" + HEIGHTMAP_FILENAME);
	
	public final static Vector3f terrainCenter = new Vector3f(TERRAIN_SIZE/2,0,TERRAIN_SIZE/2);

}

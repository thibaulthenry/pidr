package main.parameters;

import main.graphics.terrains.Terrain;

public class TerrainManager {
	
	public final static String HEIGHTMAP_FILENAME= "heightmap";

	public static final float TERRAIN_SIZE = 8000f;
	public static final float TERRAIN_MAX_HEIGHT = 50f;
	
	public static final float SKY_SIZE = 5000f;
	
	public static Terrain terrain = new Terrain(0,0, GuiManager.terrainTexturePack, GuiManager.blendmap, "terrain\\" + HEIGHTMAP_FILENAME);

}

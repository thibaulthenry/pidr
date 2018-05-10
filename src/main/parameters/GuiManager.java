package main.parameters;

import main.graphics.renderer.Loader;
import main.graphics.textures.TerrainTexture;
import main.graphics.textures.TerrainTexturePack;

public class GuiManager {
	
	private static final Loader loader = new Loader();
	
	/* Buttons */
	
	public final static int recordTexture = loader.loadTexture("buttons/rightmenu/record");
	public final static int recordHoverTexture = loader.loadTexture("buttons/rightmenu/recordHover");
	
	/* Entity */
	
	public final static int droneTexture = loader.loadTexture("entities/white");
	public final static int rotorTexture = loader.loadTexture("entities/white");
	public final static int sphereTexture = loader.loadTexture("entities/red");
	
	/* Terrain */
	
	private final static TerrainTexture backComponent = new TerrainTexture(loader.loadTexture("terrain/backComponent"));
	private final static TerrainTexture redComponent = new TerrainTexture(loader.loadTexture("terrain/redComponent"));
	private final static TerrainTexture greenComponent = new TerrainTexture(loader.loadTexture("terrain/greenComponent"));
	private final static TerrainTexture blueComponent = new TerrainTexture(loader.loadTexture("terrain/blueComponent"));
	public final static TerrainTexturePack terrainTexturePack= new TerrainTexturePack(backComponent, redComponent, greenComponent, blueComponent);
	public final static TerrainTexture blendmap = new TerrainTexture(loader.loadTexture("terrain/blendmap"));

}

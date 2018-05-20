package main.parameters;

import main.graphics.renderer.Loader;
import main.graphics.textures.TerrainTexture;
import main.graphics.textures.TerrainTexturePack;

public class TextureManager {
	
	private static final Loader loader = new Loader();
	
	/* Settings */
	
	public final static int onTexture = loader.loadTexture("buttons/menu/on");
	public final static int offTexture = loader.loadTexture("buttons/menu/off");
	public final static int offHoverTexture = loader.loadTexture("buttons/menu/offHover");
	
	/* Main Menu */
	
	public final static int Main_menu = loader.loadTexture("interface/Main_menu");
	public final static int Main_menu_back = loader.loadTexture("interface/Main_menu_back");
	public final static int settings_no_buttons = loader.loadTexture("interface/settings_no_buttons");
	
	/* Record */ 
	
	public final static int encodingTexture = loader.loadTexture("record/encoding");
	public final static int analysingTexture = loader.loadTexture("record/analysing");
	
	/* Buttons */
	
	public final static int settingsTexture = loader.loadTexture("buttons/menu/settings");
	public final static int settingsHoverTexture = loader.loadTexture("buttons/menu/settingsHover");
	public final static int settingsClickTexture = loader.loadTexture("buttons/menu/settingsClick");
	
	public final static int startTexture = loader.loadTexture("buttons/menu/start");
	public final static int startHoverTexture = loader.loadTexture("buttons/menu/startHover");
	public final static int startClickTexture = loader.loadTexture("buttons/menu/startClick");
	
	public final static int recordTexture = loader.loadTexture("buttons/rightmenu/record");
	public final static int recordHoverTexture = loader.loadTexture("buttons/rightmenu/recordHover");
	
	/* Entity */
	
	public final static int droneTexture = loader.loadTexture("entities/white");
	public final static int rotorTexture = loader.loadTexture("entities/white");
	public final static int sphereTexture = loader.loadTexture("entities/red");
	public final static int voidTexture = loader.loadTexture("entities/void");
	
	/* Terrain */
	
	private final static TerrainTexture backComponent = new TerrainTexture(loader.loadTexture("terrain/backComponent"));
	private final static TerrainTexture redComponent = new TerrainTexture(loader.loadTexture("terrain/redComponent"));
	private final static TerrainTexture greenComponent = new TerrainTexture(loader.loadTexture("terrain/greenComponent"));
	private final static TerrainTexture blueComponent = new TerrainTexture(loader.loadTexture("terrain/blueComponent"));
	public final static TerrainTexturePack terrainTexturePack= new TerrainTexturePack(backComponent, redComponent, greenComponent, blueComponent);
	public final static TerrainTexture blendmap = new TerrainTexture(loader.loadTexture("terrain/blendmap"));

}

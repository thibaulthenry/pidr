package main.parameters;

import main.graphics.renderer.Loader;
import main.graphics.textures.TerrainTexture;
import main.graphics.textures.TerrainTexturePack;

public class TextureManager {
	
	private static final Loader loader = new Loader();
	
	/* Lateral Menu Button */ 
	
	public final static int recordTexture = loader.loadTexture("buttons/lateralMenu/record");
	public final static int recordHoverTexture = loader.loadTexture("buttons/lateralMenu/recordHover");
	public final static int recordClickTexture = loader.loadTexture("buttons/lateralMenu/recordClick");
	
	public final static int to1speedTexture = loader.loadTexture("buttons/lateralMenu/to1speed");
	public final static int to1speedHoverTexture = loader.loadTexture("buttons/lateralMenu/to1speedHover");
	public final static int to1speedClickTexture = loader.loadTexture("buttons/lateralMenu/to1speedClick");
	
	public final static int upSpeedTexture = loader.loadTexture("buttons/lateralMenu/upSpeed");
	public final static int upSpeedHoverTexture = loader.loadTexture("buttons/lateralMenu/upSpeedHover");
	public final static int upSpeedClickTexture = loader.loadTexture("buttons/lateralMenu/upSpeedClick");
	
	public final static int mountainTexture = loader.loadTexture("buttons/lateralMenu/mountain");
	public final static int mountainHoverTexture = loader.loadTexture("buttons/lateralMenu/mountainHover");
	public final static int mountainClickTexture = loader.loadTexture("buttons/lateralMenu/mountainClick");
	
	public final static int flatTexture = loader.loadTexture("buttons/lateralMenu/flat");
	public final static int flatHoverTexture = loader.loadTexture("buttons/lateralMenu/flatHover");
	public final static int flatClickTexture = loader.loadTexture("buttons/lateralMenu/flatClick");
	
	public final static int pauseTexture = loader.loadTexture("buttons/lateralMenu/pause");
	public final static int pauseHoverTexture = loader.loadTexture("buttons/lateralMenu/pauseHover");
	public final static int pauseClickTexture = loader.loadTexture("buttons/lateralMenu/pauseClick");
	
	public final static int cameraTexture = loader.loadTexture("buttons/lateralMenu/camera");
	public final static int cameraHoverTexture = loader.loadTexture("buttons/lateralMenu/cameraHover");
	public final static int cameraClickTexture = loader.loadTexture("buttons/lateralMenu/cameraClick");
	
	public final static int cameraLockedTexture = loader.loadTexture("buttons/lateralMenu/cameraLocked");
	public final static int cameraLockedHoverTexture = loader.loadTexture("buttons/lateralMenu/cameraLockedHover");
	public final static int cameraLockedClickTexture = loader.loadTexture("buttons/lateralMenu/cameraLockedClick");
	
	public final static int beaconTexture = loader.loadTexture("buttons/lateralMenu/beacon");
	public final static int beaconHoverTexture = loader.loadTexture("buttons/lateralMenu/beaconHover");
	public final static int beaconClickTexture = loader.loadTexture("buttons/lateralMenu/beaconClick");
	
	/* Settings */
	
	public final static int onTexture = loader.loadTexture("buttons/menu/on");
	public final static int offTexture = loader.loadTexture("buttons/menu/off");
	public final static int offHoverTexture = loader.loadTexture("buttons/menu/offHover");
	
	/* Main Menu */
	
	public final static int Main_menu = loader.loadTexture("interface/Main_menu");
	public final static int Main_menu_back = loader.loadTexture("interface/Main_menu_back");
	public final static int settings_no_buttons = loader.loadTexture("interface/settings_no_buttons");
	
	/* Lateral Menu */
	
	public final static int lateral_Panel = loader.loadTexture("interface/lateral_menu");
	
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

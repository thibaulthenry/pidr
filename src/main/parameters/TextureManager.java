package main.parameters;

import main.graphics.renderer.Loader;
import main.graphics.textures.TerrainTexture;
import main.graphics.textures.TerrainTexturePack;

public class TextureManager {
	
	private static final Loader loader = new Loader();
	
	/* Lateral Menu Button */ 
	
	public final static int recordTexture = loader.loadTexture("buttons/lateralMenu/record", true);
	public final static int recordHoverTexture = loader.loadTexture("buttons/lateralMenu/recordHover", true);
	public final static int recordClickTexture = loader.loadTexture("buttons/lateralMenu/recordClick", true);
	
	public final static int to1speedTexture = loader.loadTexture("buttons/lateralMenu/to1speed", true);
	public final static int to1speedHoverTexture = loader.loadTexture("buttons/lateralMenu/to1speedHover", true);
	public final static int to1speedClickTexture = loader.loadTexture("buttons/lateralMenu/to1speedClick", true);
	
	public final static int upSpeedTexture = loader.loadTexture("buttons/lateralMenu/upSpeed", true);
	public final static int upSpeedHoverTexture = loader.loadTexture("buttons/lateralMenu/upSpeedHover", true);
	public final static int upSpeedClickTexture = loader.loadTexture("buttons/lateralMenu/upSpeedClick", true);
	
	public final static int mountainTexture = loader.loadTexture("buttons/lateralMenu/mountain", true);
	public final static int mountainHoverTexture = loader.loadTexture("buttons/lateralMenu/mountainHover", true);
	public final static int mountainClickTexture = loader.loadTexture("buttons/lateralMenu/mountainClick", true);
	
	public final static int flatTexture = loader.loadTexture("buttons/lateralMenu/flat", true);
	public final static int flatHoverTexture = loader.loadTexture("buttons/lateralMenu/flatHover", true);
	public final static int flatClickTexture = loader.loadTexture("buttons/lateralMenu/flatClick", true);
	
	public final static int pauseTexture = loader.loadTexture("buttons/lateralMenu/pause", true);
	public final static int pauseHoverTexture = loader.loadTexture("buttons/lateralMenu/pauseHover", true);
	public final static int pauseClickTexture = loader.loadTexture("buttons/lateralMenu/pauseClick", true);
	
	public final static int cameraTexture = loader.loadTexture("buttons/lateralMenu/camera", true);
	public final static int cameraHoverTexture = loader.loadTexture("buttons/lateralMenu/cameraHover", true);
	public final static int cameraClickTexture = loader.loadTexture("buttons/lateralMenu/cameraClick", true);
	
	public final static int cameraLockedTexture = loader.loadTexture("buttons/lateralMenu/cameraLocked", true);
	public final static int cameraLockedHoverTexture = loader.loadTexture("buttons/lateralMenu/cameraLockedHover", true);
	public final static int cameraLockedClickTexture = loader.loadTexture("buttons/lateralMenu/cameraLockedClick", true);
	
	public final static int beaconTexture = loader.loadTexture("buttons/lateralMenu/beacon", true);
	public final static int beaconHoverTexture = loader.loadTexture("buttons/lateralMenu/beaconHover", true);
	public final static int beaconClickTexture = loader.loadTexture("buttons/lateralMenu/beaconClick", true);
	
	public final static int resetTexture = loader.loadTexture("buttons/lateralMenu/reset", true);
	public final static int resetHoverTexture = loader.loadTexture("buttons/lateralMenu/resetHover", true);
	public final static int resetClickTexture = loader.loadTexture("buttons/lateralMenu/resetClick", true);
	
	/* Settings */
	
	public final static int onTexture = loader.loadTexture("buttons/menu/on", true);
	public final static int offTexture = loader.loadTexture("buttons/menu/off", true);
	public final static int offHoverTexture = loader.loadTexture("buttons/menu/offHover", true);
	
	/* Main Menu */
	
	public final static int menuHeaderFooter1920 = loader.loadTexture("interface/menuHeaderFooter1920", true);
	public final static int menuBack1920 = loader.loadTexture("interface/menuBack1920", true);
	public final static int settingsBack1920 = loader.loadTexture("interface/settingsBack1920", true);
	public final static int menuHeaderFooter1280 = loader.loadTexture("interface/menuHeaderFooter1280", true);
	public final static int menuBack1280 = loader.loadTexture("interface/menuBack1280", true);
	public final static int settingsBack1280 = loader.loadTexture("interface/settingsBack1280", true);
	public final static int menuHeaderFooter640 = loader.loadTexture("interface/menuHeaderFooter640", true);
	public final static int menuBack640 = loader.loadTexture("interface/menuBack640", true);
	public final static int settingsBack640 = loader.loadTexture("interface/settingsBack640", true);
	
	/* Lateral Menu */
	
	public final static int lateralBar1920 = loader.loadTexture("interface/lateralBar1920", true);
	public final static int lateralBar1280 = loader.loadTexture("interface/lateralBar1280", true);
	public final static int lateralBar640 = loader.loadTexture("interface/lateralBar640", true);
	
	/* Record */ 
	
	public final static int encodingTexture = loader.loadTexture("record/encoding", true);
	public final static int analysingTexture = loader.loadTexture("record/analysing", true);
	
	/* Buttons */
	
	public final static int settingsTexture = loader.loadTexture("buttons/menu/settings", true);
	public final static int settingsHoverTexture = loader.loadTexture("buttons/menu/settingsHover", true);
	public final static int settingsClickTexture = loader.loadTexture("buttons/menu/settingsClick", true);
	
	public final static int startTexture = loader.loadTexture("buttons/menu/start", true);
	public final static int startHoverTexture = loader.loadTexture("buttons/menu/startHover", true);
	public final static int startClickTexture = loader.loadTexture("buttons/menu/startClick", true);
	
	/* Entity */
	
	public final static int droneTexture = loader.loadTexture("entities/white", false);
	public final static int rotorTexture = loader.loadTexture("entities/white", false);
	public final static int sphereTexture = loader.loadTexture("entities/red", false);
	public final static int voidTexture = loader.loadTexture("entities/void", false);
	
	/* Terrain */
	
	private final static TerrainTexture backComponent = new TerrainTexture(loader.loadTexture("terrain/backComponent", false));
	private final static TerrainTexture redComponent = new TerrainTexture(loader.loadTexture("terrain/redComponent", false));
	private final static TerrainTexture greenComponent = new TerrainTexture(loader.loadTexture("terrain/greenComponent", false));
	private final static TerrainTexture blueComponent = new TerrainTexture(loader.loadTexture("terrain/blueComponent", false));
	public final static TerrainTexturePack terrainTexturePack= new TerrainTexturePack(backComponent, redComponent, greenComponent, blueComponent);
	public final static TerrainTexture blendmap = new TerrainTexture(loader.loadTexture("terrain/blendmap", false));

}

package me.finz0.osiris.module.modules.gui;

// Java.
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.*;

// Finz0.
import me.finz0.osiris.module.ModuleManager;
import me.finz0.osiris.settings.Setting;
import me.finz0.osiris.module.Module;
import me.finz0.osiris.OsirisMod;

// Rina.
import rina.hud.OsirisPlusHUD;
import rina.util.TurokString;

/**
 * @author Rina!
 *
 * Created by Rina in 09/08/2020.
 *
 **/
public class OsirisPlusHUDModule extends Module {
	List<OsirisPlusHUD> pretty_huds_left_up;

	public OsirisPlusHUDModule() {
		super("HUD", Category.GUI, "HUD Config.");

		OsirisMod.getInstance().settingsManager.rSetting(new Setting("info", this, "HUD String", "GUINameColor"));
		OsirisMod.getInstance().settingsManager.rSetting(new Setting("Red", this, 255, 0, 255, true, "HUDStringRed"));
		OsirisMod.getInstance().settingsManager.rSetting(new Setting("Green", this, 255, 0, 255, true, "HUDStringGreen"));
		OsirisMod.getInstance().settingsManager.rSetting(new Setting("Blue", this, 255, 0, 255, true, "HUDStringBlue"));
	
		this.pretty_huds_left_up = new ArrayList<>();
	}

	@Override
	public void onUpdate() {
//		this.pretty_huds_left_up = ModuleManager.getHUDList().stream().filter(hud -> ((Module) hud).isEnabled())
//																	  .filter(hud -> hud.isDockIn("LeftUp")).collect(Collectors.toList());
//		
//
//		int y_position_left_up = 1;
//		for (OsirisPlusHUD huds : this.pretty_huds_left_up) {
//			huds.x = 1;
//			huds.y = y_position_left_up;
//
//			y_position_left_up = huds.h + 1;
//		}
	}
}
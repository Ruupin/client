package me.finz0.osiris.module.modules.gui;

// Java.
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.*;

// Finz0.
import me.finz0.osiris.module.ModuleManager;
import me.finz0.osiris.settings.Setting;
import me.finz0.osiris.module.Module;

// Rina.
import rina.hud.OsirisPlusHUD;
import rina.util.TurokString;

/**
 * @author Rina!
 *
 * Created by Rina in 08/08/2020.
 *
 **/
public class HUDArrayList extends OsirisPlusHUD {
	Setting rgb_effect;

	List<Module> pretty_modules;

	public HUDArrayList() {
		super("ArrayList", "Show modules enabled.");

		rgb_effect = addSetting(new Setting("RGB", (Module) this, false, "ArrayListHUDRGB"));

		pretty_modules = new ArrayList<>();

		// Make this for side setting add for last setting. And use this.
		releaseHUDAsModule();
	}

	@Override
	public void onRenderHUD() {
		Comparator<Module> comparator = (module_1, module_2) -> {
			String module_1_string = module_1.getName() + (module_1.getHudInfo().equals("") == true ? "" : gray_color + " [" + reset_color + module_1.getHudInfo() + gray_color + "]" + reset_color);
			String module_2_string = module_2.getName() + (module_2.getHudInfo().equals("") == true ? "" : gray_color + " [" + reset_color + module_2.getHudInfo() + gray_color + "]" + reset_color);

			float diff = getStringWidth(module_2_string) - getStringWidth(module_1_string);

			if (isDockIn("LeftUp") || isDockIn("RightUp")) {
				return diff != 0 ? (int) diff : module_2_string.compareTo(module_1_string);
			} else {
				return (int) diff;
			}
		};

		if (isDockIn("LeftUp") || isDockIn("RightUp")) {
			pretty_modules = ModuleManager.getModules().stream().filter(module -> module.isEnabled()).sorted(comparator).collect(Collectors.toList());
		} else if (isDockIn("LeftDown") || isDockIn("RightDown")) {
			pretty_modules = ModuleManager.getModules().stream().filter(module -> module.isEnabled()).sorted(Comparator.comparing(module -> getStringWidth(module.getName() + (module.getHudInfo().equals("") == true ? "" : gray_color + " [" + reset_color + module.getHudInfo() + gray_color + "]" + reset_color)))).collect(Collectors.toList());
		}

		int position_update_y = 0;

		for (Module modules : pretty_modules) {
			String module_name = (
				modules.getName() + (modules.getHudInfo().equals("") == true ? "" : gray_color + " [" + reset_color + modules.getHudInfo() + gray_color + "]" + reset_color)
			);

			if (rgb_effect.getValBoolean()) {
				renderString(module_name, 0, position_update_y, r_rgb, g_rgb, b_rgb);
			} else {
				renderString(module_name, 0, position_update_y);
			}

			position_update_y += getStringHeight(module_name);

			if (getStringWidth(module_name) >= this.w) {
				this.w = getStringWidth(module_name) + 2;
			}

			this.h = position_update_y;
		}
	}
}
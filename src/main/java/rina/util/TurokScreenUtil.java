package rina.util;

// Minecraft.
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.Minecraft;

/**
 * @author Rina!
 *
 * Created by Rina in 27/07/2020.
 *
 **/
public class TurokScreenUtil {
	public static ScaledResolution scl_minecraft_screen = new ScaledResolution(Minecraft.getMinecraft());

	public static int getScreenWidth() {
		return scl_minecraft_screen.getScaledWidth();
	}

	public static int getScreenHeight() {
		return scl_minecraft_screen.getScaledHeight();
	}
}
package me.finz0.osiris.module.modules.player;

import me.finz0.osiris.OsirisMod;
import me.finz0.osiris.module.Module;
import me.finz0.osiris.command.Command;
import me.finz0.osiris.util.TpsUtils;
import me.finz0.osiris.settings.Setting;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class WeaknessAlert extends Module {
    public WeaknessAlert() {
        super("WeaknessAlert", Category.PLAYER);
    }

    private boolean hasAlertedPlayer = false;
    DecimalFormat format1 = new DecimalFormat("0");
    DecimalFormat format2 = new DecimalFormat("00");
    Setting announce;
    Setting mode;
    public void setup(){
        ArrayList<String> modes = new ArrayList<>();
        modes.add("Simple");
        modes.add("Advanced");
        OsirisMod.getInstance().settingsManager.rSetting(mode = new Setting("Mode", this, "Simple", modes, "WeaknessAlertMode"));
    }
    public void onUpdate() {
        if (mc.player.isPotionActive(MobEffects.WEAKNESS) && !hasAlertedPlayer) {
            if(mode.getValString().equalsIgnoreCase("advanced")) {
                PotionEffect weakness = mc.player.getActivePotionEffect(MobEffects.WEAKNESS);
                        int amplifier = weakness.getAmplifier() + 1;
                        double duration = weakness.getDuration() / TpsUtils.getTickRate();
                        double p1 = duration % 60;
                        double p2 = duration / 60;
                        double p3 = p2 % 60;
                        String minutes = format1.format(p3);
                        String seconds = format2.format(p1);
                        Command.sendClientMessage("You have Weakness " + amplifier + " for " + minutes + ":" + seconds);
            }
            if(mode.getValString().equalsIgnoreCase("simple")) {
                Command.sendClientMessage("You have Weakness");
            }
            hasAlertedPlayer = true;
        }
        if (!mc.player.isPotionActive(MobEffects.WEAKNESS) && hasAlertedPlayer){
            hasAlertedPlayer = false;
            Command.sendClientMessage("You no longer have Weakness");
        }
    }
}

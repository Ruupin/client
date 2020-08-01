package me.finz0.osiris.module.modules.combat;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

import me.finz0.osiris.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.util.text.TextComponentString;

public class Strength2Detect extends Module {
    public Strength2Detect() {
        super("StrengthDetect", Category.COMBAT, "Alerts you when a player has Strength");
    }
    private Set<EntityPlayer> str = Collections.newSetFromMap(new WeakHashMap());
    public static final Minecraft mc = Minecraft.getMinecraft();

    public void onUpdate() {
        for (EntityPlayer player : Strength2Detect.mc.world.playerEntities) {
            if (player.equals(Strength2Detect.mc.player)) continue;
            if (player.isPotionActive(MobEffects.STRENGTH) && !this.str.contains(player)) {
                Minecraft.getMinecraft().player.sendMessage(new TextComponentString(player.getDisplayNameString() + " Has Strength"));
                this.str.add(player);
            }
            if (!this.str.contains(player) || player.isPotionActive(MobEffects.STRENGTH)) continue;
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString((player.getDisplayNameString() + " Has Ran Out Of Strength")));
            this.str.remove(player);
        }
    }
}
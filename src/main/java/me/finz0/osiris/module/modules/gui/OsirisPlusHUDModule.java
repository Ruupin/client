package me.finz0.osiris.module.modules.gui;

import me.finz0.osiris.settings.Setting;
import me.finz0.osiris.OsirisMod;
import me.finz0.osiris.command.Command;
import me.finz0.osiris.module.Module;
import me.finz0.osiris.module.ModuleManager;
import me.finz0.osiris.module.modules.chat.Announcer;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class OsirisPlusHUDModule extends Module {
    public OsirisPlusHUDModule() {
        super("HUD", Category.GUI, "HUD Config.");
    }
}
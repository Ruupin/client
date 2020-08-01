package me.finz0.osiris.module.modules.misc;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.finz0.osiris.OsirisRPC;
import me.finz0.osiris.command.Command;
import me.finz0.osiris.module.Module;

public class RpcModule extends Module {
    public RpcModule() {
        super("DiscordRPC", Category.MISC, "Shows the client off in Discord!");
        setDrawn(false);
    }

    public void onEnable(){
        OsirisRPC.init();
        if(mc.player != null)
            Command.sendClientMessage(ChatFormatting.WHITE + "Discord RPC " + ChatFormatting.GREEN + "started!");
    }

    public void onDisable(){
        Command.sendClientMessage(ChatFormatting.WHITE + "You need to" + ChatFormatting.RED + " restart your game " + ChatFormatting.WHITE + "to disable the RPC.");
    }
}

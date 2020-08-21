package me.finz0.osiris.module;

import me.finz0.osiris.event.events.RenderEvent;
import me.finz0.osiris.module.modules.combat.*;
import me.finz0.osiris.module.modules.movement.*;
import me.finz0.osiris.module.modules.misc.*;
import me.finz0.osiris.module.modules.chat.*;
import me.finz0.osiris.module.modules.gui.*;
import me.finz0.osiris.module.modules.player.*;
import me.finz0.osiris.module.modules.render.*;
import me.finz0.osiris.util.OsirisTessellator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.stream.Collectors;

import rina.hud.OsirisPlusHUD;

public class ModuleManager {
    public static ArrayList<Module> modules;
    public static ArrayList<OsirisPlusHUD> hud_list;

    public ModuleManager(){
        modules  = new ArrayList<>();
        hud_list = new ArrayList<>();

        //Combat
        addMod(new AutoArmor());
        addMod(new KillAura());
		addMod(new MultiTask());
        addMod(new AutoCrystal());
		addMod(new AutoCrystalPlus());
        addMod(new AutoFeetPlace());
        addMod(new AutoOffhand());
        addMod(new AutoTrap());
		addMod(new AutoTotem());
        addMod(new HoleFill());
		addMod(new HoleTP());
        addMod(new Criticals());
        addMod(new SmartOffhand());
        addMod(new BedAura());
        addMod(new BowSpam());
        addMod(new AutoWeb());
        addMod(new CombatInfo());
        //Player
		addMod(new AutoReplanish());
        addMod(new Blink());
        addMod(new PortalGodMode());
        addMod(new FastUse());
        addMod(new NoSwing());
        addMod(new SpeedMine());
        addMod(new NoInteract());
        //Movement
        addMod(new Sprint());
        addMod(new Velocity());
        addMod(new NoPush());
        addMod(new GuiMove());
        addMod(new ElytraFly());
        addMod(new NoSlow());
        addMod(new Speed());
        //Misc
        addMod(new Timer());
        addMod(new NoEntityTrace());
        addMod(new XCarry());
        addMod(new AutoNomadHut());
        addMod(new RpcModule());
        addMod(new Notifications());
        addMod(new LogoutSpots());
        addMod(new AutoRespawn());
        addMod(new MiddleClickFriends());
        addMod(new DeathWaypoint());
        addMod(new ClinetTimer());
        addMod(new TotemPopCounter());
        addMod(new BreakTweaks());
        addMod(new FakePlayer());
        //Chat
        addMod(new VisualRange());
        addMod(new BetterChat());
        addMod(new ToggleMsgs());
        addMod(new Announcer());
        addMod(new UwuChat());
        addMod(new AutoGG());
        addMod(new DotGodSpammer());
        addMod(new Spammer());
        addMod(new AutoReply());
        addMod(new Welcomer());
        addMod(new ColorChat());
        addMod(new ChatSuffix());
        addMod(new KettuLinuxDupe());
        addMod(new ChatTimeStamps());
        //Render
        addMod(new GlowESP());
        addMod(new CameraClip());
        addMod(new Brightness());
        addMod(new LowHands());
        addMod(new HoleESP());
        addMod(new StorageESP());
        addMod(new BlockHighlight());
        addMod(new NoRender());
        addMod(new Tracers());
        addMod(new CsgoESP());
        addMod(new CapesModule());
        addMod(new HitboxESP());
        addMod(new FovModule());
        addMod(new BoxESP());
        addMod(new TabGui());
        addMod(new ShulkerPreview());
        addMod(new CityESP());

        // Gui.
        addMod(new OsirisPlusGUIModule());
        addMod(new OsirisPlusHUDModule());
        addMod(new OsirisPlusHUDEditor());

        // HUD.
        addHUD(new HUDArrayList());
        addHUD(new HUDCoordinates());
        addHUD(new HUDGUIWatermark());
        addHUD(new HUDWatermark());
        addHUD(new HUDInventory());
        addHUD(new HUDArmor());
        addHUD(new HUDTotem());
        addHUD(new HUDGoldenApple());
        addHUD(new HUDExperienceBottle());
        addHUD(new HUDCrystal());
        addHUD(new HUDPlayer());
        addHUD(new HUDBps());
        addHUD(new HUDFPS());
        addHUD(new HUDServerInfo());
        addHUD(new HUDPvpInfo());
    }

    public static void addMod(Module m){
        modules.add(m);
    }

    public static void addHUD(OsirisPlusHUD hud){
        modules.add((Module) hud);
        hud_list.add(hud);
    }

    public static void onUpdate() {
        modules.stream().filter(Module::isEnabled).forEach(Module::onUpdate);
    }

    public static void onRender() {
        modules.stream().filter(Module::isEnabled).forEach(Module::onRender);
    }

    public static void onWorldRender(RenderWorldLastEvent event) {
        Minecraft.getMinecraft().profiler.startSection("osiris");

        Minecraft.getMinecraft().profiler.startSection("setup");
//        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        GlStateManager.disableDepth();

        GlStateManager.glLineWidth(1f);
        Vec3d renderPos = Surround.getInterpolatedPos(Minecraft.getMinecraft().player, event.getPartialTicks());

        RenderEvent e = new RenderEvent(OsirisTessellator.INSTANCE, renderPos, event.getPartialTicks());
        e.resetTranslation();
        Minecraft.getMinecraft().profiler.endSection();

        modules.stream().filter(module -> module.isEnabled()).forEach(module -> {
            Minecraft.getMinecraft().profiler.startSection(module.getName());
            module.onWorldRender(e);
            Minecraft.getMinecraft().profiler.endSection();
        });

        Minecraft.getMinecraft().profiler.startSection("release");
        GlStateManager.glLineWidth(1f);

        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.enableCull();
//        GlStateManager.popMatrix();
        OsirisTessellator.releaseGL();
        Minecraft.getMinecraft().profiler.endSection();

        Minecraft.getMinecraft().profiler.endSection();
    }


    public static ArrayList<Module> getModules() {
        return modules;
    }

    public static ArrayList<OsirisPlusHUD> getHUDList() {
        return hud_list;
    }

    public static ArrayList<Module> getModulesInCategory(Module.Category c){
        ArrayList<Module> list = (ArrayList<Module>) getModules().stream().filter(m -> m.getCategory().equals(c)).collect(Collectors.toList());
        return list;
    }

    public static void onBind(int key) {
        if (key == 0 || key == Keyboard.KEY_NONE) return;
        modules.forEach(module -> {
            if(module.getBind() == key){
                module.toggle();
            }
        });
    }

    public static OsirisPlusHUD getHUDByName(String name) {
        OsirisPlusHUD hud_requested = null;

        for (OsirisPlusHUD huds : getHUDList()) {
            if (huds.getName().equals(name)) {
                hud_requested = huds;

                break;
            }
        }

        return hud_requested;
    }

    public static Module getModuleByName(String name){
        Module m = getModules().stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        return m;
    }

    public static boolean isModuleEnabled(String name){
        Module m = getModules().stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        return m.isEnabled();
    }
    public static boolean isModuleEnabled(Module m){
        return m.isEnabled();
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  org.lwjgl.input.Keyboard
 */
package org.fenci.fencingfplus2;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.fenci.fencingfplus2.clickgui.ClickGui;
import org.fenci.fencingfplus2.managers.CommandManager;
import org.fenci.fencingfplus2.managers.FriendManager;
import org.fenci.fencingfplus2.managers.KitManager;
import org.fenci.fencingfplus2.managers.PopManager;
import org.fenci.fencingfplus2.managers.TargetManager;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.module.ModuleManager;
import org.fenci.fencingfplus2.settings.SettingManager;
import org.lwjgl.input.Keyboard;

public class FencingFPlus {
    public static FencingFPlus instance;
    public static PopManager POP_MANAGER;
    public static FriendManager FRIEND_MANAGER;
    public ModuleManager moduleManager;
    public SettingManager settingsManager;
    public CommandManager COMMAND_MANAGER;
    public TargetManager TARGET_MANAGER;
    public KitManager KIT_MANAGER;
    public ClickGui clickGui;

    public void init() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.settingsManager = new SettingManager();
        this.moduleManager = new ModuleManager();
        POP_MANAGER = new PopManager();
        this.clickGui = new ClickGui();
        this.COMMAND_MANAGER = new CommandManager();
        this.TARGET_MANAGER = new TargetManager();
        FRIEND_MANAGER = new FriendManager();
        this.KIT_MANAGER = new KitManager();
    }

    @SubscribeEvent
    public void key(InputEvent.KeyInputEvent e) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null) {
            return;
        }
        try {
            if (Keyboard.isCreated() && Keyboard.getEventKeyState()) {
                int keyCode = Keyboard.getEventKey();
                if (keyCode <= 0) {
                    return;
                }
                for (Module m : this.moduleManager.modules) {
                    if (m.getKey() != keyCode) continue;
                    m.toggle();
                }
            }
        }
        catch (Exception q) {
            q.printStackTrace();
        }
    }
}


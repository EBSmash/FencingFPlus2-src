//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 */
package org.fenci.fencingfplus2.module.misc;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Arrays;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.RPC;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;
import org.fenci.fencingfplus2.utils.ClientMessage;

public class DiscordRPC
extends Module {
    public DiscordRPC() {
        super("DiscordRPC", "Makes a Discord Rich Presence", Category.MISC);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Mode", this, "FencingF+2", Arrays.asList("FencingF+2", "bergenware.cc")));
    }

    @Override
    public void onEnable() {
        String mode = FencingFPlus.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        if (mode.equals("FencingF+2")) {
            RPC.startRPC();
        } else {
            RPC.startRPC2();
        }
        ClientMessage.sendMessage("Starting " + ChatFormatting.GREEN + "RPC" + ChatFormatting.RESET + "!");
    }

    @Override
    public void onUpdate() {
        if (DiscordRPC.mc.world == null) {
            RPC.stopRPC();
            RPC.stopRPC2();
        }
    }

    @Override
    public void onDisable() {
        RPC.stopRPC();
        RPC.stopRPC2();
        ClientMessage.sendMessage("Stopping " + ChatFormatting.RED + "RPC" + ChatFormatting.RESET + "!");
    }
}


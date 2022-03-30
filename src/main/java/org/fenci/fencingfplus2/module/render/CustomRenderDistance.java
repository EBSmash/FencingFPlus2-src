//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 */
package org.fenci.fencingfplus2.module.render;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;
import org.fenci.fencingfplus2.utils.ClientMessage;

public class CustomRenderDistance
extends Module {
    public CustomRenderDistance() {
        super("CustomRenderDistance", "Allows you to easily change render distance", Category.RENDER);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("RenderDistance", this, 8.0, 0.0, 100.0, true));
    }

    @Override
    public void onUpdate() {
        CustomRenderDistance.mc.gameSettings.renderDistanceChunks = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "RenderDistance").getValDouble();
        for (Module mod : FencingFPlus.instance.moduleManager.getModuleList()) {
            if (!mod.getName().equalsIgnoreCase("ChatNotifier") || !mod.isToggled()) continue;
            ClientMessage.sendOverwriteClientMessage(ChatFormatting.GREEN + this.getName() + ":" + ChatFormatting.BOLD + " Enabled");
        }
    }

    @Override
    public void onDisable() {
        CustomRenderDistance.mc.gameSettings.renderDistanceChunks = 8;
        for (Module mod : FencingFPlus.instance.moduleManager.getModuleList()) {
            if (!mod.getName().equalsIgnoreCase("ChatNotifier") || !mod.isToggled()) continue;
            ClientMessage.sendOverwriteClientMessage(ChatFormatting.RED + this.getName() + ":" + ChatFormatting.BOLD + " Disabled");
        }
    }
}


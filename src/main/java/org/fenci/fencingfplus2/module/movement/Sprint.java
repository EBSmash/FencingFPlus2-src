//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.client.settings.KeyBinding
 */
package org.fenci.fencingfplus2.module.movement;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.settings.KeyBinding;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.utils.ClientMessage;

public class Sprint
extends Module {
    public Sprint() {
        super("Sprint", "Automatically sprints", Category.MOVEMENT);
    }

    @Override
    public void onUpdate() {
        if (Sprint.mc.player != null && Sprint.mc.world != null) {
            KeyBinding.setKeyBindState((int)Sprint.mc.gameSettings.keyBindSprint.getKeyCode(), (boolean)true);
        }
    }

    @Override
    public void onDisable() {
        KeyBinding.setKeyBindState((int)Sprint.mc.gameSettings.keyBindSprint.getKeyCode(), (boolean)false);
        for (Module mod : FencingFPlus.instance.moduleManager.getModuleList()) {
            if (!mod.getName().equalsIgnoreCase("ChatNotifier") || !mod.isToggled()) continue;
            ClientMessage.sendOverwriteClientMessage(ChatFormatting.RED + this.getName() + ":" + ChatFormatting.BOLD + " Disabled");
        }
    }
}


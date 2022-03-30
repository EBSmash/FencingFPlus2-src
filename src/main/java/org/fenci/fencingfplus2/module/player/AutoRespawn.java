//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiGameOver
 *  net.minecraftforge.client.event.GuiOpenEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package org.fenci.fencingfplus2.module.player;

import net.minecraft.client.gui.GuiGameOver;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;

public class AutoRespawn
extends Module {
    public AutoRespawn() {
        super("AutoRespawn", "Automatically respawns you when you die", Category.PLAYER);
    }

    @SubscribeEvent
    public void onDisplayDeathScreen(GuiOpenEvent event) {
        if (event.getGui() instanceof GuiGameOver) {
            AutoRespawn.mc.player.respawnPlayer();
        }
    }
}


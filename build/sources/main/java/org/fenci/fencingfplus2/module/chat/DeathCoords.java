//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiGameOver
 *  net.minecraftforge.client.event.GuiOpenEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package org.fenci.fencingfplus2.module.chat;

import net.minecraft.client.gui.GuiGameOver;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.utils.ClientMessage;

public class DeathCoords
extends Module {
    public DeathCoords() {
        super("Death Coords", "Displays coords of where you die in chat.", Category.CHAT);
    }

    @SubscribeEvent
    public void onDisplayDeathScreen(GuiOpenEvent event) {
        if (DeathCoords.mc.world == null) {
            return;
        }
        int coordX = (int)DeathCoords.mc.player.posX;
        int coordY = (int)DeathCoords.mc.player.posY;
        int coordZ = (int)DeathCoords.mc.player.posZ;
        if (event.getGui() instanceof GuiGameOver) {
            ClientMessage.sendOverwriteClientMessage("You died at: " + coordX + " " + coordY + " " + coordZ);
        }
    }
}


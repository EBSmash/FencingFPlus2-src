//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package org.fenci.fencingfplus2.module.movement;

import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;

public class Flight
extends Module {
    public Flight() {
        super("Flight", "Allows you to fly", Category.MOVEMENT);
    }

    @Override
    public void onUpdate() {
        if (Flight.mc.player == null || Flight.mc.world == null) {
            return;
        }
        Flight.mc.player.capabilities.isFlying = true;
        if (Flight.mc.player.capabilities.isCreativeMode) {
            return;
        }
        if (Flight.mc.player.isAirBorne) {
            Flight.mc.player.capabilities.allowFlying = true;
        }
    }

    @Override
    public void onDisable() {
        if (Flight.mc.player.capabilities.isCreativeMode) {
            return;
        }
        Flight.mc.player.capabilities.isFlying = false;
    }
}


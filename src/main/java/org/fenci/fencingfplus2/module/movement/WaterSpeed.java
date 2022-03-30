//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package org.fenci.fencingfplus2.module.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;

public class WaterSpeed
extends Module {
    public WaterSpeed() {
        super("WaterSpeed", "Go faster in water", Category.MOVEMENT);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        if (WaterSpeed.mc.player != null && WaterSpeed.mc.world != null && WaterSpeed.mc.player.isInWater()) {
            WaterSpeed.mc.player.jump();
        }
    }
}


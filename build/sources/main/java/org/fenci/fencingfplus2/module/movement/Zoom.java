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
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;

public class Zoom
extends Module {
    public Zoom() {
        super("Zoom", "Zoom", Category.MOVEMENT);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Speed", this, 2.0, 1.0, 10.0, true));
    }

    public static float getDirection() {
        float var1 = Zoom.mc.player.rotationYaw;
        if (Zoom.mc.player.moveForward < 0.0f) {
            var1 += 180.0f;
        }
        float forward = 1.0f;
        if (Zoom.mc.player.moveForward < 0.0f) {
            forward = -0.5f;
        } else if (Zoom.mc.player.moveForward > 0.0f) {
            forward = 0.5f;
        }
        if (Zoom.mc.player.moveStrafing > 0.0f) {
            var1 -= 90.0f * forward;
        }
        if (Zoom.mc.player.moveStrafing < 0.0f) {
            var1 += 90.0f * forward;
        }
        return var1 *= (float)Math.PI / 180;
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        if (Zoom.mc.player != null && Zoom.mc.world != null) {
            float speed = (float)FencingFPlus.instance.settingsManager.getSettingByName(this, "Speed").getValDouble();
            if (Zoom.mc.player.moveForward > 0.0f) {
                double direction = Zoom.getDirection();
                Zoom.mc.player.motionX = -Math.sin(direction) * (double)speed;
                Zoom.mc.player.motionZ = Math.cos(direction) * (double)speed;
            }
        }
    }
}


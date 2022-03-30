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

public class FastSneak
extends Module {
    public FastSneak() {
        super("FastSneak", "Allows you to move faster while sneaking", Category.MOVEMENT);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Speed", this, 1.0, 1.0, 10.0, true));
    }

    public static float getDirection() {
        float var1 = FastSneak.mc.player.rotationYaw;
        if (FastSneak.mc.player.moveForward < 0.0f) {
            var1 += 180.0f;
        }
        float forward = 1.0f;
        if (FastSneak.mc.player.moveForward < 0.0f) {
            forward = -0.5f;
        } else if (FastSneak.mc.player.moveForward > 0.0f) {
            forward = 0.5f;
        }
        if (FastSneak.mc.player.moveStrafing > 0.0f) {
            var1 -= 90.0f * forward;
        }
        if (FastSneak.mc.player.moveStrafing < 0.0f) {
            var1 += 90.0f * forward;
        }
        return var1 *= (float)Math.PI / 180;
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        if (FastSneak.mc.player != null && FastSneak.mc.world != null) {
            float speed = (float)FencingFPlus.instance.settingsManager.getSettingByName(this, "Speed").getValDouble();
            if (FastSneak.mc.player.isSneaking()) {
                double direction = FastSneak.getDirection();
                FastSneak.mc.player.motionX = -Math.sin(direction) * 0.23 + (double)(speed / 100.0f);
                FastSneak.mc.player.motionZ = Math.cos(direction) * 0.23 + (double)(speed / 100.0f);
            }
        }
    }
}


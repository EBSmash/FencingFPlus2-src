//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package org.fenci.fencingfplus2.module.movement;

import net.minecraft.entity.Entity;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;

public class ReverseStep
extends Module {
    int delay;

    public ReverseStep() {
        super("ReverseStep", "ReverseStep", Category.MOVEMENT);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Height", this, 2.0, 1.0, 10.0, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("2b2t", this, false));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Falling", this, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("StairSpeed", this, false));
    }

    @Override
    public void onUpdate() {
        if (ReverseStep.mc.player == null || ReverseStep.mc.world == null) {
            return;
        }
        try {
            if (ReverseStep.mc.player.isInLava() || ReverseStep.mc.player.isInWater() || ReverseStep.mc.player.isOnLadder()) {
                return;
            }
        }
        catch (Exception ignored) {
            return;
        }
        boolean twobee = FencingFPlus.instance.settingsManager.getSettingByName(this, "2b2t").getValBoolean();
        boolean Stairspeed = FencingFPlus.instance.settingsManager.getSettingByName(this, "StairSpeed").getValBoolean();
        int height = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "Height").getValDouble();
        boolean falling = FencingFPlus.instance.settingsManager.getSettingByName(this, "Falling").getValBoolean();
        if (!twobee && ReverseStep.mc.player.onGround) {
            for (double y = 0.0; y < (double)height + 0.5; y += 0.01) {
                if (ReverseStep.mc.world.getCollisionBoxes((Entity)ReverseStep.mc.player, ReverseStep.mc.player.getEntityBoundingBox().offset(0.0, -y, 0.0)).isEmpty()) continue;
                ReverseStep.mc.player.motionY = -10.0;
                if (!Stairspeed) break;
                double direction = this.getDirection();
                ReverseStep.mc.player.motionY = ReverseStep.mc.player.motionX = -Math.sin(direction) + 0.78;
                ReverseStep.mc.player.motionY = ReverseStep.mc.player.motionZ = Math.cos(direction) + 0.78;
                break;
            }
        }
        if (twobee) {
            if (this.delay > 0) {
                --this.delay;
            }
            if (falling) {
                if (ReverseStep.mc.player.motionY > (double)-0.06f) {
                    this.delay = 20;
                }
                if (ReverseStep.mc.player.fallDistance > 0.0f && ReverseStep.mc.player.fallDistance < 1.0f && this.delay == 0 && !ReverseStep.mc.player.isInWater()) {
                    ReverseStep.mc.player.motionY = -3.9200038147008747;
                }
            }
        }
    }

    public float getDirection() {
        float var1 = ReverseStep.mc.player.rotationYaw;
        if (ReverseStep.mc.player.moveForward < 0.0f) {
            var1 += 180.0f;
        }
        float forward = 1.0f;
        if (ReverseStep.mc.player.moveForward < 0.0f) {
            forward = -0.5f;
        } else if (ReverseStep.mc.player.moveForward > 0.0f) {
            forward = 0.5f;
        }
        if (ReverseStep.mc.player.moveStrafing > 0.0f) {
            var1 -= 90.0f * forward;
        }
        if (ReverseStep.mc.player.moveStrafing < 0.0f) {
            var1 += 90.0f * forward;
        }
        return var1 *= (float)Math.PI / 180;
    }
}


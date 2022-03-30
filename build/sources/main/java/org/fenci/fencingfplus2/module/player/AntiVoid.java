//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package org.fenci.fencingfplus2.module.player;

import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;

public class AntiVoid
extends Module {
    public AntiVoid() {
        super("AntiVoid", "Allows you to avoid the void", Category.PLAYER);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("AutoReturn", this, false));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Jump", this, true));
    }

    @Override
    public void onUpdate() {
        boolean autoReturn = FencingFPlus.instance.settingsManager.getSettingByName(this, "AutoReturn").getValBoolean();
        boolean jump = FencingFPlus.instance.settingsManager.getSettingByName(this, "Jump").getValBoolean();
        if (AntiVoid.mc.player == null || AntiVoid.mc.world == null) {
            return;
        }
        if (AntiVoid.mc.player.posY < 1.0) {
            AntiVoid.mc.player.motionY = 0.0;
            if (!autoReturn && AntiVoid.mc.player.moveForward > 0.0f) {
                AntiVoid.mc.player.motionY = 0.15;
            }
            if (autoReturn) {
                AntiVoid.mc.player.motionY = 0.15;
            } else if (jump) {
                AntiVoid.mc.player.jump();
            }
        }
    }
}


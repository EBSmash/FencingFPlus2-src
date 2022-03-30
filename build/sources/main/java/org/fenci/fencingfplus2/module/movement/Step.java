//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package org.fenci.fencingfplus2.module.movement;

import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;

public class Step
extends Module {
    public Step() {
        super("Step", "Allows you to step up blocks more easily", Category.MOVEMENT);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Vanilla", this, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Height", this, 1.0, 0.0, 6.0, true));
    }

    @Override
    public void onUpdate() {
        Step.mc.player.stepHeight = (float)FencingFPlus.instance.settingsManager.getSettingByName(this, "Height").getValDouble();
    }

    @Override
    public void onDisable() {
        Step.mc.player.stepHeight = 0.5f;
    }
}


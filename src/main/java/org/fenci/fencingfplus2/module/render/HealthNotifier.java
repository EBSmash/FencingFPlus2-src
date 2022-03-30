//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package org.fenci.fencingfplus2.module.render;

import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;
import org.fenci.fencingfplus2.utils.ClientMessage;

public class HealthNotifier
extends Module {
    public HealthNotifier() {
        super("HealthNotifier", "Notifies you in chat once your health become too low", Category.RENDER);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Health", this, 14.0, 1.0, 36.0, true));
    }

    public float getPlayerHealth() {
        return HealthNotifier.mc.player.getHealth() + HealthNotifier.mc.player.getAbsorptionAmount();
    }

    @Override
    public void onUpdate() {
        int health = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "health").getValDouble();
        if (this.getPlayerHealth() <= (float)health) {
            ClientMessage.sendOverwriteClientMessage("Your health is at " + this.getPlayerHealth() + " EAT YOUR GAPPLES!");
        }
    }
}


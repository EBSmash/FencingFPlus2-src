//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.client.event.EntityViewRenderEvent$FOVModifier
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package org.fenci.fencingfplus2.module.player;

import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;

public class FOVslider
extends Module {
    public FOVslider() {
        super("FOVSlider", "Allows you to easily ajust your FOV", Category.PLAYER);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("FOV", this, FOVslider.mc.gameSettings.fovSetting, 0.0, 200.0, false));
    }

    @SubscribeEvent
    public void Fovmodifier(EntityViewRenderEvent.FOVModifier e) {
        FOVslider.mc.gameSettings.fovSetting = (float)FencingFPlus.instance.settingsManager.getSettingByName(this, "FOV").getValDouble();
    }
}


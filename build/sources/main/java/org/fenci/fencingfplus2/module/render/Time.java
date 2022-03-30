//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$RenderTickEvent
 */
package org.fenci.fencingfplus2.module.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;

public class Time
extends Module {
    public Time() {
        super("Time", "Allows you to change the time and moon cycle", Category.RENDER);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Custom Time", this, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Time", this, 20.0, 1.0, 30.0, true));
    }

    @SubscribeEvent
    public void onTick(TickEvent.RenderTickEvent e) {
        if (Time.mc.player != null && Time.mc.world != null) {
            float night = (float)FencingFPlus.instance.settingsManager.getSettingByName(this, "Time").getValDouble();
            boolean customtime = FencingFPlus.instance.settingsManager.getSettingByName(this, "Custom Time").getValBoolean();
            if (customtime) {
                float time = night * 1000.0f;
                Time.mc.world.setWorldTime((long)time);
            }
        }
    }
}


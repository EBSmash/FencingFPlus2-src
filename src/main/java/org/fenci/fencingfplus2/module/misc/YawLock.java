//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiGameOver
 *  net.minecraftforge.client.event.GuiOpenEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package org.fenci.fencingfplus2.module.misc;

import net.minecraft.client.gui.GuiGameOver;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;

public class YawLock
extends Module {
    public YawLock() {
        super("YawLock", "Locks your yaw", Category.MISC);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Disable on Death", this, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Yaw", this, 0.0, 0.0, 8.0, true));
    }

    @Override
    public void onUpdate() {
        float yaw = (float)FencingFPlus.instance.settingsManager.getSettingByName(this, "Yaw").getValDouble();
        YawLock.mc.player.rotationYaw = yaw * 45.0f;
    }

    @SubscribeEvent
    public void onDisplayDeathScreen(GuiOpenEvent event) {
        boolean disable = FencingFPlus.instance.settingsManager.getSettingByName(this, "Disable on Death").getValBoolean();
        if (event.getGui() instanceof GuiGameOver && disable) {
            this.toggle();
        }
    }
}


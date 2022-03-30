//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package org.fenci.fencingfplus2.module.movement;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;

public class AutoWalk
extends Module {
    public AutoWalk() {
        super("AutoWalk", "AutoWalk", Category.MOVEMENT);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Sprint", this, false));
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        if (AutoWalk.mc.player != null && AutoWalk.mc.world != null) {
            boolean sprint = FencingFPlus.instance.settingsManager.getSettingByName(this, "Sprint").getValBoolean();
            KeyBinding.setKeyBindState((int)AutoWalk.mc.gameSettings.keyBindForward.getKeyCode(), (boolean)true);
            if (sprint) {
                KeyBinding.setKeyBindState((int)AutoWalk.mc.gameSettings.keyBindSprint.getKeyCode(), (boolean)true);
            }
        }
    }
}


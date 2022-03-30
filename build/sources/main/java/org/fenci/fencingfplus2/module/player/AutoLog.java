//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.play.server.SPacketDisconnect
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package org.fenci.fencingfplus2.module.player;

import java.util.Objects;
import net.minecraft.network.play.server.SPacketDisconnect;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;

public class AutoLog
extends Module {
    public AutoLog() {
        super("AutoLog", "Auto disconnects you from the server when you are below a specific amount of health", Category.PLAYER);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Health", this, 7.0, 1.0, 20.0, true));
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        float health = (float)FencingFPlus.instance.settingsManager.getSettingByName(this, "Health").getValDouble();
        float playerHealth = AutoLog.mc.player.getHealth();
        if (AutoLog.mc.player.getHealth() <= health) {
            Objects.requireNonNull(mc.getConnection()).handleDisconnect(new SPacketDisconnect((ITextComponent)new TextComponentString("Logged out, health was at: " + playerHealth)));
            this.toggle();
        }
    }
}


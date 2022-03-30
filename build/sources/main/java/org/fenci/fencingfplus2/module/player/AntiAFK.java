//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package org.fenci.fencingfplus2.module.player;

import java.awt.AWTException;
import java.awt.Robot;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;

public class AntiAFK
extends Module {
    long lastmsg = 0L;
    long lastswing = 0L;
    long lastjump = 0L;
    long lastrotate = 0L;
    long lastchatmsg = 0L;

    public AntiAFK() {
        super("AntiAFK", "Attempts to prevent you from getting kicked for AFK on servers", Category.PLAYER);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Delay", this, 20.0, 0.0, 60.0, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Swing", this, false));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Rotate", this, false));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("ChatMessage", this, false));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Jump", this, false));
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) throws InterruptedException {
        if (AntiAFK.mc.player != null && AntiAFK.mc.world != null) {
            float delay = (float)FencingFPlus.instance.settingsManager.getSettingByName(this, "Delay").getValDouble();
            boolean swing = FencingFPlus.instance.settingsManager.getSettingByName(this, "Swing").getValBoolean();
            boolean jump = FencingFPlus.instance.settingsManager.getSettingByName(this, "Jump").getValBoolean();
            boolean rotate = FencingFPlus.instance.settingsManager.getSettingByName(this, "Rotate").getValBoolean();
            boolean cmsg = FencingFPlus.instance.settingsManager.getSettingByName(this, "ChatMessage").getValBoolean();
            float newdelay = delay * 1000.0f;
            if (System.currentTimeMillis() - this.lastmsg >= (long)newdelay && !AntiAFK.mc.player.isDead) {
                AntiAFK.mc.player.sendChatMessage("/stats");
                this.lastmsg = System.currentTimeMillis();
            }
            if (swing && System.currentTimeMillis() - this.lastswing >= (long)newdelay && !AntiAFK.mc.player.isDead) {
                try {
                    Robot robot = new Robot();
                    robot.mousePress(1024);
                    robot.mouseRelease(1024);
                    this.lastswing = System.currentTimeMillis();
                }
                catch (AWTException awtException) {
                    awtException.printStackTrace();
                }
            }
            if (jump && System.currentTimeMillis() - this.lastjump >= (long)newdelay && !AntiAFK.mc.player.isDead) {
                AntiAFK.mc.player.jump();
                this.lastjump = System.currentTimeMillis();
            }
            if (rotate && System.currentTimeMillis() - this.lastrotate >= (long)newdelay && !AntiAFK.mc.player.isDead) {
                AntiAFK.mc.player.prevRotationYawHead = 90.0f;
                AntiAFK.mc.player.prevCameraYaw = 90.0f;
                AntiAFK.mc.player.prevRotationYaw = 90.0f;
                this.lastrotate = System.currentTimeMillis();
            }
            if (cmsg && System.currentTimeMillis() - this.lastchatmsg >= (long)newdelay && !AntiAFK.mc.player.isDead) {
                AntiAFK.mc.player.sendChatMessage("Who at the nuggets? | AntiAFK");
                this.lastchatmsg = System.currentTimeMillis();
            }
        }
    }
}


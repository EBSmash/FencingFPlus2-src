//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package org.fenci.fencingfplus2.module.chat;

import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;

public class ChatSpammer
extends Module {
    long lastmsg = 0L;
    long lastmsg2 = 0L;
    long lastmsg3 = 0L;
    long lastmsg4 = 0L;
    long lastmsg5 = 0L;

    public ChatSpammer() {
        super("ChatSpammer", "Spams in chat.", Category.CHAT);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Delay", this, 20.0, 0.0, 60.0, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Spammer", this, false));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Inventory", this, true));
    }

    @Override
    public void onUpdate() {
        if (ChatSpammer.mc.player == null || ChatSpammer.mc.world == null) {
            this.toggle();
        }
        float delay = (float)FencingFPlus.instance.settingsManager.getSettingByName(this, "Delay").getValDouble();
        boolean spammer = FencingFPlus.instance.settingsManager.getSettingByName(this, "Spammer").getValBoolean();
        boolean inventory2 = FencingFPlus.instance.settingsManager.getSettingByName(this, "Inventory").getValBoolean();
        float newdelay = delay * 1000.0f;
        if (spammer && System.currentTimeMillis() - this.lastmsg >= (long)newdelay) {
            ChatSpammer.mc.player.sendChatMessage("I just duped " + Math.random() + " totem shulkers thanks to FencingF+2!");
            this.lastmsg = System.currentTimeMillis();
        }
        if (ChatSpammer.mc.currentScreen == null && ChatSpammer.mc.gameSettings.keyBindInventory.isKeyDown() && inventory2) {
            ChatSpammer.mc.player.sendChatMessage("I just opened my inventory thanks to FencingF+2!");
        }
    }
}


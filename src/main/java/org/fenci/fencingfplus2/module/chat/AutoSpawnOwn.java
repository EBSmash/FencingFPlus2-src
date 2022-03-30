//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package org.fenci.fencingfplus2.module.chat;

import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;

public class AutoSpawnOwn
extends Module {
    long lastmsg = 0L;

    public AutoSpawnOwn() {
        super("AutoSpawnOwn", "Just turn it on.", Category.CHAT);
    }

    @Override
    public void onUpdate() {
        if (AutoSpawnOwn.mc.world.playerEntities.size() == 1 && AutoSpawnOwn.mc.player.posX < 100.0 && AutoSpawnOwn.mc.player.posX > -100.0 && AutoSpawnOwn.mc.player.posZ < 100.0 && AutoSpawnOwn.mc.player.posZ > -100.0 && System.currentTimeMillis() - this.lastmsg >= 60000L) {
            AutoSpawnOwn.mc.player.sendChatMessage(">I am currently owning spawn thanks to FencingF+2!");
            this.lastmsg = System.currentTimeMillis();
        }
    }
}


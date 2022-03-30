//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketChatMessage
 */
package org.fenci.fencingfplus2.module.combat;

import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;

public class AutoKill
extends Module {
    public AutoKill() {
        super("Auto/Kill", "idk", Category.COMBAT);
    }

    @Override
    public void onEnable() {
        mc.getConnection().sendPacket((Packet)new CPacketChatMessage("/kill"));
        AutoKill.mc.player.sendChatMessage("I just /killed thanks to FencingF+2 Auto/Kill!");
        this.toggle();
    }
}


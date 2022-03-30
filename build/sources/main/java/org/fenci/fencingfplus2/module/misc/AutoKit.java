//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketChatMessage
 *  net.minecraft.network.play.server.SPacketRespawn
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package org.fenci.fencingfplus2.module.misc;

import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.server.SPacketRespawn;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.event.events.PacketEvent;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;

public class AutoKit
extends Module {
    String kit;

    public AutoKit() {
        super("Auto/Kit", "Automatically gives you a kit", Category.MISC);
    }

    @SubscribeEvent
    public void onPacket(PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketRespawn) {
            mc.getConnection().sendPacket((Packet)new CPacketChatMessage("/kit " + this.getManagedName()));
        }
    }

    public String getManagedName() {
        this.kit = FencingFPlus.instance.KIT_MANAGER.getKit().toString();
        this.kit = this.kit.replaceAll("[\\p{Ps}\\p{Pe}]", "");
        return this.kit;
    }
}


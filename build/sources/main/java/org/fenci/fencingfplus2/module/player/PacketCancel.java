/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketInput
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.network.play.client.CPacketUseEntity
 *  net.minecraft.network.play.client.CPacketVehicleMove
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package org.fenci.fencingfplus2.module.player;

import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketInput;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.client.CPacketVehicleMove;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.event.events.PacketEvent;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;

public class PacketCancel
extends Module {
    public PacketCancel() {
        super("PacketCancel", "Cancels Packets", Category.PLAYER);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("CPacketInput", this, false));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("CPacketPlayer", this, false));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("CPacketEntityAction", this, false));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("CPacketUseEntity", this, false));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("CPacketVehicleMove", this, false));
    }

    @SubscribeEvent
    public void onPacket(PacketEvent.Send event) {
        boolean cPacket_input = FencingFPlus.instance.settingsManager.getSettingByName(this, "CPacketInput").getValBoolean();
        boolean cPacket_player = FencingFPlus.instance.settingsManager.getSettingByName(this, "CPacketPlayer").getValBoolean();
        boolean cPacket_entity_action = FencingFPlus.instance.settingsManager.getSettingByName(this, "CPacketEntityAction").getValBoolean();
        boolean cPacket_use_entity = FencingFPlus.instance.settingsManager.getSettingByName(this, "CPacketUseEntity").getValBoolean();
        boolean cPacket_vehicle_move = FencingFPlus.instance.settingsManager.getSettingByName(this, "CPacketVehicleMove").getValBoolean();
        if (cPacket_input && event.getPacket() instanceof CPacketInput) {
            event.setCanceled(true);
        }
        if (cPacket_player && event.getPacket() instanceof CPacketPlayer) {
            event.setCanceled(true);
        }
        if (cPacket_entity_action && event.getPacket() instanceof CPacketEntityAction) {
            event.setCanceled(true);
        }
        if (cPacket_use_entity && event.getPacket() instanceof CPacketUseEntity) {
            event.setCanceled(true);
        }
        if (cPacket_vehicle_move && event.getPacket() instanceof CPacketVehicleMove) {
            event.setCanceled(true);
        }
    }
}


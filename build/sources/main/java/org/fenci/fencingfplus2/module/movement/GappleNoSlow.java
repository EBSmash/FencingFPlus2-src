//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 */
package org.fenci.fencingfplus2.module.movement;

import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.utils.ModuleEnabler;

public class GappleNoSlow
extends Module {
    public GappleNoSlow() {
        super("2b2tGappleNoSlow", "2b2tNoSlow - The first practical 2b2tGappleNoSlowBypass", Category.MOVEMENT);
    }

    @Override
    public void onUpdate() {
        if (GappleNoSlow.mc.player == null || GappleNoSlow.mc.world == null) {
            return;
        }
        ModuleEnabler.enable("NoSlow");
        if (GappleNoSlow.mc.player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem() == Items.GOLDEN_APPLE && GappleNoSlow.mc.gameSettings.keyBindUseItem.isKeyDown()) {
            GappleNoSlow.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.findGappleInHotbar()));
        }
    }

    private int findGappleInHotbar() {
        int slot = 0;
        for (int i = 0; i < 9; ++i) {
            if (GappleNoSlow.mc.player.inventory.getStackInSlot(i).getItem() != Items.GOLDEN_APPLE) continue;
            slot = i;
            break;
        }
        return slot;
    }
}


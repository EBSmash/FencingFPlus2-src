//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItem
 *  net.minecraft.util.math.BlockPos
 */
package org.fenci.fencingfplus2.module.combat;

import java.util.Objects;
import net.minecraft.init.Items;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.math.BlockPos;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;

public class BowBomb
extends Module {
    public BowBomb() {
        super("BowSpam", "MachienGunBrrr", Category.COMBAT);
    }

    @Override
    public void onUpdate() {
        if (BowBomb.mc.player == null || BowBomb.mc.world == null) {
            return;
        }
        if (BowBomb.mc.player.getHeldItemMainhand().getItem() == Items.BOW && BowBomb.mc.player.isHandActive() && BowBomb.mc.player.getItemInUseMaxCount() > 3) {
            Objects.requireNonNull(mc.getConnection()).sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, BowBomb.mc.player.getHorizontalFacing()));
            mc.getConnection().sendPacket((Packet)new CPacketPlayerTryUseItem(BowBomb.mc.player.getActiveHand()));
            BowBomb.mc.player.stopActiveHand();
        }
    }
}


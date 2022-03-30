//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 */
package org.fenci.fencingfplus2.module.combat;

import java.util.Objects;
import net.minecraft.init.Items;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.mixin.mixins.accessor.IMinecraftMixin;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;

public class FastEXP
extends Module {
    int prvSlot;

    public FastEXP() {
        super("FastExp", "Allows you to use EXP bottles faster.", Category.COMBAT);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Silent", this, false));
    }

    @Override
    public void onUpdate() {
        boolean silent = FencingFPlus.instance.settingsManager.getSettingByName(this, "Silent").getValBoolean();
        if (FastEXP.mc.player == null || FastEXP.mc.world == null) {
            return;
        }
        this.prvSlot = FastEXP.mc.player.inventory.currentItem;
        if (FastEXP.mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE && !silent) {
            ((IMinecraftMixin)mc).setRightClickDelayTimerAccessor(0);
        }
        if (silent) {
            ((IMinecraftMixin)mc).setRightClickDelayTimerAccessor(0);
            Objects.requireNonNull(mc.getConnection()).sendPacket((Packet)new CPacketHeldItemChange(this.findExpInHotbar()));
        }
    }

    private int findExpInHotbar() {
        int slot = 0;
        for (int i = 0; i < 9; ++i) {
            if (FastEXP.mc.player.inventory.getStackInSlot(i).getItem() != Items.EXPERIENCE_BOTTLE) continue;
            slot = i;
            break;
        }
        return slot;
    }
}


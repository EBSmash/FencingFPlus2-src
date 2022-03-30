//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.util.EnumHand
 *  net.minecraft.world.World
 */
package org.fenci.fencingfplus2.module.combat;

import java.util.Arrays;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;

public class AutoGapple
extends Module {
    Item oldSlot;
    boolean isEating;
    int prvSlot;

    public AutoGapple() {
        super("AutoGapple", "Gapple without gappling", Category.COMBAT);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Mode", this, "Normal", Arrays.asList("Normal", "Packet")));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("SwitchBack", this, true));
    }

    @Override
    public void onUpdate() {
        if (AutoGapple.mc.player != null && AutoGapple.mc.world != null) {
            String mode = FencingFPlus.instance.settingsManager.getSettingByName(this, "Mode").getValString();
            boolean switchBack = FencingFPlus.instance.settingsManager.getSettingByName(this, "SwitchBack").getValBoolean();
            if (mode.equals("Normal") && AutoGapple.mc.player.getHeldItemMainhand().getItem() != Items.EXPERIENCE_BOTTLE && AutoGapple.mc.player.getHeldItemMainhand().getItem() != Items.CHORUS_FRUIT && AutoGapple.mc.player.getHeldItemMainhand().getItem() != Items.BOW && AutoGapple.mc.player.getHeldItemMainhand().getItem() != Items.POTIONITEM && AutoGapple.mc.player.getHeldItemMainhand().getItem() != Items.ENDER_PEARL && AutoGapple.mc.gameSettings.keyBindUseItem.isKeyDown()) {
                this.isEating = true;
                this.oldSlot = AutoGapple.mc.player.getHeldItemMainhand().getItem();
                if (this.doesNextSlotHaveGapple() && AutoGapple.mc.player.getHeldItemMainhand().getItem() != Items.GOLDEN_APPLE) {
                    for (int i = 0; i < 9; ++i) {
                        if (AutoGapple.mc.player.inventory.getStackInSlot(i).getItem() != Items.GOLDEN_APPLE) continue;
                        AutoGapple.mc.player.inventory.currentItem = i;
                        AutoGapple.mc.playerController.processRightClick((EntityPlayer)AutoGapple.mc.player, (World)AutoGapple.mc.world, EnumHand.MAIN_HAND);
                    }
                }
            }
            if (mode.equals("Packet")) {
                AutoGapple.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.findGappleInHotbar()));
            }
        }
    }

    public float playerhealth() {
        return AutoGapple.mc.player.getHealth() + AutoGapple.mc.player.getAbsorptionAmount();
    }

    private boolean doesNextSlotHaveGapple() {
        for (int i = 0; i < 9; ++i) {
            AutoGapple.mc.player.inventory.getStackInSlot(i);
            if (AutoGapple.mc.player.inventory.getStackInSlot(i).getItem() != Items.GOLDEN_APPLE) continue;
            return true;
        }
        return false;
    }

    private boolean doesNextSlotHaveLastItem() {
        for (int a = 0; a < 9; ++a) {
            AutoGapple.mc.player.inventory.getStackInSlot(a);
            if (AutoGapple.mc.player.inventory.getStackInSlot(a).getItem() != this.oldSlot) continue;
            return true;
        }
        return false;
    }

    private int findGappleInHotbar() {
        int slot = 0;
        for (int i = 0; i < 9; ++i) {
            if (AutoGapple.mc.player.inventory.getStackInSlot(i).getItem() != Items.GOLDEN_APPLE) continue;
            slot = i;
            break;
        }
        return slot;
    }

    private int findCurrentItem() {
        int slot = 0;
        for (int i = 0; i < 9; ++i) {
            if (AutoGapple.mc.player.inventory.getStackInSlot(i).getItem() != Items.GOLDEN_APPLE) continue;
            slot = i;
            break;
        }
        return slot;
    }
}


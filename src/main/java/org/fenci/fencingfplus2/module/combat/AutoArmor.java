//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.InventoryEffectRenderer
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package org.fenci.fencingfplus2.module.combat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;

public class AutoArmor
extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();

    public AutoArmor() {
        super("AutoArmor", "Automatically equips you with armor", Category.COMBAT);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        if (this.mc.player != null && this.mc.world != null) {
            int armorType;
            if (this.mc.player.ticksExisted % 2 == 0) {
                return;
            }
            if (this.mc.currentScreen instanceof GuiContainer && !(this.mc.currentScreen instanceof InventoryEffectRenderer)) {
                return;
            }
            int[] bestArmorSlots = new int[4];
            int[] bestArmorValues = new int[4];
            for (armorType = 0; armorType < 4; ++armorType) {
                ItemStack oldArmor = this.mc.player.inventory.armorItemInSlot(armorType);
                if (oldArmor.getItem() instanceof ItemArmor) {
                    bestArmorValues[armorType] = ((ItemArmor)oldArmor.getItem()).damageReduceAmount;
                }
                bestArmorSlots[armorType] = -1;
            }
            for (int slot = 0; slot < 36; ++slot) {
                int armorValue;
                ItemStack stack = this.mc.player.inventory.getStackInSlot(slot);
                if (stack.getCount() > 1 || !(stack.getItem() instanceof ItemArmor)) continue;
                ItemArmor armor = (ItemArmor)stack.getItem();
                int armorType2 = armor.armorType.ordinal() - 2;
                if (armorType2 == 2 && this.mc.player.inventory.armorItemInSlot(armorType2).getItem().equals(Items.ELYTRA) || (armorValue = armor.damageReduceAmount) <= bestArmorValues[armorType2]) continue;
                bestArmorSlots[armorType2] = slot;
                bestArmorValues[armorType2] = armorValue;
            }
            for (armorType = 0; armorType < 4; ++armorType) {
                ItemStack oldArmor;
                int slot = bestArmorSlots[armorType];
                if (slot == -1 || (oldArmor = this.mc.player.inventory.armorItemInSlot(armorType)) == ItemStack.EMPTY && this.mc.player.inventory.getFirstEmptyStack() == -1) continue;
                if (slot < 9) {
                    slot += 36;
                }
                this.mc.playerController.windowClick(0, 8 - armorType, 0, ClickType.QUICK_MOVE, (EntityPlayer)this.mc.player);
                this.mc.playerController.windowClick(0, slot, 0, ClickType.QUICK_MOVE, (EntityPlayer)this.mc.player);
                break;
            }
        }
    }
}


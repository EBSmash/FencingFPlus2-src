//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.Item
 *  net.minecraft.util.EnumHand
 *  net.minecraft.world.World
 */
package org.fenci.fencingfplus2.module.combat;

import java.util.Arrays;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;
import org.fenci.fencingfplus2.utils.ModuleEnabler;

public class Offhand
extends Module {
    Item offitem = null;

    public Offhand() {
        super("Offhand", "Puts either a crystal or gapple into your offhand.", Category.COMBAT);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("MainHandFall", this, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Health Switch", this, 20.0, 0.0, 36.0, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Gapple Slot", this, 3.0, 1.0, 9.0, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Sword Gapple Switch", this, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Sword Mode", this, "RightClick", Arrays.asList("RightClick", "Normal")));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("OffhandItem", this, "Crystals", Arrays.asList("Crystals", "Gapples")));
    }

    @Override
    public void onUpdate() {
        int gappleSlot = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "Gapple Slot").getValDouble();
        if (Offhand.mc.player != null && Offhand.mc.world != null) {
            int slot;
            ModuleEnabler.disable("AutoTotem");
            this.getItem();
            if (Offhand.mc.player.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).getItem() != this.offitem && (slot = this.getItemSlot()) != -1) {
                Offhand.mc.playerController.windowClick(Offhand.mc.player.inventoryContainer.windowId, slot, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
                Offhand.mc.playerController.windowClick(Offhand.mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
                Offhand.mc.playerController.windowClick(Offhand.mc.player.inventoryContainer.windowId, slot, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
                Offhand.mc.playerController.updateController();
            }
        }
    }

    private int getItemSlot() {
        for (int i = 0; i < 36; ++i) {
            Item item = Offhand.mc.player.inventory.getStackInSlot(i).getItem();
            if (item != this.offitem) continue;
            if (i < 9) {
                i += 36;
            }
            return i;
        }
        return -1;
    }

    public void getItem() {
        boolean mainHandFall = FencingFPlus.instance.settingsManager.getSettingByName(this, "MainHandFall").getValBoolean();
        int threshold = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "Health Switch").getValDouble();
        boolean gappleSwitch = FencingFPlus.instance.settingsManager.getSettingByName(this, "Sword Gapple Switch").getValBoolean();
        String smode = FencingFPlus.instance.settingsManager.getSettingByName(this, "Sword Mode").getValString();
        int gappleSlot = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "Gapple Slot").getValDouble();
        String offhandItem = FencingFPlus.instance.settingsManager.getSettingByName(this, "OffhandItem").getValString();
        int gappleInHotbar = gappleSlot + 35;
        if (Offhand.mc.player.getHeldItemMainhand().getItem() != Items.TOTEM_OF_UNDYING && mainHandFall && Offhand.mc.player.fallDistance > 10.0f && this.doesNextSlotHaveTotem()) {
            for (int a = 0; a < 9; ++a) {
                if (Offhand.mc.player.inventory.getStackInSlot(a).getItem() != Items.TOTEM_OF_UNDYING) continue;
                Offhand.mc.player.inventory.currentItem = a;
                Offhand.mc.playerController.processRightClick((EntityPlayer)Offhand.mc.player, (World)Offhand.mc.world, EnumHand.MAIN_HAND);
            }
        }
        if (Offhand.mc.player.getHealth() + Offhand.mc.player.getAbsorptionAmount() <= (float)threshold) {
            this.offitem = Items.TOTEM_OF_UNDYING;
        }
        if (offhandItem.equals("Gapples") && Offhand.mc.player.getHealth() + Offhand.mc.player.getAbsorptionAmount() >= (float)threshold) {
            this.offitem = Items.GOLDEN_APPLE;
        }
        if (offhandItem.equals("Crystals") && Offhand.mc.player.getHealth() + Offhand.mc.player.getAbsorptionAmount() >= (float)threshold) {
            this.offitem = Items.END_CRYSTAL;
            if (Offhand.mc.player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem() == Items.DIAMOND_SWORD && gappleSwitch && smode.equals("Normal")) {
                this.offitem = Items.GOLDEN_APPLE;
            }
            if (Offhand.mc.player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem() == Items.DIAMOND_SWORD && gappleSwitch && Offhand.mc.gameSettings.keyBindUseItem.isKeyDown() && smode.equals("RightClick")) {
                this.offitem = Items.GOLDEN_APPLE;
            }
        }
    }

    private boolean doesNextSlotHaveTotem() {
        for (int i = 0; i < 9; ++i) {
            Offhand.mc.player.inventory.getStackInSlot(i);
            if (Offhand.mc.player.inventory.getStackInSlot(i).getItem() != Items.TOTEM_OF_UNDYING) continue;
            return true;
        }
        return false;
    }

    private boolean doesNextSlotNotHaveGap() {
        int gappleSlot = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "Gapple Slot").getValDouble();
        for (int b = 0; b < gappleSlot; ++b) {
            Offhand.mc.player.inventory.getStackInSlot(b);
            if (Offhand.mc.player.inventory.getStackInSlot(b).getItem() != Items.GOLDEN_APPLE) continue;
            return true;
        }
        return false;
    }

    public void putGappleInSlot() {
        int gappleSlot = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "Gapple Slot").getValDouble();
        int gappleInHotbar = gappleSlot + 35;
        int slottt = this.getItemSlotGap();
        if (slottt != -1) {
            Offhand.mc.playerController.windowClick(Offhand.mc.player.inventoryContainer.windowId, slottt, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
            Offhand.mc.playerController.windowClick(Offhand.mc.player.inventoryContainer.windowId, gappleInHotbar, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
            Offhand.mc.playerController.windowClick(Offhand.mc.player.inventoryContainer.windowId, slottt, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
            Offhand.mc.playerController.updateController();
        }
    }

    private int getItemSlotGap() {
        for (int n = 0; n < 36; ++n) {
            Item item = Offhand.mc.player.inventory.getStackInSlot(n).getItem();
            if (item != Items.GOLDEN_APPLE) continue;
            if (n < 9) {
                n += 36;
            }
            return n;
        }
        return -1;
    }

    private int getItemSlotAir() {
        for (int n = 0; n < 36; ++n) {
            Item item = Offhand.mc.player.inventory.getStackInSlot(n).getItem();
            if (item != Items.AIR) continue;
            if (n < 9) {
                n += 36;
            }
            return n;
        }
        return -1;
    }
}


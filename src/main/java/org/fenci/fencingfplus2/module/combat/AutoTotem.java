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
 */
package org.fenci.fencingfplus2.module.combat;

import java.util.Arrays;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;
import org.fenci.fencingfplus2.utils.ModuleEnabler;

public class AutoTotem
extends Module {
    Item offhanditem = null;

    public AutoTotem() {
        super("AutoTotem", "Automatically puts totem in your offhand", Category.COMBAT);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("GapSword", this, "Off", Arrays.asList("Off", "RightClick", "Always")));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("GapThreshold", this, 20.0, 0.0, 36.0, true));
    }

    @Override
    public void onUpdate() {
        if (AutoTotem.mc.player != null && AutoTotem.mc.world != null) {
            int slot;
            String smode = FencingFPlus.instance.settingsManager.getSettingByName(this, "GapSword").getValString();
            ModuleEnabler.disable("Offhand");
            this.getItem();
            if (AutoTotem.mc.player.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).getItem() != this.offhanditem && (slot = this.getItemSlot()) != -1) {
                AutoTotem.mc.playerController.windowClick(AutoTotem.mc.player.inventoryContainer.windowId, slot, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
                AutoTotem.mc.playerController.windowClick(AutoTotem.mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
                AutoTotem.mc.playerController.windowClick(AutoTotem.mc.player.inventoryContainer.windowId, slot, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
                AutoTotem.mc.playerController.updateController();
            }
        }
    }

    private int getItemSlot() {
        for (int i = 0; i < 36; ++i) {
            Item item = AutoTotem.mc.player.inventory.getStackInSlot(i).getItem();
            if (item != this.offhanditem) continue;
            if (i < 9) {
                i += 36;
            }
            return i;
        }
        return -1;
    }

    public void getItem() {
        String smode = FencingFPlus.instance.settingsManager.getSettingByName(this, "GapSword").getValString();
        int gapThreshold = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "GapThreshold").getValDouble();
        if (smode.equals("Off")) {
            this.offhanditem = Items.TOTEM_OF_UNDYING;
        }
        this.offhanditem = smode.equals("RightClick") && AutoTotem.mc.player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem() == Items.DIAMOND_SWORD && AutoTotem.mc.gameSettings.keyBindUseItem.isKeyDown() && AutoTotem.mc.player.getHealth() + AutoTotem.mc.player.getAbsorptionAmount() >= (float)gapThreshold ? Items.GOLDEN_APPLE : Items.TOTEM_OF_UNDYING;
        this.offhanditem = smode.equals("Always") && AutoTotem.mc.player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem() == Items.DIAMOND_SWORD && AutoTotem.mc.player.getHealth() + AutoTotem.mc.player.getAbsorptionAmount() >= (float)gapThreshold ? Items.GOLDEN_APPLE : Items.TOTEM_OF_UNDYING;
    }
}


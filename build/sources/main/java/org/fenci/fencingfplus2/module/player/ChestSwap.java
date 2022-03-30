//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package org.fenci.fencingfplus2.module.player;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;
import org.fenci.fencingfplus2.utils.ClientMessage;
import org.fenci.fencingfplus2.utils.inventory;

public class ChestSwap
extends Module {
    public ChestSwap() {
        super("ChestSwap", "Swaps your chest item", Category.PLAYER);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("ChatAnnounce", this, true));
    }

    @Override
    public void onEnable() {
        boolean chat = FencingFPlus.instance.settingsManager.getSettingByName(this, "ChatAnnounce").getValBoolean();
        ItemStack itemStack = inventory.getItemStack(38);
        assert (itemStack != null);
        if (itemStack.getItem() == Items.ELYTRA) {
            int slot = this.getChestPlateSlot();
            if (slot != -1) {
                inventory.clickSlot(slot);
                inventory.clickSlot(38);
                inventory.clickSlot(slot);
                if (chat) {
                    ClientMessage.sendOverwriteClientMessage("Switched to Chestplate");
                }
            } else {
                ClientMessage.sendMessage("No Chestplate was found in inventory", true);
            }
        } else if (inventory.hasItem(Items.ELYTRA)) {
            int slot = inventory.getSlot(Items.ELYTRA);
            inventory.clickSlot(slot);
            inventory.clickSlot(38);
            inventory.clickSlot(slot);
            if (chat) {
                ClientMessage.sendOverwriteClientMessage("Switched to Elytra");
            }
        } else {
            ClientMessage.sendMessage("No Elytra was found in inventory", true);
        }
        this.toggle();
    }

    public int getChestPlateSlot() {
        Item[] items;
        for (Item item : items = new Item[]{Items.DIAMOND_CHESTPLATE, Items.CHAINMAIL_CHESTPLATE, Items.IRON_CHESTPLATE, Items.GOLDEN_CHESTPLATE, Items.LEATHER_CHESTPLATE}) {
            if (!inventory.hasItem(item)) continue;
            return inventory.getSlot(item);
        }
        return -1;
    }
}


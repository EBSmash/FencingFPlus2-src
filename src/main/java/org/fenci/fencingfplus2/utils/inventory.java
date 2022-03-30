//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package org.fenci.fencingfplus2.utils;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.fenci.fencingfplus2.utils.Globals;

public class inventory
implements Globals {
    public static int getHandSlot() {
        return inventory.mc.player.inventory.currentItem;
    }

    public static int getSlot(Block block) {
        try {
            for (ItemStackUtil itemStack : inventory.getAllItems()) {
                if (!Block.getBlockFromItem((Item)itemStack.itemStack.getItem()).equals(block)) continue;
                return itemStack.slotId;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return -1;
    }

    public static int getSlot(Item item) {
        try {
            for (ItemStackUtil itemStack : inventory.getAllItems()) {
                if (!itemStack.itemStack.getItem().equals(item)) continue;
                return itemStack.slotId;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return -1;
    }

    public static void clickSlot(int id) {
        if (id != -1) {
            try {
                inventory.mc.playerController.windowClick(inventory.mc.player.openContainer.windowId, inventory.getClickSlot(id), 0, ClickType.PICKUP, (EntityPlayer)inventory.mc.player);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public static void clickSlot(int id, int otherRows) {
        if (id != -1) {
            try {
                inventory.mc.playerController.windowClick(inventory.mc.player.openContainer.windowId, inventory.getClickSlot(id) + otherRows, 0, ClickType.PICKUP, (EntityPlayer)inventory.mc.player);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public static int getClickSlot(int id) {
        if (id == -1) {
            return id;
        }
        if (id < 9) {
            return id += 36;
        }
        if (id == 39) {
            id = 5;
        } else if (id == 38) {
            id = 6;
        } else if (id == 37) {
            id = 7;
        } else if (id == 36) {
            id = 8;
        } else if (id == 40) {
            id = 45;
        }
        return id;
    }

    public static ItemStack getItemStack(int id) {
        try {
            return inventory.mc.player.inventory.getStackInSlot(id);
        }
        catch (NullPointerException e) {
            return null;
        }
    }

    public static int getAmountOfItem(Item item) {
        int count = 0;
        for (ItemStackUtil itemStack : inventory.getAllItems()) {
            if (itemStack.itemStack == null || !itemStack.itemStack.getItem().equals(item)) continue;
            count += itemStack.itemStack.getCount();
        }
        return count;
    }

    public static boolean hasItem(Item item) {
        return inventory.getAmountOfItem(item) != 0;
    }

    public static ArrayList<ItemStackUtil> getAllItems() {
        ArrayList<ItemStackUtil> items = new ArrayList<ItemStackUtil>();
        for (int i = 0; i < 36; ++i) {
            items.add(new ItemStackUtil(inventory.getItemStack(i), i));
        }
        return items;
    }

    public static class ItemStackUtil {
        public ItemStack itemStack;
        public int slotId;

        public ItemStackUtil(ItemStack itemStack, int slotId) {
            this.itemStack = itemStack;
            this.slotId = slotId;
        }
    }
}


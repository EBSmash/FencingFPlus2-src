//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  org.lwjgl.input.Mouse
 */
package org.fenci.fencingfplus2.module.movement;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;
import org.fenci.fencingfplus2.utils.ClientMessage;
import org.lwjgl.input.Mouse;

public class MCP
extends Module {
    int pearls;
    Item oldSlot;
    private boolean clicked;

    public MCP() {
        super("MCP", "Throws a pearl whenever you middle click your mouse", Category.MOVEMENT);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("ChatAnnounce", this, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("SwitchBack", this, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("NoPearlBlock", this, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("NoPearlPlayer", this, false));
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        if (MCP.mc.player != null && MCP.mc.world != null) {
            boolean chat = FencingFPlus.instance.settingsManager.getSettingByName(this, "ChatAnnounce").getValBoolean();
            boolean switchback = FencingFPlus.instance.settingsManager.getSettingByName(this, "SwitchBack").getValBoolean();
            boolean noPearlBlock = FencingFPlus.instance.settingsManager.getSettingByName(this, "NoPearlBlock").getValBoolean();
            boolean noPearlPlayer = FencingFPlus.instance.settingsManager.getSettingByName(this, "NoPearlPlayer").getValBoolean();
            if (Mouse.isButtonDown((int)2)) {
                this.oldSlot = MCP.mc.player.getHeldItemMainhand().getItem();
                if (!this.clicked) {
                    RayTraceResult result;
                    this.pearls = MCP.mc.player.inventory.mainInventory.stream().filter(stack -> stack.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();
                    if (!this.doesNextSlotHavePearl()) {
                        ClientMessage.sendMessage("No pearl found in hotbar");
                    }
                    if (noPearlPlayer && (result = MCP.mc.objectMouseOver) != null && result.typeOfHit == RayTraceResult.Type.ENTITY) {
                        return;
                    }
                    if (noPearlBlock && (result = MCP.mc.objectMouseOver) != null && result.typeOfHit == RayTraceResult.Type.BLOCK) {
                        return;
                    }
                    if (chat && this.doesNextSlotHavePearl()) {
                        ClientMessage.sendMessage("Throwing Pearl");
                    }
                    if (MCP.mc.player.getHeldItemMainhand().getItem() == Items.ENDER_PEARL) {
                        MCP.mc.playerController.processRightClick((EntityPlayer)MCP.mc.player, (World)MCP.mc.world, EnumHand.MAIN_HAND);
                    }
                    if (this.doesNextSlotHavePearl()) {
                        EnumHand hand = EnumHand.MAIN_HAND;
                        if (MCP.mc.player.getHeldItemOffhand().getItem() == Items.ENDER_PEARL) {
                            hand = EnumHand.OFF_HAND;
                        } else if (MCP.mc.player.getHeldItemMainhand().getItem() != Items.ENDER_PEARL) {
                            for (int i = 0; i < 9; ++i) {
                                if (MCP.mc.player.inventory.getStackInSlot(i).getItem() != Items.ENDER_PEARL) continue;
                                MCP.mc.player.inventory.currentItem = i;
                                MCP.mc.playerController.processRightClick((EntityPlayer)MCP.mc.player, (World)MCP.mc.world, EnumHand.MAIN_HAND);
                                if (!switchback || !this.doesNextSlotHaveLastItem()) continue;
                                for (int a = 0; a < 9; ++a) {
                                    if (MCP.mc.player.inventory.getStackInSlot(a).getItem() != this.oldSlot) continue;
                                    MCP.mc.player.inventory.currentItem = a;
                                    MCP.mc.playerController.processRightClick((EntityPlayer)MCP.mc.player, (World)MCP.mc.world, EnumHand.MAIN_HAND);
                                }
                            }
                        }
                    }
                }
                this.clicked = true;
            } else {
                this.clicked = false;
            }
        }
    }

    private boolean doesNextSlotHavePearl() {
        for (int i = 0; i < 9; ++i) {
            MCP.mc.player.inventory.getStackInSlot(i);
            if (MCP.mc.player.inventory.getStackInSlot(i).getItem() != Items.ENDER_PEARL) continue;
            return true;
        }
        return false;
    }

    private boolean doesNextSlotHaveLastItem() {
        for (int i = 0; i < 9; ++i) {
            MCP.mc.player.inventory.getStackInSlot(i);
            if (MCP.mc.player.inventory.getStackInSlot(i).getItem() != this.oldSlot) continue;
            return true;
        }
        return false;
    }
}


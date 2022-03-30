//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package org.fenci.fencingfplus2.module.combat;

import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.event.events.PacketEvent;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;
import org.fenci.fencingfplus2.utils.ClientMessage;

public class AntiTotemFail2b2t
extends Module {
    int totems = 0;

    public AntiTotemFail2b2t() {
        super("AntiTotemFail2b2t", "Attempts to not let you totem fail.", Category.COMBAT);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Health Threshold", this, 7.0, 0.0, 36.0, false));
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send event) {
        this.totems = AntiTotemFail2b2t.mc.player.inventory.mainInventory.stream().filter(stack -> stack.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();
        if (this.totems > 0) {
            float threshold = (float)FencingFPlus.instance.settingsManager.getSettingByName(this, "Health Threshold").getValDouble();
            if (this.playerHealth() <= threshold && AntiTotemFail2b2t.mc.player.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).getItem() != Items.TOTEM_OF_UNDYING) {
                ClientMessage.sendMessage("No totem in offhand; disabling movement");
                if (event.getPacket() instanceof CPacketPlayer) {
                    event.setCanceled(true);
                    ClientMessage.sendMessage("Movement Disabled");
                }
            }
        }
    }

    public float playerHealth() {
        return AntiTotemFail2b2t.mc.player.getHealth() + AntiTotemFail2b2t.mc.player.getAbsorptionAmount();
    }
}


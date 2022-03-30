//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package org.fenci.fencingfplus2.module.combat;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.utils.ClientMessage;

public class TotemFailWarning
extends Module {
    int totems = 0;

    public TotemFailWarning() {
        super("TotemFailWarning", "Sends a chat message in chat if you are totem failing", Category.COMBAT);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        if (TotemFailWarning.mc.player == null || TotemFailWarning.mc.world == null) {
            return;
        }
        this.totems = TotemFailWarning.mc.player.inventory.mainInventory.stream().filter(stack -> stack.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();
        TotemFailWarning.mc.player.getHeldItemOffhand();
        if (FencingFPlus.instance.moduleManager.getModule("AutoTotem").isToggled() && (TotemFailWarning.mc.player.getHeldItemOffhand().getItem() != Items.TOTEM_OF_UNDYING & this.totems > 0 || TotemFailWarning.mc.player.getHeldItemOffhand().getItem() != Items.GOLDEN_APPLE || TotemFailWarning.mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL)) {
            ClientMessage.sendOverwriteClientMessage("There is no totem in your offhand!");
        }
    }
}


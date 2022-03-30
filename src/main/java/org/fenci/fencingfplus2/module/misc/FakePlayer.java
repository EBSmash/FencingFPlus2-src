//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Enchantments
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.GameType
 *  net.minecraft.world.World
 */
package org.fenci.fencingfplus2.module.misc;

import com.mojang.authlib.GameProfile;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.UUID;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;
import org.fenci.fencingfplus2.utils.ClientMessage;

public class FakePlayer
extends Module {
    private final ItemStack[] armors = new ItemStack[]{new ItemStack((Item)Items.DIAMOND_BOOTS), new ItemStack((Item)Items.DIAMOND_LEGGINGS), new ItemStack((Item)Items.DIAMOND_CHESTPLATE), new ItemStack((Item)Items.DIAMOND_HELMET)};

    public FakePlayer() {
        super("FakePlayer", "Makes a clientside player", Category.MISC);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Health", this, 1.0, 1.0, 20.0, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Armor", this, true));
    }

    @Override
    public void onEnable() {
        if (FakePlayer.mc.player == null || FakePlayer.mc.player.isDead) {
            this.toggle();
            return;
        }
        float health = (float)FencingFPlus.instance.settingsManager.getSettingByName(this, "Health").getValDouble();
        boolean Armor = FencingFPlus.instance.settingsManager.getSettingByName(this, "Armor").getValBoolean();
        EntityOtherPlayerMP clonedPlayer = new EntityOtherPlayerMP((World)FakePlayer.mc.world, new GameProfile(UUID.fromString("976bd8e3-8379-4ad5-8dba-4fe20e42ee6e"), "FencingF"));
        clonedPlayer.copyLocationAndAnglesFrom((Entity)FakePlayer.mc.player);
        clonedPlayer.rotationYawHead = FakePlayer.mc.player.rotationYawHead;
        clonedPlayer.rotationYaw = FakePlayer.mc.player.rotationYaw;
        clonedPlayer.rotationPitch = FakePlayer.mc.player.rotationPitch;
        clonedPlayer.setGameType(GameType.SURVIVAL);
        clonedPlayer.setHealth(health);
        FakePlayer.mc.world.addEntityToWorld(-1234, (Entity)clonedPlayer);
        for (Module mod : FencingFPlus.instance.moduleManager.getModuleList()) {
            if (!mod.getName().equalsIgnoreCase("ChatNotifier") || !mod.isToggled()) continue;
            ClientMessage.sendOverwriteClientMessage(ChatFormatting.GREEN + this.getName() + ":" + ChatFormatting.BOLD + " Enabled");
        }
        for (int i = 0; i < 4; ++i) {
            if (!Armor) continue;
            ItemStack item = this.armors[i];
            item.addEnchantment(i == 2 ? Enchantments.BLAST_PROTECTION : Enchantments.PROTECTION, 4);
            clonedPlayer.inventory.armorInventory.set(i, item);
            clonedPlayer.onLivingUpdate();
        }
    }

    @Override
    public void onDisable() {
        if (FakePlayer.mc.world != null) {
            FakePlayer.mc.world.removeEntityFromWorld(-1234);
        }
        for (Module mod : FencingFPlus.instance.moduleManager.getModuleList()) {
            if (!mod.getName().equalsIgnoreCase("ChatNotifier") || !mod.isToggled()) continue;
            ClientMessage.sendOverwriteClientMessage(ChatFormatting.RED + this.getName() + ":" + ChatFormatting.BOLD + " Disabled");
        }
    }
}


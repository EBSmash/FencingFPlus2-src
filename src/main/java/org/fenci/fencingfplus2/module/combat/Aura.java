//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.util.EnumHand
 *  net.minecraft.world.World
 */
package org.fenci.fencingfplus2.module.combat;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;
import org.fenci.fencingfplus2.utils.ClientMessage;

public class Aura
extends Module {
    Item disableItem;

    public Aura() {
        super("Aura", "KillAura", Category.COMBAT);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Range", this, 4.0, 1.0, 10.0, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("SwitchMode", this, "Switch", Arrays.asList("Switch", "Off")));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("WeaponsCheck", this, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Delay", this, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Players", this, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("HostileMobs", this, false));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("PassiveMobs", this, false));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("OnDisableSwitch", this, "None", Arrays.asList("None", "Crystals", "Gapples")));
    }

    @Override
    public void onUpdate() {
        int range = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "Range").getValDouble();
        if (Aura.mc.player == null || Aura.mc.player.isDead) {
            return;
        }
        List<Entity> targets = Aura.mc.world.loadedEntityList.stream().filter(entity -> entity != Aura.mc.player).filter(entity -> Aura.mc.player.getDistance(entity) <= (float)range).filter(entity -> !entity.isDead).filter(this::attackCheck).sorted(Comparator.comparing(s -> Float.valueOf(Aura.mc.player.getDistance(s)))).collect(Collectors.toList());
        targets.forEach(this::attack);
    }

    @Override
    public void onEnable() {
        ClientMessage.sendOverwriteClientMessage(ChatFormatting.GREEN + this.getName() + ":" + ChatFormatting.BOLD + " Enabled");
        String smode = FencingFPlus.instance.settingsManager.getSettingByName(this, "SwitchMode").getValString();
        if (smode.equals("Switch") && this.doesNextSlotHaveSword() && Aura.mc.player.getHeldItemMainhand().getItem() != Items.DIAMOND_SWORD) {
            for (int i = 0; i < 9; ++i) {
                if (Aura.mc.player.inventory.getStackInSlot(i).getItem() != Items.DIAMOND_SWORD) continue;
                Aura.mc.player.inventory.currentItem = i;
                Aura.mc.playerController.processRightClick((EntityPlayer)Aura.mc.player, (World)Aura.mc.world, EnumHand.MAIN_HAND);
            }
        }
    }

    @Override
    public void onDisable() {
        ClientMessage.sendOverwriteClientMessage(ChatFormatting.RED + this.getName() + ":" + ChatFormatting.BOLD + " Disabled");
        String onDisableSwitch = FencingFPlus.instance.settingsManager.getSettingByName(this, "OnDisableSwitch").getValString();
        if (this.doesNextSlotHaveDisableItem() && Aura.mc.player.getHeldItemMainhand().getItem() != this.getDisableItem()) {
            for (int i = 0; i < 9; ++i) {
                if (Aura.mc.player.inventory.getStackInSlot(i).getItem() != this.getDisableItem()) continue;
                Aura.mc.player.inventory.currentItem = i;
                Aura.mc.playerController.processRightClick((EntityPlayer)Aura.mc.player, (World)Aura.mc.world, EnumHand.MAIN_HAND);
            }
        }
    }

    public void attack(Entity e) {
        boolean weaponsCheck = FencingFPlus.instance.settingsManager.getSettingByName(this, "WeaponsCheck").getValBoolean();
        boolean delay = FencingFPlus.instance.settingsManager.getSettingByName(this, "Delay").getValBoolean();
        if (weaponsCheck && Aura.mc.player.getHeldItemMainhand().getItem() != Items.DIAMOND_SWORD) {
            return;
        }
        if (delay && Aura.mc.player.getCooledAttackStrength(0.0f) >= 1.0f) {
            Aura.mc.playerController.attackEntity((EntityPlayer)Aura.mc.player, e);
            Aura.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
        if (!delay) {
            Aura.mc.playerController.attackEntity((EntityPlayer)Aura.mc.player, e);
            Aura.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }

    private boolean attackCheck(Entity entity) {
        boolean players = FencingFPlus.instance.settingsManager.getSettingByName(this, "Players").getValBoolean();
        boolean hostileMobs = FencingFPlus.instance.settingsManager.getSettingByName(this, "HostileMobs").getValBoolean();
        boolean passiveMobs = FencingFPlus.instance.settingsManager.getSettingByName(this, "PassiveMobs").getValBoolean();
        if (players && entity instanceof EntityPlayer && ((EntityPlayer)entity).getHealth() > 0.0f) {
            return true;
        }
        if (passiveMobs && entity instanceof EntityAnimal) {
            return !(entity instanceof EntityTameable);
        }
        return hostileMobs && entity instanceof EntityMob;
    }

    private boolean doesNextSlotHaveSword() {
        for (int i = 0; i < 9; ++i) {
            if (Aura.mc.player.inventory.getStackInSlot(i) == null || Aura.mc.player.inventory.getStackInSlot(i).getItem() != Items.DIAMOND_SWORD) continue;
            return true;
        }
        return false;
    }

    public Item getDisableItem() {
        String onDisableSwitch = FencingFPlus.instance.settingsManager.getSettingByName(this, "OnDisableSwitch").getValString();
        if (onDisableSwitch.equals("Crystals")) {
            this.disableItem = Items.END_CRYSTAL;
        }
        if (onDisableSwitch.equals("Gapples")) {
            this.disableItem = Items.GOLDEN_APPLE;
        }
        if (onDisableSwitch.equals("None")) {
            this.disableItem = Aura.mc.player.getHeldItemMainhand().getItem();
        }
        return this.disableItem;
    }

    private boolean doesNextSlotHaveDisableItem() {
        for (int i = 0; i < 9; ++i) {
            if (Aura.mc.player.inventory.getStackInSlot(i) == null || Aura.mc.player.inventory.getStackInSlot(i).getItem() != this.getDisableItem()) continue;
            return true;
        }
        return false;
    }
}


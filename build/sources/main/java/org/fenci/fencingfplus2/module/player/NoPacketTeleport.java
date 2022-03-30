//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.World
 */
package org.fenci.fencingfplus2.module.player;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;

public class NoPacketTeleport
extends Module {
    private double posX;
    private double posY;
    private double posZ;
    private float pitch;
    private float yaw;
    private EntityOtherPlayerMP clonedPlayer;
    private boolean isRidingEntity;
    private Entity ridingEntity;

    public NoPacketTeleport() {
        super("NoPacketTeleport", "lmao idfk", Category.PLAYER);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Speed", this, 5.0, 1.0, 10.0, true));
    }

    @Override
    public void onEnable() {
        if (NoPacketTeleport.mc.player != null) {
            float speed = (float)FencingFPlus.instance.settingsManager.getSettingByName(this, "Speed").getValDouble();
            this.isRidingEntity = NoPacketTeleport.mc.player.getRidingEntity() != null;
            boolean bl = this.isRidingEntity;
            if (NoPacketTeleport.mc.player.getRidingEntity() == null) {
                this.posX = NoPacketTeleport.mc.player.posX;
                this.posY = NoPacketTeleport.mc.player.posY;
                this.posZ = NoPacketTeleport.mc.player.posZ;
            } else {
                this.ridingEntity = NoPacketTeleport.mc.player.getRidingEntity();
                NoPacketTeleport.mc.player.dismountRidingEntity();
            }
            this.pitch = NoPacketTeleport.mc.player.rotationPitch;
            this.yaw = NoPacketTeleport.mc.player.rotationYaw;
            this.clonedPlayer = new EntityOtherPlayerMP((World)NoPacketTeleport.mc.world, mc.getSession().getProfile());
            this.clonedPlayer.copyLocationAndAnglesFrom((Entity)NoPacketTeleport.mc.player);
            this.clonedPlayer.rotationYawHead = NoPacketTeleport.mc.player.rotationYawHead;
            NoPacketTeleport.mc.world.addEntityToWorld(-100, (Entity)this.clonedPlayer);
            NoPacketTeleport.mc.player.capabilities.setPlayerWalkSpeed(speed * 100.0f);
            NoPacketTeleport.mc.player.noClip = true;
        }
    }

    @Override
    public void onDisable() {
        EntityPlayerSP localPlayer = NoPacketTeleport.mc.player;
        if (localPlayer != null) {
            NoPacketTeleport.mc.player.setPositionAndRotation(this.posX, this.posY, this.posZ, this.yaw, this.pitch);
            NoPacketTeleport.mc.world.removeEntityFromWorld(-100);
            this.clonedPlayer = null;
            this.posZ = 0.0;
            this.posY = 0.0;
            this.posX = 0.0;
            this.yaw = 0.0f;
            this.pitch = 0.0f;
            NoPacketTeleport.mc.player.capabilities.isFlying = false;
            NoPacketTeleport.mc.player.capabilities.setFlySpeed(500.0f);
            NoPacketTeleport.mc.player.noClip = false;
            NoPacketTeleport.mc.player.motionZ = 0.0;
            NoPacketTeleport.mc.player.motionY = 0.0;
            NoPacketTeleport.mc.player.motionX = 0.0;
            if (this.isRidingEntity) {
                NoPacketTeleport.mc.player.startRiding(this.ridingEntity, true);
            }
        }
    }
}


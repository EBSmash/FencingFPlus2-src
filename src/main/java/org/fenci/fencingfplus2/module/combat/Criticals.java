//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 *  net.minecraft.network.play.client.CPacketUseEntity
 *  net.minecraft.network.play.client.CPacketUseEntity$Action
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package org.fenci.fencingfplus2.module.combat;

import java.util.Arrays;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.event.events.PacketEvent;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;

public class Criticals
extends Module {
    public Criticals() {
        super("Criticals", "Lots of damage (skidded from spidersense)", Category.COMBAT);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Mode", this, "Packet", Arrays.asList("Packet", "Jump", "NCPStrict", "MiniJump")));
    }

    @SubscribeEvent
    public void onPacket(PacketEvent.Send event) {
        String mode = FencingFPlus.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        if (event.getPacket() instanceof CPacketUseEntity) {
            CPacketUseEntity packet = (CPacketUseEntity)event.getPacket();
            if (packet.getAction() != CPacketUseEntity.Action.ATTACK || !(packet.getEntityFromWorld((World)Criticals.mc.world) instanceof EntityLivingBase) || !Criticals.mc.player.onGround || Criticals.mc.player.isInLava() || Criticals.mc.player.isInWater()) {
                return;
            }
            switch (mode) {
                case "Jump": {
                    Criticals.mc.player.jump();
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY - 0.05, Criticals.mc.player.posZ, false));
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                    break;
                }
                case "Packet": {
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.3, Criticals.mc.player.posZ, false));
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                    break;
                }
                case "MiniJump": {
                    Criticals.mc.player.motionY = 0.2;
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY - 0.02, Criticals.mc.player.posZ, false));
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                    break;
                }
                case "NCPStrict": {
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.062602401692772, Criticals.mc.player.posZ, false));
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.0726023996066094, Criticals.mc.player.posZ, false));
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                }
            }
        }
    }
}


//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.MobEffects
 *  net.minecraft.network.play.server.SPacketExplosion
 *  net.minecraftforge.client.event.RenderBlockOverlayEvent
 *  net.minecraftforge.client.event.RenderBlockOverlayEvent$OverlayType
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package org.fenci.fencingfplus2.module.render;

import net.minecraft.init.MobEffects;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.event.events.PacketEvent;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;

public class NoRender
extends Module {
    public NoRender() {
        super("No Render", "Allows you to not render stuff", Category.RENDER);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Nausea", this, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Blindness", this, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Weather", this, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Hurtcam", this, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Explosions", this, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Fire", this, true));
    }

    @SubscribeEvent
    public void renderBlockEvent(RenderBlockOverlayEvent event) {
        if (NoRender.mc.player != null && NoRender.mc.world != null) {
            boolean nausea = FencingFPlus.instance.settingsManager.getSettingByName(this, "Nausea").getValBoolean();
            boolean blindness = FencingFPlus.instance.settingsManager.getSettingByName(this, "Blindness").getValBoolean();
            boolean weather = FencingFPlus.instance.settingsManager.getSettingByName(this, "Weather").getValBoolean();
            boolean fire = FencingFPlus.instance.settingsManager.getSettingByName(this, "Fire").getValBoolean();
            if (blindness && NoRender.mc.player.isPotionActive(MobEffects.BLINDNESS)) {
                NoRender.mc.player.removePotionEffect(MobEffects.BLINDNESS);
            }
            if (nausea && NoRender.mc.player.isPotionActive(MobEffects.NAUSEA)) {
                NoRender.mc.player.removePotionEffect(MobEffects.NAUSEA);
            }
            if (fire && event.getOverlayType() == RenderBlockOverlayEvent.OverlayType.FIRE) {
                event.setCanceled(true);
            }
            if (weather) {
                NoRender.mc.world.setRainStrength(0.0f);
            }
        }
    }

    @SubscribeEvent
    public void onPacket(PacketEvent.Receive event) {
        boolean explosions = FencingFPlus.instance.settingsManager.getSettingByName(this, "Explosions").getValBoolean();
        if (event.getPacket() instanceof SPacketExplosion && explosions) {
            event.setCanceled(true);
        }
    }
}


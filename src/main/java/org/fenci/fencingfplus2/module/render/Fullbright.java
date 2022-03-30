//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 */
package org.fenci.fencingfplus2.module.render;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Arrays;
import java.util.Objects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;
import org.fenci.fencingfplus2.utils.ClientMessage;

public class Fullbright
extends Module {
    public Fullbright() {
        super("Fullbright", "Makes your game brighter", Category.RENDER);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Mode", this, "Gamma", Arrays.asList("Gamma", "Potion")));
    }

    @Override
    public void onUpdate() {
        String mode = FencingFPlus.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        if (mode.equals("Gamma")) {
            Fullbright.mc.player.isPotionActive(Objects.requireNonNull(Potion.getPotionById((int)16)));
            Fullbright.mc.player.removeActivePotionEffect(Potion.getPotionById((int)16));
            Fullbright.mc.gameSettings.gammaSetting = 1000.0f;
        }
        if (mode.equals("Potion")) {
            PotionEffect nightVision = new PotionEffect(Objects.requireNonNull(Potion.getPotionById((int)16)), Integer.MAX_VALUE, 0);
            Fullbright.mc.gameSettings.gammaSetting = 1.0f;
            nightVision.setPotionDurationMax(true);
            Fullbright.mc.player.addPotionEffect(nightVision);
        }
    }

    @Override
    public void onDisable() {
        Fullbright.mc.player.isPotionActive(Objects.requireNonNull(Potion.getPotionById((int)16)));
        Fullbright.mc.player.removeActivePotionEffect(Potion.getPotionById((int)16));
        Fullbright.mc.gameSettings.gammaSetting = 1.0f;
        for (Module mod : FencingFPlus.instance.moduleManager.getModuleList()) {
            if (!mod.getName().equalsIgnoreCase("ChatNotifier") || !mod.isToggled()) continue;
            ClientMessage.sendOverwriteClientMessage(ChatFormatting.RED + this.getName() + ":" + ChatFormatting.BOLD + " Disabled");
        }
    }
}


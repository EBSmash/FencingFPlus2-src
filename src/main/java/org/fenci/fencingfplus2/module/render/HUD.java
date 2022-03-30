//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$RenderTickEvent
 */
package org.fenci.fencingfplus2.module.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;
import org.fenci.fencingfplus2.utils.ClientMessage;

public class HUD
extends Module {
    public HUD() {
        super("HUD", "Allows you to modify your heads up display", Category.RENDER);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("ModuleList", this, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("FPS", this, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("FPSX", this, 100.0, 1.0, 930.0, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("FPSY", this, 10.0, 1.0, 930.0, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Watermark", this, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("WatermarkX", this, 4.0, 0.0, 600.0, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("WatermarkY", this, 4.0, 0.0, 600.0, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Coords", this, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("CoordsinChat", this, false));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("SpoofX", this, 0.0, 0.0, 999.0, false));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("SpoofZ", this, 0.0, 0.0, 999.0, false));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("SpoofMultiplierX", this, 100.0, 0.0, 999.0, false));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("SpoofMultiplierZ", this, 100.0, 0.0, 999.0, false));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("ScreenPosXCoords", this, 3.0, 0.0, 600.0, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("ScreenPosYCoords", this, 343.0, 0.0, 600.0, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("ClientSideCoords", this, false));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Server", this, false));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("ServerX", this, 4.0, 0.0, 600.0, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("ServerY", this, 4.0, 0.0, 600.0, true));
    }

    @SubscribeEvent
    public void onRender(TickEvent.RenderTickEvent event) {
        if (HUD.mc.player == null || HUD.mc.world == null) {
            return;
        }
        boolean arrayList = FencingFPlus.instance.settingsManager.getSettingByName(this, "ModuleList").getValBoolean();
        boolean fps = FencingFPlus.instance.settingsManager.getSettingByName(this, "FPS").getValBoolean();
        boolean coords = FencingFPlus.instance.settingsManager.getSettingByName(this, "Coords").getValBoolean();
        boolean watermark = FencingFPlus.instance.settingsManager.getSettingByName(this, "Watermark").getValBoolean();
        int waterX = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "WatermarkX").getValDouble();
        int waterY = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "WatermarkY").getValDouble();
        int X = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "ScreenPosXCoords").getValDouble();
        int Y = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "ScreenPosYCoords").getValDouble();
        float spoofX = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "SpoofX").getValDouble();
        float spoofZ = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "SpoofZ").getValDouble();
        float spoofMultiplierX = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "SpoofMultiplierX").getValDouble();
        float spoofMultiplierZ = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "SpoofMultiplierZ").getValDouble();
        if (arrayList) {
            int inventorytotems = HUD.mc.player.inventory.mainInventory.stream().filter(stack -> stack.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();
            ScaledResolution srr = new ScaledResolution(Minecraft.getMinecraft());
            int y = 2;
            for (Module mod : FencingFPlus.instance.moduleManager.getModuleList()) {
                if (mod.getName().equalsIgnoreCase("HUD") || !mod.isToggled() || !mod.visible) continue;
                FontRenderer frr = Minecraft.getMinecraft().fontRenderer;
                HUD.mc.fontRenderer.drawStringWithShadow(mod.getName(), (float)(srr.getScaledWidth() - HUD.mc.fontRenderer.getStringWidth(mod.getName()) - 1), (float)y, 0xFFFFFF);
                y += frr.FONT_HEIGHT;
            }
        }
        if (HUD.mc.currentScreen instanceof GuiChat) {
            return;
        }
        if (coords) {
            float finalspoofX = spoofX * spoofMultiplierX;
            float finalspoofZ = spoofZ * spoofMultiplierZ;
            float netherX = (float)(HUD.mc.player.posX / 8.0) + finalspoofX * 8.0f;
            float netherY = (float)HUD.mc.player.posY;
            float netherZ = (float)(HUD.mc.player.posZ / 8.0) + finalspoofZ * 8.0f;
            float owX = (float)(HUD.mc.player.posX * 8.0) + finalspoofX;
            float owY = (float)HUD.mc.player.posY;
            float owZ = (float)(HUD.mc.player.posZ * 8.0) + finalspoofZ;
            float currX = (float)HUD.mc.player.posX + finalspoofX;
            float currY = (float)HUD.mc.player.posY;
            float currZ = (float)HUD.mc.player.posZ + finalspoofZ;
            double owX2 = (double)Math.round((double)owX * 10.0) / 10.0 / 8.0;
            double owY2 = (double)Math.round((double)owY * 10.0) / 10.0;
            double owZ2 = (double)Math.round((double)owZ * 10.0) / 10.0 / 8.0;
            double netherX2 = (double)Math.round((double)netherX * 10.0) / 10.0;
            double netherY2 = (double)Math.round((double)netherY * 10.0) / 10.0;
            double netherZ2 = (double)Math.round((double)netherZ * 10.0) / 10.0;
            double currX2 = (double)Math.round((double)currX * 10.0) / 10.0;
            double currY2 = (double)Math.round((double)currY * 10.0) / 10.0;
            double currZ2 = (double)Math.round((double)currZ * 10.0) / 10.0;
            if (HUD.mc.player.dimension == 0) {
                HUD.mc.fontRenderer.drawStringWithShadow("XYZ [" + currX2 + " " + currY2 + " " + currZ2 + "] [" + netherX2 + " " + netherY2 + " " + netherZ2 + "]", (float)X, (float)Y, 0xFFFFFF);
            }
            if (HUD.mc.player.dimension == -1) {
                HUD.mc.fontRenderer.drawStringWithShadow("XYZ [" + currX2 + " " + currY2 + " " + currZ2 + "] [" + owX2 + " " + owY2 + " " + owZ2 + "]", (float)X, (float)Y, 0xD3443D);
            }
            if (HUD.mc.player.dimension == 1) {
                HUD.mc.fontRenderer.drawStringWithShadow("XYZ [" + currX2 + " " + currY2 + " " + currZ2 + "]", (float)X, (float)Y, 14048757);
            }
        }
        if (watermark) {
            HUD.mc.fontRenderer.drawStringWithShadow("FencingF", (float)waterX, (float)waterY, -1);
            HUD.mc.fontRenderer.drawStringWithShadow("+2", (float)(waterX + 46), (float)waterY, 7025269);
        }
        if (fps) {
            float fpsx = (float)FencingFPlus.instance.settingsManager.getSettingByName(this, "FPSX").getValDouble();
            float fpsy = (float)FencingFPlus.instance.settingsManager.getSettingByName(this, "FPSY").getValDouble();
            float frames = Minecraft.getDebugFPS();
            HUD.mc.fontRenderer.drawStringWithShadow("FPS: ", fpsx, fpsy, -1);
            HUD.mc.fontRenderer.drawStringWithShadow("" + Minecraft.getDebugFPS(), fpsx + 21.0f, fpsy, 0xAA0000);
        }
    }

    @Override
    public void onUpdate() {
        if (HUD.mc.player == null || HUD.mc.world == null) {
            return;
        }
        int spoofX = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "SpoofX").getValDouble();
        int spoofZ = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "SpoofZ").getValDouble();
        int spoofMultiplierX = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "SpoofMultiplierX").getValDouble();
        int spoofMultiplierZ = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "SpoofMultiplierZ").getValDouble();
        boolean clientSideCoords = FencingFPlus.instance.settingsManager.getSettingByName(this, "CoordsinChat").getValBoolean();
        int finalspoofX = spoofX * spoofMultiplierX;
        int finalspoofZ = spoofZ * spoofMultiplierZ;
        if (clientSideCoords) {
            int coordX = (int)HUD.mc.player.posX + finalspoofX;
            int coordY = (int)HUD.mc.player.posY;
            int coordZ = (int)HUD.mc.player.posZ + finalspoofZ;
            ClientMessage.sendOverwriteClientMessage("X: " + coordX + " Y: " + coordY + " Z: " + coordZ);
        }
    }
}


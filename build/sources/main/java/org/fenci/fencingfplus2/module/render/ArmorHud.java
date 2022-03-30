//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderItem
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$RenderTickEvent
 */
package org.fenci.fencingfplus2.module.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;
import org.fenci.fencingfplus2.utils.ColourHolder;

public class ArmorHud
extends Module {
    private static final RenderItem itemRender = Minecraft.getMinecraft().getRenderItem();
    private final Minecraft mc = Minecraft.getMinecraft();
    public boolean on;

    public ArmorHud() {
        super("ArmorHud", "Renders your armor above your hotbar", Category.RENDER);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Percentage", this, false));
    }

    @SubscribeEvent
    public void onRender(TickEvent.RenderTickEvent event) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        boolean percentage = FencingFPlus.instance.settingsManager.getSettingByName(this, "Percentage").getValBoolean();
        GlStateManager.enableTexture2D();
        ScaledResolution resolution = new ScaledResolution(this.mc);
        int i = resolution.getScaledWidth() / 2;
        int iteration = 0;
        int y = resolution.getScaledHeight() - 55 - (this.mc.player.isInWater() ? 10 : 0);
        for (ItemStack is : this.mc.player.inventory.armorInventory) {
            ++iteration;
            if (is.isEmpty()) continue;
            int x = i - 90 + (9 - iteration) * 20 + 2;
            GlStateManager.enableDepth();
            ArmorHud.itemRender.zLevel = 200.0f;
            itemRender.renderItemAndEffectIntoGUI(is, x, y);
            itemRender.renderItemOverlayIntoGUI(this.mc.fontRenderer, is, x, y, "");
            ArmorHud.itemRender.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            String s = is.getCount() > 1 ? is.getCount() + "" : "";
            this.mc.fontRenderer.drawStringWithShadow(s, (float)(x + 19 - 2 - this.mc.fontRenderer.getStringWidth(s)), (float)(y + 9), 0xFFFFFF);
            if (!percentage) continue;
            float green = ((float)is.getMaxDamage() - (float)is.getItemDamage()) / (float)is.getMaxDamage();
            float red = 1.0f - green;
            int dmg = 100 - (int)(red * 100.0f);
            this.mc.fontRenderer.drawStringWithShadow(dmg + "", (float)(x + 8 - this.mc.fontRenderer.getStringWidth(dmg + "") / 2), (float)(y - 11), ColourHolder.toHex((int)(red * 255.0f), (int)(green * 255.0f), 0));
        }
        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
    }
}


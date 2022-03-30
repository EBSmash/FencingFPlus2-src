//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 */
package org.fenci.fencingfplus2.utils;

import java.awt.Color;
import net.minecraft.client.renderer.GlStateManager;

public class JColor
extends Color {
    private static final long serialVersionUID = 1L;

    public JColor(int rgb) {
        super(rgb);
    }

    public JColor(int rgba, boolean hasalpha) {
        super(rgba, hasalpha);
    }

    public JColor(int r, int g, int b) {
        super(r, g, b);
    }

    public JColor(int r, int g, int b, int a) {
        super(r, g, b, a);
    }

    public JColor(Color color) {
        super(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public JColor(JColor color, int a) {
        super(color.getRed(), color.getGreen(), color.getBlue(), a);
    }

    public static JColor fromHSB(float hue, float saturation, float brightness) {
        return new JColor(Color.getHSBColor(hue, saturation, brightness));
    }

    public float getHue() {
        return JColor.RGBtoHSB(this.getRed(), this.getGreen(), this.getBlue(), null)[0];
    }

    public float getSaturation() {
        return JColor.RGBtoHSB(this.getRed(), this.getGreen(), this.getBlue(), null)[1];
    }

    public float getBrightness() {
        return JColor.RGBtoHSB(this.getRed(), this.getGreen(), this.getBlue(), null)[2];
    }

    public void glColor() {
        GlStateManager.color((float)((float)this.getRed() / 255.0f), (float)((float)this.getGreen() / 255.0f), (float)((float)this.getBlue() / 255.0f), (float)((float)this.getAlpha() / 255.0f));
    }
}


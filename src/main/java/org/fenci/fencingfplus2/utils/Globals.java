//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package org.fenci.fencingfplus2.utils;

import java.util.Random;
import net.minecraft.client.Minecraft;

public interface Globals {
    public static final Minecraft mc = Minecraft.getMinecraft();
    public static final Random random = new Random();
    public static final char SECTIONSIGN = '\u00a7';

    default public boolean nullCheck() {
        return Globals.mc.player == null || Globals.mc.world == null;
    }
}


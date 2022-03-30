//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 */
package org.fenci.fencingfplus2.module.render;

import net.minecraft.client.gui.GuiScreen;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;

public class ClickGUI
extends Module {
    public ClickGUI() {
        super("ClickGUI", "menu.skeet", Category.RENDER);
        this.setKey(37);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        mc.displayGuiScreen((GuiScreen)FencingFPlus.instance.clickGui);
        this.setToggled(false);
    }
}


//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  org.lwjgl.opengl.GL11
 */
package org.fenci.fencingfplus2.clickgui.component.components;

import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.clickgui.ClickGui;
import org.fenci.fencingfplus2.clickgui.component.Component;
import org.fenci.fencingfplus2.clickgui.component.Frame;
import org.fenci.fencingfplus2.clickgui.component.components.sub.Checkbox;
import org.fenci.fencingfplus2.clickgui.component.components.sub.Keybind;
import org.fenci.fencingfplus2.clickgui.component.components.sub.ModeButton;
import org.fenci.fencingfplus2.clickgui.component.components.sub.Slider;
import org.fenci.fencingfplus2.clickgui.component.components.sub.VisibleButton;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;
import org.lwjgl.opengl.GL11;

public class Button
extends Component {
    private final ArrayList<Component> subcomponents;
    private final int height;
    public Module mod;
    public Frame parent;
    public int offset;
    public boolean open;
    private boolean isHovered;

    public Button(Module mod, Frame parent, int offset) {
        this.mod = mod;
        this.parent = parent;
        this.offset = offset;
        this.subcomponents = new ArrayList();
        this.open = false;
        this.height = 12;
        int opY = offset + 12;
        if (FencingFPlus.instance.settingsManager.getSettingsByMod(mod) != null) {
            for (Setting s : FencingFPlus.instance.settingsManager.getSettingsByMod(mod)) {
                if (s.isCombo()) {
                    this.subcomponents.add(new ModeButton(s, this, mod, opY));
                    opY += 12;
                }
                if (s.isSlider()) {
                    this.subcomponents.add(new Slider(s, this, opY));
                    opY += 12;
                }
                if (!s.isCheck()) continue;
                this.subcomponents.add(new Checkbox(s, this, opY));
                opY += 12;
            }
        }
        this.subcomponents.add(new Keybind(this, opY));
        this.subcomponents.add(new VisibleButton(this, mod, opY));
    }

    @Override
    public void setOff(int newOff) {
        this.offset = newOff;
        int opY = this.offset + 12;
        for (Component comp : this.subcomponents) {
            comp.setOff(opY);
            opY += 12;
        }
    }

    @Override
    public void renderComponent() {
        Gui.drawRect((int)this.parent.getX(), (int)(this.parent.getY() + this.offset), (int)(this.parent.getX() + this.parent.getWidth()), (int)(this.parent.getY() + 12 + this.offset), (int)(this.isHovered ? (this.mod.isToggled() ? new Color(39, 90, 138, 100).darker().getRGB() : 65535) : (this.mod.isToggled() ? new Color(11, 76, 128, 100).getRGB() : 65535)));
        GL11.glPushMatrix();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.mod.getName(), (float)((this.parent.getX() + 2) * 2), (float)((this.parent.getY() + this.offset + 2) * 2 + 4), this.mod.isToggled() ? new Color(149, 214, 255).getRGB() : -13408615);
        if (this.subcomponents.size() > 2) {
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.open ? "V" : ">", (float)((this.parent.getX() + this.parent.getWidth() - 5) * 2), (float)((this.parent.getY() + this.offset + 2) * 2 + 4), -1);
        }
        GL11.glPopMatrix();
        if (this.open && !this.subcomponents.isEmpty()) {
            for (Component comp : this.subcomponents) {
                comp.renderComponent();
            }
            Gui.drawRect((int)(this.parent.getX() + 2), (int)(this.parent.getY() + this.offset + 12), (int)(this.parent.getX() + 3), (int)(this.parent.getY() + this.offset + (this.subcomponents.size() + 1) * 12), (int)ClickGui.color);
        }
    }

    @Override
    public int getHeight() {
        if (this.open) {
            return 12 * (this.subcomponents.size() + 1);
        }
        return 12;
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        this.isHovered = this.isMouseOnButton(mouseX, mouseY);
        if (!this.subcomponents.isEmpty()) {
            for (Component comp : this.subcomponents) {
                comp.updateComponent(mouseX, mouseY);
            }
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (this.isMouseOnButton(mouseX, mouseY) && button == 0) {
            this.mod.toggle();
        }
        if (this.isMouseOnButton(mouseX, mouseY) && button == 1) {
            this.open = !this.open;
            this.parent.refresh();
        }
        for (Component comp : this.subcomponents) {
            comp.mouseClicked(mouseX, mouseY, button);
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        for (Component comp : this.subcomponents) {
            comp.mouseReleased(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    public void keyTyped(char typedChar, int key) {
        for (Component comp : this.subcomponents) {
            comp.keyTyped(typedChar, key);
        }
    }

    public boolean isMouseOnButton(int x, int y) {
        return x > this.parent.getX() && x < this.parent.getX() + this.parent.getWidth() && y > this.parent.getY() + this.offset && y < this.parent.getY() + 12 + this.offset;
    }
}


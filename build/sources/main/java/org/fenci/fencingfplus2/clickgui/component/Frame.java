//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  org.lwjgl.opengl.GL11
 */
package org.fenci.fencingfplus2.clickgui.component;

import java.util.ArrayList;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.clickgui.ClickGui;
import org.fenci.fencingfplus2.clickgui.component.Component;
import org.fenci.fencingfplus2.clickgui.component.components.Button;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.lwjgl.opengl.GL11;

public class Frame {
    private final int width;
    private final int barHeight;
    public ArrayList<Component> components = new ArrayList();
    public Category category;
    public int dragX;
    public int dragY;
    private boolean open;
    private int y;
    private int x;
    private boolean isDragging;

    public Frame(Category cat) {
        this.category = cat;
        this.width = 55;
        this.x = 5;
        this.y = 5;
        this.barHeight = 13;
        this.dragX = 0;
        this.open = true;
        this.isDragging = false;
        int tY = this.barHeight;
        for (Module mod : FencingFPlus.instance.moduleManager.getModulesInCategory(this.category)) {
            Button modButton = new Button(mod, this, tY);
            this.components.add(modButton);
            tY += 12;
        }
    }

    public ArrayList<Component> getComponents() {
        return this.components;
    }

    public void setDrag(boolean drag) {
        this.isDragging = drag;
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void renderFrame(FontRenderer fontRenderer) {
        Gui.drawRect((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + this.barHeight), (int)ClickGui.color);
        GL11.glPushMatrix();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        fontRenderer.drawStringWithShadow(this.category.name(), (float)((this.x + 2) * 2 + 5), ((float)this.y + 2.5f) * 2.0f + 5.0f, 336699);
        fontRenderer.drawStringWithShadow(this.open ? "V" : ">", (float)((this.x + this.width - 10) * 2 + 5), ((float)this.y + 2.5f) * 2.0f + 5.0f, 336699);
        GL11.glPopMatrix();
        if (this.open && !this.components.isEmpty()) {
            for (Component component : this.components) {
                component.renderComponent();
            }
        }
    }

    public void refresh() {
        int off = this.barHeight;
        for (Component comp : this.components) {
            comp.setOff(off);
            off += comp.getHeight();
        }
    }

    public int getX() {
        return this.x;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public int getWidth() {
        return this.width;
    }

    public void updatePosition(int mouseX, int mouseY) {
        if (this.isDragging) {
            this.setX(mouseX - this.dragX);
            this.setY(mouseY - this.dragY);
        }
    }

    public boolean isWithinHeader(int x, int y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.barHeight;
    }
}


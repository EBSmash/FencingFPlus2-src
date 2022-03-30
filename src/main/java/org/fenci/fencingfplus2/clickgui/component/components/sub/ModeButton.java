//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  org.lwjgl.opengl.GL11
 */
package org.fenci.fencingfplus2.clickgui.component.components.sub;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.fenci.fencingfplus2.clickgui.component.Component;
import org.fenci.fencingfplus2.clickgui.component.components.Button;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;
import org.lwjgl.opengl.GL11;

public class ModeButton
extends Component {
    private final Button parent;
    private final Setting set;
    private final Module mod;
    private boolean hovered;
    private int offset;
    private int x;
    private int y;
    private int modeIndex;

    public ModeButton(Setting set, Button button, Module mod, int offset) {
        this.set = set;
        this.parent = button;
        this.mod = mod;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = offset;
        this.modeIndex = 0;
    }

    @Override
    public void setOff(int newOff) {
        this.offset = newOff;
    }

    @Override
    public void renderComponent() {
        Gui.drawRect((int)this.parent.parent.getX(), (int)(this.parent.parent.getY() + this.offset), (int)(this.parent.parent.getX() + this.parent.parent.getWidth()), (int)(this.parent.parent.getY() + this.offset + 12), (int)(this.hovered ? new Color(39, 90, 138, 100).getRGB() : 65535));
        Gui.drawRect((int)this.parent.parent.getX(), (int)(this.parent.parent.getY() + this.offset), (int)(this.parent.parent.getX() + 2), (int)(this.parent.parent.getY() + this.offset + 12), (int)65535);
        GL11.glPushMatrix();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.set.getName() + ": " + this.set.getValString(), (float)((this.parent.parent.getX() + 7) * 2), (float)((this.parent.parent.getY() + this.offset + 2) * 2 + 5), -1);
        GL11.glPopMatrix();
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        this.hovered = this.isMouseOnButton(mouseX, mouseY);
        this.y = this.parent.parent.getY() + this.offset;
        this.x = this.parent.parent.getX();
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        int maxIndex;
        if (this.isMouseOnButton(mouseX, mouseY) && button == 0 && this.parent.open) {
            maxIndex = this.set.getOptions().size();
            this.modeIndex = this.modeIndex + 1 > maxIndex ? 0 : ++this.modeIndex;
            try {
                this.set.setValString(this.set.getOptions().get(this.modeIndex));
            }
            catch (Exception e) {
                this.modeIndex = 0;
                this.set.setValString(this.set.getOptions().get(this.modeIndex));
            }
        }
        if (this.isMouseOnButton(mouseX, mouseY) && button == 1 && this.parent.open) {
            maxIndex = this.set.getOptions().size();
            this.modeIndex = this.modeIndex == 0 ? maxIndex - 1 : --this.modeIndex;
            try {
                this.set.setValString(this.set.getOptions().get(this.modeIndex));
            }
            catch (Exception e) {
                this.modeIndex = 0;
                this.set.setValString(this.set.getOptions().get(this.modeIndex));
            }
        }
    }

    public boolean isMouseOnButton(int x, int y) {
        return x > this.x && x < this.x + 55 && y > this.y && y < this.y + 12;
    }
}


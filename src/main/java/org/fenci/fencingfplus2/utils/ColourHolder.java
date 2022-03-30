/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package org.fenci.fencingfplus2.utils;

import java.awt.Color;
import org.lwjgl.opengl.GL11;

public class ColourHolder {
    int r;
    int g;
    int b;
    int a;

    public ColourHolder(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = 255;
    }

    public ColourHolder(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public static ColourHolder fromHex(int hex) {
        ColourHolder n = new ColourHolder(0, 0, 0);
        n.becomeHex(hex);
        return n;
    }

    public static int toHex(int r, int g, int b) {
        return 0xFF000000 | (r & 0xFF) << 16 | (g & 0xFF) << 8 | b & 0xFF;
    }

    public ColourHolder brighter() {
        return new ColourHolder(Math.min(this.r + 10, 255), Math.min(this.g + 10, 255), Math.min(this.b + 10, 255), this.getA());
    }

    public ColourHolder darker() {
        return new ColourHolder(Math.max(this.r - 10, 0), Math.max(this.g - 10, 0), Math.max(this.b - 10, 0), this.getA());
    }

    public void setGLColour() {
        this.setGLColour(-1, -1, -1, -1);
    }

    public void setGLColour(int dr, int dg, int db, int da) {
        GL11.glColor4f((float)((float)(dr == -1 ? this.r : dr) / 255.0f), (float)((float)(dg == -1 ? this.g : dg) / 255.0f), (float)((float)(db == -1 ? this.b : db) / 255.0f), (float)((float)(da == -1 ? this.a : da) / 255.0f));
    }

    public void becomeGLColour() {
    }

    public void becomeHex(int hex) {
        this.setR((hex & 0xFF0000) >> 16);
        this.setG((hex & 0xFF00) >> 8);
        this.setB(hex & 0xFF);
        this.setA(255);
    }

    public int toHex() {
        return ColourHolder.toHex(this.r, this.g, this.b);
    }

    public int getB() {
        return this.b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getG() {
        return this.g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getR() {
        return this.r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getA() {
        return this.a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public ColourHolder clone() throws CloneNotSupportedException {
        ColourHolder colourHolder = (ColourHolder)super.clone();
        return new ColourHolder(this.r, this.g, this.b, this.a);
    }

    public Color toJavaColour() {
        return new Color(this.r, this.g, this.b, this.a);
    }
}


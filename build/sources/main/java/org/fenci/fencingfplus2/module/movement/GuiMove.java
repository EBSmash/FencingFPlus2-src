//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.settings.KeyBinding
 *  org.lwjgl.input.Keyboard
 */
package org.fenci.fencingfplus2.module.movement;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.lwjgl.input.Keyboard;

public class GuiMove
extends Module {
    private static final KeyBinding[] keys = new KeyBinding[]{GuiMove.mc.gameSettings.keyBindForward, GuiMove.mc.gameSettings.keyBindBack, GuiMove.mc.gameSettings.keyBindLeft, GuiMove.mc.gameSettings.keyBindRight, GuiMove.mc.gameSettings.keyBindJump, GuiMove.mc.gameSettings.keyBindSprint};

    public GuiMove() {
        super("GuiMove", "Allows you to move even if your inventory is open", Category.MOVEMENT);
    }

    @Override
    public void onUpdate() {
        if (GuiMove.mc.currentScreen instanceof GuiChat || GuiMove.mc.currentScreen == null) {
            return;
        }
        for (KeyBinding bind : keys) {
            KeyBinding.setKeyBindState((int)bind.getKeyCode(), (boolean)Keyboard.isKeyDown((int)bind.getKeyCode()));
        }
        if (GuiMove.mc.currentScreen == null) {
            for (KeyBinding bind : keys) {
                if (Keyboard.isKeyDown((int)bind.getKeyCode())) continue;
                KeyBinding.setKeyBindState((int)bind.getKeyCode(), (boolean)false);
            }
        }
        if (!(GuiMove.mc.currentScreen instanceof GuiChat)) {
            if (Keyboard.isKeyDown((int)200)) {
                GuiMove.mc.player.rotationPitch -= 5.0f;
            }
            if (Keyboard.isKeyDown((int)208)) {
                GuiMove.mc.player.rotationPitch += 5.0f;
            }
            if (Keyboard.isKeyDown((int)205)) {
                GuiMove.mc.player.rotationYaw += 5.0f;
            }
            if (Keyboard.isKeyDown((int)203)) {
                GuiMove.mc.player.rotationYaw -= 5.0f;
            }
            if (GuiMove.mc.player.rotationPitch > 90.0f) {
                GuiMove.mc.player.rotationPitch = 90.0f;
            }
            if (GuiMove.mc.player.rotationPitch < -90.0f) {
                GuiMove.mc.player.rotationPitch = -90.0f;
            }
        }
    }
}


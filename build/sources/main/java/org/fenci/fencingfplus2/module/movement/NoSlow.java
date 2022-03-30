//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.MovementInput
 *  net.minecraftforge.client.event.InputUpdateEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package org.fenci.fencingfplus2.module.movement;

import net.minecraft.util.MovementInput;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;

public class NoSlow
extends Module {
    public NoSlow() {
        super("NoSlow", "NoSlow", Category.MOVEMENT);
    }

    @SubscribeEvent
    public void onInputUpdate(InputUpdateEvent event) {
        if (NoSlow.mc.player.isHandActive() && !NoSlow.mc.player.isRiding()) {
            MovementInput movementInput = event.getMovementInput();
            movementInput.moveStrafe *= 5.0f;
            MovementInput movementInput2 = event.getMovementInput();
            movementInput2.moveForward *= 5.0f;
        }
    }
}


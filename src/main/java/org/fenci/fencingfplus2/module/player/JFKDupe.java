//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.play.server.SPacketDisconnect
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package org.fenci.fencingfplus2.module.player;

import java.util.Objects;
import net.minecraft.network.play.server.SPacketDisconnect;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;

public class JFKDupe
extends Module {
    public JFKDupe() {
        super("JFKDupe", "Dupe?", Category.PLAYER);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        if (JFKDupe.mc.player != null && JFKDupe.mc.world != null && JFKDupe.mc.gameSettings.keyBindUseItem.isKeyDown()) {
            JFKDupe.mc.player.sendChatMessage(">I am currently duping thanks to JFK dupe on FencingF+2!");
            Objects.requireNonNull(mc.getConnection()).handleDisconnect(new SPacketDisconnect((ITextComponent)new TextComponentString("Dupe?")));
            this.toggle();
        }
    }
}


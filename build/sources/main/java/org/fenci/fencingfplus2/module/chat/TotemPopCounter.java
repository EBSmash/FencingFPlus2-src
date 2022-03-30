//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package org.fenci.fencingfplus2.module.chat;

import java.util.HashMap;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.utils.ClientMessage;

public class TotemPopCounter
extends Module {
    private static final TotemPopCounter INSTANCE = new TotemPopCounter();
    public static HashMap<String, Integer> TotemPopContainer = new HashMap();

    public TotemPopCounter() {
        super("TotemPopCounter", "Informs you in chat when someone pops a totem.", Category.CHAT);
    }

    @Override
    public void onUpdate() {
        if (TotemPopCounter.mc.player == null || TotemPopCounter.mc.world == null) {
            return;
        }
        if (!FencingFPlus.POP_MANAGER.toAnnouce.isEmpty()) {
            try {
                for (String string : FencingFPlus.POP_MANAGER.toAnnouce) {
                    ClientMessage.sendOverwriteClientMessage(string);
                }
                FencingFPlus.POP_MANAGER.toAnnouce.clear();
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }
}


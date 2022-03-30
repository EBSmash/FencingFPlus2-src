/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 */
package org.fenci.fencingfplus2.module.chat;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.utils.ClientMessage;

public class ChatNotifier
extends Module {
    public ChatNotifier() {
        super("ChatNotifier", "Shows when you Enable/Disable Modules in chat.", Category.CHAT);
    }

    @Override
    public void onDisable() {
        ClientMessage.sendOverwriteClientMessage(ChatFormatting.RED + this.getName() + ":" + ChatFormatting.BOLD + " Disabled");
    }
}


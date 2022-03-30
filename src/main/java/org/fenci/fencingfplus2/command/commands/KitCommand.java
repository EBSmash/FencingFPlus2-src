/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 */
package org.fenci.fencingfplus2.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.command.Command;
import org.fenci.fencingfplus2.utils.ClientMessage;

public class KitCommand
extends Command {
    public KitCommand() {
        super("autokit", "Auto kit selector", "autokit [kitname]");
    }

    @Override
    public void runCommand(String[] args) {
        if (args.length > 1) {
            try {
                if (FencingFPlus.instance.KIT_MANAGER.KitCount() != 0) {
                    FencingFPlus.instance.KIT_MANAGER.removeKit();
                }
                FencingFPlus.instance.KIT_MANAGER.addKit(args[1]);
                ClientMessage.sendMessage(ChatFormatting.BOLD + "Kit set to" + ChatFormatting.GOLD + " " + FencingFPlus.instance.KIT_MANAGER.getKit());
            }
            catch (Exception exception) {}
        } else {
            ClientMessage.sendErrorMessage("Invalid Syntax");
        }
    }
}


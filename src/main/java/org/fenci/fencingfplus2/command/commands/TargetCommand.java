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

public class TargetCommand
extends Command {
    public TargetCommand() {
        super("target", "Target players for autopvp", "target [player]");
    }

    @Override
    public void runCommand(String[] args) {
        if (args.length > 1) {
            try {
                if (FencingFPlus.instance.TARGET_MANAGER.targetCount() != 0) {
                    FencingFPlus.instance.TARGET_MANAGER.removeTarget();
                }
                FencingFPlus.instance.TARGET_MANAGER.addTarget(args[1]);
                ClientMessage.sendModMessage(ChatFormatting.BOLD + "Targeting" + ChatFormatting.GOLD + " " + FencingFPlus.instance.TARGET_MANAGER.getTarget());
            }
            catch (Exception exception) {}
        } else {
            ClientMessage.sendErrorMessage("Invalid Syntax");
        }
    }
}


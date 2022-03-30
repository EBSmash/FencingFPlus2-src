/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.text.TextFormatting
 */
package org.fenci.fencingfplus2.command.commands;

import net.minecraft.util.text.TextFormatting;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.command.Command;
import org.fenci.fencingfplus2.utils.ClientMessage;

public class CommandsCommand
extends Command {
    public CommandsCommand() {
        super("commands", "shows all the possible commands", "command");
    }

    @Override
    public void runCommand(String[] args) {
        try {
            for (Command c : FencingFPlus.instance.COMMAND_MANAGER.commands) {
                ClientMessage.sendMessage(TextFormatting.WHITE + c.getName() + TextFormatting.GRAY + " " + c.getDescription() + " syntax: " + c.getSyntax());
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}


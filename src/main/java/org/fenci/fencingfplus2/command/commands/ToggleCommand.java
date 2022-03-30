/*
 * Decompiled with CFR 0.152.
 */
package org.fenci.fencingfplus2.command.commands;

import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.command.Command;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.utils.ClientMessage;

public class ToggleCommand
extends Command {
    public ToggleCommand() {
        super("toggle", "Allows you to toggle a module.", "toggle [module]");
    }

    @Override
    public void runCommand(String[] args) {
        if (args.length > 1) {
            try {
                for (Module m : FencingFPlus.instance.moduleManager.getModuleList()) {
                    if (!m.getName().equalsIgnoreCase(args[1])) continue;
                    m.toggle();
                }
            }
            catch (Exception exception) {}
        } else {
            ClientMessage.sendErrorMessage("Invalid Syntax");
        }
    }
}


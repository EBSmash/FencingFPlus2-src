/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.client.event.ClientChatEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package org.fenci.fencingfplus2.managers;

import java.util.ArrayList;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.fenci.fencingfplus2.command.Command;
import org.fenci.fencingfplus2.command.commands.CommandsCommand;
import org.fenci.fencingfplus2.command.commands.KitCommand;
import org.fenci.fencingfplus2.command.commands.TargetCommand;
import org.fenci.fencingfplus2.command.commands.ToggleCommand;
import org.fenci.fencingfplus2.utils.ClientMessage;

public class CommandManager {
    public String prefix = "@";
    public ArrayList<Command> commands = new ArrayList();

    public CommandManager() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.init();
    }

    public void init() {
        this.commands.add(new CommandsCommand());
        this.commands.add(new KitCommand());
        this.commands.add(new TargetCommand());
        this.commands.add(new ToggleCommand());
    }

    @SubscribeEvent
    public void chatEvent(ClientChatEvent event) {
        String[] args = event.getMessage().split(" ");
        if (event.getMessage().startsWith(this.prefix)) {
            event.setCanceled(true);
            for (Command command : this.commands) {
                if (!args[0].equalsIgnoreCase(this.prefix + command.getName())) continue;
                command.runCommand(args);
                return;
            }
            ClientMessage.sendMessage("Invalid command try " + this.prefix + "commands");
        }
    }
}


//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package org.fenci.fencingfplus2;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import java.util.Objects;
import org.fenci.fencingfplus2.utils.Globals;

public class RPC
implements Globals {
    private static final DiscordRichPresence discordRichPresence = new DiscordRichPresence();
    private static final DiscordRPC discordRPC = DiscordRPC.INSTANCE;

    public static void startRPC() {
        DiscordEventHandlers eventHandlers = new DiscordEventHandlers();
        String ip = mc.isSingleplayer() ? "Singleplayer" : Objects.requireNonNull(RPC.mc.getCurrentServerData()).serverIP.toLowerCase();
        eventHandlers.disconnected = (var1, var2) -> System.out.println("Discord RPC disconnected, var1: " + var1 + ", var2: " + var2);
        String discordID = "895540838485073930";
        discordRPC.Discord_Initialize(discordID, eventHandlers, true, null);
        RPC.discordRichPresence.startTimestamp = System.currentTimeMillis() / 1000L;
        RPC.discordRichPresence.details = "Currently duping on " + ip;
        RPC.discordRichPresence.largeImageKey = "fencingfplusttwoidkanymore";
        RPC.discordRichPresence.largeImageText = "https://discord.gg/q4Xvyj4UCr";
        RPC.discordRichPresence.smallImageKey = "JoeMoma";
        RPC.discordRichPresence.smallImageText = "Duping";
        RPC.discordRichPresence.state = null;
        discordRPC.Discord_UpdatePresence(discordRichPresence);
    }

    public static void stopRPC() {
        discordRPC.Discord_Shutdown();
        discordRPC.Discord_ClearPresence();
    }

    public static void startRPC2() {
        DiscordEventHandlers eventHandlers = new DiscordEventHandlers();
        String ip = mc.isSingleplayer() ? "Singleplayer" : Objects.requireNonNull(RPC.mc.getCurrentServerData()).serverIP.toLowerCase();
        eventHandlers.disconnected = (var1, var2) -> System.out.println("Discord RPC disconnected, var1: " + var1 + ", var2: " + var2);
        String discordID = "900500424203841536";
        discordRPC.Discord_Initialize(discordID, eventHandlers, true, null);
        RPC.discordRichPresence.startTimestamp = System.currentTimeMillis() / 1000L;
        RPC.discordRichPresence.details = "Currently duping on " + ip;
        RPC.discordRichPresence.largeImageKey = "bergenwarecc";
        RPC.discordRichPresence.largeImageText = "https://discord.gg/q4Xvyj4UCr";
        RPC.discordRichPresence.smallImageKey = "Sheeeeesh";
        RPC.discordRichPresence.smallImageText = "Bergen";
        RPC.discordRichPresence.state = null;
        discordRPC.Discord_UpdatePresence(discordRichPresence);
    }

    public static void stopRPC2() {
        discordRPC.Discord_Shutdown();
        discordRPC.Discord_ClearPresence();
    }
}


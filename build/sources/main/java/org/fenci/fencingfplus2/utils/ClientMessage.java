//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentBase
 *  net.minecraft.util.text.TextComponentString
 */
package org.fenci.fencingfplus2.utils;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentBase;
import net.minecraft.util.text.TextComponentString;
import org.fenci.fencingfplus2.utils.Globals;

public class ClientMessage
implements Globals {
    private static final String opener = ChatFormatting.BLACK + "[" + ChatFormatting.YELLOW + "FencingF+2" + ChatFormatting.BLACK + "] " + ChatFormatting.RESET;
    private static final String opener2 = ChatFormatting.BLACK + "[" + ChatFormatting.YELLOW + "FencingF+2" + ChatFormatting.BLACK + "] " + ChatFormatting.WHITE + ChatFormatting.BOLD + "[AutoPvP] ";

    public static void sendMessage(String message) {
        ClientMessage.sendClientMessage(opener + message);
    }

    public static void sendDoubleMessage(double message) {
        ClientMessage.sendClientMessage(opener + message);
    }

    public static void sendCoordMessage(BlockPos message) {
        ClientMessage.sendClientMessage(opener + message);
    }

    public static void sendMessageFloat(float message) {
        ClientMessage.sendClientMessage(opener + message);
    }

    public static void sendMessageInt(int message) {
        ClientMessage.sendClientMessage(opener + message);
    }

    public static void sendMessageList(int message) {
        ClientMessage.sendClientMessage(opener + message);
    }

    public static void sendModMessage(String message) {
        ClientMessage.sendClientMessage(opener2 + message);
    }

    public static void sendErrorMessage(String message) {
        ClientMessage.sendClientMessage(opener + ChatFormatting.RED + message);
    }

    private static void sendClientMessage(String message) {
        if (ClientMessage.mc.player != null) {
            ClientMessage.mc.player.sendMessage((ITextComponent)new ChatMessage(message));
        }
    }

    public static void sendOverwriteClientMessage(String message) {
        if (ClientMessage.mc.player != null) {
            TextComponentString itc = new TextComponentString(opener + message);
            ClientMessage.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)itc, 5936);
        }
    }

    public static void sendRainbowMessage(String message) {
        StringBuilder stringBuilder = new StringBuilder(message);
        stringBuilder.insert(0, "\u00a7+");
        ClientMessage.mc.player.sendMessage((ITextComponent)new ChatMessage(stringBuilder.toString()));
    }

    public static void sendMessage(String message, boolean perm) {
        if (ClientMessage.mc.player == null) {
            return;
        }
        try {
            TextComponentString component = new TextComponentString(opener + message);
            int i = perm ? 0 : 12076;
            ClientMessage.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)component, i);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static class ChatMessage
    extends TextComponentBase {
        String message_input;

        public ChatMessage(String message) {
            Pattern p = Pattern.compile("&[0123456789abcdefrlosmk]");
            Matcher m = p.matcher(message);
            StringBuffer sb = new StringBuffer();
            while (m.find()) {
                String replacement = "\u00a7" + m.group().substring(1);
                m.appendReplacement(sb, replacement);
            }
            m.appendTail(sb);
            this.message_input = sb.toString();
        }

        public String getUnformattedComponentText() {
            return this.message_input;
        }

        public ITextComponent createCopy() {
            return new ChatMessage(this.message_input);
        }
    }
}


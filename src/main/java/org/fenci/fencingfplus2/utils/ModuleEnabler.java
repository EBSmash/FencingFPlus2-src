//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentBase
 */
package org.fenci.fencingfplus2.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentBase;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.utils.ClientMessage;

public class ModuleEnabler {
    public static void enable(String module) {
        for (Module mod : FencingFPlus.instance.moduleManager.getModuleList()) {
            if (!mod.getName().equalsIgnoreCase(module) || mod.isToggled()) continue;
            mod.toggle();
        }
    }

    public static void disable(String module) {
        for (Module mod : FencingFPlus.instance.moduleManager.getModuleList()) {
            if (!mod.getName().equalsIgnoreCase(module) || !mod.isToggled()) continue;
            mod.toggle();
        }
    }

    public static class ChatMessage
    extends TextComponentBase {
        String mod_input;

        public ChatMessage(String module) {
            Pattern p = Pattern.compile("&[0123456789abcdefrlosmk]");
            Matcher m = p.matcher(module);
            StringBuffer sb = new StringBuffer();
            while (m.find()) {
                String replacement = "\u00a7" + m.group().substring(1);
                m.appendReplacement(sb, replacement);
            }
            m.appendTail(sb);
            this.mod_input = sb.toString();
        }

        public String getUnformattedComponentText() {
            return this.mod_input;
        }

        public ITextComponent createCopy() {
            return new ClientMessage.ChatMessage(this.mod_input);
        }
    }
}


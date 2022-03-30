//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.entity.player.EntityPlayer
 */
package org.fenci.fencingfplus2.managers;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.entity.player.EntityPlayer;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.utils.Globals;

public class PopManager
implements Globals {
    public final List<String> toAnnouce = new ArrayList<String>();
    private Map<EntityPlayer, Integer> popList = new ConcurrentHashMap<EntityPlayer, Integer>();

    public void onTotemPop(EntityPlayer player) {
        this.popTotem(player);
        if (!player.equals((Object)PopManager.mc.player) && player.isEntityAlive()) {
            this.toAnnouce.add(this.getPopString(player, this.getTotemPops(player)));
        }
    }

    public void onDeath(EntityPlayer player) {
        if (this.getTotemPops(player) != 0 && !player.equals((Object)PopManager.mc.player)) {
            this.toAnnouce.add(this.getDeathString(player, this.getTotemPops(player)));
        }
        this.resetPops(player);
    }

    public void onLogout() {
        this.clearList();
    }

    public void clearList() {
        this.popList = new ConcurrentHashMap<EntityPlayer, Integer>();
    }

    public void resetPops(EntityPlayer player) {
        this.setTotemPops(player, 0);
    }

    public void popTotem(EntityPlayer player) {
        this.popList.merge(player, 1, Integer::sum);
    }

    public void setTotemPops(EntityPlayer player, int amount) {
        this.popList.put(player, amount);
    }

    public int getTotemPops(EntityPlayer player) {
        return this.popList.get(player) == null ? 0 : this.popList.get(player);
    }

    private String getDeathString(EntityPlayer player, int pops) {
        if (FencingFPlus.FRIEND_MANAGER.isFriend(player.getName())) {
            return "Your friend " + ChatFormatting.AQUA + player.getName() + ChatFormatting.RESET + " just died after popping " + ChatFormatting.GREEN + ChatFormatting.BOLD + pops + ChatFormatting.RESET + (pops == 1 ? " totem!" : " totems!");
        }
        return ChatFormatting.RED + player.getName() + ChatFormatting.RESET + " just died after popping " + ChatFormatting.GREEN + ChatFormatting.BOLD + pops + ChatFormatting.RESET + (pops == 1 ? " totem!" : " totems!");
    }

    private String getPopString(EntityPlayer player, int pops) {
        if (FencingFPlus.FRIEND_MANAGER.isFriend(player.getName())) {
            return "Your friend " + ChatFormatting.AQUA + player.getName() + ChatFormatting.RESET + " popped " + ChatFormatting.RED + ChatFormatting.BOLD + pops + ChatFormatting.RESET + (pops == 1 ? " totem!" : " totems!");
        }
        return ChatFormatting.RED + player.getName() + ChatFormatting.RESET + " has popped " + ChatFormatting.RED + ChatFormatting.BOLD + pops + ChatFormatting.RESET + (pops == 1 ? " totem!" : " totems!");
    }
}


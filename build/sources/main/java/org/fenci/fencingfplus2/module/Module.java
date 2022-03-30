//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package org.fenci.fencingfplus2.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.utils.ClientMessage;

public abstract class Module {
    protected static Minecraft mc = Minecraft.getMinecraft();
    private final String name;
    private final Category category;
    public boolean visible = true;
    boolean isListening = false;
    private String description;
    private int key;
    private boolean toggled;

    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.key = 0;
        this.category = category;
        this.toggled = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean getVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isToggled() {
        return this.toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
        if (this.toggled) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    public boolean isEnabled() {
        return this.isEnabled();
    }

    public boolean isDisabled() {
        return this.isDisabled();
    }

    public void toggle() {
        boolean bl = this.toggled = !this.toggled;
        if (this.toggled) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        for (Module mod : FencingFPlus.instance.moduleManager.getModuleList()) {
            if (!mod.getName().equalsIgnoreCase("ChatNotifier") || !mod.isToggled()) continue;
            ClientMessage.sendOverwriteClientMessage(ChatFormatting.GREEN + this.getName() + ":" + ChatFormatting.BOLD + " Enabled");
        }
    }

    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        for (Module mod : FencingFPlus.instance.moduleManager.getModuleList()) {
            if (!mod.getName().equalsIgnoreCase("ChatNotifier") || !mod.isToggled()) continue;
            ClientMessage.sendOverwriteClientMessage(ChatFormatting.RED + this.getName() + ":" + ChatFormatting.BOLD + " Disabled");
        }
    }

    public String getName() {
        return this.name;
    }

    public Category getCategory() {
        return this.category;
    }

    protected void onReader() {
    }

    @SubscribeEvent
    public void onUpdate(LivingEvent.LivingUpdateEvent event) {
        for (Module mod : FencingFPlus.instance.moduleManager.getModuleList()) {
            if (!mod.isToggled()) continue;
            mod.onUpdate();
        }
    }

    public void onUpdate() {
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        for (Module module : FencingFPlus.instance.moduleManager.getModuleList()) {
            if (!module.isToggled()) continue;
            module.onTick();
        }
    }

    public void onTick() {
    }
}


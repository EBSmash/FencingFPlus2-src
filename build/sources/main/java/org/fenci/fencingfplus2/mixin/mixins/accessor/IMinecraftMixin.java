/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package org.fenci.fencingfplus2.mixin.mixins.accessor;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={Minecraft.class})
public interface IMinecraftMixin {
    @Accessor(value="rightClickDelayTimer")
    public void setRightClickDelayTimerAccessor(int var1);
}


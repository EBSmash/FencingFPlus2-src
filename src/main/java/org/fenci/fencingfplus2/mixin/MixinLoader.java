/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.IFMLLoadingPlugin
 *  net.minecraftforge.fml.relauncher.IFMLLoadingPlugin$MCVersion
 *  net.minecraftforge.fml.relauncher.IFMLLoadingPlugin$Name
 *  net.minecraftforge.fml.relauncher.IFMLLoadingPlugin$SortingIndex
 */
package org.fenci.fencingfplus2.mixin;

import java.util.Map;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.fenci.fencingfplus2.FencingFPlusTwo;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

@IFMLLoadingPlugin.Name(value="FencingF+2")
@IFMLLoadingPlugin.MCVersion(value="1.12.2")
@IFMLLoadingPlugin.SortingIndex(value=0x7FFFFFFF)
public class MixinLoader
implements IFMLLoadingPlugin {
    public MixinLoader() {
        MixinBootstrap.init();
        FencingFPlusTwo.LOGGER.info("Mixins initialized.");
        Mixins.addConfiguration("mixins.fencingfplus.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
    }

    public String[] getASMTransformerClass() {
        return new String[0];
    }

    public String getModContainerClass() {
        return null;
    }

    public String getSetupClass() {
        return null;
    }

    public void injectData(Map<String, Object> data) {
        data.get("runtimeDeobfuscationEnabled");
    }

    public String getAccessTransformerClass() {
        return null;
    }
}


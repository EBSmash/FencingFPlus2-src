/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package org.fenci.fencingfplus2;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fenci.fencingfplus2.FencingFPlus;

@Mod(modid="fencingfplustwo", name="FencingF+2", version="1.9")
public class FencingFPlusTwo {
    public static final String MODID = "fencingfplustwo";
    public static final String MODNAME = "FencingF+2";
    public static final String MODVER = "1.9";
    public static final Logger LOGGER = LogManager.getLogger((String)"fencingfplustwo");

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        FencingFPlus.instance = new FencingFPlus();
        FencingFPlus.instance.init();
    }
}


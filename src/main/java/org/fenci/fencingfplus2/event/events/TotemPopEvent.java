/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package org.fenci.fencingfplus2.event.events;

import net.minecraft.entity.player.EntityPlayer;
import org.fenci.fencingfplus2.event.EventStage;

public class TotemPopEvent
extends EventStage {
    private final EntityPlayer entity;

    public TotemPopEvent(EntityPlayer entity) {
        this.entity = entity;
    }

    public EntityPlayer getEntity() {
        return this.entity;
    }
}


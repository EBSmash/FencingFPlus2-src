/*
 * Decompiled with CFR 0.152.
 */
package org.fenci.fencingfplus2.utils;

import java.util.UUID;

public class FPlusPlayer
implements Globals {
    private final String name;
    private String nickName;

    public FPlusPlayer(String name) {
        this.name = name;
    }

    public FPlusPlayer(String name, UUID uuid) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String name) {
        this.nickName = name;
    }
}


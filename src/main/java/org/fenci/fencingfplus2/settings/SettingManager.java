/*
 * Decompiled with CFR 0.152.
 */
package org.fenci.fencingfplus2.settings;

import java.util.ArrayList;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;

public class SettingManager {
    private final ArrayList<Setting> settings = new ArrayList();

    public void rSetting(Setting in) {
        this.settings.add(in);
    }

    public ArrayList<Setting> getSettings() {
        return this.settings;
    }

    public ArrayList<Setting> getSettingsByMod(Module mod) {
        ArrayList<Setting> out = new ArrayList<Setting>();
        for (Setting s : this.getSettings()) {
            if (!s.getParentMod().equals(mod)) continue;
            out.add(s);
        }
        if (out.isEmpty()) {
            return null;
        }
        return out;
    }

    public Setting getSettingByName(Module mod, String name) {
        for (Setting set : this.getSettings()) {
            if (!set.getName().equalsIgnoreCase(name) || set.getParentMod() != mod) continue;
            return set;
        }
        System.err.println("[Tutorial] Error Setting NOT found: '" + name + "'!");
        return null;
    }
}


/*
 * Decompiled with CFR 0.152.
 */
package org.fenci.fencingfplus2.managers;

import java.util.ArrayList;
import org.fenci.fencingfplus2.utils.Globals;

public class KitManager
implements Globals {
    ArrayList<String> kits = new ArrayList();

    public void addKit(String name) {
        this.kits.add(name);
    }

    public void removeKit() {
        this.kits.clear();
    }

    public boolean hasKit() {
        return !this.kits.isEmpty();
    }

    public int KitCount() {
        return this.kits.size();
    }

    public ArrayList<String> getKit() {
        return this.kits;
    }
}


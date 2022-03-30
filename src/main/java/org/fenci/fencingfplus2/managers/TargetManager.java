/*
 * Decompiled with CFR 0.152.
 */
package org.fenci.fencingfplus2.managers;

import java.util.ArrayList;
import org.fenci.fencingfplus2.utils.Globals;

public class TargetManager
implements Globals {
    ArrayList<String> targets = new ArrayList();

    public void addTarget(String name) {
        this.targets.add(name);
    }

    public void removeTarget() {
        this.targets.clear();
    }

    public boolean hasTarget() {
        return !this.targets.isEmpty();
    }

    public int targetCount() {
        return this.targets.size();
    }

    public ArrayList<String> getTarget() {
        return this.targets;
    }
}


/*
 * Decompiled with CFR 0.152.
 */
package org.fenci.fencingfplus2.managers;

import java.util.ArrayList;
import java.util.List;
import org.fenci.fencingfplus2.utils.FPlusPlayer;
import org.fenci.fencingfplus2.utils.Globals;

public class FriendManager
implements Globals {
    private List<FPlusPlayer> friends = new ArrayList<FPlusPlayer>();

    public void addFriend(String name) {
        if (!this.isFriend(name)) {
            this.friends.add(new FPlusPlayer(name));
        }
    }

    public void removeFriend(String name) {
        this.friends.removeIf(player -> player.getName().equalsIgnoreCase(name));
    }

    public boolean isFriend(String name) {
        for (FPlusPlayer player : this.friends) {
            if (!player.getName().equalsIgnoreCase(name)) continue;
            return true;
        }
        return false;
    }

    public boolean hasFriends() {
        return !this.friends.isEmpty();
    }

    public List<FPlusPlayer> getFriends() {
        return this.friends;
    }

    public void setFriends(List<FPlusPlayer> list) {
        this.friends = list;
    }

    public void toggleFriend(String name) {
        if (this.isFriend(name)) {
            this.removeFriend(name);
        } else {
            this.addFriend(name);
        }
    }

    public void clear() {
        this.friends.clear();
    }
}


/*
 * Decompiled with CFR 0.152.
 */
package org.fenci.fencingfplus2.utils;

public class Timer {
    private long time = -1L;

    public boolean passedS(double s) {
        return this.passedMs((long)s * 1000L);
    }

    public boolean passedDms(double dms) {
        return this.passedMs((long)dms * 10L);
    }

    public boolean passedDs(double ds) {
        return this.passedMs((long)ds * 100L);
    }

    public boolean passedMs(long ms) {
        return this.passedNS(this.convertToNS(ms));
    }

    public void setMs(long ms) {
        this.time = System.nanoTime() - this.convertToNS(ms);
    }

    public boolean passedNS(long ns) {
        return System.nanoTime() - this.time >= ns;
    }

    public long getPassedTimeMs() {
        return this.getMs(System.nanoTime() - this.time);
    }

    public void reset() {
        this.time = System.nanoTime();
    }

    public long getMs(long time) {
        return time / 1000000L;
    }

    public long convertToNS(long time) {
        return time * 1000000L;
    }
}


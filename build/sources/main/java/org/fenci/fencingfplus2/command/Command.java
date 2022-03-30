/*
 * Decompiled with CFR 0.152.
 */
package org.fenci.fencingfplus2.command;

public class Command {
    private final String name;
    private final String description;
    private final String syntax;

    public Command(String name, String description, String syntax) {
        this.name = name;
        this.description = description;
        this.syntax = syntax;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getSyntax() {
        return this.syntax;
    }

    public void runCommand(String[] args) {
    }
}


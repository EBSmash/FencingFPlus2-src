/*
 * Decompiled with CFR 0.152.
 */
package org.fenci.fencingfplus2.module;

import java.util.ArrayList;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.module.chat.AutoSpawnOwn;
import org.fenci.fencingfplus2.module.chat.ChatNotifier;
import org.fenci.fencingfplus2.module.chat.ChatSpammer;
import org.fenci.fencingfplus2.module.chat.DeathCoords;
import org.fenci.fencingfplus2.module.chat.TotemPopCounter;
import org.fenci.fencingfplus2.module.combat.AntiTotemFail2b2t;
import org.fenci.fencingfplus2.module.combat.Aura;
import org.fenci.fencingfplus2.module.combat.AutoArmor;
import org.fenci.fencingfplus2.module.combat.AutoGapple;
import org.fenci.fencingfplus2.module.combat.AutoKill;
import org.fenci.fencingfplus2.module.combat.AutoPvP;
import org.fenci.fencingfplus2.module.combat.AutoTotem;
import org.fenci.fencingfplus2.module.combat.BowBomb;
import org.fenci.fencingfplus2.module.combat.Criticals;
import org.fenci.fencingfplus2.module.combat.FastEXP;
import org.fenci.fencingfplus2.module.combat.Offhand;
import org.fenci.fencingfplus2.module.combat.TotemFailWarning;
import org.fenci.fencingfplus2.module.misc.AutoKit;
import org.fenci.fencingfplus2.module.misc.DiscordRPC;
import org.fenci.fencingfplus2.module.misc.FakePlayer;
import org.fenci.fencingfplus2.module.misc.YawLock;
import org.fenci.fencingfplus2.module.movement.AutoWalk;
import org.fenci.fencingfplus2.module.movement.FastSneak;
import org.fenci.fencingfplus2.module.movement.Flight;
import org.fenci.fencingfplus2.module.movement.GappleNoSlow;
import org.fenci.fencingfplus2.module.movement.GuiMove;
import org.fenci.fencingfplus2.module.movement.HoleTP;
import org.fenci.fencingfplus2.module.movement.MCP;
import org.fenci.fencingfplus2.module.movement.NoSlow;
import org.fenci.fencingfplus2.module.movement.ReverseStep;
import org.fenci.fencingfplus2.module.movement.Sprint;
import org.fenci.fencingfplus2.module.movement.Step;
import org.fenci.fencingfplus2.module.movement.Velocity;
import org.fenci.fencingfplus2.module.movement.WaterSpeed;
import org.fenci.fencingfplus2.module.movement.Zoom;
import org.fenci.fencingfplus2.module.player.AntiAFK;
import org.fenci.fencingfplus2.module.player.AntiVoid;
import org.fenci.fencingfplus2.module.player.AutoLog;
import org.fenci.fencingfplus2.module.player.AutoRespawn;
import org.fenci.fencingfplus2.module.player.ChestSwap;
import org.fenci.fencingfplus2.module.player.FOVslider;
import org.fenci.fencingfplus2.module.player.JFKDupe;
import org.fenci.fencingfplus2.module.player.NoPacketTeleport;
import org.fenci.fencingfplus2.module.player.PacketCancel;
import org.fenci.fencingfplus2.module.render.ArmorHud;
import org.fenci.fencingfplus2.module.render.ClickGUI;
import org.fenci.fencingfplus2.module.render.CustomRenderDistance;
import org.fenci.fencingfplus2.module.render.Fullbright;
import org.fenci.fencingfplus2.module.render.HUD;
import org.fenci.fencingfplus2.module.render.HealthNotifier;
import org.fenci.fencingfplus2.module.render.NoRender;
import org.fenci.fencingfplus2.module.render.Time;

public class ModuleManager {
    public ArrayList<Module> modules = new ArrayList();

    public ModuleManager() {
        this.modules.clear();
        this.modules.add(new GappleNoSlow());
        this.modules.add(new AntiAFK());
        this.modules.add(new AntiTotemFail2b2t());
        this.modules.add(new AntiVoid());
        this.modules.add(new Aura());
        this.modules.add(new AutoArmor());
        this.modules.add(new AutoGapple());
        this.modules.add(new ArmorHud());
        this.modules.add(new AutoKit());
        this.modules.add(new AutoKill());
        this.modules.add(new AutoLog());
        this.modules.add(new AutoRespawn());
        this.modules.add(new AutoSpawnOwn());
        this.modules.add(new AutoTotem());
        this.modules.add(new AutoPvP());
        this.modules.add(new AutoWalk());
        this.modules.add(new BowBomb());
        this.modules.add(new ChatNotifier());
        this.modules.add(new ChatSpammer());
        this.modules.add(new ChestSwap());
        this.modules.add(new ClickGUI());
        this.modules.add(new Criticals());
        this.modules.add(new CustomRenderDistance());
        this.modules.add(new DeathCoords());
        this.modules.add(new DiscordRPC());
        this.modules.add(new FakePlayer());
        this.modules.add(new FastEXP());
        this.modules.add(new FastSneak());
        this.modules.add(new Flight());
        this.modules.add(new FOVslider());
        this.modules.add(new Fullbright());
        this.modules.add(new GuiMove());
        this.modules.add(new HealthNotifier());
        this.modules.add(new HoleTP());
        this.modules.add(new HUD());
        this.modules.add(new JFKDupe());
        this.modules.add(new MCP());
        this.modules.add(new NoPacketTeleport());
        this.modules.add(new NoRender());
        this.modules.add(new NoSlow());
        this.modules.add(new Offhand());
        this.modules.add(new PacketCancel());
        this.modules.add(new ReverseStep());
        this.modules.add(new Sprint());
        this.modules.add(new Step());
        this.modules.add(new Time());
        this.modules.add(new TotemFailWarning());
        this.modules.add(new TotemPopCounter());
        this.modules.add(new Velocity());
        this.modules.add(new WaterSpeed());
        this.modules.add(new YawLock());
        this.modules.add(new Zoom());
    }

    public Module getModule(String name) {
        for (Module m : this.modules) {
            if (!m.getName().equalsIgnoreCase(name)) continue;
            return m;
        }
        return null;
    }

    public ArrayList<Module> getModuleList() {
        return this.modules;
    }

    public ArrayList<Module> getModulesInCategory(Category c) {
        ArrayList<Module> mods = new ArrayList<Module>();
        for (Module m : this.modules) {
            if (m.getCategory() != c) continue;
            mods.add(m);
        }
        return mods;
    }
}


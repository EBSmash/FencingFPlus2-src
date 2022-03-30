//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiGameOver
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.init.MobEffects
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.play.server.SPacketDisconnect
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.world.World
 */
package org.fenci.fencingfplus2.module.combat;

import java.util.Arrays;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketDisconnect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import org.fenci.fencingfplus2.FencingFPlus;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;
import org.fenci.fencingfplus2.settings.Setting;
import org.fenci.fencingfplus2.utils.ClientMessage;
import org.fenci.fencingfplus2.utils.ModuleEnabler;

public class AutoPvP
extends Module {
    String ip;
    String opp;
    BlockPos targetPos;
    int targetDistance;
    double range;
    long delay1 = 0L;
    long delay2 = 0L;

    public AutoPvP() {
        super("AutoPvP", "First version of auto pvp", Category.COMBAT);
        FencingFPlus.instance.settingsManager.rSetting(new Setting("PvPMode", this, "Normal", Arrays.asList("Normal", "Gearplay")));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Mode", this, "Offhand", Arrays.asList("Offhand", "Mainhand")));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("EnvMode", this, "Duel", Arrays.asList("Duel", "Normal")));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Config", this, "Custom", Arrays.asList("Custom", "2b2tPvP", "2b2t", "CC")));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("GappleAt: ", this, 35.0, 1.0, 36.0, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("AntiWeakness", this, true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("Strict", this, false));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("DeBug", this, false));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("AutoLog", this, false));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("FollowDistance", this, 7.0, 0.0, this.blocksInRender(), true));
        FencingFPlus.instance.settingsManager.rSetting(new Setting("MaxCloseDistance", this, 7.0, 0.0, this.blocksInRender(), true));
    }

    public float playerHealth() {
        return AutoPvP.mc.player.getHealth() + AutoPvP.mc.player.getAbsorptionAmount();
    }

    public int blocksInRender() {
        return AutoPvP.mc.gameSettings.renderDistanceChunks * 16;
    }

    @Override
    public void onEnable() {
        if (AutoPvP.mc.player == null || AutoPvP.mc.world == null) {
            return;
        }
        String mode = FencingFPlus.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        String config = FencingFPlus.instance.settingsManager.getSettingByName(this, "Config").getValString();
        Module aura = FencingFPlus.instance.moduleManager.getModule("Aura");
        for (Setting setting : FencingFPlus.instance.settingsManager.getSettingsByMod(aura)) {
            if (!setting.isCombo() || !Objects.equals(setting.getName(), "SwitchMode")) continue;
            setting.setValString("Off");
        }
        if (AutoPvP.mc.world.playerEntities.size() < 2) {
            ClientMessage.sendModMessage("No player in render distance, disabling.");
            this.toggle();
        }
        ModuleEnabler.disable("GuiMove");
        ModuleEnabler.enable("AutoArmor");
        ClientMessage.sendModMessage("Configuring before we start");
        AutoPvP.mc.gameSettings.autoJump = false;
    }

    public void configurer() {
        String mode = FencingFPlus.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        String config = FencingFPlus.instance.settingsManager.getSettingByName(this, "Config").getValString();
        boolean strict = FencingFPlus.instance.settingsManager.getSettingByName(this, "Strict").getValBoolean();
        ModuleEnabler.disable("GuiMove");
        ModuleEnabler.disable("AutoGapple");
        ModuleEnabler.disable("YawLock");
        ModuleEnabler.enable("ChatNotifier");
        ModuleEnabler.enable("MCP");
        ModuleEnabler.enable("Velocity");
        if (Objects.equals(config, "CC")) {
            AutoPvP.mc.gameSettings.autoJump = false;
            AutoPvP.mc.player.stepHeight = 2.0f;
        } else {
            AutoPvP.mc.gameSettings.autoJump = true;
            ModuleEnabler.disable("Step");
            AutoPvP.mc.player.stepHeight = 0.5f;
        }
        if (!strict && Objects.equals(config, "Custom") || Objects.equals(config, "2b2tPvP") || Objects.equals(config, "CC")) {
            ModuleEnabler.enable("ReverseStep");
            ModuleEnabler.enable("NoSlow");
        }
        if (strict && Objects.equals(config, "Custom") || Objects.equals(config, "2b2t")) {
            ModuleEnabler.disable("ReverseStep");
            ModuleEnabler.disable("NoSlow");
        }
        if (Objects.equals(mode, "Offhand")) {
            ModuleEnabler.disable("AutoTotem");
            ModuleEnabler.enable("Offhand");
        }
        if (Objects.equals(mode, "Mainhand")) {
            ModuleEnabler.disable("Offhand");
            ModuleEnabler.enable("AutoTotem");
        }
    }

    public void AutoLogCheck() {
        boolean autoLog = FencingFPlus.instance.settingsManager.getSettingByName(this, "AutoLog").getValBoolean();
        float playerHealth = AutoPvP.mc.player.getHealth() + AutoPvP.mc.player.getAbsorptionAmount();
        int inventorytotems = AutoPvP.mc.player.inventory.mainInventory.stream().filter(stack -> stack.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();
        if (autoLog && inventorytotems <= 1) {
            mc.getConnection().handleDisconnect(new SPacketDisconnect((ITextComponent)new TextComponentString("[AutoPvP]: Had 2 totems remaining; logged out at " + playerHealth + " hp")));
            this.toggle();
        }
    }

    public int findBiggestValue() {
        int followDistance = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "FollowDistance").getValDouble();
        int maxCloseDistance = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "MaxCloseDistance").getValDouble();
        if (maxCloseDistance > followDistance) {
            return maxCloseDistance;
        }
        return followDistance;
    }

    public String getManagedName() {
        this.opp = FencingFPlus.instance.TARGET_MANAGER.getTarget().toString();
        this.opp = this.opp.replaceAll("[\\p{Ps}\\p{Pe}]", "");
        return this.opp;
    }

    public BlockPos getTargetPos() {
        for (Entity entity : AutoPvP.mc.world.loadedEntityList) {
            if (!(entity instanceof EntityPlayer) || !entity.getName().equals(this.getManagedName())) continue;
            this.targetPos = entity.getPosition();
        }
        return this.targetPos;
    }

    public int getTargetDistance() {
        for (Entity entity : AutoPvP.mc.world.loadedEntityList) {
            if (!(entity instanceof EntityPlayer) || !entity.getName().equals(this.getManagedName())) continue;
            this.targetDistance = (int)entity.getDistance((Entity)AutoPvP.mc.player);
        }
        return this.targetDistance;
    }

    public void findPlayerCoords() {
        int followDistance = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "FollowDistance").getValDouble();
        int maxCloseDistance = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "MaxCloseDistance").getValDouble();
        String config = FencingFPlus.instance.settingsManager.getSettingByName(this, "Config").getValString();
        String pvpMode = FencingFPlus.instance.settingsManager.getSettingByName(this, "PvPMode").getValString();
        this.ip = Objects.requireNonNull(Minecraft.getMinecraft().getCurrentServerData()).serverIP;
        BlockPos thisPlayerPos = AutoPvP.mc.player.getPosition();
        int thisX = thisPlayerPos.getX();
        int thisZ = thisPlayerPos.getZ();
        int playerX = this.getTargetPos().getX();
        int playerZ = this.getTargetPos().getZ();
        if (pvpMode == "Normal") {
            if (thisPlayerPos.getX() > this.getTargetPos().getX() && this.getTargetDistance() >= followDistance && this.isWB() && this.getTargetDistance() >= maxCloseDistance) {
                AutoPvP.mc.player.rotationYaw = 90.0f;
                KeyBinding.setKeyBindState((int)AutoPvP.mc.gameSettings.keyBindForward.getKeyCode(), (boolean)true);
                KeyBinding.setKeyBindState((int)AutoPvP.mc.gameSettings.keyBindBack.getKeyCode(), (boolean)false);
            }
            if (thisPlayerPos.getX() < this.getTargetPos().getX() && this.getTargetDistance() >= followDistance && this.isWB() && this.getTargetDistance() >= maxCloseDistance) {
                AutoPvP.mc.player.rotationYaw = 270.0f;
                KeyBinding.setKeyBindState((int)AutoPvP.mc.gameSettings.keyBindForward.getKeyCode(), (boolean)true);
                KeyBinding.setKeyBindState((int)AutoPvP.mc.gameSettings.keyBindBack.getKeyCode(), (boolean)false);
            }
            if (thisPlayerPos.getZ() > this.getTargetPos().getZ() && this.getTargetDistance() >= followDistance && this.isWB() && this.getTargetDistance() >= maxCloseDistance) {
                AutoPvP.mc.player.rotationYaw = 180.0f;
                KeyBinding.setKeyBindState((int)AutoPvP.mc.gameSettings.keyBindForward.getKeyCode(), (boolean)true);
                KeyBinding.setKeyBindState((int)AutoPvP.mc.gameSettings.keyBindBack.getKeyCode(), (boolean)false);
            }
            if (thisPlayerPos.getZ() < this.getTargetPos().getZ() && this.getTargetDistance() >= followDistance && this.isWB() && this.getTargetDistance() >= maxCloseDistance) {
                AutoPvP.mc.player.rotationYaw = 0.0f;
                KeyBinding.setKeyBindState((int)AutoPvP.mc.gameSettings.keyBindForward.getKeyCode(), (boolean)true);
                KeyBinding.setKeyBindState((int)AutoPvP.mc.gameSettings.keyBindBack.getKeyCode(), (boolean)false);
            }
            if (this.getTargetDistance() < maxCloseDistance) {
                KeyBinding.setKeyBindState((int)AutoPvP.mc.gameSettings.keyBindForward.getKeyCode(), (boolean)false);
                KeyBinding.setKeyBindState((int)AutoPvP.mc.gameSettings.keyBindBack.getKeyCode(), (boolean)true);
            }
            if (this.getTargetDistance() == followDistance && this.isWB()) {
                AutoPvP.mc.gameSettings.autoJump = false;
                KeyBinding.setKeyBindState((int)AutoPvP.mc.gameSettings.keyBindForward.getKeyCode(), (boolean)false);
                KeyBinding.setKeyBindState((int)AutoPvP.mc.gameSettings.keyBindBack.getKeyCode(), (boolean)false);
            }
        }
        if (pvpMode == "Gearplay") {
            // empty if block
        }
    }

    public void eatGapple() {
        int i;
        String mode = FencingFPlus.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        int gappleAt = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "GappleAt: ").getValDouble();
        boolean antiWeakness = FencingFPlus.instance.settingsManager.getSettingByName(this, "AntiWeakness").getValBoolean();
        int GoldenAppleCount = AutoPvP.mc.player.inventory.mainInventory.stream().filter(stack -> stack.getItem() == Items.GOLDEN_APPLE).mapToInt(ItemStack::getCount).sum();
        if (Objects.equals(mode, "Offhand") && GoldenAppleCount > 0 && this.playerHealth() < (float)gappleAt) {
            if (this.doesNextSlotHaveGapple() && AutoPvP.mc.player.getHeldItemMainhand().getItem() != Items.GOLDEN_APPLE) {
                for (i = 0; i < 9; ++i) {
                    if (AutoPvP.mc.player.inventory.getStackInSlot(i).getItem() != Items.GOLDEN_APPLE) continue;
                    AutoPvP.mc.player.inventory.currentItem = i;
                    AutoPvP.mc.playerController.processRightClick((EntityPlayer)AutoPvP.mc.player, (World)AutoPvP.mc.world, EnumHand.MAIN_HAND);
                }
            }
            KeyBinding.setKeyBindState((int)AutoPvP.mc.gameSettings.keyBindUseItem.getKeyCode(), (boolean)true);
        }
        if (Objects.equals(mode, "Offhand") && GoldenAppleCount > 0 && this.playerHealth() > (float)gappleAt && !AutoPvP.mc.gameSettings.keyBindUseItem.isKeyDown()) {
            KeyBinding.setKeyBindState((int)AutoPvP.mc.gameSettings.keyBindUseItem.getKeyCode(), (boolean)false);
            if (AutoPvP.mc.player.isPotionActive(MobEffects.WEAKNESS) && antiWeakness) {
                ModuleEnabler.disable("Aura");
                if (this.doesNextSlotHaveSword() && AutoPvP.mc.player.getHeldItemMainhand().getItem() != Items.DIAMOND_SWORD) {
                    for (i = 0; i < 9; ++i) {
                        if (AutoPvP.mc.player.inventory.getStackInSlot(i).getItem() != Items.DIAMOND_SWORD) continue;
                        AutoPvP.mc.player.inventory.currentItem = i;
                        AutoPvP.mc.playerController.processRightClick((EntityPlayer)AutoPvP.mc.player, (World)AutoPvP.mc.world, EnumHand.MAIN_HAND);
                    }
                }
            }
        }
        if (Objects.equals(mode, "Mainhand") && GoldenAppleCount > 0 && this.playerHealth() < (float)gappleAt) {
            if (this.doesNextSlotHaveGapple() && AutoPvP.mc.player.getHeldItemMainhand().getItem() != Items.GOLDEN_APPLE) {
                for (i = 0; i < 9; ++i) {
                    if (AutoPvP.mc.player.inventory.getStackInSlot(i).getItem() != Items.GOLDEN_APPLE) continue;
                    AutoPvP.mc.player.inventory.currentItem = i;
                    AutoPvP.mc.playerController.processRightClick((EntityPlayer)AutoPvP.mc.player, (World)AutoPvP.mc.world, EnumHand.MAIN_HAND);
                }
            }
            KeyBinding.setKeyBindState((int)AutoPvP.mc.gameSettings.keyBindUseItem.getKeyCode(), (boolean)true);
        }
        if (Objects.equals(mode, "Mainhand") && GoldenAppleCount > 0 && this.playerHealth() > (float)gappleAt) {
            KeyBinding.setKeyBindState((int)AutoPvP.mc.gameSettings.keyBindUseItem.getKeyCode(), (boolean)false);
        }
    }

    @Override
    public void onDisable() {
        KeyBinding.setKeyBindState((int)AutoPvP.mc.gameSettings.keyBindForward.getKeyCode(), (boolean)false);
        KeyBinding.setKeyBindState((int)AutoPvP.mc.gameSettings.keyBindUseItem.getKeyCode(), (boolean)false);
        AutoPvP.mc.gameSettings.autoJump = false;
        ModuleEnabler.disable("AutoGapple");
        AutoPvP.mc.player.stepHeight = 0.5f;
    }

    private boolean doesNextSlotHaveChorus() {
        for (int i = 0; i < 9; ++i) {
            if (AutoPvP.mc.player.inventory.getStackInSlot(i) == null || AutoPvP.mc.player.inventory.getStackInSlot(i).getItem() != Items.CHORUS_FRUIT) continue;
            return true;
        }
        return false;
    }

    private boolean doesNextSlotHavePearl() {
        for (int i = 0; i < 9; ++i) {
            if (AutoPvP.mc.player.inventory.getStackInSlot(i) == null || AutoPvP.mc.player.inventory.getStackInSlot(i).getItem() != Items.ENDER_PEARL) continue;
            return true;
        }
        return false;
    }

    private boolean doesNextSlotHaveGapple() {
        for (int i = 0; i < 9; ++i) {
            if (AutoPvP.mc.player.inventory.getStackInSlot(i) == null || AutoPvP.mc.player.inventory.getStackInSlot(i).getItem() != Items.GOLDEN_APPLE) continue;
            return true;
        }
        return false;
    }

    private boolean doesNextSlotHaveSword() {
        for (int i = 0; i < 9; ++i) {
            if (AutoPvP.mc.player.inventory.getStackInSlot(i) == null || AutoPvP.mc.player.inventory.getStackInSlot(i).getItem() != Items.DIAMOND_SWORD) continue;
            return true;
        }
        return false;
    }

    public void throwPearl() {
        if (this.doesNextSlotHavePearl()) {
            KeyBinding.setKeyBindState((int)AutoPvP.mc.gameSettings.keyBindPickBlock.getKeyCode(), (boolean)true);
        }
        KeyBinding.setKeyBindState((int)AutoPvP.mc.gameSettings.keyBindPickBlock.getKeyCode(), (boolean)false);
    }

    public void switchToChorus() {
        if (this.doesNextSlotHaveChorus()) {
            for (int i = 0; i < 9; ++i) {
                if (AutoPvP.mc.player.inventory.getStackInSlot(i).getItem() != Items.CHORUS_FRUIT) continue;
                AutoPvP.mc.player.inventory.currentItem = i;
                AutoPvP.mc.playerController.processRightClick((EntityPlayer)AutoPvP.mc.player, (World)AutoPvP.mc.world, EnumHand.MAIN_HAND);
            }
        }
    }

    public void ifDead() {
        if (AutoPvP.mc.currentScreen instanceof GuiGameOver) {
            this.toggle();
        }
    }

    public double distanceFromSpawn() {
        return AutoPvP.mc.player.getDistance(0.0, AutoPvP.mc.player.posY, 0.0);
    }

    public double WBRadius() {
        return AutoPvP.mc.world.getWorldBorder().getDiameter() / 2.0;
    }

    public boolean isWB() {
        return this.distanceFromSpawn() < this.WBRadius() - 1.0;
    }

    public boolean swordBoolean() {
        return !AutoPvP.mc.gameSettings.keyBindForward.isKeyDown() && !AutoPvP.mc.gameSettings.keyBindRight.isKeyDown() && !AutoPvP.mc.gameSettings.keyBindLeft.isKeyDown() && !AutoPvP.mc.gameSettings.keyBindBack.isKeyDown();
    }

    public double auraRange() {
        Module aura = FencingFPlus.instance.moduleManager.getModule("Aura");
        for (Setting setting : FencingFPlus.instance.settingsManager.getSettingsByMod(aura)) {
            if (!setting.isSlider() || setting.getName() != "Range") continue;
            this.range = setting.getValDouble();
        }
        return this.range;
    }

    public void shouldSword() {
        if (!this.swordBoolean()) {
            ModuleEnabler.disable("Aura");
        }
        if (!AutoPvP.mc.player.isPotionActive(MobEffects.WEAKNESS) && this.doesNextSlotHaveSword() && this.swordBoolean() && this.auraRange() >= (double)this.getTargetDistance()) {
            ModuleEnabler.enable("Aura");
            if (AutoPvP.mc.player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem() != Items.DIAMOND_SWORD) {
                this.switchToSword();
            }
        }
    }

    public void switchToSword() {
        if (this.doesNextSlotHaveSword() && AutoPvP.mc.player.getHeldItemMainhand().getItem() != Items.DIAMOND_SWORD) {
            for (int i = 0; i < 9; ++i) {
                if (AutoPvP.mc.player.inventory.getStackInSlot(i).getItem() != Items.DIAMOND_SWORD) continue;
                AutoPvP.mc.player.inventory.currentItem = i;
                AutoPvP.mc.playerController.processRightClick((EntityPlayer)AutoPvP.mc.player, (World)AutoPvP.mc.world, EnumHand.MAIN_HAND);
            }
        }
    }

    @Override
    public void onUpdate() {
        block12: {
            if (AutoPvP.mc.player == null || AutoPvP.mc.world == null) {
                return;
            }
            int gappleAt = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "GappleAt: ").getValDouble();
            int maxCloseDistance = (int)FencingFPlus.instance.settingsManager.getSettingByName(this, "MaxCloseDistance").getValDouble();
            String pvpMode = FencingFPlus.instance.settingsManager.getSettingByName(this, "PvPMode").getValString();
            KeyBinding.setKeyBindState((int)AutoPvP.mc.gameSettings.keyBindSprint.getKeyCode(), (boolean)true);
            if (AutoPvP.mc.world.playerEntities.size() < 2) {
                ClientMessage.sendModMessage("No player in render distance, disabling.");
                this.toggle();
            }
            if (AutoPvP.mc.world.playerEntities.size() <= 1) break block12;
            this.ifDead();
            this.configurer();
            this.AutoLogCheck();
            this.findPlayerCoords();
            this.eatGapple();
            Object result = null;
            if (this.getTargetDistance() > maxCloseDistance && this.playerHealth() < 36.0f) {
                if (this.doesNextSlotHaveGapple() && AutoPvP.mc.player.getHeldItemMainhand().getItem() != Items.GOLDEN_APPLE) {
                    for (int i = 0; i < 9; ++i) {
                        if (AutoPvP.mc.player.inventory.getStackInSlot(i).getItem() != Items.GOLDEN_APPLE) continue;
                        AutoPvP.mc.player.inventory.currentItem = i;
                        AutoPvP.mc.playerController.processRightClick((EntityPlayer)AutoPvP.mc.player, (World)AutoPvP.mc.world, EnumHand.MAIN_HAND);
                    }
                }
                KeyBinding.setKeyBindState((int)AutoPvP.mc.gameSettings.keyBindUseItem.getKeyCode(), (boolean)true);
            }
            if (this.getTargetDistance() > maxCloseDistance && this.playerHealth() == 36.0f) {
                KeyBinding.setKeyBindState((int)AutoPvP.mc.gameSettings.keyBindUseItem.getKeyCode(), (boolean)false);
            }
            Module offhand = FencingFPlus.instance.moduleManager.getModule("Offhand");
            Module offhand2 = FencingFPlus.instance.moduleManager.getModule("Offhand");
            float newdelay1 = 1000.0f;
            float newdelay2 = 1000.0f;
            if (FencingFPlus.instance.moduleManager.getModule("Aura").isToggled() && !AutoPvP.mc.player.isPotionActive(MobEffects.WEAKNESS) && System.currentTimeMillis() - this.delay1 >= (long)newdelay1) {
                for (Setting setting : FencingFPlus.instance.settingsManager.getSettingsByMod(offhand)) {
                    if (!setting.isCombo() || setting.getName() != "OffhandItem") continue;
                    ModuleEnabler.disable("Offhand");
                    setting.setValString("Gapples");
                    ModuleEnabler.enable("Offhand");
                    if (!this.doesNextSlotHaveSword() || AutoPvP.mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_SWORD) continue;
                    for (int i = 0; i < 9; ++i) {
                        if (AutoPvP.mc.player.inventory.getStackInSlot(i).getItem() != Items.DIAMOND_SWORD) continue;
                        AutoPvP.mc.player.inventory.currentItem = i;
                        AutoPvP.mc.playerController.processRightClick((EntityPlayer)AutoPvP.mc.player, (World)AutoPvP.mc.world, EnumHand.MAIN_HAND);
                    }
                }
            } else {
                for (Setting setting : FencingFPlus.instance.settingsManager.getSettingsByMod(offhand2)) {
                    if (!setting.isCombo() || setting.getName() != "OffhandItem" || System.currentTimeMillis() - this.delay2 < (long)newdelay2) continue;
                    ModuleEnabler.disable("Offhand");
                    setting.setValString("Crystals");
                    ModuleEnabler.enable("Offhand");
                    if (!this.doesNextSlotHaveSword() || AutoPvP.mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_SWORD) continue;
                    for (int i = 0; i < 9; ++i) {
                        if (AutoPvP.mc.player.inventory.getStackInSlot(i).getItem() != Items.DIAMOND_SWORD) continue;
                        AutoPvP.mc.player.inventory.currentItem = i;
                        AutoPvP.mc.playerController.processRightClick((EntityPlayer)AutoPvP.mc.player, (World)AutoPvP.mc.world, EnumHand.MAIN_HAND);
                    }
                }
            }
        }
    }
}


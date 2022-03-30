//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "/Users/lbozo/Downloads/FencingF2/1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.init.Blocks
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.IBlockAccess
 */
package org.fenci.fencingfplus2.module.movement;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import org.fenci.fencingfplus2.module.Category;
import org.fenci.fencingfplus2.module.Module;

public class HoleTP
extends Module {
    private final double[] oneblockPositions = new double[]{0.42, 0.75};
    private int packets;
    private boolean jumped;

    public HoleTP() {
        super("HoleTP", "ReverseStep but for holes only", Category.MOVEMENT);
    }

    @Override
    public void onUpdate() {
        if (!HoleTP.mc.player.onGround) {
            if (HoleTP.mc.gameSettings.keyBindJump.isKeyDown()) {
                this.jumped = true;
            }
        } else {
            this.jumped = false;
        }
        if (!this.jumped && (double)HoleTP.mc.player.fallDistance < 0.5 && this.isInHole() && HoleTP.mc.player.posY - this.getNearestBlockBelow() <= 1.125 && HoleTP.mc.player.posY - this.getNearestBlockBelow() <= 0.95 && !this.isOnLiquid() && !this.isInLiquid()) {
            if (!HoleTP.mc.player.onGround) {
                ++this.packets;
            }
            if (!(HoleTP.mc.player.onGround || HoleTP.mc.player.isInsideOfMaterial(Material.WATER) || HoleTP.mc.player.isInsideOfMaterial(Material.LAVA) || HoleTP.mc.gameSettings.keyBindJump.isKeyDown() || HoleTP.mc.player.isOnLadder() || this.packets <= 0)) {
                BlockPos blockPos = new BlockPos(HoleTP.mc.player.posX, HoleTP.mc.player.posY, HoleTP.mc.player.posZ);
                for (double position : this.oneblockPositions) {
                    HoleTP.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position((double)((float)blockPos.getX() + 0.5f), HoleTP.mc.player.posY - position, (double)((float)blockPos.getZ() + 0.5f), true));
                }
                HoleTP.mc.player.setPosition((double)((float)blockPos.getX() + 0.5f), this.getNearestBlockBelow() + 0.1, (double)((float)blockPos.getZ() + 0.5f));
                this.packets = 0;
            }
        }
    }

    private boolean isInHole() {
        BlockPos blockPos = new BlockPos(HoleTP.mc.player.posX, HoleTP.mc.player.posY, HoleTP.mc.player.posZ);
        IBlockState blockState = HoleTP.mc.world.getBlockState(blockPos);
        return this.isBlockValid(blockState, blockPos);
    }

    private double getNearestBlockBelow() {
        for (double y = HoleTP.mc.player.posY; y > 0.0; y -= 0.001) {
            if (HoleTP.mc.world.getBlockState(new BlockPos(HoleTP.mc.player.posX, y, HoleTP.mc.player.posZ)).getBlock() instanceof BlockSlab || HoleTP.mc.world.getBlockState(new BlockPos(HoleTP.mc.player.posX, y, HoleTP.mc.player.posZ)).getBlock().getDefaultState().getCollisionBoundingBox((IBlockAccess)HoleTP.mc.world, new BlockPos(0, 0, 0)) == null) continue;
            return y;
        }
        return -1.0;
    }

    private boolean isBlockValid(IBlockState blockState, BlockPos blockPos) {
        return blockState.getBlock() == Blocks.AIR && HoleTP.mc.player.getDistanceSq(blockPos) >= 1.0 && HoleTP.mc.world.getBlockState(blockPos.up()).getBlock() == Blocks.AIR && HoleTP.mc.world.getBlockState(blockPos.up(2)).getBlock() == Blocks.AIR && (this.isBedrockHole(blockPos) || this.isObbyHole(blockPos) || this.isBothHole(blockPos) || this.isElseHole(blockPos));
    }

    private boolean isObbyHole(BlockPos blockPos) {
        BlockPos[] array;
        for (BlockPos touching : array = new BlockPos[]{blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()}) {
            IBlockState touchingState = HoleTP.mc.world.getBlockState(touching);
            if (touchingState.getBlock() != Blocks.AIR && touchingState.getBlock() == Blocks.OBSIDIAN) continue;
            return false;
        }
        return true;
    }

    private boolean isBedrockHole(BlockPos blockPos) {
        BlockPos[] array;
        for (BlockPos touching : array = new BlockPos[]{blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()}) {
            IBlockState touchingState = HoleTP.mc.world.getBlockState(touching);
            if (touchingState.getBlock() != Blocks.AIR && touchingState.getBlock() == Blocks.BEDROCK) continue;
            return false;
        }
        return true;
    }

    private boolean isBothHole(BlockPos blockPos) {
        BlockPos[] array;
        for (BlockPos touching : array = new BlockPos[]{blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()}) {
            IBlockState touchingState = HoleTP.mc.world.getBlockState(touching);
            if (touchingState.getBlock() != Blocks.AIR && (touchingState.getBlock() == Blocks.BEDROCK || touchingState.getBlock() == Blocks.OBSIDIAN)) continue;
            return false;
        }
        return true;
    }

    private boolean isElseHole(BlockPos blockPos) {
        BlockPos[] array;
        for (BlockPos touching : array = new BlockPos[]{blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()}) {
            IBlockState touchingState = HoleTP.mc.world.getBlockState(touching);
            if (touchingState.getBlock() != Blocks.AIR && touchingState.isFullBlock()) continue;
            return false;
        }
        return true;
    }

    private boolean isOnLiquid() {
        double y = HoleTP.mc.player.posY - 0.03;
        for (int x = MathHelper.floor((double)HoleTP.mc.player.posX); x < MathHelper.ceil((double)HoleTP.mc.player.posX); ++x) {
            for (int z = MathHelper.floor((double)HoleTP.mc.player.posZ); z < MathHelper.ceil((double)HoleTP.mc.player.posZ); ++z) {
                BlockPos pos = new BlockPos(x, MathHelper.floor((double)y), z);
                if (!(HoleTP.mc.world.getBlockState(pos).getBlock() instanceof BlockLiquid)) continue;
                return true;
            }
        }
        return false;
    }

    private boolean isInLiquid() {
        double y = HoleTP.mc.player.posY + 0.01;
        for (int x = MathHelper.floor((double)HoleTP.mc.player.posX); x < MathHelper.ceil((double)HoleTP.mc.player.posX); ++x) {
            for (int z = MathHelper.floor((double)HoleTP.mc.player.posZ); z < MathHelper.ceil((double)HoleTP.mc.player.posZ); ++z) {
                BlockPos pos = new BlockPos(x, (int)y, z);
                if (!(HoleTP.mc.world.getBlockState(pos).getBlock() instanceof BlockLiquid)) continue;
                return true;
            }
        }
        return false;
    }
}


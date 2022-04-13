package me.mrvintage.kingdom.magic.spell;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public class SpellPositionTarget {

    private ServerWorld world;
    private Vec3d pos;

    public SpellPositionTarget(ServerWorld world, Vec3d pos) {
        this.world = world;
        this.pos = pos;
    }

    public SpellPositionTarget(ServerWorld world, double x, double y, double z) {
        this.world = world;
        this.pos = new Vec3d(x, y, z);
    }

    public ServerWorld getWorld() {
        return world;
    }

    public Vec3d getPos() {
        return pos;
    }

    public double getX() {
        return pos.x;
    }

    public double getY() {
        return pos.y;
    }

    public double getZ() {
        return pos.z;
    }
}

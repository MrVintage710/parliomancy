package me.mrvintage.kingdom.magic.spell;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.math.BlockPos;

public enum SpellTargetType {
    POSITION(BlockPos.class),
    ENTITY(Entity.class),
    INVENTORY(Inventory.class);

    private Class clazz;

    SpellTargetType(Class clazz) {
        this.clazz = clazz;
    }
}

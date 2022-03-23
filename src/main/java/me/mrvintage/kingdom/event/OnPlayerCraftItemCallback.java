package me.mrvintage.kingdom.event;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

public interface OnPlayerCraftItemCallback {

    void onPlayerCraftItem(ServerPlayerEntity player, ItemStack stack, int amount);
}

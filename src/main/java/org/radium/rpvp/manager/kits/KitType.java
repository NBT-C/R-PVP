package org.radium.rpvp.manager.kits;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.radium.rpvp.util.ItemBuilder;

import java.util.Arrays;

public enum KitType {
    YOUTUBER(new ItemStack[]{
            new ItemBuilder(Material.DIAMOND_BOOTS, 1, 0).setUnbreakable(true).setDisplayname("&7Armour").build(),
            new ItemBuilder(Material.DIAMOND_LEGGINGS, 1, 0).setUnbreakable(true).setDisplayname("&7Armour").build(),
            new ItemBuilder(Material.IRON_CHESTPLATE, 1, 0).setUnbreakable(true).setDisplayname("&7Armour").build(),
            new ItemBuilder(Material.DIAMOND_HELMET, 1, 0).setUnbreakable(true).setDisplayname("&7Armour").build(),
            new ItemBuilder(Material.IRON_SWORD, 1, 0).setUnbreakable(true).setDisplayname("&7Sword").build(),
            new ItemBuilder(Material.FISHING_ROD, 1, 0).setUnbreakable(true).setDisplayname("&7Rod").build(),
            new ItemBuilder(Material.BOW, 1, 0).setUnbreakable(true).setDisplayname("&7Bow").build(),
            new ItemBuilder(Material.ARROW, 5, 0).build(),
            new ItemBuilder(Material.FLINT_AND_STEEL, 1, 0).setUnbreakable(true).setDisplayname("Flint").build()}),
    RADIUMPLUS(new ItemStack[]{
            new ItemBuilder(Material.IRON_BOOTS, 1, 0).setUnbreakable(true).setDisplayname("&7Armour").build(),
            new ItemBuilder(Material.DIAMOND_LEGGINGS, 1, 0).setUnbreakable(true).setDisplayname("&7Armour").build(),
            new ItemBuilder(Material.IRON_CHESTPLATE, 1, 0).setUnbreakable(true).setDisplayname("&7Armour").build(),
            new ItemBuilder(Material.DIAMOND_HELMET, 1, 0).setUnbreakable(true).setDisplayname("&7Armour").build(),
            new ItemBuilder(Material.IRON_SWORD, 1, 0).setUnbreakable(true).setDisplayname("&7Sword").build(),
            new ItemBuilder(Material.FISHING_ROD, 1, 0).setUnbreakable(true).setDisplayname("&7Rod").build(),
            new ItemBuilder(Material.BOW, 1, 0).setUnbreakable(true).setDisplayname("&7Bow").build(),
            new ItemBuilder(Material.ARROW, 5, 0).build(),
            new ItemBuilder(Material.FLINT_AND_STEEL, 1, 0).setUnbreakable(true).setDisplayname("Flint").build()}
    ),

    RADIUM(new ItemStack[]{
            new ItemBuilder(Material.DIAMOND_BOOTS, 1, 0).setUnbreakable(true).setDisplayname("&7Armour").build(),
            new ItemBuilder(Material.IRON_LEGGINGS, 1, 0).setUnbreakable(true).setDisplayname("&7Armour").build(),
            new ItemBuilder(Material.IRON_CHESTPLATE, 1, 0).setUnbreakable(true).setDisplayname("&7Armour").build(),
            new ItemBuilder(Material.DIAMOND_HELMET, 1, 0).setUnbreakable(true).setDisplayname("&7Armour").build(),
            new ItemBuilder(Material.IRON_SWORD, 1, 0).setUnbreakable(true).setDisplayname("&7Sword").build(),
            new ItemBuilder(Material.FISHING_ROD, 1, 0).setUnbreakable(true).setDisplayname("&7Rod").build(),
            new ItemBuilder(Material.BOW, 1, 0).setUnbreakable(true).setDisplayname("&7Bow").build(),
            new ItemBuilder(Material.ARROW, 5, 0).build(),
            new ItemBuilder(Material.FLINT_AND_STEEL, 1, 0).setUnbreakable(true).setDisplayname("Flint").build()}
    ),

    VIP(new ItemStack[]{
            new ItemBuilder(Material.IRON_BOOTS, 1, 0).setUnbreakable(true).setDisplayname("&7Armour").build(),
            new ItemBuilder(Material.IRON_LEGGINGS, 1, 0).setUnbreakable(true).setDisplayname("&7Armour").build(),
            new ItemBuilder(Material.IRON_CHESTPLATE, 1, 0).setUnbreakable(true).setDisplayname("&7Armour").build(),
            new ItemBuilder(Material.DIAMOND_HELMET, 1, 0).setUnbreakable(true).setDisplayname("&7Armour").build(),
            new ItemBuilder(Material.IRON_SWORD, 1, 0).setUnbreakable(true).setDisplayname("&7Sword").build(),
            new ItemBuilder(Material.FISHING_ROD, 1, 0).setUnbreakable(true).setDisplayname("&7Rod").build(),
            new ItemBuilder(Material.BOW, 1, 0).setUnbreakable(true).setDisplayname("&7Bow").build(),
            new ItemBuilder(Material.ARROW, 5, 0).build(),
            new ItemBuilder(Material.FLINT_AND_STEEL, 1, 0).setUnbreakable(true).setDisplayname("Flint").build()}
    ),

    DEFAULT(new ItemStack[]{
        new ItemBuilder(Material.IRON_BOOTS, 1, 0).setUnbreakable(true).setDisplayname("&7Armour").build(),
                new ItemBuilder(Material.IRON_LEGGINGS, 1, 0).setUnbreakable(true).setDisplayname("&7Armour").build(),
                new ItemBuilder(Material.IRON_CHESTPLATE, 1, 0).setUnbreakable(true).setDisplayname("&7Armour").build(),
                new ItemBuilder(Material.CHAINMAIL_HELMET, 1, 0).setUnbreakable(true).setDisplayname("&7Armour").build(),
                new ItemBuilder(Material.IRON_SWORD, 1, 0).setUnbreakable(true).setDisplayname("&7Sword").build(),
                new ItemBuilder(Material.FISHING_ROD, 1, 0).setUnbreakable(true).setDisplayname("&7Rod").build(),
                new ItemBuilder(Material.BOW, 1, 0).setUnbreakable(true).setDisplayname("&7Bow").build(),
                new ItemBuilder(Material.ARROW, 5, 0).build(),
                new ItemBuilder(Material.FLINT_AND_STEEL, 1, 0).setUnbreakable(true).setDisplayname("Flint").build()}
    );

    private final ItemStack[] itemStacks;
    KitType(ItemStack[] itemStacks){
        this.itemStacks = itemStacks;
    }
    public ItemStack[] getArmour() {
        return Arrays.copyOfRange(itemStacks, 0, 4);
    }
    public ItemStack[] getTools() {
        return Arrays.copyOfRange(itemStacks, 4, itemStacks.length);
    }
}

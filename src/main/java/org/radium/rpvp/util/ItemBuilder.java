package org.radium.rpvp.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemBuilder {


    private final ItemMeta itemMeta;

    private final ItemStack itemStack;

    public ItemBuilder(Material mat, int size, int theByte) {
        this.itemStack = new ItemStack(mat, size, (short)(byte)theByte);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    public ItemBuilder setDisplayname(String s) {
        this.itemMeta.setDisplayName(TextHelper.format(s));
        return this;
    }

    public ItemBuilder addHiddenEnchantment(){
        itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }
    public ItemBuilder setLore(String... s) {
        this.itemMeta.setLore(Arrays.asList(s));
        return this;
    }

    public ItemBuilder addItemFlags(ItemFlag... s) {
        this.itemMeta.addItemFlags(s);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean flag) {
        this.itemMeta.spigot().setUnbreakable(flag);
        return this;
    }
    public ItemBuilder addEnchant(Enchantment enchant, int level) {
        this.itemMeta.addEnchant(enchant, level, true);
        return this;
    }

    public String toString() {
        return "ItemBuilder{itemMeta=" + this.itemMeta + ", itemStack=" + this.itemStack + '}';
    }

    public ItemStack build() {
        this.itemStack.setItemMeta(this.itemMeta);
        return this.itemStack;
    }

}

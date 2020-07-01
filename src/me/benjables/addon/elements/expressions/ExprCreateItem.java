package me.benjables.addon.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ExprCreateItem extends SimpleExpression<ItemStack> {

    static {
        Skript.registerExpression(ExprCreateItem.class, ItemStack.class, ExpressionType.COMBINED, "new Item{%string%, %string%, %number%, %strings%");
    }

    Expression<String> material;
    Expression<String> name;
    Expression<Number> amount;
    Expression<List<String>> lore;

    @Override
    protected ItemStack[] get(Event event) {
        String n = name.getSingle(event);
        n = ChatColor.translateAlternateColorCodes('&', n);
        int a = amount.getSingle(event).intValue();
        String[] l = (String[]) lore.getSingle(event).toArray();
        for (int i = 0; i < l.length; i++) {
            l[i] = ChatColor.translateAlternateColorCodes('&', l[i]);
        }
        Material m = Material.valueOf(material.getSingle(event));
        ItemStack item = new ItemStack(m, a);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(n);
        meta.setLore(Arrays.asList(l));
        item.setItemMeta(meta);
        return new ItemStack[] {item};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "Create an item";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        material = (Expression<String>) expressions[0];
        name = (Expression<String>) expressions[1];
        amount = (Expression<Number>) expressions[2];
        lore = (Expression<List<String>>) expressions[3];
        return true;
    }
}

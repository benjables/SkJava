package me.benjables.addon.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EffHideFlags extends Effect {
    static {
        Skript.registerEffect(EffHideFlags.class, "%itemstack%.addItemFlag{%string%}");
    }

    Expression<ItemStack> item;
    Expression<String> flag;

    @Override
    protected void execute(Event event) {
        ItemStack i = item.getSingle(event);
        ItemFlag iFlag = ItemFlag.valueOf(flag.getSingle(event));
        if (i != null || iFlag != null) {
            ItemMeta meta = i.getItemMeta();
            meta.addItemFlags(iFlag);
            i.setItemMeta(meta);
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return "Hide flags on an item";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        item = (Expression<ItemStack>) expressions[0];
        flag = (Expression<String>) expressions[1];
        return true;
    }
}

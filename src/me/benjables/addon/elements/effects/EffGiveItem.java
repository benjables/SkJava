package me.benjables.addon.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public class EffGiveItem extends Effect {

    static {
        Skript.registerEffect(EffGiveItem.class, "%player%.giveItem{%itemstack%}");
    }

    Expression<Player> player;
    Expression<ItemStack> item;

    @Override
    protected void execute(Event event) {
        Player p = player.getSingle(event);
        ItemStack i = item.getSingle(event);
        if (p != null && i != null) {
            p.getInventory().addItem(i);
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return "Give player an item";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        player = (Expression<Player>) expressions[0];
        item = (Expression<ItemStack>) expressions[1];
        return true;
    }
}

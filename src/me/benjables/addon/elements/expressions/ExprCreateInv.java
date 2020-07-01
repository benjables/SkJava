package me.benjables.addon.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;

import javax.annotation.Nullable;

public class ExprCreateInv extends SimpleExpression<Inventory> {

    static {
        Skript.registerExpression(ExprCreateInv.class, Inventory.class, ExpressionType.COMBINED, "%player%.createInventory{%number%, %string%}");
    }

    private Expression<Player> player;
    private Expression<Number> rows;
    private Expression<String> name;

    @Override
    public Class<? extends Inventory> getReturnType() {
        return Inventory.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        player = (Expression<Player>) exprs[0];
        rows = (Expression<Number>) exprs[1];
        name = (Expression<String>) exprs[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Create an inventory";
    }

    @Override
    @Nullable
    protected Inventory[] get(Event event) {
        Player p = player.getSingle(event);
        Integer rowSize = rows.getSingle(event).intValue();
        String title = ChatColor.translateAlternateColorCodes('&', name.getSingle(event));
        if (p != null && rowSize != null && title != null) {
            Inventory inv = Bukkit.createInventory(p, rowSize * 9, title);
            return new Inventory[]{inv};
        }
        return null;
    }
}

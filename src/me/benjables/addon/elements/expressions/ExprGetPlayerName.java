package me.benjables.addon.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class ExprGetPlayerName extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprGetPlayerName.class, String.class, ExpressionType.SIMPLE, "%player%.getDisplayName{}");
    }

    Expression<Player> player;

    @Override
    protected String[] get(Event event) {
        Player p = player.getSingle(event);
        if (p != null) {
            return new String[] {p.getDisplayName()};
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "Get player name";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        player = (Expression<Player>) expressions[0];
        return true;
    }
}

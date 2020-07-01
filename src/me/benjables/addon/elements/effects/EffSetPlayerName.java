package me.benjables.addon.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EffSetPlayerName extends Effect {

    static {
        Skript.registerEffect(EffSetPlayerName.class, "%player%.setDisplayName{%string%}");
    }

    Expression<Player> player;
    Expression<String> name;

    @Override
    protected void execute(Event event) {
        Player p = player.getSingle(event);
        String s = name.getSingle(event);
        s = ChatColor.translateAlternateColorCodes('&', s);
        if (p != null && s != null) {
            p.setDisplayName(s);
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return "Set player's display name";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        player = (Expression<Player>) expressions[0];
        name = (Expression<String>) expressions[1];
        return true;
    }
}

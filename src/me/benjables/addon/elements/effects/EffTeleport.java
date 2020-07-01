package me.benjables.addon.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EffTeleport extends Effect {

    Expression<Player> player;
    Expression<Location> location;

    static {
        Skript.registerEffect(EffTeleport.class, "%player%.teleport(%location%)");
    }

    @Override
    protected void execute(Event event) {
        Location loc = this.location.getSingle(event);
        Player player = this.player.getSingle(event);
        player.teleport(loc);
    }

    @Override
    public String toString(Event event, boolean b) {
        return "Teleport player";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.player = (Expression<Player>) expressions[0];
        this.location = (Expression<Location>) expressions[1];
        return true;
    }
}

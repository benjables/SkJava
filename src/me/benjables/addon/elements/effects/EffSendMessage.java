package me.benjables.addon.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EffSendMessage extends Effect {

    Expression<Player> player;
    Expression<String> msg;

    static {
        Skript.registerEffect(EffSendMessage.class, "%player%.sendMessage(%string%)");
    }

    @Override
    protected void execute(Event event) {
        String s = this.msg.getSingle(event);
        Player p = this.player.getSingle(event);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
    }

    @Override
    public String toString(Event event, boolean b) {
        return "Send Message to player";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.player = (Expression<Player>) expressions[0];
        this.msg = (Expression<String>) expressions[1];
        return true;
    }
}

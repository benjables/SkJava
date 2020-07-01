package me.benjables.addon.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Event;

public class ExprCreateLocation extends SimpleExpression<Location> {

    static {
        Skript.registerExpression(ExprCreateLocation.class, Location.class, ExpressionType.COMBINED, "new Location{%world%, %number%, %number%, %number%}");
    }

    private Expression<World> world;
    private Expression<Number> x;
    private Expression<Number> y;
    private Expression<Number> z;

    @Override
    protected Location[] get(Event event) {
        World w = world.getSingle(event);
        double xValue = x.getSingle(event).doubleValue();
        double yValue = y.getSingle(event).doubleValue();
        double zValue = z.getSingle(event).doubleValue();
        Location loc = new Location(w, xValue, yValue, zValue);
        return new Location[] {loc};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Location> getReturnType() {
        return Location.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "Create new location";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        world = (Expression<World>) expressions[0];
        x = (Expression<Number>) expressions[1];
        y = (Expression<Number>) expressions[2];
        z = (Expression<Number>) expressions[3];
        return true;
    }
}

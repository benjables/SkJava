package me.benjables;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.lang.ExpressionType;
import me.benjables.addon.elements.expressions.ExprCreateInv;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    Logger logger = Bukkit.getLogger();
    Main instance;
    SkriptAddon addon;
    private static List<Inventory> inventories;

    public static List<Inventory> getInventories() {
        return inventories;
    }

    @Override
    public void onEnable() {
        inventories = new ArrayList<>();
        addon = Skript.registerAddon(this);
        if (Bukkit.getPluginManager().getPlugin("Skript") != null) {
            try {
                addon.loadClasses("me.benjables.addon", "elements");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info("SkTest enabled!");
    }

    public Main getInstance() {
        return instance;
    }

    public SkriptAddon getAddonInstance() {
        return addon;
    }
}

package me.l1near.harvestingtools;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.plugin.PluginBase;

public class Loader extends PluginBase {


    private static Loader instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        System.out.println("Harvesting-Tools by L1near is now enabled!");

        this.getServer().getPluginManager().registerEvents(new EventListener(), this);
        this.getServer().getCommandMap().register("command", new HarvestingToolCommand());
    }

    public static void addItem(Player player, Item item) {
        if (player.getInventory().firstEmpty(item) == -1) {
            player.sendTitle(Loader.getInstance().getConfig().getString("Messages.Title.Text"), Loader.getInstance().getConfig().getString("Messages.Title.Sub-Title"));
            player.getLevel().dropItem(player.getLocation(), item);
            return;
        }
        player.getInventory().addItem(item);
    }


    public static Loader getInstance() {
        return instance;
    }
}

package me.l1near.harvestingtools;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemID;
import cn.nukkit.nbt.tag.CompoundTag;

public class HarvestingToolCommand extends Command {
    public HarvestingToolCommand() {
        super("harvestingtool");
        this.setAliases(new String[]{"ht", "htool", "harvesttool"});
        this.setPermission("admin.harvesting-tool");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!this.testPermission(commandSender)) {
            commandSender.sendMessage("§r§cYou do not have enough permission to execute this command!");
            return false;
        }
        if (strings.length < 1) return false;
        Player target = Server.getInstance().getPlayer(strings[0]);
        Item item = Item
                .get(ItemID.DIAMOND_HOE, 0, 1)
                .setCustomName("§r§l§aHarvesting Hoe")
                .setNamedTag(new CompoundTag("harvesting-tool").putString("harvesting-tool", "hoe"));
        if (target == null) {
            commandSender.sendMessage("§r§cPlayer cannot be found or is offline!");
            return false;
        }
        target.getInventory().addItem(item);
        target.sendMessage("§r§aYou have received a §r§l§aHarvesting Hoe");
        commandSender.sendMessage("§r§a" + target.getName() + " was given a §r§l§aHarvesting Hoe");
        return false;
    }
}

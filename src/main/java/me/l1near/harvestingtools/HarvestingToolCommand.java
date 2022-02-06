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
            commandSender.sendMessage(Loader.getInstance().getConfig().getString("Messages.No-Permission message"));
            return false;
        }
        if (strings.length < 1) return false;
        Player target = Server.getInstance().getPlayer(strings[0]);
        Item item = Item
                .get(ItemID.DIAMOND_HOE, 0, 1)
                .setCustomName(Loader.getInstance().getConfig().getString("HarvestingTools.Hoe.Custom Name"))
                .setNamedTag(new CompoundTag("harvesting-tool").putString("harvesting-tool", "hoe"));
        if (target == null) {
            commandSender.sendMessage(Loader.getInstance().getConfig().getString("Messages.Player not found")
                    .replace("{TARGET_USERNAME}", strings[0])
                    .replace("{COMMAND_SENDER_USERNAME}", commandSender.getName())
            );
            return false;
        }
        target.getInventory().addItem(item);
        target.sendMessage(Loader.getInstance().getConfig().getString("Messages.Item received")
                .replace("{TARGET_USERNAME}", target.getName())
                .replace("{COMMAND_SENDER_USERNAME}", commandSender.getName())
        );
        commandSender.sendMessage(Loader.getInstance().getConfig().getString("Messages.Item received")
                .replace("{TARGET_USERNAME}", target.getName())
                .replace("{COMMAND_SENDER_USERNAME}", commandSender.getName())
        );
        return false;
    }
}

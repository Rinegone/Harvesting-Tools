package me.l1near.harvestingtools;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.block.BlockSugarcane;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemID;
import cn.nukkit.level.particle.DestroyBlockParticle;

import java.util.Objects;

public class EventListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Item item = player.getInventory().getItemInHand();
        Block block = event.getBlock();
        if (event.isCancelled()) return;
        if (item.getNamedTag().contains("harvesting-tool") && Objects.equals(item.getNamedTag().getString("harvesting-tool"), "hoe")) {
            if (block instanceof BlockSugarcane) {
                for (int i = 0; i < 3; i++) {
                    Block sugarCane = (Block) player.getLevel().getBlock(block).add(0, i, 0);
                    if (sugarCane.getId() != BlockID.SUGARCANE_BLOCK) return;
                    event.setCancelled();
                    player.getLevel().setBlock(sugarCane.getLocation(), Block.get(Block.AIR));
                    player.getLevel().addParticle(new DestroyBlockParticle(sugarCane, Block.get(BlockID.SUGARCANE_BLOCK)));
                    Loader.addItem(player, Item.get(ItemID.SUGARCANE, 0, 1));
                }
            }
        }
    }

}

package org.lakki.sphardcorel;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class AdEvent implements Listener {
    private HashMap<UUID, Integer> timerMap = new HashMap<>();


    private final JavaPlugin plugin;

    public AdEvent(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this,plugin);
    }


    @EventHandler
    public void onPlaerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Location to = event.getTo();
        World world = to.getWorld();
        if (world != null && world.getName().equalsIgnoreCase("world_nether")) {
            if (!timerMap.containsKey(uuid)) {
                startTimer(player);
            } else {
                resetTimer(player);
            }
        } else {
            resetTimer(player);
        }
    }

    private void startTimer(Player player) {
        timerMap.put(player.getUniqueId(), Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            if (player.isOnline()) {
                player.setFireTicks(Integer.MAX_VALUE); // Fire will last indefinitely until player leaves hell

            }
        }, 0L, 18000L)); // 15 minutes (18000 ticks = 15 minutes)
    }


    private void resetTimer(Player player) {
        if (timerMap.containsKey(player.getUniqueId())) {
            Bukkit.getScheduler().cancelTask(timerMap.get(player.getUniqueId()));
            timerMap.remove(player.getUniqueId());
        }
    }
}

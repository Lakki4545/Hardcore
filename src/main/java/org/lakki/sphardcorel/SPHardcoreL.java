package org.lakki.sphardcorel;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.TimeSkipEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public final class SPHardcoreL extends JavaPlugin implements  Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new ZombieSpawn(), this);
        Bukkit.getPluginManager().registerEvents(new SkeletSpawn(), this);
        Bukkit.getPluginManager().registerEvents(new PiglinSpawn(), this);
        Bukkit.getPluginManager().registerEvents(new CreeperSpawn(), this);
        Bukkit.getPluginManager().registerEvents(new SpiderSpawn(), this);
        Bukkit.getPluginManager().registerEvents(new BlazeSpawn(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerHeightEffect(), this);
        Bukkit.getPluginManager().registerEvents(new PowerfulZombie(), this);
        Bukkit.getPluginManager().registerEvents(new PhantomSpawn(), this);
    }
    @Override
    public void onDisable() {

    }





    private boolean isBloodyMoon = false;
    private final Random random2 = new Random();



    @EventHandler
    public void onTimeSkip(TimeSkipEvent event) {
        World world = event.getWorld();
        long skippedTime = event.getSkipAmount();
        long currentTime = world.getTime();


        if (!isBloodyMoon && currentTime >= 13000 && random2.nextDouble() <= 0.1) {
            startBloodyMoon(world);
            isBloodyMoon = true;
        } else if(isBloodyMoon && currentTime >= 1000){
            endBloodyMoon(world);
            isBloodyMoon = false;
        }

    }

    private void startBloodyMoon(World world) {

        Bukkit.broadcastMessage(ChatColor.RED + "Кровавая луна наступает");
        world.setTime(13000); // Установка времени на начало ночи
        world.setStorm(true);
        world.setThundering(true);
        world.setWeatherDuration(6000); // Продолжительность грозы (в тиках) для 5 минут ночи

        Bukkit.getScheduler().runTaskTimer( this, () -> {
            for (LivingEntity entity : world.getLivingEntities()) {
                if (entity.getType() == EntityType.ZOMBIE) {
                    ((LivingEntity) entity).getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(30);
                    ((LivingEntity) entity).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.5);
                    ((LivingEntity) entity).setHealth(40);
                }
                if (entity.getType() == EntityType.SKELETON) {
                    ((LivingEntity) entity).getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(30);
                    ((LivingEntity) entity).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.5);
                    ((LivingEntity) entity).setHealth(40);
                }
                if (entity.getType() == EntityType.CREEPER) {
                    ((LivingEntity) entity).getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(30);
                    ((LivingEntity) entity).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.5);
                    ((LivingEntity) entity).setHealth(40);
                }
                if (entity.getType() == EntityType.SPIDER) {
                    ((LivingEntity) entity).getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(30);
                    ((LivingEntity) entity).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.5);
                    ((LivingEntity) entity).setHealth(40);
                }
                if (entity.getType() == EntityType.PHANTOM){

                }
            }
        }, 0, 20);

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            world.spawnEntity(world.getSpawnLocation(), EntityType.PHANTOM);
        }, 0, 200);
    }

    private void endBloodyMoon(World world) {

        Bukkit.broadcastMessage(ChatColor.RED + "Кровавая луна отступает");
        world.setStorm(false);
        world.setThundering(false);

        for (LivingEntity entity : world.getLivingEntities()) {
            if (entity.getType() == EntityType.ZOMBIE) {
                ((LivingEntity) entity).getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(12);
                ((LivingEntity) entity).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.2);
                ((LivingEntity) entity).setHealth(40);
            }
            if (entity.getType() == EntityType.SKELETON) {
                ((LivingEntity) entity).getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(12);
                ((LivingEntity) entity).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1);
                ((LivingEntity) entity).setHealth(40);
            }
            if (entity.getType() == EntityType.CREEPER) {
                ((LivingEntity) entity).getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(12);
                ((LivingEntity) entity).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
                ((LivingEntity) entity).setHealth(40);
            }
            if (entity.getType() == EntityType.SPIDER) {
                ((LivingEntity) entity).getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(12);
                ((LivingEntity) entity).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
                ((LivingEntity) entity).setHealth(40);
            }
        }
    }








    private HashMap<Player, Integer> timeInHell = new HashMap<>();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getWorld().getName().equals("world_nether")) {
            timeInHell.put(player, timeInHell.getOrDefault(player, 0) + 1);
            if (timeInHell.get(player) >= 18000) {
                player.setFireTicks(100);
            }
        } else {
            timeInHell.remove(player);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        timeInHell.remove(event.getPlayer());
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getWorld().getName().equals("world_nether")) {
            if (player.getInventory().contains(Material.WATER_BUCKET)) {
                ItemStack bonestack = new ItemStack(Material.BUCKET, 1);
                player.getInventory().addItem(bonestack);

                        player.setFireTicks(200); //Много горения на 10 секунд


                player.getInventory().remove(Material.WATER_BUCKET);
            }
        }
    }

}

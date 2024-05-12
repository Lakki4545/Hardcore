package org.lakki.sphardcorel;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class PowerfulZombie implements Listener {

    private final Random random = new Random();
    private LivingEntity currentPowerfulZombie = null;

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        if (event.getEntityType() == EntityType.ZOMBIE) {
            LivingEntity zombie = (LivingEntity) event.getEntity();
            if (random.nextInt(10) == 0) { // Adjust the chance as needed
                makeZombiePowerful(zombie);
                currentPowerfulZombie = zombie;
            }
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity() == currentPowerfulZombie) {
            currentPowerfulZombie = null;
            // Spawn a new powerful zombie
            spawnPowerfulZombie();
        }
    }

    private void spawnPowerfulZombie() {
        // Spawn a new powerful zombie here
    }

    private void makeZombiePowerful(LivingEntity zombie) {
        if (zombie instanceof Zombie) {
            Zombie powerfulZombie = (Zombie) zombie;
            powerfulZombie.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).setBaseValue(80.0); // Устанавливаем максимальное здоровье зомби
            powerfulZombie.setHealth(80.0); // Устанавливаем текущее здоровье зомби
            powerfulZombie.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4); // Устанавливаем скорость зомби
        }

        zombie.getEquipment().setHelmet(new ItemStack(Material.GOLDEN_HELMET));
        zombie.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));
        zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
    }   // You can add more
}

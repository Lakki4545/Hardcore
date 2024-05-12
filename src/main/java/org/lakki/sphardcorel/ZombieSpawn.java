package org.lakki.sphardcorel;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ZombieSpawn implements Listener {
    @EventHandler
    public void onZonbieSpawn(CreatureSpawnEvent event){
        if (event.getEntityType() == EntityType.ZOMBIE){
            Zombie zombie = (Zombie) event.getEntity();
            //бафы

            zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(12.0);
            zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);
            zombie.setHealth(40.0);
            zombie.setVelocity(zombie.getVelocity().setY(0.6));
            zombie.setJumping(true);

            zombie.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,Integer.MAX_VALUE, 5, true, false));
        }
        if (event.getEntityType() == EntityType.DROWNED){
            Zombie zombie = (Zombie) event.getEntity();
            //бафы

            zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(12.0);
            zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);
            zombie.setHealth(40.0);




            zombie.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,Integer.MAX_VALUE, 5, true, false));
        }
        if (event.getEntityType() == EntityType.HUSK){
            Zombie zombie = (Zombie) event.getEntity();
            //бафы

            zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(12.0);
            zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);
            zombie.setHealth(40.0);




            zombie.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,Integer.MAX_VALUE, 5, true, false));
        }
        if (event.getEntityType() == EntityType.ZOMBIE_VILLAGER){
            Zombie zombie = (Zombie) event.getEntity();
            //бафы



            zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(12.0);
            zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);
            zombie.setHealth(40.0);



            zombie.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,Integer.MAX_VALUE, 10, true, false));
        }



    }





    @EventHandler
    public void onZombieTarget(EntityTargetEvent event) {
        if (event.getEntityType() == EntityType.ZOMBIE) {
            Zombie zombie = (Zombie) event.getEntity();
            Location zombieLocation = zombie.getLocation();

            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        if (x == 0 && y == 0 && z == 0) continue;
                        if (y < 0) continue; // Добавляем проверку на положительную высоту блока
                        Location blockLocation = zombieLocation.clone().add(x, y, z);
                        if (blockLocation.getBlock().getType() != Material.AIR &&
                                blockLocation.getBlock().getType() != Material.WATER &&
                                blockLocation.getBlock().getType() != Material.LAVA &&
                                blockLocation.getBlock().getType() != Material.OBSIDIAN &&
                                blockLocation.getBlock().getType() != Material.BEDROCK &&
                                blockLocation.getBlock().getType() != Material.END_PORTAL_FRAME) {
                            blockLocation.getBlock().setType(Material.AIR);
                        }
                    }
                }
            }
        }
    }
}

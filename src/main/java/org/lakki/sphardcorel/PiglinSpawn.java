package org.lakki.sphardcorel;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Piglin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class PiglinSpawn implements Listener {

    private Random random = new Random();

    @EventHandler
    public void onpigSpawn(EntitySpawnEvent event) {
        if (event.getEntity().getType() == EntityType.PIGLIN) {
            Piglin piglin = (Piglin) event.getEntity();
            piglin.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(12.0);
            piglin.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);
            piglin.setHealth(40.0);
        }
    }

  //  @EventHandler
  //  public void onEnitySpawn(EntitySpawnEvent event) {
  //      if (event.getEntityType() == EntityType.PIGLIN) {
  //          if (random.nextInt(5) == 0) { // 20% chance
  //              event.getEntity().remove();
  //              Location location = event.getLocation();
  //              World world = location.getWorld();
//
//
  //              Piglin brutishPiglin = (Piglin) world.spawnEntity(location, EntityType.PIGLIN_BRUTE);
//
  //              brutishPiglin.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
//
  //          }
  //      }
  //  }
//



}

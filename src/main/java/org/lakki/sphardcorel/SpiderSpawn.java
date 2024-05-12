package org.lakki.sphardcorel;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpiderSpawn implements Listener {

    @EventHandler
    public void onSpiderSpawn(EntitySpawnEvent event) {
        if (event.getEntity().getType() == EntityType.SPIDER) {
            Spider spider = (Spider) event.getEntity();

            // Делаем паука ядовитым
            spider.addPotionEffect(new PotionEffect(PotionEffectType.POISON, Integer.MAX_VALUE, 1, false, false));


            spider.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);
            spider.setHealth(40.0);

            spider.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,Integer.MAX_VALUE, 5, true, false));
        }
    }

    @EventHandler
    public void onSpiderAttack(EntityDamageByEntityEvent event) {
        if (event.getEntityType() == EntityType.PLAYER && event.getDamager() instanceof Spider) {
            Player player = (Player) event.getEntity();
            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0));
        }
    }
}

package org.lakki.sphardcorel;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

public class BlazeSpawn implements Listener {


    @EventHandler
    public  void onBlazeSpawn(EntityShootBowEvent event){
        if (event.getEntityType() == EntityType.BLAZE && event.getEntity().getCustomName() != null && event.getEntity().getCustomName().equals("Ifrit")) {
            for (int i = 0; i < 3; i++) {
                Fireball projectile = event.getEntity().launchProjectile(Fireball.class);
                projectile.setVelocity(event.getProjectile().getVelocity()); // Устанавливаем скорость дополнительных снарядов
            }
        }
    }
}

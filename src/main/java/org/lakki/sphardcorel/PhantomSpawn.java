package org.lakki.sphardcorel;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Phantom;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class PhantomSpawn implements Listener {
    @EventHandler
    public void onPhantomSpawn(EntityDamageEvent event){
        if (event.getEntity().getType() == EntityType.PLAYER && event.getEntity() instanceof Phantom){
            Phantom phantom = (Phantom) event.getEntity();
            phantom.getWorld().createExplosion(phantom.getLocation(), 4.0f, false, false);
            phantom.remove();
            event.setCancelled(true);
        }
    }
}

package org.lakki.sphardcorel;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreeperPowerEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.UUID;

public class CreeperSpawn implements Listener {

    @EventHandler
    public void onCreeperSpawn(EntitySpawnEvent event) {
        if (event.getEntity() instanceof Creeper) {
            Creeper creeper = (Creeper) event.getEntity();

            // Увеличение скорости крипера на 1,5%
            double currentSpeed = creeper.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue();
            AttributeModifier speedBoost = new AttributeModifier(UUID.randomUUID(), "Speed Boost", currentSpeed * 0.4, AttributeModifier.Operation.ADD_NUMBER);
            creeper.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).addModifier(speedBoost);

            // Заряжаем крипера
            creeper.setPowered(true);

            creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(60.0);
            creeper.setHealth(60.0);
        }
    }

    @EventHandler
    public void onCreeperDamage(CreeperPowerEvent event){
        Creeper creeper = (Creeper) event.getEntity();
        creeper.setExplosionRadius(8);
    }
}

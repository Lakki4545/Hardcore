package org.lakki.sphardcorel;

import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

public class MobHelmet implements Listener {

    private final Material[] helmetMaterials = {Material.LEATHER_HELMET, Material.CHAINMAIL_HELMET, Material.IRON_HELMET};

    private ItemStack getRandomHelmet() {
        Random random = new Random();
        Material helmetMaterial = helmetMaterials[random.nextInt(helmetMaterials.length)];
        ItemStack helmet = new ItemStack(helmetMaterial);
        // Устанавливаем случайную прочность для шлема (для имитации износа)
        ItemMeta meta = helmet.getItemMeta();
        if (meta instanceof Damageable) {
            Damageable damageable = (Damageable) meta;
            damageable.damage(random.nextInt(helmetMaterial.getMaxDurability()));
            helmet.setItemMeta(meta);
        }
        return helmet;
    }


    @EventHandler
    public void onSkeletonAndZombieSpawn(EntitySpawnEvent event) {
        if (event.getEntityType() == EntityType.SKELETON || event.getEntityType() == EntityType.ZOMBIE) {
            LivingEntity entity = (LivingEntity) event.getEntity();
            ItemStack helmet = getRandomHelmet();
            entity.getEquipment().setHelmet(helmet);
        }
    }
}

package org.lakki.sphardcorel;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.SpectralArrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;

public class SkeletSpawn implements Listener {


    @EventHandler
    public void onSkeletSpawn(EntitySpawnEvent event){
        if(event.getEntity().getType() == EntityType.SKELETON){
            Skeleton skeleton = (Skeleton) event.getEntity();
            //лук
            ItemStack bow = new ItemStack(Material.BOW);
            bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 3);
            skeleton.getEquipment().setItemInMainHand(bow);

            //стрелы
            ItemStack arrow = new ItemStack(Material.ARROW);
            skeleton.getEquipment().setItemInOffHand(arrow);

            skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40.0);
            skeleton.setHealth(40.0);
        }
    }


    @EventHandler
    public  void onSkeletonShoot(EntityShootBowEvent event){
        if(event.getEntity().getType() == EntityType.SKELETON){
            Skeleton skeleton = (Skeleton) event.getEntity();
            if (skeleton.getSkeletonType() == Skeleton.SkeletonType.NORMAL){
                SpectralArrow arrow = (SpectralArrow) skeleton.launchProjectile(SpectralArrow.class);
                arrow.setFireTicks(200);
            }
        }
    }
}

package frosty.hastati.entity.custom;

import frosty.hastati.Hastati;
import frosty.hastati.entity.ModEntities;
import frosty.hastati.item.ModItems;
import net.minecraft.client.util.math.Vector2f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

import java.util.Objects;

public class PilumEntity extends PersistentProjectileEntity {


    public PilumEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public PilumEntity(World world, PlayerEntity player) {
        super(ModEntities.PILUM, player, world, new ItemStack(ModItems.PILUM), null);

    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModItems.PILUM);
    }




    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();

        LivingEntity livingEntity2 = (LivingEntity)entity;
        if (!this.getWorld().isClient && this.getPierceLevel() <= 0) {
            livingEntity2.setStuckArrowCount(0);
        }


        if (entity instanceof PlayerEntity player) {
            if (player.isBlocking()) {
                breakShield(player);
                playSound(this.getWorld(), entity);
            } else {
                entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 8);
                applySlowness(this.getWorld(), player);
            }
        } else {
            entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 11);
            if(entity instanceof LivingEntity mob) {
                applySlowness(this.getWorld(), mob);
            }
        }

        if (!this.getWorld().isClient()) {
            this.getWorld().sendEntityStatus(this, (byte)3);
            this.discard();
        }
    }

    public void applySlowness(World world, LivingEntity entity) {
        if (!world.isClient) { // Always apply effects on the server side
            entity.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.SLOWNESS, // The Effect
                    40,                // Duration in Ticks (20 ticks = 1 second, so 200 = 10s)
                    2,                  // Amplifier (0 = Level I, 1 = Level II)
                    false,              // Ambient (makes particles translucent like beacons)
                    false,               // Show Particles
                    true                // Show Icon in HUD
            ));
        }
    }


    private void playSound(World world, Entity user) {
        if (!world.isClient) {
                world.playSound(
                        null,                      // The player to EXCLUDE (null = everyone hears it)
                        user.getBlockPos(),        // Position
                        SoundEvents.ITEM_SHIELD_BREAK, // The sound
                        SoundCategory.PLAYERS,     // The volume slider this sound follows
                        1.0F,                      // Volume
                        1.0F                       // Pitch (1.0 is normal)
                );
        }
    }



    private void breakShield(PlayerEntity player) {
        // 100 ticks = 5 seconds
        player.getItemCooldownManager().set(Items.SHIELD, 100);
        player.getItemCooldownManager().set(ModItems.HASTATI_SHIELD, 100);
        player.getItemCooldownManager().set(ModItems.SCUTUM_RED, 100);
        player.getItemCooldownManager().set(ModItems.SCUTUM_RED_BOAR, 100);
        player.getItemCooldownManager().set(ModItems.SCUTUM_RED_GOLD, 100);
        player.getItemCooldownManager().set(ModItems.SCUTUM_RED_LAUREL, 100);
        player.getItemCooldownManager().set(ModItems.SCUTUM_WHITE, 100);
        player.getItemCooldownManager().set(ModItems.SCUTUM_WHITE_PATTERNS, 100);

        // Trigger the shield break sound/particle effect
        player.getWorld().sendEntityStatus(player, (byte) 30);

        // Stop the player from using the item immediately
        player.stopUsingItem();
    }
}

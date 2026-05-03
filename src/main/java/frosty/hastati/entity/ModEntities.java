package frosty.hastati.entity;

import frosty.hastati.Hastati;
import frosty.hastati.entity.custom.PilumEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<PilumEntity> PILUM = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Hastati.MOD_ID, "pilum"),
            EntityType.Builder.<PilumEntity>create(PilumEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 1.15f).build());

    public static void registerModEntities() {
        Hastati.LOGGER.info("[Registering Mod Entities");
    }
}

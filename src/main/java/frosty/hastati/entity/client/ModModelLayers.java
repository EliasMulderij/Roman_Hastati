package frosty.hastati.entity.client;

import frosty.hastati.Hastati;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer PILUM = new EntityModelLayer(
            Identifier.of(Hastati.MOD_ID, "pilum"), "main");
}
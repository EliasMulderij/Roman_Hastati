package frosty.hastati;

import com.github.crimsondawn45.fabricshieldlib.initializers.FabricShieldLibClient;
import frosty.hastati.entity.ModEntities;
import frosty.hastati.entity.client.ModModelLayers;
import frosty.hastati.entity.client.PilumEntityRenderer;
import frosty.hastati.entity.client.PilumProjectileModel;
import frosty.hastati.item.ModItems;
import frosty.hastati.item.custom.PilumItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.util.Identifier;


public class HastatiClient implements ClientModInitializer {

    private static int clientTicks = 0;


    @Override
    public void onInitializeClient() {


        ModelLoadingPlugin.register(pluginContext -> {
            pluginContext.addModels(Identifier.of("hastati", "item/hastati_shield_3d"));
        });


        ModItems.registerModItems();
        ModEntities.registerModEntities();

        EntityRendererRegistry.register(ModEntities.PILUM, PilumEntityRenderer::new);

        // 2. Link the Model Layer to the Model's geometry (the Blockbench code)
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PILUM, PilumProjectileModel::getTexturedModelData);



    }
}

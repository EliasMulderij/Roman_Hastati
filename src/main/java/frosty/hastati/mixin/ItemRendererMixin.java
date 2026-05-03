package frosty.hastati.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import frosty.hastati.Hastati;
import frosty.hastati.item.ModItems;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.builder.Diff;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Random;
import java.util.random.RandomGenerator;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Shadow
    @Final
    private ItemModels models;

    @Shadow
    public abstract ItemModels getModels();


    @ModifyVariable(
            method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
            at = @At(value = "HEAD"),
            argsOnly = true
    )
    public BakedModel renderItem(BakedModel bakedModel, @Local(argsOnly = true) ItemStack stack, @Local(argsOnly = true) ModelTransformationMode renderMode) {


        if (renderMode == ModelTransformationMode.GUI) {
            if (bakedModel.isSideLit()) {
                DiffuseLighting.enableGuiDepthLighting();
            } else {
                DiffuseLighting.disableGuiDepthLighting();
            }
        } else {
            DiffuseLighting.enableForLevel();
        }



        if (stack.getItem() == ModItems.GLADIUS && (renderMode == ModelTransformationMode.GUI || renderMode == ModelTransformationMode.GROUND || renderMode == ModelTransformationMode.FIXED)) {
            DiffuseLighting.disableGuiDepthLighting();
            return getModels().getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Identifier.of(Hastati.MOD_ID, "gladius")));
        }
        if (stack.getItem() == ModItems.PILUM && (renderMode == ModelTransformationMode.GUI || renderMode == ModelTransformationMode.GROUND || renderMode == ModelTransformationMode.FIXED)) {
            DiffuseLighting.disableGuiDepthLighting();
            return getModels().getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Identifier.of(Hastati.MOD_ID, "pilum")));
        }

        return bakedModel;
    }

    @ModifyVariable(
            method = "getModel",
            at = @At(value = "STORE"),
            ordinal = 1
    )
    public BakedModel getHeldItemModelMixin(BakedModel bakedModel, @Local(argsOnly = true) ItemStack stack) {
        if (stack.getItem() == ModItems.GLADIUS) {
            return this.models.getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Identifier.of(Hastati.MOD_ID, "gladius_3d")));
        }
        if (stack.getItem() == ModItems.PILUM) {
            return this.models.getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Identifier.of(Hastati.MOD_ID, "pilum_3d")));
        }

        return bakedModel;
    }
}
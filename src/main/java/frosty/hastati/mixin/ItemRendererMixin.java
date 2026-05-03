package frosty.hastati.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import frosty.hastati.Hastati;
import frosty.hastati.item.ModItems;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.builder.Diff;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;
import java.util.random.RandomGenerator;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Shadow @Final private ItemModels models;

    @ModifyVariable(
            method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
            at = @At("HEAD"),
            argsOnly = true
    )
    public BakedModel swapModel(BakedModel originalModel, @Local(argsOnly = true) ItemStack stack, @Local(argsOnly = true) ModelTransformationMode renderMode) {
        if (!stack.isEmpty() && (stack.isOf(ModItems.GLADIUS) || stack.isOf(ModItems.PILUM))) {

            // 'bl' from the vanilla code (GUI, Ground, or Fixed)
            boolean is2DView = renderMode == ModelTransformationMode.GUI ||
                    renderMode == ModelTransformationMode.GROUND ||
                    renderMode == ModelTransformationMode.FIXED;

            String baseName = stack.isOf(ModItems.GLADIUS) ? "gladius" : "pilum";

            // Exactly like vanilla: If we are in GUI/Ground/Fixed, we want the 2D version.
            // Otherwise (Hand), we want the 3D version.
            String modelPath = is2DView ? baseName : baseName + "_3d";

            return this.models.getModelManager().getModel(
                    ModelIdentifier.ofInventoryVariant(Identifier.of("hastati", modelPath))
            );
        }
        return originalModel;
    }
}
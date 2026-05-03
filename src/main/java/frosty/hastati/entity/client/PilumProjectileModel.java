package frosty.hastati.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

public class PilumProjectileModel<T extends Entity> extends EntityModel<T> {
    private final ModelPart bone;

    public PilumProjectileModel(ModelPart root) {
        this.bone = root.getChild("bone");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        // Converted from Mojang Mappings (CubeListBuilder/PartDefinition) to Yarn (ModelPartBuilder/ModelPartData)
        modelPartData.addChild("bone",
                ModelPartBuilder.create()
                        .uv(28, 10).cuboid(-8.5F, -21.0F, 7.5F, 1.0F, 21.0F, 1.0F, new Dilation(0.0F))
                        .uv(26, 23).cuboid(-8.25F, -31.0F, 7.75F, 0.5F, 8.0F, 0.5F, new Dilation(0.0F))
                        .uv(18, 28).cuboid(-9.0F, -23.0F, 7.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(8.0F, 24.0F, -8.0F));

        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        // Equivalent to setupAnim
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        // 1.21.1 uses a single 'int color' instead of 4 floats (RGBA)
        bone.render(matrices, vertices, light, overlay, color);
    }
}
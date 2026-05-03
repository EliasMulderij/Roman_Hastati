package frosty.hastati.entity.client;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import frosty.hastati.Hastati;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

// FIX: Only use <T extends PersistentProjectileEntity> here
public class PilumEntityRenderer<T extends PersistentProjectileEntity> extends ProjectileEntityRenderer<T> {
    public static final Identifier TEXTURE = Identifier.of(Hastati.MOD_ID, "textures/entity/projectiles/pilum.png");
    protected final PilumProjectileModel<T> model;

    public PilumEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        // We bake the model here and store it
        this.model = new PilumProjectileModel<>(context.getPart(ModModelLayers.PILUM));
    }

    @Override
    public Identifier getTexture(T entity) {
        return TEXTURE;
    }

    @Override
    public void render(T entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        // This helper method from ProjectileEntityRenderer handles the math
        // to make the projectile point in the direction of its velocity.
        float g = MathHelper.lerpAngleDegrees(tickDelta, entity.prevYaw, entity.getYaw());
        float h = MathHelper.lerpAngleDegrees(tickDelta, entity.prevPitch, entity.getPitch());

        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(g - 90.0F));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(h + 90.0F));

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.model.getLayer(getTexture(entity)));
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, -1);

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}
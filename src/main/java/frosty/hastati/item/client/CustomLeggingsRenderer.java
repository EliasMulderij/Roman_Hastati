package frosty.hastati.item.client;

import frosty.hastati.Hastati;
import frosty.hastati.item.custom.CustomHastatiLeggingsItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class CustomLeggingsRenderer extends GeoArmorRenderer<CustomHastatiLeggingsItem> {
    public CustomLeggingsRenderer() {
        super(new DefaultedItemGeoModel<>(Identifier.of(Hastati.MOD_ID, "custom_leggings")));
    }

    @Override
    public Identifier getTextureLocation(CustomHastatiLeggingsItem animatable) {
        return Identifier.of(Hastati.MOD_ID, "textures/armor/custom_leggings.png");
    }
}
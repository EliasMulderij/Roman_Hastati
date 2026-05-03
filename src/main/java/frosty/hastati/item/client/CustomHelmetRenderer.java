package frosty.hastati.item.client;

import frosty.hastati.Hastati;
import frosty.hastati.item.custom.CustomHastatiHelmetItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class CustomHelmetRenderer extends GeoArmorRenderer<CustomHastatiHelmetItem> {
    public CustomHelmetRenderer() {
        super(new DefaultedItemGeoModel<>(Identifier.of(Hastati.MOD_ID, "custom_helmet")));
    }

    @Override
    public Identifier getTextureLocation(CustomHastatiHelmetItem animatable) {
        return Identifier.of(Hastati.MOD_ID, "textures/armor/custom_helmet.png");
    }
}
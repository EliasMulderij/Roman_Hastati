package frosty.hastati.item;

import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;
import frosty.hastati.Hastati;
import frosty.hastati.item.custom.CustomHastatiHelmetItem;
import frosty.hastati.item.custom.CustomHastatiLeggingsItem;
import frosty.hastati.item.custom.PilumItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item GLADIUS = register(new SwordItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.IRON, 3, -1.5F))), "gladius");
    public static final Item PILUM = register(new PilumItem(new Item.Settings().maxCount(2)), "pilum");
    public static final Item HASTATI_HELMET = register(new CustomHastatiHelmetItem(ModArmorMaterials.HASTATI_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(net.minecraft.item.ArmorItem.Type.HELMET.getMaxDamage(15))), "hastati_helmet");
    public static final Item HASTATI_CHESTPLATE = register(new ArmorItem(ModArmorMaterials.HASTATI_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(15))), "hastati_chestplate");
    public static final Item HASTATI_LEGGINGS = register(new CustomHastatiLeggingsItem(ModArmorMaterials.HASTATI_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(15))), "hastati_leggings");
    public static final Item HASTATI_BOOTS = register(new ArmorItem(ModArmorMaterials.HASTATI_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(15))), "hastati_boots");
    public static final Item HASTATI_SHIELD = register(new FabricShieldItem(new Item.Settings().maxDamage(500), 100, 13, Items.IRON_INGOT), "hastati_shield");
    public static final Item SCUTUM_RED = register(new FabricShieldItem(new Item.Settings().maxDamage(500), 100, 13, Items.IRON_INGOT), "scutum_red");
    public static final Item SCUTUM_RED_BOAR     = register(new FabricShieldItem(new Item.Settings().maxDamage(500), 100, 13, Items.IRON_INGOT), "scutum_red_boar");
    public static final Item SCUTUM_RED_GOLD     = register(new FabricShieldItem(new Item.Settings().maxDamage(500), 100, 13, Items.IRON_INGOT), "scutum_red_gold");
    public static final Item SCUTUM_RED_LAUREL     = register(new FabricShieldItem(new Item.Settings().maxDamage(500), 100, 13, Items.IRON_INGOT), "scutum_red_laurel");
    public static final Item SCUTUM_WHITE     = register(new FabricShieldItem(new Item.Settings().maxDamage(500), 100, 13, Items.IRON_INGOT), "scutum_white");
    public static final Item SCUTUM_WHITE_PATTERNS     = register(new FabricShieldItem(new Item.Settings().maxDamage(500), 100, 13, Items.IRON_INGOT), "scutum_white_patterns");



    public static Item register(Item item, String id) {
        return Registry.register(Registries.ITEM, Identifier.of(Hastati.MOD_ID, id), item);
    }

    public static void registerModItems() {



        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((entries) -> {
            entries.add(GLADIUS);
            entries.add(PILUM);
            entries.add(HASTATI_HELMET);
            entries.add(HASTATI_CHESTPLATE);
            entries.add(HASTATI_LEGGINGS);
            entries.add(HASTATI_BOOTS);
            entries.add(HASTATI_SHIELD);
            entries.add(SCUTUM_RED);
            entries.add(SCUTUM_RED_BOAR);
            entries.add(SCUTUM_RED_GOLD);
            entries.add(SCUTUM_RED_LAUREL);
            entries.add(SCUTUM_WHITE);
            entries.add(SCUTUM_WHITE_PATTERNS);
        });
    }
}

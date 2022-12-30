package lepimond.christmastime.registry;

import lepimond.christmastime.ChristmasTime;
import lepimond.christmastime.items.LeggedBoatItem;
import lepimond.christmastime.items.LinkedPearlItem;
import lepimond.christmastime.items.SnowGunItem;
import lepimond.christmastime.items.SpruceMealItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ChristmasItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ChristmasTime.MODID);

    public static final RegistryObject<Item> spruceMeal = ITEMS.register("spruce_meal", SpruceMealItem::new);
    public static final RegistryObject<Item> snowGun = ITEMS.register("snow_gun", SnowGunItem::new);
    public static final RegistryObject<Item> leggedBoat = ITEMS.register("legged_boat", LeggedBoatItem::new);
    public static final RegistryObject<Item> cookieSeeds = ITEMS.register("cookie_seeds",
            () -> new ItemNameBlockItem(ChristmasBlocks.cookieCrop.get(), new Item.Properties()));
    public static final RegistryObject<Item> linkedPearl = ITEMS.register("linked_pearl", LinkedPearlItem::new);
}

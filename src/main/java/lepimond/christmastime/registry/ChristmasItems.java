package lepimond.christmastime.registry;

import lepimond.christmastime.ChristmasTime;
import lepimond.christmastime.items.*;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.List;

public class ChristmasItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ChristmasTime.MODID);

    public static final RegistryObject<Item> spruceMeal = ITEMS.register("spruce_meal", SpruceMealItem::new);
    public static final RegistryObject<Item> snowGun = ITEMS.register("snow_gun", SnowGunItem::new);
    public static final RegistryObject<Item> leggedBoat = ITEMS.register("legged_boat", LeggedBoatItem::new);
    public static final RegistryObject<Item> cookieSeeds = ITEMS.register("cookie_seeds",
            () -> new ItemNameBlockItem(ChristmasBlocks.cookieCrop.get(), new Item.Properties()));
    public static final RegistryObject<Item> linkedPearl = ITEMS.register("linked_pearl", LinkedPearlItem::new);

    public static final RegistryObject<Item> christmasSongDisc = ITEMS.register("christmas_song",
            () -> new RecordItem(14, ChristmasSounds.christmasSong,
                    standardProps().stacksTo(1).rarity(Rarity.RARE), 30) {
                @Override
                public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
                    components.add(Component.literal("8% chance to drop from Presents").withStyle(ChatFormatting.DARK_PURPLE));
                }

                public MutableComponent getDisplayName() {
                    return Component.translatable("Lepimond - 365");
                }
    });

    public static final RegistryObject<Item> boatSpawnEgg = ITEMS.register("living_boat_spawn_egg",
            () -> new ForgeSpawnEggItem(ChristmasEntities.livingBoat, 0x5B3315, 0xBC987E, standardProps().stacksTo(16)));
    public static final RegistryObject<Item> particleMonsterEgg = ITEMS.register("particle_monster_spawn_egg",
            () -> new ForgeSpawnEggItem(ChristmasEntities.particleMonster, 0x5B3315, 0xBC987E, standardProps().stacksTo(16)));
    public static final RegistryObject<Item> boatRemains = ITEMS.register("boat_remains", BoatRemainsItem::new);
    public static final RegistryObject<Item> blink = ITEMS.register("blink", BlinkItem::new);

    public static Item.Properties standardProps() {
        return new Item.Properties().tab(ChristmasCreativeTabs.CHRISTMAS_TAB);
    }
}

package com.infamous.dungeons_mobs;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = DungeonsMobs.MODID)
public class DungeonsGearCompat {
    public static final String DUNGEONS_GEAR_MOD_ID = "dungeons_gear";
    private static boolean IS_LOADED = false;
    private static Supplier<Item> GOLD_AXE = () -> Items.AIR;
    private static Supplier<Item> FIREBRAND = () -> Items.AIR;

    @SubscribeEvent
    public static void onInterMod(InterModProcessEvent event){
        if(ModList.get().isLoaded(DUNGEONS_GEAR_MOD_ID)){
            IS_LOADED = true;
            GOLD_AXE = () -> getRegisteredItem("gold_axe");
            FIREBRAND = () -> getRegisteredItem("firebrand");
        }
    }

    private static Item getRegisteredItem(String item) {
        return ForgeRegistries.ITEMS.getValue(getDungeonsGearResource(item));
    }

    public static Supplier<Item> getGoldAxe() {
        return GOLD_AXE;
    }

    public static Supplier<Item> getFirebrand() {
        return FIREBRAND;
    }

    public static boolean isLoaded(){
        return IS_LOADED;
    }

    private static ResourceLocation getDungeonsGearResource(String itemId) {
        return new ResourceLocation(DUNGEONS_GEAR_MOD_ID, itemId);
    }
}
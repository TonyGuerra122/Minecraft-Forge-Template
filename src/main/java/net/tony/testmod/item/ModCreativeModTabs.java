package net.tony.testmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tony.testmod.TestMod;
import net.tony.testmod.block.ModBlocks;

public class ModCreativeModTabs {
    
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TestMod.MOD_ID);
    
    public static final RegistryObject<CreativeModeTab> SAPPHIRE_TAB = CREATIVE_MODE_TAB.register("sapphire_mod_tab", 
        () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SAPPHIRE.get()))
        .title(Component.translatable("creativetab.sapphire_mod_tab"))
        .displayItems((pParameters, pOutput) -> {
            pOutput.accept(ModItems.SAPPHIRE.get());
            pOutput.accept(ModItems.RAW_SAPPHIRE.get());
            pOutput.accept(ModBlocks.SAPPHIRE_BLOCK.get());
            pOutput.accept(ModBlocks.RAW_SAPPHIRE_BLOCK.get());
            pOutput.accept(ModBlocks.SAPPHIRE_ORE.get());
            pOutput.accept(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get());
            pOutput.accept(ModBlocks.NETHER_SAPPHIRE_ORE.get());
            pOutput.accept(ModBlocks.END_STONE_SAPPHIRE_ORE.get());


            pOutput.accept(Items.DIAMOND);
        })
        .build());
    
    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
    

}

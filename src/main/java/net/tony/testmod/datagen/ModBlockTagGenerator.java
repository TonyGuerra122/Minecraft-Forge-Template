package net.tony.testmod.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.tony.testmod.TestMod;
import net.tony.testmod.block.ModBlocks;
import net.tony.testmod.util.ModTags;

public class ModBlockTagGenerator extends BlockTagsProvider {

    public ModBlockTagGenerator(PackOutput output, CompletableFuture<Provider> lookupProvider,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TestMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider p_256380_) {
        this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES).add(ModBlocks.SAPPHIRE_ORE.get()).addTag(Tags.Blocks.ORES);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.SAPPHIRE_BLOCK.get(),
                ModBlocks.SAPPHIRE_BLOCK.get(),
                ModBlocks.SAPPHIRE_ORE.get(),
                ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),
                ModBlocks.NETHER_SAPPHIRE_ORE.get(),
                ModBlocks.END_STONE_SAPPHIRE_ORE.get(),
                ModBlocks.SOUND_BLOCK.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.SAPPHIRE_BLOCK.get()).addTag(Tags.Blocks.ORES);
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.RAW_SAPPHIRE_BLOCK.get()).addTag(Tags.Blocks.ORES);
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.NETHER_SAPPHIRE_ORE.get()).addTag(Tags.Blocks.ORES);
        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.END_STONE_SAPPHIRE_ORE.get());

        this.tag(BlockTags.FENCES).add(ModBlocks.SAPPHIRE_FENCE.get());
        this.tag(BlockTags.FENCE_GATES).add(ModBlocks.SAPPHIRE_FENCE_GATE.get());
        this.tag(BlockTags.WALLS).add(ModBlocks.SAPPHIRE_WALL.get());

    }

}

package net.tony.testmod.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.tony.testmod.TestMod;

public class ModItemTagGenerator extends ItemTagsProvider{

    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<Provider> p_275729_,
            CompletableFuture<TagLookup<Block>> p_275322_,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, TestMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider p_256380_) {
        
    }
    
}

package net.tony.testmod.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PoiTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.PoiTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.tony.testmod.TestMod;

public class ModPoiTypeTagsProvider extends PoiTypeTagsProvider{

    public ModPoiTypeTagsProvider(PackOutput p_256012_, CompletableFuture<Provider> p_256617_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_256012_, p_256617_, TestMod.MOD_ID, existingFileHelper);
       
    }
    
    @Override
    protected void addTags(Provider p_256206_) {
        tag(PoiTypeTags.ACQUIRABLE_JOB_SITE)
            .addOptional(new ResourceLocation(TestMod.MOD_ID, "sound_poi"));
    }

}

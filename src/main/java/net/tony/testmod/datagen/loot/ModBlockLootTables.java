package net.tony.testmod.datagen.loot;

import java.util.Set;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.tony.testmod.block.ModBlocks;
import net.tony.testmod.block.custom.CornCropBlock;
import net.tony.testmod.block.custom.StrawberryCropBlock;
import net.tony.testmod.item.ModItems;

public class ModBlockLootTables extends BlockLootSubProvider {
        public ModBlockLootTables() {
                super(Set.of(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
                this.dropSelf(ModBlocks.SAPPHIRE_BLOCK.get());
                this.dropSelf(ModBlocks.RAW_SAPPHIRE_BLOCK.get());
                this.dropSelf(ModBlocks.SOUND_BLOCK.get());

                this.add(ModBlocks.SAPPHIRE_ORE.get(),
                                block -> createCopperLikeOreDrops(ModBlocks.SAPPHIRE_ORE.get(),
                                                ModItems.RAW_SAPPHIRE.get()));
                this.add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),
                                block -> createCopperLikeOreDrops(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),
                                                ModItems.RAW_SAPPHIRE.get()));
                this.add(ModBlocks.NETHER_SAPPHIRE_ORE.get(),
                                block -> createCopperLikeOreDrops(ModBlocks.NETHER_SAPPHIRE_ORE.get(),
                                                ModItems.RAW_SAPPHIRE.get()));
                this.add(ModBlocks.END_STONE_SAPPHIRE_ORE.get(),
                                block -> createCopperLikeOreDrops(ModBlocks.END_STONE_SAPPHIRE_ORE.get(),
                                                ModItems.RAW_SAPPHIRE.get()));

                this.dropSelf(ModBlocks.SAPPHIRE_STAIRS.get());
                this.dropSelf(ModBlocks.SAPPHIRE_BUTTON.get());
                this.dropSelf(ModBlocks.SAPPHIRE_PRESSURE_PLATE.get());
                this.dropSelf(ModBlocks.SAPPHIRE_TRAP_DOOR.get());
                this.dropSelf(ModBlocks.SAPPHIRE_FENCE.get());
                this.dropSelf(ModBlocks.SAPPHIRE_FENCE_GATE.get());
                this.dropSelf(ModBlocks.SAPPHIRE_WALL.get());

                this.add(ModBlocks.SAPPHIRE_SLAB.get(),
                                block -> createSlabItemTable(ModBlocks.SAPPHIRE_SLAB.get()));
                this.add(ModBlocks.SAPPHIRE_DOOR.get(),
                                block -> createDoorTable(ModBlocks.SAPPHIRE_DOOR.get()));

                LootItemCondition.Builder lootitemcon$builder = LootItemBlockStatePropertyCondition
                                .hasBlockStateProperties(ModBlocks.STRAWBERRY_CROP.get())
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(StrawberryCropBlock.AGE, 5));
                this.add(ModBlocks.STRAWBERRY_CROP.get(),
                                createCropDrops(ModBlocks.STRAWBERRY_CROP.get(), ModItems.STRAWBERRY.get(),
                                                ModItems.STRAWBERRY_SEEDS.get(), lootitemcon$builder));
                LootItemCondition.Builder lootitemcon$builder2 = LootItemBlockStatePropertyCondition
                                .hasBlockStateProperties(ModBlocks.CORN_CROP.get())
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(CornCropBlock.AGE, 7))
                                                .or(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CORN_CROP.get())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CornCropBlock.AGE, 8)));
                this.add(ModBlocks.STRAWBERRY_CROP.get(),
                                createCropDrops(ModBlocks.STRAWBERRY_CROP.get(), ModItems.STRAWBERRY.get(),
                                                ModItems.CORN_SEEDS.get(), lootitemcon$builder));
                this.add(ModBlocks.CORN_CROP.get(),
                                createCropDrops(ModBlocks.CORN_CROP.get(), ModItems.CORN.get(),
                                                ModItems.CORN_SEEDS.get(), lootitemcon$builder2));

                                                
        }

        protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
                return createSilkTouchDispatchTable(pBlock,
                                this.applyExplosionDecay(pBlock,
                                                LootItem.lootTableItem(item)
                                                                .apply(SetItemCountFunction.setCount(
                                                                                UniformGenerator.between(2.0F, 5.0F)))
                                                                .apply(ApplyBonusCount.addOreBonusCount(
                                                                                Enchantments.BLOCK_FORTUNE))));
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
                return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
        }
}
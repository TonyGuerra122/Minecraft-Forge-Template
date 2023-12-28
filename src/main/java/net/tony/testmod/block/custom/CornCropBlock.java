package net.tony.testmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IPlantable;
import net.tony.testmod.item.ModItems;

public class CornCropBlock extends CropBlock{

    public static final int FIRST_STAGE_MAX_AGE = 7;
    public static final int SECOND_STAGE_MAX_AGE = 1;

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)
    };
    
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 8);

    public CornCropBlock(Properties p_52247_) {
        super(p_52247_);
    }
    
    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevelReader, BlockPos pBlockPos) {
        return super.canSurvive(pState, pLevelReader, pBlockPos) || (pLevelReader.getBlockState(pBlockPos.below(1)).is(this) &&
            pLevelReader.getBlockState(pBlockPos.below(1)).getValue(AGE) == 7);
    }

    @Override
    public void randomTick(BlockState pBlockState, ServerLevel pServerLevel, BlockPos pBlockPos, RandomSource pRandomSource) {
        if(pServerLevel.getRawBrightness(pBlockPos, 0) >= 9){
            int currentAge = this.getAge(pBlockState);

            if(currentAge < this.getMaxAge()){
                float growthSpeed = getGrowthSpeed(this, pServerLevel, pBlockPos);

                if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(pServerLevel, pBlockPos, pBlockState, pRandomSource.nextInt((int)(25.0F / growthSpeed) + 1) == 0)){
                    if(currentAge == FIRST_STAGE_MAX_AGE){
                        if(pServerLevel.getBlockState(pBlockPos.above(1)).is(Blocks.AIR)){
                            pServerLevel.setBlock(pBlockPos.above(1), this.getStateForAge(currentAge + 1), 2);
                        }else{
                            pServerLevel.setBlock(pBlockPos, this.getStateForAge(currentAge + 1), 2);
                        }

                        net.minecraftforge.common.ForgeHooks.onCropsGrowPost(pServerLevel, pBlockPos, pBlockState);
                    }
                }
            }
        }
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing,
    IPlantable plantable) {
        return super.mayPlaceOn(state, world, pos);
    }

    @Override
    public void growCrops(Level pLevel, BlockPos pBlockPos, BlockState pBlockState) {
     
        int nextAge = this.getAge(pBlockState) + this.getBonemealAgeIncrease(pLevel);
        int maxAge = this.getMaxAge();

        if(nextAge > maxAge){
            nextAge = maxAge;
        }

        if(this.getAge(pBlockState) == FIRST_STAGE_MAX_AGE && pLevel.getBlockState(pBlockPos.above(1)).is(Blocks.AIR)){
            pLevel.setBlock(pBlockPos.above(1), this.getStateForAge(nextAge), 2);
        }else{
            pLevel.setBlock(pBlockPos, this.getStateForAge(nextAge - SECOND_STAGE_MAX_AGE), 2);
        }
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return FIRST_STAGE_MAX_AGE + SECOND_STAGE_MAX_AGE;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.CORN_SEEDS.get();
    }

    @Override
    public VoxelShape getShape(BlockState p_52297_, BlockGetter p_52298_, BlockPos p_52299_,
            CollisionContext p_52300_) {
        return SHAPE_BY_AGE[this.getAge(p_52297_)];
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> p_52286_) {
        p_52286_.add(AGE);
    }

}

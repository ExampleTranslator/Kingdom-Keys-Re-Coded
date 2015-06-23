package wehavecookies56.kk.worldgen;

import java.util.Random;

import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import wehavecookies56.kk.block.ModBlocks;
import wehavecookies56.kk.lib.Config;

public class WorldGenBlox implements IWorldGenerator{

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimensionId()) {
            case -1:
                break;
            case 0:
                generateSurface(world, random, chunkX * 16, chunkZ * 16);
                break;
            case 1:
                break;
        }
    }

    //The actual generation method.
    private void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
        for (int k = 0; k < 10; k++)
        {
            int firstBlockXCoord = chunkX + rand.nextInt(16);
            int firstBlockZCoord = chunkZ + rand.nextInt(16);
            int quisqueY = rand.nextInt(world.getHeight() - 40) + 40;
            int OreY = rand.nextInt(100);
            BlockPos quisquePos = new BlockPos(firstBlockXCoord, quisqueY, firstBlockZCoord);
            BlockPos OrePos = new BlockPos(firstBlockXCoord, OreY, firstBlockZCoord);
            

            if (Config.EnableWorldGen)
            {
                (new WorldGenMinable(ModBlocks.NormalBlox.getDefaultState(), 10, BlockHelper.forBlock(Blocks.grass))).generate(world, rand, quisquePos);
            	(new WorldGenMinable(ModBlocks.HardBlox.getDefaultState(), 10, BlockHelper.forBlock(Blocks.grass))).generate(world, rand, quisquePos);
                (new WorldGenMinable(ModBlocks.MetalBlox.getDefaultState(), 10, BlockHelper.forBlock(Blocks.grass))).generate(world, rand, quisquePos);
                (new WorldGenMinable(ModBlocks.DangerBlox.getDefaultState(), 10, BlockHelper.forBlock(Blocks.grass))).generate(world, rand, quisquePos);
                (new WorldGenMinable(ModBlocks.PrizeBlox.getDefaultState(), 5, BlockHelper.forBlock(Blocks.grass))).generate(world, rand, quisquePos);
                (new WorldGenMinable(ModBlocks.RarePrizeBlox.getDefaultState(), 5, BlockHelper.forBlock(Blocks.grass))).generate(world, rand, quisquePos);

                (new WorldGenMinable(ModBlocks.BlazingOre.getDefaultState(), 3, BlockHelper.forBlock(Blocks.stone))).generate(world, rand, OrePos);
                (new WorldGenMinable(ModBlocks.BrightOre.getDefaultState(), 3, BlockHelper.forBlock(Blocks.stone))).generate(world, rand, OrePos);
                (new WorldGenMinable(ModBlocks.DenseOre.getDefaultState(), 3, BlockHelper.forBlock(Blocks.stone))).generate(world, rand, OrePos);
                (new WorldGenMinable(ModBlocks.FrostOre.getDefaultState(), 3, BlockHelper.forBlock(Blocks.stone))).generate(world, rand, OrePos);
                (new WorldGenMinable(ModBlocks.LucidOre.getDefaultState(), 3, BlockHelper.forBlock(Blocks.stone))).generate(world, rand, OrePos);
                (new WorldGenMinable(ModBlocks.PowerOre.getDefaultState(), 3, BlockHelper.forBlock(Blocks.stone))).generate(world, rand, OrePos);
                (new WorldGenMinable(ModBlocks.RemembranceOre.getDefaultState(), 3, BlockHelper.forBlock(Blocks.stone))).generate(world, rand, OrePos);
                (new WorldGenMinable(ModBlocks.TranquilOre.getDefaultState(), 3, BlockHelper.forBlock(Blocks.stone))).generate(world, rand, OrePos);
                (new WorldGenMinable(ModBlocks.TwilightOre.getDefaultState(), 3, BlockHelper.forBlock(Blocks.stone))).generate(world, rand, OrePos);

                /*In case we need it
                private void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
			        for (int k = 0; k < 10; k++) {
			            int firstBlockXCoord = chunkX + rand.nextInt(16);
			            int firstBlockZCoord = chunkZ + rand.nextInt(16);
			            int quisqueY = rand.nextInt(20);
			
			            if (ConfigHandler.enableGeneration)
			                (new WorldGenMinable(BlockRegistry.quisqueLapisOre, 0, 4, Blocks.stone)).generate(world, rand, firstBlockXCoord, quisqueY, firstBlockZCoord);
			        }
			    }
                */
            }
        }
    }
}
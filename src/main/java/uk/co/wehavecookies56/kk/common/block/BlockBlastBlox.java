package uk.co.wehavecookies56.kk.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import uk.co.wehavecookies56.kk.common.block.base.BlockBlox;
import uk.co.wehavecookies56.kk.common.entity.block.EntityBlastBlox;

import java.util.Random;

public class BlockBlastBlox extends BlockBlox {

	protected BlockBlastBlox (Material material, String toolClass, int level, float hardness, float resistance) {
		super(material, toolClass, level, hardness, resistance);
	}

	@SuppressWarnings("deprecation")
	@Override
	public AxisAlignedBB getCollisionBoundingBox (IBlockState worldIn, World pos, BlockPos state) {
		float f = 0.0625F;
		return new AxisAlignedBB(state.getX() + f, state.getY(), state.getZ() + f, state.getX() + 1 - f, state.getY() + 1 - f, state.getZ() + 1 - f);
	}
	
	@Override
	public void onEntityCollidedWithBlock (World world, BlockPos pos, IBlockState state, Entity entity) {
		explode(world, pos.getX(), pos.getY(), pos.getZ(), 1, entity instanceof EntityLivingBase ? (EntityLivingBase) entity : null);
		world.setBlockToAir(pos);
	}

	@Override
	public int quantityDropped (Random par1Random) {
		return 1;
	}

	@Override
	public void onBlockDestroyedByExplosion (World par1World, BlockPos pos, Explosion par5Explosion) {
		if (!par1World.isRemote) {
			EntityBlastBlox entitytntprimed = new EntityBlastBlox(par1World, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, par5Explosion.getExplosivePlacedBy());
			entitytntprimed.fuse = par1World.rand.nextInt(entitytntprimed.fuse / 4) + entitytntprimed.fuse / 8;
			par1World.spawnEntityInWorld(entitytntprimed);
		}
	}

	public void explode (World world, int x, int y, int z, int state, EntityLivingBase entity) {
		if (!world.isRemote) if ((state & 1) == 1) {
			EntityBlastBlox entitytntprimed = new EntityBlastBlox(world, x + 0.5F, y + 0.5F, z + 0.5F, entity);
			//entitytntprimed.fuse = 60;
			world.spawnEntityInWorld(entitytntprimed);
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player.getHeldItem(EnumHand.MAIN_HAND) != null && player.getHeldItem(EnumHand.MAIN_HAND) == new ItemStack(Items.FLINT_AND_STEEL)) {
			explode(world, pos.getX(), pos.getY(), pos.getZ(), 1, player);
			world.setBlockToAir(pos);
			player.getHeldItem(EnumHand.MAIN_HAND).damageItem(1, player);
			return true;
		} else
			return super.onBlockActivated(world, pos, state, player, hand, heldItem, side, hitX, hitY, hitZ);
	}

	@Override
	public void onBlockClicked (World world, BlockPos pos, EntityPlayer player) {
		if (player.getHeldItem(EnumHand.MAIN_HAND) != null) {
			if (player.getHeldItem(EnumHand.MAIN_HAND).getItem() == Items.FEATHER)
				world.destroyBlock(pos, true);
			else {
				explode(world, pos.getX(), pos.getY(), pos.getZ(), 1, player);
				world.setBlockToAir(pos);
			}
		} else {
			explode(world, pos.getX(), pos.getY(), pos.getZ(), 1, player);
			world.setBlockToAir(pos);
		}
	}

	/**
	 * Return whether this block can drop from an explosion.
	 */
	@Override
	public boolean canDropFromExplosion (Explosion par1Explosion) {
		return false;
	}

}
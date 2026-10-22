package ganymedes01.etfuturum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import java.util.ArrayList;
import java.util.Random;

/** Submerged vegetation is itself water material; no waterlogging engine is needed. */
public class BlockSeagrass extends BaseBlock implements IShearable {
	public BlockSeagrass() { super(Material.water); setNames("seagrass"); setHardness(0); setBlockBounds(.1F, 0, .1F, .9F, .8F, .9F); }
	@Override public boolean isOpaqueCube() { return false; }
	@Override public boolean renderAsNormalBlock() { return false; }
	@Override public int getRenderType() { return 1; }
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) { return null; }
	@Override public boolean canCollideCheck(int meta, boolean liquid) { return false; }
	@Override public boolean canPlaceBlockAt(World world, int x, int y, int z) { return world.getBlock(x, y, z).getMaterial() == Material.water && canBlockStay(world, x, y, z); }
	@Override public boolean canBlockStay(World world, int x, int y, int z) { return world.getBlock(x, y + 1, z).getMaterial() == Material.water && world.getBlock(x, y - 1, z).isSideSolid(world, x, y - 1, z, net.minecraftforge.common.util.ForgeDirection.UP); }
	@Override public void onNeighborBlockChange(World world, int x, int y, int z, Block block) { if (!world.isRemote && !canBlockStay(world, x, y, z)) world.setBlock(x, y, z, Blocks.water); }
	@Override public int quantityDropped(Random random) { return 0; }
	@Override public boolean isShearable(ItemStack stack, net.minecraft.world.IBlockAccess world, int x, int y, int z) { return true; }
	@Override public ArrayList<ItemStack> onSheared(ItemStack stack, net.minecraft.world.IBlockAccess world, int x, int y, int z, int fortune) { ArrayList<ItemStack> drops = new ArrayList<>(); drops.add(new ItemStack(this)); return drops; }
	@Override public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) { if (!world.isRemote) world.setBlock(x, y, z, Blocks.water); }
}

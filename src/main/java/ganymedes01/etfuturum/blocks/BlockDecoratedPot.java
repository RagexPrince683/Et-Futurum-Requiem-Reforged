package ganymedes01.etfuturum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/** Legacy loot pot: retained inventory, broken-pot drops, native cuboid geometry. */
public class BlockDecoratedPot extends BaseBlock {
	public BlockDecoratedPot() { super(Material.rock); setNames("decorated_pot"); setBlockTextureName("decorated_pot_side"); setHardness(0); setBlockBounds(.0625F, 0, .0625F, .9375F, 1, .9375F); }
	@Override public boolean isOpaqueCube() { return false; }
	@Override public boolean hasTileEntity(int meta) { return true; }
	@Override public TileEntity createTileEntity(World world, int meta) { return new net.minecraft.tileentity.TileEntityDispenser(); }
	@Override public void breakBlock(World world, int x, int y, int z, Block old, int meta) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (!world.isRemote && tile instanceof IInventory inventory) for (int i = 0; i < inventory.getSizeInventory(); i++) { ItemStack stack = inventory.getStackInSlot(i); if (stack != null) world.spawnEntityInWorld(new net.minecraft.entity.item.EntityItem(world, x + .5, y + .5, z + .5, stack.copy())); }
		world.removeTileEntity(x, y, z); super.breakBlock(world, x, y, z, old, meta);
	}
}

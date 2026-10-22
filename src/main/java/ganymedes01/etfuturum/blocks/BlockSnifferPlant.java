package ganymedes01.etfuturum.blocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import java.util.Random;
public class BlockSnifferPlant extends BlockBush {
	private final boolean pitcher;
	@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT) private net.minecraft.util.IIcon top;
	@Override @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT) public void registerBlockIcons(net.minecraft.client.renderer.texture.IIconRegister icons) { super.registerBlockIcons(icons); if (pitcher) top = icons.registerIcon("pitcher_plant_top"); }
	@Override @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT) public net.minecraft.util.IIcon getIcon(int side, int meta) { return pitcher && (meta & 8) != 0 ? top : blockIcon; }
	public BlockSnifferPlant(boolean pitcher) { this.pitcher = pitcher; setBlockName("etfuturum." + (pitcher ? "pitcher_plant" : "torchflower")); setBlockTextureName(pitcher ? "pitcher_plant_bottom" : "torchflower"); setCreativeTab(ganymedes01.etfuturum.EtFuturum.creativeTabBlocks); }
	@Override public boolean canPlaceBlockAt(World world, int x, int y, int z) { return super.canPlaceBlockAt(world, x, y, z) && (!pitcher || world.isAirBlock(x, y + 1, z)); }
	@Override public void onBlockAdded(World world, int x, int y, int z) { if (pitcher && !world.isRemote && (world.getBlockMetadata(x, y, z) & 8) == 0 && world.isAirBlock(x, y + 1, z)) world.setBlock(x, y + 1, z, this, 8, 2); }
	@Override public boolean canBlockStay(World world, int x, int y, int z) { return pitcher && (world.getBlockMetadata(x, y, z) & 8) != 0 ? world.getBlock(x, y - 1, z) == this : super.canBlockStay(world, x, y, z); }
	@Override public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) { if (pitcher && (world.getBlockMetadata(x, y, z) & 8) == 0 && world.getBlock(x, y + 1, z) != this) { if (!world.isRemote) { dropBlockAsItem(world, x, y, z, 0, 0); world.setBlockToAir(x, y, z); } } else super.onNeighborBlockChange(world, x, y, z, neighbor); }
	@Override public Item getItemDropped(int meta, Random random, int fortune) { return (meta & 8) != 0 ? null : Item.getItemFromBlock(this); }
}

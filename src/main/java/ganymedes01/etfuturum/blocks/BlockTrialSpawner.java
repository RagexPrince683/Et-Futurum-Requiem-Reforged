package ganymedes01.etfuturum.blocks;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
public class BlockTrialSpawner extends BlockContainer {
	public BlockTrialSpawner() { super(Material.iron); setBlockName("etfuturum.trial_spawner"); setBlockTextureName("trial_spawner_side_inactive"); setHardness(50); setResistance(2000); setCreativeTab(ganymedes01.etfuturum.EtFuturum.creativeTabBlocks); }
	@Override public TileEntity createNewTileEntity(World world, int meta) { return new ganymedes01.etfuturum.tileentities.TileEntityTrialSpawner(); }
	@Override public net.minecraft.item.Item getItemDropped(int meta, java.util.Random random, int fortune) { return null; }
}

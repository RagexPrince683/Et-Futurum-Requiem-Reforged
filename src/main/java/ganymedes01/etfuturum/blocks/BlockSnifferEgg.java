package ganymedes01.etfuturum.blocks;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import java.util.Random;
public class BlockSnifferEgg extends BaseBlock {
	@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT) private net.minecraft.util.IIcon[][] faces;
	@Override @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT) public void registerBlockIcons(net.minecraft.client.renderer.texture.IIconRegister register) { String[] stages = {"sniffer_egg_not_cracked", "sniffer_egg_slightly_cracked", "sniffer_egg_very_cracked"}; String[] sides = {"bottom", "top", "north", "south", "west", "east"}; faces = new net.minecraft.util.IIcon[stages.length][6]; for (int stage = 0; stage < stages.length; stage++) for (int side = 0; side < 6; side++) faces[stage][side] = register.registerIcon(stages[stage] + "_" + sides[side]); blockIcon = faces[0][1]; }
	@Override @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT) public net.minecraft.util.IIcon getIcon(int side, int meta) { return faces[Math.min(2, meta & 3)][side]; }
	public BlockSnifferEgg() { super(Material.dragonEgg); setNames("sniffer_egg"); setHardness(.5F); setBlockBounds(.1F, 0, .1F, .9F, 1, .9F); }
	@Override public boolean isOpaqueCube() { return false; }
	@Override public boolean renderAsNormalBlock() { return false; }
	private int delay(World world, int x, int y, int z) { return world.getBlock(x, y - 1, z) == ganymedes01.etfuturum.ModBlocks.MOSS_BLOCK.get() ? 4000 : 8000; }
	@Override public void onBlockAdded(World world, int x, int y, int z) { world.scheduleBlockUpdate(x, y, z, this, delay(world, x, y, z)); }
	@Override public void updateTick(World world, int x, int y, int z, Random random) { if (world.isRemote) return; int stage = world.getBlockMetadata(x, y, z); if (stage < 2) { world.setBlockMetadataWithNotify(x, y, z, stage + 1, 2); world.scheduleBlockUpdate(x, y, z, this, delay(world, x, y, z)); } else { ganymedes01.etfuturum.entities.EntitySniffer sniffer = new ganymedes01.etfuturum.entities.EntitySniffer(world); sniffer.setLocationAndAngles(x + .5, y, z + .5, 0, 0); sniffer.setGrowingAge(-48000); if (world.spawnEntityInWorld(sniffer)) world.setBlockToAir(x, y, z); else world.scheduleBlockUpdate(x, y, z, this, 200); } }
}

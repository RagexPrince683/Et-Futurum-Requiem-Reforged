package ganymedes01.etfuturum.blocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import java.util.Random;
public class BlockDriedGhast extends BaseBlock {
	@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT) private net.minecraft.util.IIcon[][] faces;
	@Override @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT) public void registerBlockIcons(net.minecraft.client.renderer.texture.IIconRegister register) { String[] stages = {"dried_ghast_hydration_0", "dried_ghast_hydration_1", "dried_ghast_hydration_2", "dried_ghast_hydration_3"}; String[] sides = {"bottom", "top", "north", "south", "west", "east"}; faces = new net.minecraft.util.IIcon[stages.length][6]; for (int stage = 0; stage < stages.length; stage++) for (int side = 0; side < 6; side++) faces[stage][side] = register.registerIcon(stages[stage] + "_" + sides[side]); blockIcon = faces[0][1]; }
	@Override @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT) public net.minecraft.util.IIcon getIcon(int side, int meta) { return faces[Math.min(3, meta & 3)][side]; }
	public BlockDriedGhast() { super(Material.rock); setNames("dried_ghast"); setHardness(.5F); setBlockBounds(.2F, 0, .2F, .8F, .6F, .8F); }
	@Override public boolean isOpaqueCube() { return false; }
	@Override public boolean renderAsNormalBlock() { return false; }
	private boolean hydrated(World world, int x, int y, int z) { for (net.minecraftforge.common.util.ForgeDirection direction : net.minecraftforge.common.util.ForgeDirection.VALID_DIRECTIONS) if (world.blockExists(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ) && world.getBlock(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ).getMaterial() == Material.water) return true; return false; }
	@Override public void onBlockAdded(World world, int x, int y, int z) { world.scheduleBlockUpdate(x, y, z, this, 6000); }
	@Override public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) { if (!world.isRemote && !hydrated(world, x, y, z)) world.setBlockMetadataWithNotify(x, y, z, 0, 2); }
	@Override public void updateTick(World world, int x, int y, int z, Random random) { if (world.isRemote) return; int stage = world.getBlockMetadata(x, y, z); if (hydrated(world, x, y, z)) { if (stage < 3) world.setBlockMetadataWithNotify(x, y, z, stage + 1, 2); else { ganymedes01.etfuturum.entities.EntityHappyGhast ghast = new ganymedes01.etfuturum.entities.EntityHappyGhast(world); ghast.setLocationAndAngles(x + .5, y, z + .5, 0, 0); ghast.setGrowingAge(-24000); if (world.spawnEntityInWorld(ghast)) { world.setBlockToAir(x, y, z); return; } } } else world.setBlockMetadataWithNotify(x, y, z, Math.max(0, stage - 1), 2); world.scheduleBlockUpdate(x, y, z, this, 6000); }
}

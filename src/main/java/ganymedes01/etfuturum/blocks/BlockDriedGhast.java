package ganymedes01.etfuturum.blocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import java.util.Random;
public class BlockDriedGhast extends BaseBlock {
	@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT) private ganymedes01.etfuturum.client.renderer.block.ReleasedBlockModel[] models;
	@Override @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT) public void registerBlockIcons(net.minecraft.client.renderer.texture.IIconRegister atlas) { models = new ganymedes01.etfuturum.client.renderer.block.ReleasedBlockModel[4]; for (int i = 0; i < 4; i++) models[i] = new ganymedes01.etfuturum.client.renderer.block.ReleasedBlockModel(atlas, "dried_ghast_hydration_" + i); blockIcon = models[0].particle; }
	@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT) public ganymedes01.etfuturum.client.renderer.block.ReleasedBlockModel getReleasedModel(int meta) { return models[meta & 3]; }
	@Override public int getRenderType() { return ganymedes01.etfuturum.lib.RenderIDs.RELEASED_BLOCK; }
	public BlockDriedGhast() { super(Material.rock); setNames("dried_ghast"); setHardness(.5F); setBlockBounds(.25F, 0, .25F, .75F, .625F, .75F); setBlockSound(ganymedes01.etfuturum.client.sound.ModSounds.soundDriedGhast); }
	@Override public boolean isOpaqueCube() { return false; }
	@Override public boolean renderAsNormalBlock() { return false; }
	private boolean hydrated(World world, int x, int y, int z) { for (net.minecraftforge.common.util.ForgeDirection direction : net.minecraftforge.common.util.ForgeDirection.VALID_DIRECTIONS) if (world.blockExists(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ) && world.getBlock(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ).getMaterial() == Material.water) return true; return false; }
	@Override public void onBlockAdded(World world, int x, int y, int z) { world.scheduleBlockUpdate(x, y, z, this, 6000); }
	private void transition(World world, int x, int y, int z, int stage) {
		if (world.getBlockMetadata(x, y, z) == stage) return;
		world.setBlockMetadataWithNotify(x, y, z, stage, 2);
		sound(world, x, y, z);
	}
	private void sound(World world, int x, int y, int z) { world.playSoundEffect(x + .5, y + .5, z + .5, ganymedes01.etfuturum.Tags.MC_ASSET_VER + ":block.dried_ghast.transition", 1, 1); }
	@Override public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) { if (!world.isRemote && !hydrated(world, x, y, z)) transition(world, x, y, z, 0); }
	@Override public void updateTick(World world, int x, int y, int z, Random random) {
		if (world.isRemote) return;
		int stage = world.getBlockMetadata(x, y, z);
		if (hydrated(world, x, y, z)) {
			if (stage < 3) transition(world, x, y, z, stage + 1);
			else {
				ganymedes01.etfuturum.entities.EntityHappyGhast ghast = new ganymedes01.etfuturum.entities.EntityHappyGhast(world);
				ghast.setLocationAndAngles(x + .5, y, z + .5, 0, 0); ghast.setGrowingAge(-24000);
				if (world.spawnEntityInWorld(ghast)) { sound(world, x, y, z); world.setBlockToAir(x, y, z); return; }
			}
		} else transition(world, x, y, z, Math.max(0, stage - 1));
		world.scheduleBlockUpdate(x, y, z, this, 6000);
	}
}

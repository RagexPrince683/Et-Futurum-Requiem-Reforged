package ganymedes01.etfuturum.blocks;

import ganymedes01.etfuturum.entities.EntityTurtle;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.Random;

/** Metadata: low two bits are hatch stage; next two bits are egg count minus one. */
public class BlockTurtleEgg extends BaseBlock {
	private final net.minecraft.util.IIcon[] stages = new net.minecraft.util.IIcon[3];
	public BlockTurtleEgg() { super(Material.dragonEgg); setNames("turtle_egg"); setHardness(.5F); setTickRandomly(true); setBlockBounds(.15F, 0, .15F, .85F, .45F, .85F); }
	@Override public boolean isOpaqueCube() { return false; }
	@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
	@Override public void registerBlockIcons(net.minecraft.client.renderer.texture.IIconRegister register) { stages[0] = register.registerIcon("turtle_egg"); stages[1] = register.registerIcon("turtle_egg_slightly_cracked"); stages[2] = register.registerIcon("turtle_egg_very_cracked"); blockIcon = stages[0]; }
	@Override public net.minecraft.util.IIcon getIcon(int side, int meta) { return stages[Math.min(2, meta & 3)]; }
	@Override public boolean renderAsNormalBlock() { return false; }
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) { return null; }
	@Override public int quantityDropped(Random random) { return 0; }
	@Override protected boolean canSilkHarvest() { return true; }
	@Override protected ItemStack createStackedBlock(int meta) { return new ItemStack(this, 1 + (meta >> 2), 0); }
	@Override public void updateTick(World world, int x, int y, int z, Random random) {
		if (world.isRemote || world.getBlock(x, y - 1, z) != Blocks.sand) return;
		float angle = world.getCelestialAngle(1);
		if ((angle < .65F || angle > .69F) && random.nextInt(500) != 0) return;
		int meta = world.getBlockMetadata(x, y, z);
		if ((meta & 3) < 2) { world.setBlockMetadataWithNotify(x, y, z, meta + 1, 2); return; }
		int count = 1 + (meta >> 2), spawned = 0;
		for (int i = 0; i < count; i++) {
			EntityTurtle turtle = new EntityTurtle(world); turtle.setLocationAndAngles(x + .3 + i * .13, y, z + .3, 0, 0); turtle.setGrowingAge(-24000); turtle.setHome(x, y, z);
			if (world.spawnEntityInWorld(turtle)) spawned++;
		}
		if (spawned == count) world.setBlockToAir(x, y, z); else if (spawned > 0) world.setBlockMetadataWithNotify(x, y, z, 2 | ((count - spawned - 1) << 2), 2);
	}
	@Override public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		ItemStack held = player.getHeldItem(); int meta = world.getBlockMetadata(x, y, z);
		if (held == null || held.getItem() != net.minecraft.item.Item.getItemFromBlock(this) || (meta >> 2) >= 3) return false;
		if (!world.isRemote) { world.setBlockMetadataWithNotify(x, y, z, meta + 4, 2); if (!player.capabilities.isCreativeMode && --held.stackSize == 0) player.inventory.setInventorySlotContents(player.inventory.currentItem, null); }
		return true;
	}
	private void trample(World world, int x, int y, int z, Entity entity, int chance) {
		if (world.isRemote || !(entity instanceof EntityLivingBase) || entity instanceof EntityTurtle || entity.isSneaking() || world.rand.nextInt(chance) != 0) return;
		if (!(entity instanceof EntityPlayer) && !world.getGameRules().getGameRuleBooleanValue("mobGriefing")) return;
		int meta = world.getBlockMetadata(x, y, z); if ((meta >> 2) == 0) world.setBlockToAir(x, y, z); else world.setBlockMetadataWithNotify(x, y, z, meta - 4, 2);
		world.playSoundEffect(x + .5, y, z + .5, "dig.stone", .7F, 1);
	}
	@Override public void onEntityWalking(World world, int x, int y, int z, Entity entity) { trample(world, x, y, z, entity, 100); }
	@Override public void onFallenUpon(World world, int x, int y, int z, Entity entity, float distance) { trample(world, x, y, z, entity, 3); }
}

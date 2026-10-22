package ganymedes01.etfuturum.world;

import cpw.mods.fml.common.IWorldGenerator;
import ganymedes01.etfuturum.ModItems;
import ganymedes01.etfuturum.configuration.configs.ConfigEntities;
import ganymedes01.etfuturum.entities.*;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.*;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.MathHelper;
import net.minecraft.world.*;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.BiomeDictionary;
import java.util.Random;

/** Small native outposts fit inside their generating chunk; they never request distant terrain. */
public final class MobAcquisitionWorldgen implements IWorldGenerator {
	@Override public void generate(Random random, int cx, int cz, World world, IChunkProvider generator, IChunkProvider provider) {
		if (!ConfigEntities.enableOutposts || world.provider.dimensionId != 0 || random.nextInt(600) != 0 || !world.getWorldInfo().isMapFeaturesEnabled()) return;
		int x = cx * 16 + 7, z = cz * 16 + 7;
		if (!BiomeDictionary.isBiomeOfType(world.getBiomeGenForCoords(x, z), BiomeDictionary.Type.PLAINS) && !BiomeDictionary.isBiomeOfType(world.getBiomeGenForCoords(x, z), BiomeDictionary.Type.SAVANNA) && !BiomeDictionary.isBiomeOfType(world.getBiomeGenForCoords(x, z), BiomeDictionary.Type.SANDY) && !BiomeDictionary.isBiomeOfType(world.getBiomeGenForCoords(x, z), BiomeDictionary.Type.FOREST)) return;
		int y = world.getTopSolidOrLiquidBlock(x, z);
		if (y < 60 || y > 230 || world.villageCollectionObj.findNearestVillage(x, y, z, 80) != null) return;
		for (int dx = -4; dx <= 6; dx++) for (int dz = -4; dz <= 4; dz++) {
			if (!world.blockExists(x + dx, y, z + dz) || Math.abs(world.getTopSolidOrLiquidBlock(x + dx, z + dz) - y) > 3 || world.getBlock(x + dx, y - 1, z + dz).getMaterial().isLiquid()) return;
		}
		for (int dx = -3; dx <= 3; dx++) for (int dz = -3; dz <= 3; dz++) for (int dy = -3; dy <= 11; dy++) {
			boolean edge = Math.abs(dx) == 3 || Math.abs(dz) == 3;
			if (dy < 0) world.setBlock(x + dx, y + dy, z + dz, Blocks.cobblestone, 0, 2);
			else if (dy == 0 || dy == 5 || dy == 10) world.setBlock(x + dx, y + dy, z + dz, Blocks.planks, 1, 2);
			else if (edge && dy < 10) world.setBlock(x + dx, y + dy, z + dz, Math.abs(dx) == 3 && Math.abs(dz) == 3 ? Blocks.log : dy % 5 == 2 ? Blocks.air : Blocks.planks, 1, 2);
			else world.setBlockToAir(x + dx, y + dy, z + dz);
		}
		world.setBlockToAir(x, y + 1, z - 3); world.setBlockToAir(x, y + 2, z - 3);
		for (int dy = 1; dy < 11; dy++) world.setBlock(x + 2, y + dy, z + 2, Blocks.ladder, 2, 2);
		world.setBlock(x - 1, y + 11, z, Blocks.chest, 0, 2);
		if (world.getTileEntity(x - 1, y + 11, z) instanceof TileEntityChest chest) {
			chest.setInventorySlotContents(0, ModItems.CROSSBOW.newItemStack()); chest.setInventorySlotContents(1, new ItemStack(Items.arrow, 16)); chest.setInventorySlotContents(2, new ItemStack(Items.wheat, 8));
		}
		for (int dx = 4; dx <= 6; dx++) for (int dz = -1; dz <= 1; dz++) for (int dy = 0; dy <= 3; dy++) world.setBlock(x + dx, y + dy, z + dz, dy == 0 || dy == 3 ? Blocks.planks : dx == 4 || dx == 6 || Math.abs(dz) == 1 ? Blocks.iron_bars : Blocks.air, 0, 2);
		for (int i = 0; i < 2; i++) place(world, new EntityAllay(world), x + 5, y + 1, z);
		for (int i = 0; i < 3; i++) { EntityPillager pillager = new EntityPillager(world); pillager.setCaptain(i == 0); place(world, pillager, x + i - 1, y + 6, z); }
		if (random.nextInt(4) == 0) { place(world, new EntityEvoker(world), x, y + 1, z); place(world, new EntityVindicator(world), x + 1, y + 1, z); }
		RaidProgress.get(world).addOutpost(cx, cz);
	}
	private static void place(World world, EntityLiving mob, int x, int y, int z) { mob.setLocationAndAngles(x + .5, y, z + .5, world.rand.nextFloat() * 360, 0); mob.onSpawnWithEgg(null); mob.func_110163_bv(); world.spawnEntityInWorld(mob); }
	public static void playerSpawns(net.minecraft.entity.player.EntityPlayer player) {
		World world = player.worldObj;
		if (world.provider.dimensionId != 0 || world.difficultySetting == EnumDifficulty.PEACEFUL || !world.getGameRules().getGameRuleBooleanValue("doMobSpawning") || player.capabilities.disableDamage) return;
		int x = MathHelper.floor_double(player.posX), z = MathHelper.floor_double(player.posZ);
		if (ConfigEntities.enableModernPassiveSpawning && world.getTotalWorldTime() > 24000 && world.rand.nextInt(10) == 0 && world.getEntitiesWithinAABB(EntityWanderingTrader.class, player.boundingBox.expand(96, 48, 96)).isEmpty()) {
			int px = x + 24 + world.rand.nextInt(17), pz = z + world.rand.nextInt(49) - 24;
			if (world.blockExists(px, 64, pz)) { int y = world.getTopSolidOrLiquidBlock(px, pz); EntityWanderingTrader trader = new EntityWanderingTrader(world); trader.setLocationAndAngles(px + .5, y, pz + .5, 0, 0); if (!world.isAnyLiquid(trader.boundingBox) && world.getCollidingBoundingBoxes(trader, trader.boundingBox).isEmpty() && world.spawnEntityInWorld(trader)) for (int i = 0; i < 2; i++) { EntityTraderLlama llama = new EntityTraderLlama(world); llama.setLocationAndAngles(px + i + .5, y, pz + 2.5, 0, 0); llama.onSpawnWithEgg(null); if (world.spawnEntityInWorld(llama)) llama.setLeashedToEntity(trader, true); } }
		}
		if (!ConfigEntities.enablePatrols || world.getTotalWorldTime() < 120000 || !world.isDaytime() || world.rand.nextInt(5) != 0 || !world.getEntitiesWithinAABB(EntityPillager.class, player.boundingBox.expand(96, 48, 96)).isEmpty()) return;
		int px = x + (world.rand.nextBoolean() ? 1 : -1) * (32 + world.rand.nextInt(16)), pz = z + world.rand.nextInt(65) - 32;
		if (!world.blockExists(px, 64, pz)) return;
		int y = world.getTopSolidOrLiquidBlock(px, pz);
		if (world.villageCollectionObj.findNearestVillage(px, y, pz, 32) != null) return;
		for (int i = 0; i < 3 + world.difficultySetting.getDifficultyId(); i++) { EntityPillager mob = new EntityPillager(world); mob.setLocationAndAngles(px + .5, y, pz + .5, 0, 0); if (world.isAnyLiquid(mob.boundingBox) || !world.getCollidingBoundingBoxes(mob, mob.boundingBox).isEmpty()) continue; mob.setCaptain(i == 0); mob.setHomeArea(x, MathHelper.floor_double(player.posY), z, 48); world.spawnEntityInWorld(mob); }
	}
}

package ganymedes01.etfuturum.blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
public class BlockSculkShrieker extends BaseBlock {
	public BlockSculkShrieker() { super(Material.rock); setNames("sculk_shrieker"); setBlockTextureName("sculk_shrieker_side"); setHardness(3); }
	@Override public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
		if (world.isRemote || !(entity instanceof EntityPlayer player) || player.capabilities.disableDamage || entity.isSneaking() || (world.getBlockMetadata(x, y, z) & 8) == 0 || !ganymedes01.etfuturum.configuration.configs.ConfigEntities.enableModernHostileSpawning) return;
		net.minecraft.nbt.NBTTagCompound data = player.getEntityData(); long time = world.getTotalWorldTime(); if (data.getLong("EFRShriekCooldown") > time) return; data.setLong("EFRShriekCooldown", time + 200); int warnings = Math.min(4, data.getInteger("EFRShriekWarnings") + 1); data.setInteger("EFRShriekWarnings", warnings); world.playAuxSFX(2004, x, y, z, 0);
		if (warnings < 4 || world.difficultySetting == net.minecraft.world.EnumDifficulty.PEACEFUL || !world.getGameRules().getGameRuleBooleanValue("doMobSpawning") || !world.getEntitiesWithinAABB(ganymedes01.etfuturum.entities.EntityWarden.class, player.boundingBox.expand(48, 24, 48)).isEmpty()) return;
		for (int attempt = 0; attempt < 12; attempt++) { int px = x + world.rand.nextInt(11) - 5, pz = z + world.rand.nextInt(11) - 5; if (!world.blockExists(px, y + 3, pz)) continue; ganymedes01.etfuturum.entities.EntityWarden warden = new ganymedes01.etfuturum.entities.EntityWarden(world); warden.setLocationAndAngles(px + .5, y + 1, pz + .5, 0, 0); if (world.getCollidingBoundingBoxes(warden, warden.boundingBox).isEmpty() && world.checkNoEntityCollision(warden.boundingBox)) { world.spawnEntityInWorld(warden); break; } }
	}
	@Override public int damageDropped(int meta) { return 0; }
}

package ganymedes01.etfuturum.tileentities;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import java.util.*;
/** A small native dungeon trial, with persisted finite waves and a real cooldown. */
public class TileEntityTrialSpawner extends TileEntity {
	private int cooldown, spawned, activeTicks;
	private final Set<UUID> active = new HashSet<>();
	@Override public void updateEntity() {
		if (worldObj.isRemote || worldObj.getTotalWorldTime() % 20 != 0) return;
		if (cooldown > 0) { state(0); cooldown--; if (cooldown == 0) markDirty(); return; }
		EntityPlayer player = worldObj.getClosestPlayer(xCoord + .5, yCoord + .5, zCoord + .5, 14);
		if (player == null || player.capabilities.disableDamage || worldObj.difficultySetting == net.minecraft.world.EnumDifficulty.PEACEFUL || !worldObj.getGameRules().getGameRuleBooleanValue("doMobSpawning")) { state(0); return; }
		state(1);
		Set<UUID> present = new HashSet<>();
		for (Object object : worldObj.getEntitiesWithinAABB(EntityLiving.class, net.minecraft.util.AxisAlignedBB.getBoundingBox(xCoord - 24, yCoord - 8, zCoord - 24, xCoord + 25, yCoord + 9, zCoord + 25))) { EntityLiving mob = (EntityLiving) object; if (mob.isEntityAlive() && active.contains(mob.getUniqueID())) present.add(mob.getUniqueID()); }
		active.retainAll(present);
		if (++activeTicks > 1800) { active.clear(); spawned = 0; cooldown = 1800; activeTicks = 0; markDirty(); return; }
		if (spawned >= 6 && active.isEmpty()) { ItemStack reward = worldObj.rand.nextBoolean() ? new ItemStack(net.minecraft.init.Items.emerald, 2 + worldObj.rand.nextInt(4)) : ganymedes01.etfuturum.ModItems.OMINOUS_BOTTLE.newItemStack(); if (worldObj.spawnEntityInWorld(new net.minecraft.entity.item.EntityItem(worldObj, xCoord + .5, yCoord + 1, zCoord + .5, reward))) { state(2); sound("eject_item"); spawned = 0; cooldown = 1800; activeTicks = 0; markDirty(); } return; }
		if (active.size() >= 2 || spawned >= 6 || worldObj.getTotalWorldTime() % 60 != 0) return;
		for (int attempt = 0; attempt < 8; attempt++) { EntityLiving mob = (getBlockMetadata() & 1) == 0 ? new ganymedes01.etfuturum.entities.EntityBreeze(worldObj) : new ganymedes01.etfuturum.entities.EntityBogged(worldObj); mob.setLocationAndAngles(xCoord + .5 + worldObj.rand.nextInt(7) - 3, yCoord + 1, zCoord + .5 + worldObj.rand.nextInt(7) - 3, 0, 0); if (!worldObj.blockExists(MathHelper.floor_double(mob.posX), yCoord + 3, MathHelper.floor_double(mob.posZ)) || !worldObj.getCollidingBoundingBoxes(mob, mob.boundingBox).isEmpty() || !worldObj.checkNoEntityCollision(mob.boundingBox)) continue; ((net.minecraft.entity.EntityCreature) mob).setHomeArea(xCoord, yCoord, zCoord, 16); if (worldObj.spawnEntityInWorld(mob)) { sound("spawn_mob"); active.add(mob.getUniqueID()); spawned++; markDirty(); } break; }
	}
	private void sound(String event) { worldObj.playSoundEffect(xCoord + .5, yCoord + .5, zCoord + .5, ganymedes01.etfuturum.Tags.MC_ASSET_VER + ":block.trial_spawner." + event, 1, 1); }
	private void state(int state) { int previous = (getBlockMetadata() >> 1) & 3; if (previous == state) return; worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, (getBlockMetadata() & 1) | state << 1, 2); if (state == 1 && previous == 0) sound("open_shutter"); else if (state == 0 && previous == 1) sound("close_shutter"); }
	@Override public void writeToNBT(NBTTagCompound tag) { super.writeToNBT(tag); tag.setInteger("Cooldown", cooldown); tag.setInteger("Spawned", spawned); tag.setInteger("ActiveTicks", activeTicks); NBTTagList list = new NBTTagList(); for (UUID id : active) list.appendTag(new NBTTagString(id.toString())); tag.setTag("Mobs", list); }
	@Override public void readFromNBT(NBTTagCompound tag) { super.readFromNBT(tag); cooldown = MathHelper.clamp_int(tag.getInteger("Cooldown"), 0, 1800); spawned = MathHelper.clamp_int(tag.getInteger("Spawned"), 0, 6); activeTicks = MathHelper.clamp_int(tag.getInteger("ActiveTicks"), 0, 1800); active.clear(); NBTTagList list = tag.getTagList("Mobs", 8); for (int i = 0; i < Math.min(6, list.tagCount()); i++) try { active.add(UUID.fromString(list.getStringTagAt(i))); } catch (IllegalArgumentException ignored) { } }
}

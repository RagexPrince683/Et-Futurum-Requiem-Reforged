package ganymedes01.etfuturum.entities;
import ganymedes01.etfuturum.ModItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
public class EntityHappyGhast extends EntityRideableMob {
	public EntityHappyGhast(World world) { super(world); setSize(4, 4); tasks.addTask(2, new EntityAITempt(this, 1, Items.snowball, false)); }
	@Override protected void applyEntityAttributes() { super.applyEntityAttributes(); getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20); }
	@Override public int passengerCount() { return 4; }
	@Override public double getMountedYOffset() { return 4; }
	@Override protected boolean flyingMount() { return true; }
	@Override protected boolean saddleItem(ItemStack held) { return held != null && held.getItem() == ModItems.HARNESS.get(); }
	@Override public boolean isBreedingItem(ItemStack stack) { return false; }
	@Override public EntityAgeable createChild(EntityAgeable mate) { return null; }
	@Override protected void dash() { }
	@Override protected double mountedSpeed() { return .2; }
	@Override public boolean canBeCollidedWith() { return !isDead; }
	@Override public net.minecraft.util.AxisAlignedBB getBoundingBox() { return !isChild() ? boundingBox : null; }
	@Override protected void fall(float distance) { }
	@Override public boolean interact(EntityPlayer player) { ItemStack held = player.getHeldItem(); if (isChild() && held != null && held.getItem() == Items.snowball) { if (!worldObj.isRemote) { setGrowingAge(Math.min(0, getGrowingAge() + Math.max(20, -getGrowingAge() / 10))); consume(player, held); } return true; } if (!isChild() && isSaddled() && riddenByEntity == null && held != null && held.getItem() == Items.shears && worldObj.getEntitiesWithinAABB(EntityMobSeat.class, boundingBox.expand(8, 8, 8)).isEmpty()) { if (!worldObj.isRemote) { entityDropItem(ModItems.HARNESS.newItemStack(1, getVariant()), 0); flag(1, false); held.damageItem(1, player); } return true; } return super.interact(player); }
	@Override public void onLivingUpdate() { super.onLivingUpdate(); if (!worldObj.isRemote) { if (ticksExisted % (isWet() || posY >= 128 ? 80 : 400) == 0) heal(1); if (isChild() && width > 1) setSize(.8F, .8F); else if (!isChild() && width < 4) setSize(4, 4); if (riddenByEntity == null && ticksExisted % 10 == 0 && !worldObj.getEntitiesWithinAABB(EntityPlayer.class, net.minecraft.util.AxisAlignedBB.getBoundingBox(boundingBox.minX, boundingBox.maxY - .1, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY + .6, boundingBox.maxZ)).isEmpty()) { motionX = motionY = motionZ = 0; rotationYaw = Math.round(rotationYaw / 90) * 90; } } }

	@Override protected String getLivingSound() { return ganymedes01.etfuturum.Tags.MC_ASSET_VER + ":entity.happy_ghast.ambient"; }
	@Override protected String getHurtSound() { return ganymedes01.etfuturum.Tags.MC_ASSET_VER + ":entity.happy_ghast.hurt"; }
	@Override protected String getDeathSound() { return ganymedes01.etfuturum.Tags.MC_ASSET_VER + ":entity.happy_ghast.death"; }
}

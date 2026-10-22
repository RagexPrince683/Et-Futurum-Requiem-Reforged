package ganymedes01.etfuturum.entities;

import ganymedes01.etfuturum.ModItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.world.World;

public class EntityPillager extends EntityIllager implements IRangedAttackMob {
	public EntityPillager(World world) { super(world); setCurrentItemOrArmor(0, ModItems.CROSSBOW.newItemStack()); tasks.addTask(2, new EntityAIArrowAttack(this, 1, 50, 8)); }
	@Override public void attackEntityWithRangedAttack(EntityLivingBase target, float distance) { if (!worldObj.isRemote && canEntityBeSeen(target)) { EntityArrow arrow = new EntityArrow(worldObj, this, target, 1.6F, 14 - worldObj.difficultySetting.getDifficultyId() * 4); arrow.setDamage(3); worldObj.spawnEntityInWorld(arrow); playSound("random.bow", 1, .8F); } }

	@Override protected String getLivingSound() { return ganymedes01.etfuturum.Tags.MC_ASSET_VER + ":entity.pillager.ambient"; }
	@Override protected String getHurtSound() { return ganymedes01.etfuturum.Tags.MC_ASSET_VER + ":entity.pillager.hurt"; }
	@Override protected String getDeathSound() { return ganymedes01.etfuturum.Tags.MC_ASSET_VER + ":entity.pillager.death"; }
}

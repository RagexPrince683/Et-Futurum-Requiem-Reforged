package ganymedes01.etfuturum.mixins.early.nethermobs;

import ganymedes01.etfuturum.items.ItemCrossbow;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.*;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelBiped.class)
public class MixinCrossbowModel {
	@Inject(method = "setRotationAngles", at = @At("RETURN"))
	private void etfu$holdCrossbow(float limb, float amount, float age, float yaw, float pitch, float scale, Entity entity, CallbackInfo ci) {
		if (!(entity instanceof EntityLivingBase living)) return;
		ItemStack held = living.getHeldItem();
		if (held == null || !(held.getItem() instanceof ItemCrossbow) || !ItemCrossbow.charged(held)) return;
		ModelBiped model = (ModelBiped) (Object) this;
		model.bipedRightArm.rotateAngleX = model.bipedLeftArm.rotateAngleX = -(float) Math.PI / 2 + model.bipedHead.rotateAngleX;
		model.bipedRightArm.rotateAngleY = -.3F + model.bipedHead.rotateAngleY; model.bipedLeftArm.rotateAngleY = .6F + model.bipedHead.rotateAngleY;
		model.bipedRightArm.rotateAngleZ = model.bipedLeftArm.rotateAngleZ = 0;
	}
}

package ganymedes01.etfuturum.mixins.early.nethermobs;

import ganymedes01.etfuturum.world.nether.NetherSpawning;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpawnerAnimals.class)
public class MixinSpawnerAnimals {
	@Inject(method = "canCreatureTypeSpawnAtLocation", at = @At("HEAD"), cancellable = true)
	private static void etfu$striderLava(EnumCreatureType type, World world, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
		if (ganymedes01.etfuturum.world.MobSpawning.isAquatic(type)) {
			cir.setReturnValue(world.provider.dimensionId == 0 && world.blockExists(x, y - 1, z) && world.blockExists(x, y + 1, z)
					&& world.getBlock(x, y, z).getMaterial() == Material.water && world.getBlock(x, y - 1, z).getMaterial() == Material.water && !world.getBlock(x, y + 1, z).isNormalCube());
			return;
		}
		if (ganymedes01.etfuturum.world.structure.MonumentSpawning.GUARDIANS != null && type == ganymedes01.etfuturum.world.structure.MonumentSpawning.GUARDIANS) {
			cir.setReturnValue(world.getBlock(x, y, z).getMaterial() == Material.water && world.getBlock(x, y - 1, z).getMaterial() == Material.water
					&& !world.getBlock(x, y + 1, z).isNormalCube() && ganymedes01.etfuturum.world.structure.MapGenOceanMonument.contains(world, x, y, z));
		}
		if (NetherSpawning.STRIDERS != null && type == NetherSpawning.STRIDERS) cir.setReturnValue(world.provider.isHellWorld && world.getBlock(x, y, z).getMaterial() == Material.lava && !world.getBlock(x, y + 1, z).isNormalCube());
	}
}

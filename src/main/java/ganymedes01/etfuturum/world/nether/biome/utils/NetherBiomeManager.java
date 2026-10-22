package ganymedes01.etfuturum.world.nether.biome.utils;

import ganymedes01.etfuturum.ModBlocks;
import ganymedes01.etfuturum.core.utils.RegistrySafety;
import ganymedes01.etfuturum.configuration.configs.ConfigExperiments;
import ganymedes01.etfuturum.configuration.configs.ConfigWorld;
import ganymedes01.etfuturum.world.nether.biome.BiomeBasaltDeltas;
import ganymedes01.etfuturum.world.nether.biome.BiomeCrimsonForest;
import ganymedes01.etfuturum.world.nether.biome.BiomeSoulSandValley;
import ganymedes01.etfuturum.world.nether.biome.BiomeWarpedForest;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class NetherBiomeManager {

	public static boolean netherliciousCompat;

	public static BiomeGenBase crimsonForest;
	public static BiomeGenBase warpedForest;
	public static BiomeGenBase soulSandValley;
	public static BiomeGenBase basaltDeltas;

	public static void init() {
		if (ConfigWorld.crimsonForestID != -1 && ConfigExperiments.enableCrimsonBlocks) {
			crimsonForest = new BiomeCrimsonForest(RegistrySafety.requireVacant(BiomeGenBase.getBiomeGenArray(), ConfigWorld.crimsonForestID, "Crimson Forest")).setBiomeName("Crimson Forest");
		}
		if (ConfigWorld.warpedForestID != -1 && ConfigExperiments.enableWarpedBlocks) {
			warpedForest = new BiomeWarpedForest(RegistrySafety.requireVacant(BiomeGenBase.getBiomeGenArray(), ConfigWorld.warpedForestID, "Warped Forest")).setBiomeName("Warped Forest");
		}
		if (ConfigWorld.soulSandValleyID != -1) {
			soulSandValley = new BiomeSoulSandValley(RegistrySafety.requireVacant(BiomeGenBase.getBiomeGenArray(), ConfigWorld.soulSandValleyID, "Soul Sand Valley")).setBiomeName("Soul Sand Valley");
		}
		if (ConfigWorld.basaltDeltasID != -1 && ModBlocks.BASALT.isEnabled() && ModBlocks.BLACKSTONE.isEnabled()) {
			basaltDeltas = new BiomeBasaltDeltas(RegistrySafety.requireVacant(BiomeGenBase.getBiomeGenArray(), ConfigWorld.basaltDeltasID, "Basalt Deltas")).setBiomeName("Basalt Deltas");
		}

		BiomeGenBase.hell.topBlock = Blocks.netherrack;
		BiomeGenBase.hell.fillerBlock = Blocks.netherrack;
//		BiomeGenBase.hell.biomeName = "Nether Wastes"; //Breaks some mods that stupidly check for the biome name "hell" instead of the fucking ID or tags...
		BiomeGenBase.hell.field_76754_C = 0; // fillerBlockMetadata
		//Sets filler block meta to 0. Vanilla sets it to a ridiculous value for some reason.
		//This value seems to be unused in vanilla as well.
	}

	public static int provideCrimsonForestID() {
		return crimsonForest != null ? crimsonForest.biomeID : -1;
	}

	public static int provideWarpedForestID() {
		return warpedForest != null ? warpedForest.biomeID : -1;
	}

	public static int provideSoulSandValleyID() {
		return soulSandValley != null ? soulSandValley.biomeID : -1;
	}

	public static int provideBasaltDeltasID() {
		return basaltDeltas != null ? basaltDeltas.biomeID : -1;
	}

	public static int provideCrimsonForestWeight() {
		return crimsonForest != null ? ConfigWorld.crimsonForestWeight : -1;
	}

	public static int provideWarpedForestWeight() {
		return warpedForest != null ? ConfigWorld.warpedForestWeight : -1;
	}

	public static int provideSoulSandValleyWeight() {
		return soulSandValley != null ? ConfigWorld.soulSandValleyWeight : -1;
	}

	public static int provideBasaltDeltasWeight() {
		return basaltDeltas != null ? ConfigWorld.basaltDeltasWeight : -1;
	}
}

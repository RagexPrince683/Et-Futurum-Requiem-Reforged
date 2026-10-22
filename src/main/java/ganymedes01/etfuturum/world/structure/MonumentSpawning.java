package ganymedes01.etfuturum.world.structure;

import ganymedes01.etfuturum.entities.EntityGuardian;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.EnumHelper;
import java.util.Collections;
import java.util.List;

public final class MonumentSpawning {
	public static EnumCreatureType GUARDIANS;
	public static final List<BiomeGenBase.SpawnListEntry> SPAWNS = Collections.singletonList(new BiomeGenBase.SpawnListEntry(EntityGuardian.class, 1, 2, 4));
	private MonumentSpawning() { }
	public static void init() { if (GUARDIANS == null) GUARDIANS = EnumHelper.addCreatureType("EFR_GUARDIAN", EntityGuardian.class, 70, Material.water, false, false); }
}

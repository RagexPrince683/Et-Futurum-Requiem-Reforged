package ganymedes01.etfuturum.configuration.configs;

import ganymedes01.etfuturum.configuration.ConfigBase;

import java.io.File;

public class ConfigEnchantsPotions extends ConfigBase {

	public static boolean enableFrostWalker;
	public static boolean enableMending;
	public static boolean enableSwiftSneak;
	public static boolean enableSoulSpeed;
	public static int soulSpeedID;
	public static int mendingID;
	public static int frostWalkerID;
	public static int swiftSneakID;
	public static int levitationID;
	public static int glowingID;
	public static int darknessID, nautilusBreathID, dolphinGraceID;
	public static int quickChargeID, multishotID, piercingID, loyaltyID, impalingID, riptideID, channelingID;

	static final String catEnchants = "enchantments";
	static final String catPotions = "potions";


	public ConfigEnchantsPotions(File file) {
		super(file);
		setCategoryComment(catEnchants, "Settings for enchantments.\nBy default, only IDs up to 255 are allowed, only assign a larger value if an ID extension is present.");
		setCategoryComment(catPotions, "Settings for potions.\nBy default, only IDs up to 31 are allowed, only assign a larger value if an ID extension is present.");

		configCats.add(getCategory(catEnchants));
		configCats.add(getCategory(catPotions));
	}

	@Override
	protected void syncConfigOptions() {
		dolphinGraceID = getInt("dolphinGraceID", catPotions, 31, 0, Short.MAX_VALUE, "Dolphins temporarily reduce horizontal water drag. Keep stable.");
		darknessID = getInt("darknessID", catPotions, 29, 0, Short.MAX_VALUE, "Warden darkness. Keep stable; occupied or out-of-range IDs fail explicitly.");
		nautilusBreathID = getInt("nautilusBreathID", catPotions, 30, 0, Short.MAX_VALUE, "Breath of the Nautilus pauses oxygen consumption without replenishing air. Keep stable.");
		quickChargeID = getInt("quickChargeID", catEnchants, 204, 0, Short.MAX_VALUE, "Keep stable; occupied IDs fail explicitly.");
		multishotID = getInt("multishotID", catEnchants, 205, 0, Short.MAX_VALUE, "Keep stable; occupied IDs fail explicitly.");
		piercingID = getInt("piercingID", catEnchants, 206, 0, Short.MAX_VALUE, "Keep stable; occupied IDs fail explicitly.");
		loyaltyID = getInt("loyaltyID", catEnchants, 207, 0, Short.MAX_VALUE, "Keep stable; occupied IDs fail explicitly.");
		impalingID = getInt("impalingID", catEnchants, 208, 0, Short.MAX_VALUE, "Keep stable; occupied IDs fail explicitly.");
		riptideID = getInt("riptideID", catEnchants, 209, 0, Short.MAX_VALUE, "Keep stable; occupied IDs fail explicitly.");
		channelingID = getInt("channelingID", catEnchants, 210, 0, Short.MAX_VALUE, "Keep stable; occupied IDs fail explicitly.");
		//enchants
		enableSoulSpeed = getBoolean("soulSpeed", catEnchants, true, "Soul Speed is acquired from piglin bartering and bastion loot. It boosts movement on soul sand/soil and wears enchanted boots.");
		soulSpeedID = getInt("soulSpeedID", catEnchants, 203, 0, Short.MAX_VALUE, "Keep this ID stable in existing saves. Collisions fail explicitly.");
		enableFrostWalker = getBoolean("frostWalker", catEnchants, true, "");
		frostWalkerID = getInt("frostWalkerID", catEnchants, 200, 0, Short.MAX_VALUE, "");
		enableMending = getBoolean("mending", catEnchants, true, "");
		mendingID = getInt("mendingID", catEnchants, 201, 0, Short.MAX_VALUE, "");
		enableSwiftSneak = getBoolean("swiftSneak", catEnchants, true, "");
		swiftSneakID = getInt("swiftSneakID", catEnchants, 202, 0, Short.MAX_VALUE, "");

		//potions
		levitationID = getInt("levitationID", catPotions, 27, 0, Short.MAX_VALUE, "Since this is essential for Shulkers, this is tied to Shulkers being enabled instead of having its own option.");
		glowingID = getInt("glowingID", catPotions, 28, 0, Short.MAX_VALUE, "");
	}

}

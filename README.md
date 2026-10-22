# Et Futurum Requiem

**Download: [CurseForge](https://www.curseforge.com/minecraft/mc-mods/et-futurum-requiem/files)
| [Modrinth](https://modrinth.com/mod/etfuturum/versions)**

## [Legacy Modding Discord Server!](https://discord.gg/jBHQn3Nmsh)
(check out the _Roadhog360's mods_ category)

WARNING!

Though this project is licensed under the LGPL-3.0 a lot of the code featured here is a direct copy or adaptation of
Mojangs original code. So be careful with what you do with it.

This project is jss2a98aj's fork of KryptonCaptain's Et Futurum build, as well as a merge of my own (Roadhog460's)
changes I made to Et Futurum and never published anywhere. Thanks to other incredible notable contributors such as
makamys among various things, including helping setup mixins, contributed hugely useful changes like porting the
expanded container code for Iron Shulker Boxes, and CI + nomixin to the gradle and GitHub scripts. And embeddedt who
contributed some incredibly large-scale backports like the Backlytra port and spectator mode, the F3 gamemode switcher
and other really good contributions.

The mod uses MCLib's AssetDirector module to download assets from Mojang's servers.
Check [its wiki page](https://github.com/makamys/MCLib/wiki/AssetDirector) for more information.

## About This Fork

Et Futurum Requiem Reforged is maintained primarily for the RageCraft modpack and for the maintainer's own goals. This fork may make different architecture, compatibility, and dependency choices from upstream for those reasons.

Reforged aims to remain self-contained for players. When practical, required functionality is kept inside the project instead of requiring extra runtime library mods. This keeps RageCraft installation and maintenance simpler.

These choices are specific to Reforged. They are not a claim that the upstream project's architecture or dependency choices are wrong.

Upstream Et Futurum Requiem remains the foundation of this project and retains full credit for its original work.

![JProfiler](https://www.ej-technologies.com/images/product_banners/jprofiler_large.png)  
This mod is tested and profiled with JProfiler! JProfiler combines high-level analytics with low-level JVM data to pinpoint performance bottlenecks, memory leaks, slow JDBC queries, costly HTTP calls, and much more. Free open-source projects may be eligible for a **free** license. You can learn more about JProfiler here:  
https://www.ej-technologies.com/jprofiler

## Dependencies

- [UniMixins](https://modrinth.com/mod/unimixins)
  - Specifically, its GTNH Mixins module supplies the early and late Mixin loader interfaces.
HogUtils, FastUtil, GTNHLib, and GTNHExtLib are not runtime dependencies. Their formerly used functionality is either owned by Et Futurum or supplied by Minecraft and Forge 1.7.10 APIs already present in the target environment.

## Contributing

To enable incomplete test features, add `-Detfuturum.testing=true` to your JVM arguments. This also enables a debug item

Configuration files are under `config/etfuturum`. Restart Minecraft or the dedicated server after editing them; a world reload does not reapply content registration, recipes or Mixins. Keep content and Mixin options consistent between clients and servers. Disabling registered content in an existing world can produce Forge missing-mapping warnings and remove saved content.

## Surface and Nether content

The runtime remains Forge 1.7.10; the modern content reference stops at Minecraft 1.21.11. Modern surface biomes use native climate generation and the legacy world height. Their IDs are configurable in `world.cfg`; keep those IDs stable in existing saves. `enableSurfaceBiomes=false` disables new placement while retaining registered biome identities. Individual `generateCherryGrove`, `generateMangroveSwamp`, `generatePaleGarden` and equivalent biome switches provide the same control for each added biome. No automatic retrogen is performed.

Mangrove and Pale Garden content remain behind `enableMangroveBlocks` and `enablePaleGarden` in `experiments.cfg`. The EFR Nether provider remains experimental and is enabled separately by `netherDimensionProvider`; bastion generation has its own `enableBastions` switch and also respects Generate Structures. Nether mobs honor the existing Nether master toggle. Full Netherlicious provider compatibility is not supplied by these options.

Stonecutters, looms and cartography tables provide native processing interfaces and NEI recipe views. Loom special patterns are reusable items crafted from paper and the existing pattern ingredient. Cartography supports copying, expansion and permanent map locking with glass panes. Guardians spawn in newly saved monument bounds; enableGuardians controls them independently. The experimental End keeps native dragon combat and creates up to twenty saved victory gateways.

Modern mobs include aquatic life, illagers and raids, allays, sniffers, wardens, armadillos, copper golems, happy ghasts and the 1.21.11 mounts. Spawning and acquisition use native 1.7.10 biomes, villages and structures. Breeze/Bogged fights use converted vanilla dungeons; full Trial Chambers, vaults and keys are not included. See [mob coverage](MOB_COVERAGE.md) for survival paths, configuration boundaries and remaining differences.

See [the content catalog](CONTENT_CATALOG.md) for supported content, survival paths, deliberate exclusions and experimental limitations. Biome presence does not imply complete modern terrain, ecology or gameplay parity.

## Polar bears

Adult polar bears are hostile to players by default when `polarBearRealism` is enabled in the neutral entity configuration. Set this option to `false` to restore the modern neutral behavior, where adults attack players only while defending nearby cubs.

## Contributors
<a href="https://github.com/Roadhog360/Et-Futurum-Requiem/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=Roadhog360/Et-Futurum-Requiem" />
</a>

*The below is legacy information and is only kept for documentation purposes.*

### About `nomixin` builds (Obsolete)

From versions 2.4.1 to 2.6.0, the mod came in two flavors:

* The regular version embeds Mixin 0.7.11, allowing the mod to run standalone. However, this makes the jar a bit larger,
  and can cause problems in certain use cases.
* The version marked with `nomixin` doesn't embed Mixin, which lets it avoid these problems. But it requires a
  separate [Mixin bootstrap mod](https://gist.github.com/makamys/7cb74cd71d93a4332d2891db2624e17c#mixin-bootstrap-mods)
  to be installed in order to run. If you have one installed already, getting this version is recommended.

From those versions, mixin code will also not work if you do not
add `--tweakClass org.spongepowered.asm.launch.MixinTweaker --mixin mixins.etfuturum.json` to your program arguments.
# Vanilla functional-block compatibility

EFR augments the Minecraft 1.7.10 enchanting table, brewing stand, beacon, and anvil when their corresponding feature toggles are enabled. Normal recipes and placement therefore retain the vanilla block identity expected by legacy mods. The historical EFR replacement blocks and tile entities remain registered and functional for save compatibility.

`tileReplacementMode` is only a legacy migration control: `-1` performs no conversion, `0` converts vanilla blocks to legacy EFR replacements, and `1` converts legacy replacements back to vanilla blocks. Modern behavior does not require conversion. The optional old base daylight sensor remains on its existing one-way compatibility migration because its inverted-sensor pairing is separate from the functional-container backports.

### Optional legacy wood-family compatibility

When Biomes O' Plenty or Witchery is installed, EFR resolves its known 1.7.10 plank and log registry entries during pre-initialization and adds enabled modern derivatives. Missing optional registry entries are warned about and skipped rather than becoming hard dependencies. Compatibility follows the equivalent `enableNew*`, `enableStrippedLogs`, and `enableBarkLogs` feature switches; modded buttons and pressure plates use `enableNewWoodRedstone`.

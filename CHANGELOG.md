# Changelog

## Fix spectator crosshair rendering with missing mouse-over state

- Handle a missing `Minecraft.objectMouseOver` safely while rendering the spectator crosshair.

## Fix Forge 1.7.10 spectator event-handler crash

- Make the client and common spectator event subscribers instance methods to match their Forge singleton registrations and prevent generated event wrappers from invoking static methods as instance methods.
- Keep spectator behavior, event priorities, conditional registration, and the statically called player-model restoration helper unchanged.

## Remove remaining external runtime utility mods

- Remove the GTNHLib runtime declaration and replace its annotation subscribers with explicit Forge event-bus registration.
- Track client armor-slot item changes with a weakly keyed, allocation-stable state cache and preserve the existing equip-sound rules.
- Replace boxed ocean-monument coordinates with project-owned packed-coordinate helpers and a pre-sized primitive open-addressing map.
- Document Reforged's self-contained RageCraft focus while retaining upstream attribution.

## Remove accidental fastutil runtime dependency

- Replace every direct fastutil collection, primitive map, function, and pair use with Java 8 collections/functions or the already bundled Apache Commons pair.
- Keep block and item metadata maps identity-keyed with wildcard fallback and lazy map views, and retain tag inheritance, reverse lookup, and metadata-aware registration.
- Make both Mixin loaders bootstrap-safe without fastutil while retaining UniMixins integration.
- At this stage of the migration, event registration, client equipment events, and coordinate packing still came from GTNHLib; the newer entry above completes their replacement.

## Fix GenericUtils filename validation overload

- Restore exception-aware filename validation so namespaced tag paths can allow `:` and `/` while retaining the remaining Windows filename restrictions.

## Fix Iron Chest compatibility after HogUtils removal

- Replace the last HogUtils-shaded reflection helper usage with self-contained Java reflection while retaining optional Iron Chest variant fallbacks.

## Remove the HogUtils hard dependency

- Internalize the metadata-aware block, item, and biome tag APIs, including wildcard matching, inheritance, reverse lookup, registry-ID helpers, and early Mixins on vanilla registry objects.
- Internalize block/metadata pair maps, recipe validation and priority recipes, xoshiro256** random generation, weighted lists, and the dummy world used by ore compatibility.
- Track nested chunk generation with an Et Futurum-owned thread-local early Mixin and provide action- and metadata-specific multi-block sounds through the existing client event handler.
- Remove the HogUtils runtime declaration, Gradle dependency, bundled JARs, and nightly-install documentation while retaining GTNHLib and optional integrations.
- Credit the exact upstream HogUtils reference revision in `THIRD_PARTY_NOTICES.md`.

## Fix spectator flight capability authority

- Resolve the local player's spectator state from its actual client or server game mode instead of the potentially delayed spectator DataWatcher.
- Keep spectator flight capability maintenance and ability synchronization server-authoritative, and explicitly restore the authoritative target game mode's capabilities on spectator exit.
- Preserve the DataWatcher for remote spectator state, entity-following behavior, and spectator-owned visual-effect cleanup.

## Protect game-mode flight and CustomNPC boat passengers

- Clear stale flying state whenever a newly configured game mode does not permit flight, while preserving Spectator and Creative flight.
- Add a default-enabled server-side option that keeps CustomNPCs and CustomNPC+ NPCs out of EFR boat seats, ejects existing NPC passengers, and prevents boat-hull collision movement.

## Fix BOP/Witchery compatibility compilation

- Initialize external wood pillars with the required wood material and restore the missing vanilla chunk-coordinate import used by entity replacement tracking.

## BOP and Witchery wood-family compatibility (GTNewHorizons/Et-Futurum-Requiem#35, #80)

- Add optional, fail-safe Biomes O' Plenty and Witchery external wood descriptors and modern log, fence, redstone, door, trapdoor, boat, chest-boat, and bamboo-raft derivatives.
- Preserve the current orientation-aware `StrippedLogRegistry`, use exact external plank metadata in recipes, and register generated log forms as `logWood`.
- Keep compatibility registry identities namespaced from EFR's native cherry, mangrove, and bamboo families.

## Port ridden horse buoyancy and pet death messages (GTNewHorizons/Et-Futurum-Requiem#95, #87)

- Add an independent backport option that gives ridden horses gentle upward buoyancy while they are in water.
- Notify an online owner with the vanilla CombatTracker death message when their owned mob or horse dies.

## Port modern lighting and End atmosphere (GTNewHorizons/Et-Futurum-Requiem#91, #104)

- Add independent client options for End flashes, modern End ambience, hue-preserving gamma, warm block light, and modern night vision.
- Add deterministic End flash state, directional delayed rumble audio, additive sky rendering, and a renderer-neutral client API.
- Restrict modern lightmap processing to vanilla providers and custom providers whose complete brightness table matches a vanilla table.
- Register the End flash sound and texture through the existing Minecraft AssetDirector namespace.

## Refine entity replacement chunk tracking (#701)

- Track server-loaded chunks by dimension and packed coordinates instead of retaining weak `Chunk` references.
- Avoid every loading or generating chunk lookup during entity replacement while preserving originals when safe insertion is unavailable.
- Respect canceled and dead entity joins, prevent duplicate tracker registration during saved-chunk loading, and clean tracking state on chunk, world, and server shutdown.

## [Memory-opti:fix leak] Fix two world leaks (#63)

- Release event-handler references to worlds, players, entities, and chunks during logout, world unload, and server shutdown.
- Stop the falling dripstone renderer from retaining its most recently rendered world.

## Fix copper door oxidation duplication (GTNewHorizons/Et-Futurum-Requiem#42)

- Synchronize copper door oxidation and wax-state changes with the correct opposite half, whether the changed block is the upper or lower half.
- Preserve each door half's metadata and avoid replacing unrelated blocks beside malformed doors.

## Fix furnace `BurnTime` overflow (GTNewHorizons/Et-Futurum-Requiem#113)

- Persist Blast Furnace and Smoker remaining burn times as NBT integers so long-burning modded fuels survive world reloads without signed-short truncation.
- Synchronize both halves of the full burn-time values to furnace GUIs instead of truncating their raw values to a 16-bit progress update.

## Add polar bear realism mode

- Add the `polarBearRealism` neutral entity option and enable it by default.
- Make adult polar bears target players by default while realism mode is enabled, while preserving cub-defence behavior when it is disabled.
- Document how to restore modern neutral polar bear behavior.

## Fix disabled boat recipe startup crash (Roadhog360/Et-Futurum-Requiem#590)

- Use EFR's enum-owned enabled state instead of probing Forge registry names for constructed but disabled boat items.
- Keep disabled boat and chest-boat variants out of recipes while allowing replacement oak recipes to use the vanilla boat item.
- Use the obtainable vanilla oak boat as the oak chest boat ingredient when old boats are replaced.

## Preserve vanilla functional-block identities (Roadhog360/Et-Futurum-Requiem#311)

- Augment vanilla enchanting tables and anvils with EFR's modern containers through narrowly gated block Mixins.
- Keep vanilla brewing-stand and beacon blocks while creating EFR's `TileEntityBrewingStand` and `TileEntityBeacon` subclasses for fuel and coloured-beam state.
- Retain every legacy replacement registration for old saves and make replacement blocks migration-only, hidden from creative tabs, NEI, and normal recipes.
- Make `tileReplacementMode` a legacy migration control, default new configurations to `-1`, and preserve the existing `-1`, `0`, and `1` directions.
- Preserve tile NBT while migrating and keep replacement maps server-local so worlds cannot leak migration directions into one another.

## Fix config toggle authority (Roadhog360/Et-Futurum-Requiem#643)

- Require bamboo content as well as new boats before registering bamboo raft items, and skip recipes for every disabled boat item.
- Keep disabled sign and dyed-bed content out of recipes, creative-tab sorting, and OreDictionary registration.
- Register the fancy inventory skull and inventory bow renderers only when their respective client options are enabled.
- Allow built-in texture and language overrides to be enabled independently, with identical language filtering in development folders and packaged JARs.

## Fix armor stand duplication (#699)

- Reject repeated server-side damage after an armor stand has died so its stand item and equipment cannot be dropped more than once.

## Fix spectator renderer state

- Restore the reusable player model as soon as the client changes from Spectator Mode to Survival, Creative, or Adventure.
- Balance spectator-only model hiding during rendering so repeated game mode transitions cannot leave body parts hidden.

## Add reusable devmods development loading

- Add drag-and-drop support for RFG-remapped production mod jars and already-deobfuscated development jars.
- Keep local compatibility-testing mods isolated from release artifacts and publication metadata.

## Update spectator mode

- Maintain ambient Night Vision and Invisibility potion effects while a player is in Et Futurum spectator mode without replacing pre-existing effects.
- Synchronize remote players' Et Futurum spectator state and hide those players from JourneyMap 5.1.4 radar when that optional client mod is present.
- Allow builds without the optional JourneyMap mod on the compile classpath while retaining strict validation for other mixins.

## Fix recursive chunk-loading `StackOverflowError` (#701)

- Guard entity replacement chunk access so `EntityJoinWorldEvent` never force-loads an absent neighboring chunk.
- Keep the original entity alive and its join event active when replacement cannot be completed.

## 2026-10-20 22:28 â€” Initial audit and correctness stabilization

- Validate armor-stand interaction identity, range, hit coordinates and entity state; validate boat movement and disabled-elytra requests while preserving existing packet layouts.
- Execute custom server packet actions on the server thread through a bounded lifecycle-managed queue, and isolate scheduled client packet callbacks behind the sided proxy.
- Conserve Mending's remaining XP budget across equipment and player XP, preserve existing repair order and rounding, and correct Frost Walker straight-line/negative-coordinate behavior.
- Correct brewing inventory extraction and dirty state, keep brewing server-authoritative, and reject invalid fuel consumption. Correct the documented blaze-powder cycle count and identify unsupported legacy custom-fuel operations.
- Restore dedicated-server damage-direction synchronization, late-resolved shulker storage bans, furnace fuel rules, prismarine recipe and netherite flammability options; validate invalid regex and floating-point settings and declare restart requirements.
- Correct bamboo biome selection, fossil filtering/fallback and mud generation independent of cherry content; restrict falling-concrete work to its world and prevent repeated conversion/removal.
- Restrict the optional AE2 renderer Mixin to clients, preserve smithing NBT independence, fix its input slot ranges, persist shulker upgrades and apply the intended rabbit biome exclusion.
- Correct lava cauldron bucket withdrawal, rain/water interaction and comparator behavior without changing its registered identity.
- Add a source audit and full configuration-field trace with separately tracked unresolved findings and upstream integration candidates.
- Load dependent configurations in declaration order so the Nether master switch consistently overrides experimental Nether content.

## 2026-10-21 00:43 â€” Stage A stabilization

- Restore coarse-dirt replacement at native terrain-array generation boundaries and configured client book-page sounds; existing chunks are not scanned or regenerated.
- Restore count-aware brewing fuel registration, wildcard/exact exclusions, enumeration and CraftTweaker undo while retaining tag-based eligibility. Synchronize full integer fuel counts using native container progress updates, keep existing fuel NBT keys, and reject invalid IMC registrations.
- Bind native smithing containers to the opened table and eight-block reach. Keep the public two-argument constructor with a bounded opening-position anchor for external callers.
- Adapt dripstone fluid transfer, mud drying, growth, cave generation and single-entity falling columns from upstream #706 without restoring removed libraries or changing local metadata ordering. Preserve old falling-entity reads; add optional column/source-state NBT.
- Isolate incomplete sculk catalyst spreading by disabling its death/XP interception while preserving block and tile registrations and ordinary XP drops.
- Add native lightning-rod rendering, loaded-rod lifecycle indexing, natural strike attraction and eight-tick redstone pulses with persisted deadlines. Existing block-only rods lazily acquire their tile through native lookup. Enable bone-meal azalea growth through the native tree generator.
- Correct structure sapling-pot items, banner color matching, beetroot maturity and unknown item mappings; restrict End-city chest loot processing to the placed chunk. Honor structure/content gates and preserve EndCity/start/piece identifiers.
- Validate biome, potion and enchantment slots against live registry capacities and occupants without silently assigning new IDs; retain compatibility with runtime registry expansion.
- Prevent large generation footprints from loading absent border chunks, use non-loading Nether biome queries, fix monument pillar coordinate checks and inverted delta bounds, and restore nested Nether falling-block state with a finally boundary. Unsafe candidates are skipped before placement; border feature frequency can change, with no retrogen.
- Move elytra velocity flags into native tracker updates, group falling concrete by its world, and bound server packet admission to 128 actions per connection, 4,096 total and 256 drained per tick. Keep connection/world checks and packet discriminators.
- Recheck all 371 public configuration fields, retain ordered master toggles and optional-mod gates, remove an unused NEI class reference from the fuel map, and update the existing audit/configuration trace.

## 2026-10-21 02:43 â€” Stage B native Nether and surface content

- Inventory the maintained 1.7.10 catalog against the Java 1.21.11 ceiling, separating completed paths, progression gaps, experimental mechanics, optional integrations and excluded engine work.
- Add explicit, collision-checked surface biome identities and native climate/decorating paths for Cherry Grove, Mangrove Swamp, Pale Garden, Bamboo Jungle, meadows, groves and mountain variants. Add temperature ocean layers without replacing native coastlines, terrain noise or world height. New-generation switches retain registered biome identities; no retrogen or silent ID changes.
- Complete mangrove propagule tree growth and hanging ages using existing wood, leaf and sapling identities. Bound moss growth, protect cherry tree footprints and connect cherry sapling bee nests. Correct warped nylium placement and add native bone-meal propagation.
- Add piglins, brutes, hoglins, zoglins and striders with native AI, survival interactions, timed conversions, persisted payment/anger state, gold reactions, bartering, breeding, riding and drops. Add Soul Speed with a configured enchantment ID, native movement-attribute lifecycle and soul-sand exemption. Retain the Nether master toggle and experimental provider boundaries.
- Add four bastion template/pool families with bounded deterministic assembly, native saved structure pieces, chunk-clipped placement/loot/mobs and variant loot categories. Preserve existing fortress generation and Nether ore/debris paths.
- Add pale oak families, native growth, pale vegetation, eyeblossoms, resin production/processing/building recipes and a persisted Creaking Heart/Creaking lifecycle. Keep existing wood metadata separate; preserve Stage A's disabled sculk XP interception.
- Add frogs, frogspawn, bucketable tadpoles and froglight acquisition as the mangrove dependency chain. Preserve native bucket placement permissions and event handling.
- Retain the existing dependency/toolchain/Mixin stack, packet IDs, old entity/tile identities and old save formats. New content uses additive identities and NBT. Document legacy adaptations and remaining incomplete mechanics rather than claiming complete modern parity.

- Complete stonecutting, loom patterning and cartography with block-bound native containers, atomic ingredient/output transfers, reusable pattern items, map-copy/scale/lock persistence and NEI views. Preserve existing workstation keys and banner/map identities.
- Add native guardians and persistent elder variants, monument-bound aquatic spawning, charged beams, mining fatigue and wet sponge acquisition. Migrate new monument generation to saved chunk-clipped template pieces without retrogen or forced neighbor loading.
- Add saved dragon-victory gateway progression to the experimental End while preserving native combat/XP/portal behavior and rejecting overlapping crystal respawns. Complete eyeblossom states, bee poisoning, dyes/stew, hanging-moss bone meal and bastion degradation processors.

## 2026-10-21 21:18 — Stage B7 native mob backports and survival dependencies

- Add the missing post-1.7.10 mob families through Java 1.21.11, including aquatic life, illagers, traders/llamas, pandas/cats, allays/wardens, camels/sniffers, armadillos, Breeze/Bogged, copper golems, happy ghasts, nautiluses and undead mounts. Preserve existing local IDs and append new living/projectile/passenger identities with bounded saved state.
- Adapt acquisition to native biomes, villages and structures: bounded water populations, patrols, chunk-contained outposts/allay cages, saved finite raids, dungeon eggs/equipment and naturally summoning underground shriekers. Selected native dungeons provide finite Breeze/Bogged fights; full Trial Chambers, vaults and keys are not included.
- Add buckets, seagrass/turtle eggs, scutes, membranes, crossbows, tridents/enchantments, wind charges, ancient plants, wolf armor, harnesses, nautilus armor and spears. Use native inventory, smithing, merchant and rider paths; retain disabled-prerequisite recipe deferral and server authority.
- Add retained native models, released textures, equipment/variant layers, core state animations and AssetDirector sound events. Preserve existing compatibility boundaries and avoid modern player swimming, waterlogging, cave-engine replacement or world-height changes.
- Complete related husk conversion, fox food/village behavior, piglin crossbows/social combat, hoglin herd retreat, zoglin knockback and tadpole feeding. Index loaded chest lifecycle for bounded copper sorting, and allow adjacent chest transfers without requiring a path into the chest block.
- Release raid-owned mobs back to normal despawning when a raid ends or is abandoned, preventing permanent escaped raider accumulation. Document the full mob inventory, survival adaptations, configuration identities and remaining mechanic/presentation differences in the existing coverage and configuration documents.

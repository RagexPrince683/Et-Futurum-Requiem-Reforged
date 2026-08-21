# Changelog

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

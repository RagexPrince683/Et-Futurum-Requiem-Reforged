# 1.7.10 content catalog and Stage B coverage

Reference ceiling: Java Edition **1.21.11**. Runtime: Forge **1.7.10**. This catalog describes behavior rather than equating modern names with registry IDs. Stage A's uncommitted stabilization is the starting baseline; `AUDIT.md` records its decisions and validation separately.

Status: **implemented** = a production implementation and acquisition path were found, not an assertion of exhaustive gameplay testing; **progression gap** = implementation exists but acquisition/generation is missing; **experimental** = incomplete or explicitly gated; **approved missing** = required by Stage B; **candidate** = requires a scope decision; **excluded** = deliberately outside this project. Optional integrations are conditional, never proof that content is installed.

## Pre-Stage B inventory (historical)

This table records the starting inventory. Later sections supersede its baseline gaps; the current mob inventory and remaining differences are maintained in [MOB_COVERAGE.md](MOB_COVERAGE.md).

| Original Java version | Feature | Source ownership / existing identity | Baseline classification |
| --- | --- | --- | --- |
| 1.8 | Granite, diorite, andesite, polished variants; red sandstone | ModBlocks.STONE, RED_SANDSTONE; ModRecipes; native ore generation | implemented |
| 1.8 | Prismarine, sea lanterns, wet sponges, ocean monuments | PRISMARINE_BLOCK, SEA_LANTERN, SPONGE; OceanMonument | implemented blocks/structure; guardians missing |
| 1.8 | Slime blocks, banners, armor stands | SLIME, BANNER; EntityArmourStand; banner crafting | implemented |
| 1.8 | Coarse dirt, iron trapdoors, wood doors/fences/gates | COARSE_DIRT; CoarseDirtGeneration; native block families | implemented; preserve Stage A column conversion |
| 1.8 | Rabbits, mutton, rabbit foods and brewing ingredient | EntityRabbit; ModItems rabbit family, MUTTON_* | implemented |
| 1.8 | Inverted daylight detector, barrier, enchanting/anvil updates | DAYLIGHT_DETECTOR_INVERTED, BARRIER; early Mixins | implemented |
| 1.9 | Beetroot and soup, chorus/purpur/End bricks/rods | BEETROOTS, CHORUS_*; ModRecipes | implemented |
| 1.9 | Elytra, shulkers, tipped/spectral arrows, lingering potions | EntityShulker; equipment/projectile classes; EndCityTemplate | implemented; End city acquisition requires experimental End |
| 1.9 | Mending, Frost Walker, end crystals, dragon respawn | ModEnchantments; EntityPlacedEndCrystal/RespawnedDragon | implemented legacy adaptations |
| 1.9 | Outer End islands, cities, gateways | ChunkProviderEFREnd, EndCityPieces, TileEntityGateway | experimental; new dragon fight/gateway generation incomplete |
| 1.9 | Igloos, complete modern combat and shields | No native implementations located | structures candidate; combat overhaul excluded |
| 1.10 | Husk, stray, polar bear; magma, bone, red Nether bricks | EntityHusk/Stray/PolarBear; MAGMA, BONE, RED_NETHERBRICK | implemented |
| 1.10 | Fossils | WorldGenFossil, WorldGenNetherFossil (latter 1.16) | implemented, Stage A rejects unloaded footprints |
| 1.11 | Observer, shulker boxes, iron nugget, totem | OBSERVER, SHULKER_BOX; ModItems; inventory Mixins | implemented blocks/items; totem has an existing optional crafting recipe (enableRecipeForTotem=false by default); evokers remain candidates |
| 1.11 | Woodland mansions, illagers, vex, llamas | No native implementations located | candidates; not automatically approved |
| 1.12 | Concrete/powder, glazed terracotta, dyed beds | CONCRETE*, *_GLAZED_TERRACOTTA, *_BED | implemented |
| 1.12 | Parrots, recipe-book/advancement engines | No native implementations located | parrot candidate; engine subsystems excluded |
| 1.13 | Stripped logs/bark, blue ice, wood buttons/plates/trapdoors | LOG*_STRIPPED, BARK*, BLUE_ICE; wood families | implemented |
| 1.13 | Bubble columns, kelp/seagrass, coral, sea pickles | BUBBLE_COLUMN only; no other registered families | columns implemented; vegetation candidates |
| 1.13 | Ocean temperature biomes, ruins, shipwrecks, buried treasure | No native biome/structure implementations located | ocean environments eligible; structures candidates |
| 1.13 | Fish, turtles, dolphins, drowned, phantoms, trident/conduit | No native implementations located | candidates, not an approved aquatic subsystem rewrite |
| 1.13 | Swimming, crawling, generalized waterlogging | Not part of native EFR catalog | excluded engine changes |
| 1.14 | Bamboo, berries, cornflower, lily of valley, wither rose | BAMBOO*, SWEET_BERRY_BUSH; flower blocks and recipes | implemented |
| 1.14 | Foxes, brown mooshrooms, suspicious stew | EntityFox/BrownMooshroom; ItemSuspiciousStew | implemented |
| 1.14 | Barrel, smoker, blast furnace, composter, smithing | corresponding ModBlocks, tiles, containers, NEI handlers | implemented; smithing progression follows existing template-free rules |
| 1.14 | Stonecutter | BlockStonecutter renders a saw and applies configured contact damage; no cutting container/recipe registry | partial; crafting recipe alone does not complete stonecutting |
| 1.14 | Loom, cartography/fletching tables; modern village jobs/trading | registered utility blocks; no complete profession system | partial; fletching table is decorative in vanilla too |
| 1.14 | Bamboo jungle; pandas, cats, pillagers/raids, crossbow | no dedicated biome; no new mob/equipment classes | biome approved; new gameplay systems candidates |
| 1.15 | Bees, nests/hives, honey bottles/blocks/combs | EntityBee, TileEntityBeeHive; growth handler | implemented |
| 1.16 | Netherite/debris/scrap, smithing upgrades, Nether gold | ANCIENT_DEBRIS, NETHER_GOLD_ORE, NETHERITE_*; recipes/worldgen | implemented; preserve old recipes and saves |
| 1.16 | Basalt/blackstone, gilded blackstone, soul soil/lighting, chains | corresponding block families, recipes | implemented; gilded blackstone natural acquisition incomplete |
| 1.16 | Crimson/Warped Forest, Soul Sand Valley, Basalt Deltas | NetherBiomeManager; NetherChunkProvider; four decorators | experimental; native terrain/vegetation exists |
| 1.16 | Fungi, roots, sprouts, vines, nylium, shroomlights | NETHER_FUNGUS/ROOTS/SPROUTS, NYLIUM, WorldGenHugeFungus | experimental; audit growth and optional-content gates |
| 1.16 | Piglins, hoglins, zoglins, striders, bartering/riding | no entity/AI/render registrations | approved missing |
| 1.16.2 | Piglin brutes | no entity/AI/render registration | approved missing |
| 1.16 | Bastion remnants (four variants), ruined portals | no structure generator/loot integration | bastions approved missing; ruined portals candidate |
| 1.16 | Crying obsidian, respawn anchor, lodestone, target | CRYING_OBSIDIAN (decorative), TARGET; no anchor/lodestone | target implemented; functional additions candidates |
| 1.16 | Soul Speed, Pigstep | no Soul Speed enchantment; PIGSTEP_RECORD | missing enchantment/acquisition |
| 1.17 | Copper oxidation/waxing, raw ores, deepslate families | copper/ore/block classes and native generation | implemented |
| 1.17 | Amethyst/geodes/tinted glass, tuff/calcite, goats/horns | WorldGenGeode, EntityGoat; corresponding blocks/items | implemented (horn interaction was expanded in 1.19) |
| 1.17 | Moss/azalea, dripstone, glow berries/lichen, lightning rod | experimental blocks/generators/tiles; Stage A mechanics | experimental; no cave biomes needed |
| 1.17 | Axolotl, glow squid/ink, spyglass, candles, powder snow | no complete native implementations located | candidates |
| 1.18 | Meadow, grove, snowy slopes, jagged/frozen/stony peaks | no dedicated native surface biome registry | approved missing; retain legacy terrain height |
| 1.18 | Lush/dripstone cave biomes, height/noise engine replacement | not native EFR surface biomes | excluded; retain existing cave blocks/decorators |
| 1.19 | Mud/packed mud/bricks, mangrove wood/roots | MUD, PACKED_MUD, MANGROVE_*; wood families | implemented blocks; mangrove propagule growth missing |
| 1.19 | Mangrove Swamp, frogs/tadpoles/froglights | no biome/frog classes; no froglight registration | approved missing dependency chain |
| 1.19 | Sculk catalyst/spread, deep dark, warden, recovery compass | SCULK/SCULK_CATALYST and empty catalyst tile | experimental; spreading disabled by Stage A; cave biome excluded |
| 1.19 | Chest boats, disc 5/fragments, allay | chest boats/disc items registered; no allay | boats implemented; disc 5 fragments have existing dungeon/stronghold loot and disc crafting; allay candidate |
| 1.20 | Cherry wood/trees/petals, Cherry Grove | existing tree and mountain decoration; no grove biome | blocks implemented; biome approved missing |
| 1.20 | Bamboo wood/mosaics/rafts, chiseled bookshelf | bamboo families registered; no functional chiseled bookshelf | bamboo implemented; bookshelf candidate |
| 1.20 | Archaeology, sniffer, camel, trims/templates, hanging signs | no complete native implementations located | candidates; preserve existing smithing progression |
| 1.21 | Copper bulbs/grates/chiseled copper/doors/trapdoors, tuff family | corresponding ModBlocks and recipes | implemented; not missing merely because metadata groups variants |
| 1.21 | Trial chambers, spawners/vaults, breeze/bogged, mace, new enchants | records have existing dungeon/stronghold loot adaptations; no functional trial subsystem | candidates, not automatic requirements |
| 1.21.2 | Bundles | no native bundle inventory implementation | candidate |
| 1.21.4 | Pale Garden, pale oak, pale moss, eyeblossoms, creaking/heart, resin | no native registrations or generators | approved missing |
| 1.21.5 | Wildflowers, leaf litter, firefly bush, dry grasses/cactus flower, animal variants | no corresponding complete native families | candidates |
| 1.21.6 | Dried/happy ghast, ghastling, harness, saddle crafting, new discs | TEARS_RECORD/LAVA_CHICKEN_RECORD exist; no ghast chain | ghast chain candidate; Tears and Lava Chicken already have native returned-fireball and chicken-jockey death drops |
| 1.21.9 | Copper equipment/golem/chests/shelves/lanterns/bars/chains/torches | earlier copper building families only | candidates; new automation subsystem not automatically approved |
| 1.21.11 | Spears/Lunge, nautilus/zombie nautilus, camel husk/parched, netherite horse armor | no native implementations located | candidates; comprehensive mounted combat rewrite excluded |

## Optional-mod boundaries

Netherlicious is recognized in `ModsList`, `ExternalContent`, fossil and structure conversion. Its installed blocks may satisfy specific existing compatibility mappings; EFR's own config explicitly says full Nether biome-provider compatibility is not implemented. Do not label the requested Nether mobs or bastions as supplied by this integration without an installed, inspected implementation. External wood integrations supply their own configured families; they do not imply pale oak or mangrove tree growth. NEI, Waila, CraftTweaker and Iron Chest provide integration surfaces rather than replacement vanilla content.

## Compatibility rules for this catalog

Existing registry strings, entity IDs 0â€“25, wood metadata 0â€“4, leaf/sapling metadata 0â€“1, Nether biome IDs 200â€“203, packet layouts, tile names, NBT and defaults remain authoritative. Additional biomes use explicit configured IDs with collision failures, never a free-slot search or automatic remapping. New generation affects new chunks only. No multiversion code is part of Stage B.

## Reference releases

Original-version groupings use Java releases, including Piglin Brutes in 1.16.2 rather than 1.16. Surface adaptations retain 1.7.10's 0â€“255 height and climate layers. Primary release references: [official update timeline](https://www.minecraft.net/en-us/updates/minecraft-updates-timeline-and-evolution), [The Garden Awakens / 1.21.4](https://www.minecraft.net/en-us/article/minecraft-java-edition-1-21-4), [Mounts of Mayhem / 1.21.11](https://www.minecraft.net/en-us/article/minecraft-java-edition-1-21-11). Registration is not sufficient evidence of a finished mechanic; later implementation and validation records must identify any remaining gaps explicitly.

## Stage B implementation catalog

The rows above are the starting inventory. The following describes the additions to that baseline. **Native implementations and legacy adaptations are cataloged separately from runtime acceptance.** Experimental placement, client behavior and the limitations below must not disappear into an "implemented" label.

| Introduction | Added production behavior | Ownership and remaining limits |
| --- | --- | --- |
| 1.8 | Guardians with server-owned charged beams, aquatic movement, thorns, prismarine/fish drops; saved elders with mining fatigue and wet sponge drops | EntityGuardian; guardian-only water spawn category is bounded to saved monument volumes, with a separate cap of 70. Native saved monument pieces retain the existing 58x58 template and 32-chunk spacing, clip writes to each newly populated chunk, persist placement/elder bits and never retrogen old chunks. Old unsaved monuments are not automatically rediscovered. |
| 1.9 | Saved progression for twenty dragon-victory gateways | EndGatewayProgress and targeted native dragon death hook; respawned dragons call the same owner. Pending 3x5x3 gateway footprints wait for loaded chunks. Existing legacy dragon combat, portal, XP and four-crystal respawn remain; overlapping respawns are rejected. Modern combat phases are deliberately not introduced. |
| 1.14 | Functional stonecutter, loom and cartography containers and interfaces, plus NEI recipes | Explicit gated stonecutting families include copper quantities; loom consumes one banner/dye, retains a reusable crafted pattern item and respects six layers and existing banner NBT. Cartography copies maps, expands unlocked maps up to scale four, or creates a locked map with copied pixels. Native window-bound selection packets are used; close drops transient inputs, reach/block checks and atomic output extraction protect resources. Map locking is additive saved MapData state and remains active when the table is disabled. |
| 1.13 | Warm, lukewarm, cold, deep lukewarm, deep cold and deep frozen ocean identities; native frozen ocean reuse | OceanBiomeLayer substitutes ocean temperatures after native coastlines/rivers and feeds both terrain and Voronoi biome queries. It deliberately does not replace terrain noise. Reefs, kelp and icebergs are not supplied by these identities. Stage B7 adds seagrass and native aquatic mobs separately. |
| 1.14 | Bamboo Jungle with native jungle trees/bamboo, chickens and ocelots | BiomeModernSurface; bamboo generation still honors bambooWorldgen. Pandas remain absent. |
| 1.16 / 1.16.2 | Piglin/brute registrations, melee AI, gold armor recognition, gold/chest anger, timed bartering, conversion, persisted anger/payment timers | EntityPiglin/Brute, NetherContentEvents. Supported barter items preserve relative vanilla weights, including Soul Speed books/boots; disabled optional entries are omitted. Crossbow combat and the full modern group hunting/dancing/Brain behavior are not implemented. |
| 1.16 | Hoglin breeding, attacks, warped fungus avoidance, pork/leather drops and timed conversion; undead zoglins attack other living entities except creepers/zoglins | EntityHoglin/Zoglin. Native AI replaces modern Brain scheduling; exact herd/group combat parity remains experimental. |
| 1.16 | Strider lava spawning through a Forge creature type and targeted UniMixins hooks, saddles, server-owned rider movement, breeding, cooling, boosting and string drops | EntityStrider, NetherSpawning, ItemWarpedFungusOnAStick. Native rider input is used; riding/prediction and lava pathfinding need client acceptance. Natural jockey variants are not implemented. |
| 1.16 | Soul Speed Iâ€“III, exact configured enchantment identity, movement attribute lifecycle, soul-sand slowdown exemption and boot wear | SoulSpeed, ModEnchantments, MixinBlockSoulSand. Acquired through bartering/bastions, not ordinary enchanting. |
| 1.16 | Four bastion start variants with 167 reference templates and 60 weighted pools; bounded deterministic connector assembly, native saved/clipped pieces, mob markers, magma-cube spawners and four loot categories | MapGenBastion, BastionTemplate, BastionLoot. All palette and property-bearing jigsaw final states map explicitly; Nether wart crops remain vanilla crops and chain axes use the native 0/1/2 metadata. Assembly is limited to seven connector levels/192 pieces/80-block anchor distance. Twelve compiled reference degradation processors include cracked/gilded replacements and height-dependent decay, with deterministic per-block randomness. Exact modern loot roll distributions are adapted to supported content. Placement is experimental pending distribution/visual acceptance. |
| 1.16 | Nylium bone meal propagation from neighboring nylium and corrected warped-vine target checks | NetherContentEvents, BlockNylium; existing fungus/forest/debris/ore paths retained. |
| 1.17 | Bounded loaded-column moss growth and flower/azalea vegetation | BlockMoss now has a fixed attempt budget instead of the unfinished nested walk; existing moss acquisition recipe and Stage A azalea tree growth retained. |
| 1.18 | Meadow, Grove, Snowy Slopes, Jagged/Frozen/Stony Peaks | Native climate registration, snow/ice/stone terrain and explicit spawn lists after the general entity registration pass. The legacy height/noise engine is retained; slopes use native snow, and meadows use native horses rather than a donkey-only spawn implementation. |
| 1.19 | Mangrove Swamp, shallow-water rooted trees, planted propagule growth, five hanging propagule stages and leaf bone meal | Existing wood/leaf/sapling IDs and metadata 0/1 plus growth bits 8/9 retained; unused even metadata 2/4/6/10/12 represent hanging ages. Roots displace water instead of introducing waterlogging. |
| 1.19 | Frogs, climate-selected variants, slime-ball breeding/pregnancy, frogspawn hatching, bucketable tadpoles, growth and three froglights from eating small magma cubes | EntityFrog/Tadpole, BlockFrogspawn/Froglight, ItemTadpoleBucket. Bucket placement delegates protection and consumption to the native water-bucket path. Native aquatic movement and retained models are adaptations; full animation/ecology acceptance is outstanding. |
| 1.20 | Cherry Grove with existing cherry trees/petals, sheep/pigs/rabbits and bee-nest attempts; cherry sapling bee integration | Existing mountain cherry decoration remains. Tree footprint loading and soil checks prevent population from probing absent neighboring chunks. |
| 1.21.4 | Pale Garden; independent pale oak log/stripped wood/plank/slab/stair/fence/gate/door/trapdoor/button/plate/sign/boat families; pale moss/carpet/hanging moss, eyeblossoms, resin clumps/bricks/building variants | Existing wood metadata arrays are not extended. Recipes, OreDictionary tags, smelting and stripping use native EFR infrastructure. Eyeblossoms retain both item states, change with night/day, poison contacting bees and supply dyes/suspicious stew; hanging moss extends with bone meal. Hanging signs, full carpet side geometry and exact animation/sound parity remain absent. |
| 1.21.4 | Creaking Heart log-axis/night/player gates, heart-bound invulnerable Creaking, look-to-freeze behavior, bounded resin production and persisted entity ownership/deadlines | BlockCreakingHeart, TileEntityCreakingHeart, EntityCreaking. No global tile/entity index scan. A saved Heart waits for its entire tether area to load before replacing an absent bound entity. Client/real-player behavior still needs acceptance. |

### Deliberate exclusions and pending work

Excluded by the request: cave biomes, general cave overhaul, world-height/noise/chunk-format replacement, generalized waterlogging, swimming/crawling overhaul, renderer/lighting replacement, comprehensive combat overhaul and post-1.21.11 content. Existing cave blocks and local decorators remain.

Stage B7 adds the mob, equipment, raid, outpost and aquatic dependencies described in [MOB_COVERAGE.md](MOB_COVERAGE.md), including happy ghasts, copper golems and the 1.21.11 mounts. Full Trial Chambers/vaults/keys, Woodland Mansions, archaeology, armor trims, conduits and a replacement villager-profession engine remain outside these adapted mob acquisition paths. Dungeon trial spawners are not a Trial Chambers structure port.

Established adaptations retained: native 1.7.10 dragon combat rather than modern fight phases; optional totem crafting rather than an added illager subsystem; existing dungeon/stronghold record loot rather than unrelated new source structures. Sculk catalyst spreading remains disabled exactly as decided in Stage A. The new Nether/biome implementations also retain the limitations in the table. Future ports must distinguish these deliberate adaptations from unresolved acceptance checks and must not infer exact modern parity.

### Added identities and configuration

Existing identities and saved values are retained. New mod entity IDs are 26 piglin, 27 brute, 28 hoglin, 29 zoglin, 30 strider, 31 creaking, 32 frog, 33 tadpole and 34 guardian (saved Elder variant). The new Heart tile is `etfuturum.creaking_heart`. New block/item strings use the explicit lowercase ModBlocks/ModItems names; pale wood has its own blocks rather than repurposing existing wood metadata.

New biome IDs default to 204 Cherry Grove, 205 Mangrove Swamp, 206 Pale Garden, 207 Meadow, 208 Grove, 209 Snowy Slopes, 210 Jagged Peaks, 211 Frozen Peaks, 212 Stony Peaks, 213 Bamboo Jungle and 214â€“219 the six ocean variants above. Each existing `*ID` value is explicit and collision-checked. Setting an ID to -1 removes its registration; do not do this for biomes already used in a save. `enableSurfaceBiomes=false` retains those registered identities while disabling new placement. Ocean substitutions apply to native default/large-biome/amplified types; custom terrain providers retain ownership of their own layers.

Other added keys: experiments `enablePaleGarden=false`; entities `enableNetherMobs=true`, `enableFrogs=true` and `enableGuardians=true`; world `enableBastions=true`, `bastionSpacing=27`, `enableSurfaceBiomes=true`; enchantments `soulSpeed=true`, `soulSpeedID=203`. Individual `generateCherryGrove`, `generateMangroveSwamp`, `generatePaleGarden` and equivalent `generate<BiomeNameWithoutSpaces>` keys default to true for all sixteen added biomes. They disable placement without removing saved biome identities. The Nether master toggle also suppresses Nether mobs. Bastions require enabled blackstone, basalt and smooth quartz and the experimental Nether provider/world structure switch. They do not retrogen. New entities use additional NBT only; no existing entity, tile, packet or inventory NBT format is rewritten.

New textures/templates/pools/processors are copied from Mojang's official 1.21.11 client archive. No newer content, new dependency, second Mixin bootstrap or raw ASM transformer is introduced. All world mutation remains on the native owning thread.

Workstation identities: new item `etfuturum:banner_pattern` has six explicit metadata variants for the existing special patterns. GUI IDs 8, 9 and 10 append stonecutter, loom and cartography after existing 0â€“7; no network discriminator is changed. Locked maps add `etfuturumLocked` to native map data. Saved monuments use `EFROceanMonument` / `EFRMonumentPiece`; End victory progression uses `etfuturum_end_gateways`. None of these identities replace old registry entries.

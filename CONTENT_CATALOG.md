# UAB content catalog

UAB adapts Java Edition content through 1.21.11 to Forge 1.7.10. The native world height, loader, save format and server ownership remain in place. A modern content name does not imply complete modern engine behavior. Feature families use update-based controls in [config/uab.cfg](CONFIGURATION.md).

| Origin | Main implemented families |
| --- | --- |
| 1.8 - Bountiful Update | Granite/andesite/diorite, prismarine/sea lanterns, monuments/guardians, wet sponge, slime, banners, armour stands, rabbits/mutton, coarse dirt, wood fences/gates/doors, red sandstone, enchanting/anvil/beacon improvements. |
| 1.9 - Combat Update | Chorus/purpur/End bricks/rods, beetroot, outer End/islands/cities/gateways, shulkers, elytra, rowing boats, tipped/spectral arrows, lingering potions, Mending/Frost Walker, dragon respawn and selected sounds. |
| 1.10 - Frostburn Update | Husks, strays, polar bears, magma, bone, Nether wart/red Nether bricks, fossils and mesa generation changes. |
| 1.11 - Exploration Update | Observers, shulker boxes, iron nuggets, totems, llamas, vindicators, evokers and vexes. |
| 1.12 - World of Color Update | Concrete, glazed terracotta, colored beds and the registered Illusioner adaptation. |
| 1.13 - Update Aquatic | Fish, dolphins, turtles, drowned, phantoms, tridents, seagrass, buckets, scutes, ocean-temperature biomes, blue ice, stripped/bark logs, wood trapdoors/buttons/plates and bubble columns. |
| 1.14 - Village & Pillage | Bamboo/jungles, berries/flowers, foxes, brown mooshrooms, cats, pandas, pillagers/ravagers/traders, patrols/raids/outposts, crossbows, suspicious stew, barrels, workstations and lanterns. |
| 1.15 - Buzzy Bees | Bees, nests/hives, honey/honeycomb and newer elytra takeoff behavior. |
| 1.16 - Nether Update | Netherite/debris/gold, basalt/blackstone, soul soil/lighting, crimson/warped families, fungi/vines, Nether biomes, bastions, piglins/hoglins/zoglins/striders, bartering and Soul Speed. |
| 1.16.2 | Piglin brutes. |
| 1.17 - Caves & Cliffs Part I | Copper/raw ores, deepslate, tuff/calcite, amethyst/geodes, goats, moss/azalea, dripstone, lightning rods, glow berries/lichen, axolotls/glow squid, candles, light blocks and lava cauldrons. |
| 1.18 - Caves & Cliffs Part II | Meadow, Grove, Snowy Slopes, Jagged/Frozen/Stony Peaks and Otherside. |
| 1.19 - The Wild Update | Mud/mangrove/swamps, frogs/tadpoles/froglights, allays, chest boats, sculk/sensors/shriekers, wardens, Ancient Cities, Darkness, Swift Sneak and disc 5. |
| 1.20 - Trails & Tales | Cherry wood/groves, bamboo wood/mosaics/rafts, camels, sniffers/eggs/ancient plants, brushes and adapted decorated pots. |
| 1.20.5 - Armored Paws | Armadillos, scutes and wolf armor. |
| 1.21 - Tricky Trials | Decorative copper, expanded tuff, Trial Chambers/spawners/vaults/keys, Breeze/Bogged, wind charges, ominous bottles, heavy cores and related music. |
| 1.21.4 - The Garden Awakens | Pale Garden, pale oak/vegetation, eyeblossoms, resin and heart-bound Creaking. |
| 1.21.6 - Chase the Skies | Dried/young/happy ghasts, hydration, harnesses and Tears. |
| 1.21.7 | Lava Chicken disc. |
| 1.21.9 - The Copper Age | Copper chests, copper golems/statues and End sky flashes. |
| 1.21.11 - Mounts of Mayhem | Spears, nautiluses/zombie nautiluses, nautilus armor, parched and camel husks. |

## Adaptation boundaries

Read [mob support](MOB_COVERAGE.md) and [world generation](WORLD_GENERATION.md) for specific behaviors, dependencies and saved-state rules. Modern surface/ocean biomes use native climate selection. Structures use released templates translated into saved, chunk-bounded legacy assembly. Existing terrain is not regenerated.

There is no general waterlogging/swimming engine, world-height or global cave-engine replacement, modern combat rewrite, full modern village-profession system, woodland-mansion generation, full archaeology/pottery designs, armor trims or template-based smithing. Fletching tables are decorative. Smithing retains the existing two-input progression and legacy dragon combat remains. Catalyst XP spreading stays unfinished and disabled. Missing ingredients or reward prerequisites are deferred rather than replaced with unrelated content.

UAB retains technical `etfuturum` identities, entity numbers, resource domains, network channel and saved NBT/structure names. Disabling acquisition does not remove those identities or erase existing content. Optional integrations use the other mod's actual installed API/content and do not replace UAB's canonical systems.

## Release attribution

Minor versions are distinct where content actually differs. Mojang's [1.21.7 release notes](https://www.minecraft.net/en-us/article/minecraft-java-edition-1-21-7) identify Lava Chicken; its [1.21.9 release notes](https://www.minecraft.net/en-us/article/minecraft-java-edition-1-21-9) identify End light flashes. The ceiling remains 1.21.11. Historical audit records describe their own validation checkpoints and are not a blanket acceptance claim for this release.

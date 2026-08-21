# Former HogUtils dependency map

This document records the audit against HogUtils revision
`5782dbbe51c301512a08d820f122326984b19b7b`. Only facilities used by Et Futurum
were internalized.

| Former facility | Et Futurum owner | Runtime support / callers |
| --- | --- | --- |
| Block, item, and biome tags | `ganymedes01.etfuturum.api.tags` | Early `Block`, `Item`, and `BiomeGenBase` Mixins attach metadata-aware containers. `ModTagging`, bees, brewing/enchanting fuel, pistons, bubble columns, goats, campfires, spectators, tools, and world generation use them. |
| `BlockMetaPair`, `ItemMetaPair`, metadata maps | `ganymedes01.etfuturum.api.blocksanditems.utils` | Identity-keyed, wildcard-aware pairs and Java-collection-backed maps serve deepslate/raw ore, stripping, geodes, configuration, and entity/world mappings. |
| `RecipeHelper` | `ganymedes01.etfuturum.api.utils.RecipeHelper` | Validation, OreDictionary, smelting, shaped/shapeless recipes, priority sorter classes, and output removal used by recipes and optional integrations. |
| `GenericUtils` | `ganymedes01.etfuturum.api.utils.GenericUtils` | Only metadata bounds, biome lookup, tag-name validation, and the modern colour-name constant remain. |
| `FastRandom` | `ganymedes01.etfuturum.api.utils.FastRandom` | xoshiro256** powers particles, render effects, ambience, client events, and dummy-world randomness. |
| `DummyWorld` | `ganymedes01.etfuturum.api.world.DummyWorld` | Global and per-instance fake block-state queries used by deepslate and raw-ore property delegation. |
| Generation check | `ganymedes01.etfuturum.api.world.IGeneratingCheck` | The early `MixinChunkProviderServer` keeps a nested per-thread counter; the deepslate chunk Mixin queries it. |
| Multi-block sounds | `ganymedes01.etfuturum.api.blocksanditems.block.IMultiBlockSound` | The existing client sound handler now applies BREAK, PLACE, HIT, and WALK sound types, including metadata-specific blocks and optional late Mixins. |
| Registry iteration event | `ganymedes01.etfuturum.api.event.BlockItemIterateEvent` | Et Futurum posts init events after registry population for dynamic external-mod tags and armor/block sound discovery. |
| Weighted random list | `ganymedes01.etfuturum.api.utils.WeighedRandomList` | Fox item-selection behavior. |

HogUtils event, ambience, rendering, base-block, OreDictionary Mixin, command,
configuration, and unrelated compatibility systems were not copied because Et Futurum
does not call or depend on them.

## Collection and dependency audit

The internalized facilities no longer reference fastutil. Metadata maps use `IdentityHashMap` for outer Block/Item keys and boxed-integer `HashMap` instances for metadata, while tag containers use Java maps and sets. This preserves identity and wildcard behavior without inheriting a hidden runtime library.

GTNHLib remains independently required by Et Futurum's event subscribers, armor equipment-change handling, and ocean-monument coordinate packing. UniMixins' GTNH Mixins module supplies `IEarlyMixinLoader`/`ILateMixinLoader`; neither dependency replaces HogUtils ownership of the facilities above.

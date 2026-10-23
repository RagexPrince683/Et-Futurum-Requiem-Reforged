# Third-party notices

## Minecraft reference content

The additional pale oak, resin, frog, guardian, banner-pattern and Nether entity textures, bastion structure NBT, bastion template pools and degradation processors are reference content from Mojang's official Minecraft Java Edition 1.21.11 client archive. These assets remain Mojang/Microsoft content and are not relicensed by this project's code license. The project already contains Minecraft asset adaptations and uses MCLib AssetDirector for additional Mojang resources. No post-1.21.11 reference assets are included in this addition.

## HogUtils-derived components

The Et Futurum tag containers, block/item metadata utilities, recipe helper,
fast random generator, dummy world, generation-state tracking, registry iteration,
and multi-block sound interface include code adapted from
[HogUtils](https://github.com/Roadhog360/HogUtils), specifically revision
`5782dbbe51c301512a08d820f122326984b19b7b`.

HogUtils is distributed under the following license:

> MIT License
>
> Copyright (c) [year] [fullname]
>
> Permission is hereby granted, free of charge, to any person obtaining a copy
> of this software and associated documentation files (the "Software"), to deal
> in the Software without restriction, including without limitation the rights
> to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
> copies of the Software, and to permit persons to whom the Software is
> furnished to do so, subject to the following conditions:
>
> The above copyright notice and this permission notice shall be included in all
> copies or substantial portions of the Software.
>
> THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
> IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
> FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
> AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
> LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
> OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
> SOFTWARE.

The xoshiro256** implementation retains its upstream public-domain notice in
`FastRandom.java`.

## Stage B7 mob reference assets

The modern mob textures and retained box/pivot model definitions are adapted from Mojang’s official Java Edition 1.21.11 client, using its official mappings as a behavioral reference. These Minecraft assets and adapted vanilla code retain Mojang’s rights; the project license does not relicense them. Sounds are resolved from Mojang’s asset service through the existing MCLib AssetDirector integration rather than bundled as audio files.

The released irregular-block element/UV definitions under `assets/etfuturum/models/block`, tall-seagrass textures, and five pillager-outpost templates under `data/structure/pillager_outpost` are also adapted from that same official 1.21.11 client. Model parent inheritance is resolved in the supplied definitions for the native 1.7.10 tessellator. Outpost block states and banners are translated at placement to existing legacy identities; modern entity NBT is not imported.

The underground structure pass additionally adapts the released 1.21.11 Ancient City and Trial Chamber templates, pools, processors, loot tables and trial-spawner configurations. Added small-block retained models/textures and the bundled End flash texture come from that same client. These reference assets retain Mojang's rights and are not relicensed by the project license.

# Development mods

- Put normal production/obfuscated Minecraft 1.7.10 mod jars directly in `devmods/`.
- Put already-deobfuscated development jars in `devmods/deobf/`.
- `runClient` and `runServer` automatically prepare production jars by remapping them through RetroFuturaGradle; the results are written to `build/devmods/remapped/`.
- All jars in this directory are development-only dependencies and are not published or shipped inside Et Futurum.

# UAB development mods

Place production Minecraft 1.7.10 mod JARs in `devmods/`, and already deobfuscated development JARs in `devmods/deobf/`.

The client and server development tasks remap production JARs through the pinned RetroFuturaGradle tooling into `build/devmods/remapped/`. These local dependencies are neither published nor bundled into UAB. Keep personal installations and saves separate from disposable test instances.

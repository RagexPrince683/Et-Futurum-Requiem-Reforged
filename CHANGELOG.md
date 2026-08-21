# Changelog

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

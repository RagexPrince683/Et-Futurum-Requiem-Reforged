package ganymedes01.etfuturum.command;

import ganymedes01.etfuturum.world.structure.underground.UndergroundStructures;
import net.minecraft.command.*;
import net.minecraft.util.*;
import net.minecraft.world.World;
public final class CommandLocateUnderground extends CommandBase {
	@Override public String getCommandName() { return "uab_locate"; }
	@Override public java.util.List<String> getCommandAliases() { return java.util.Collections.singletonList("efr_locate"); }
	@Override public int getRequiredPermissionLevel() { return 2; }
	@Override public String getCommandUsage(ICommandSender sender) { return "/uab_locate <ancient_city|trial_chambers>"; }
	@Override public void processCommand(ICommandSender sender, String[] args) {
		if (args.length != 1 || !args[0].equals("ancient_city") && !args[0].equals("trial_chambers")) throw new WrongUsageException(getCommandUsage(sender));
		World world = sender.getEntityWorld(); if (world.provider.dimensionId != 0) throw new CommandException("These structures generate in the Overworld.");
		ChunkCoordinates point = UndergroundStructures.nearest(world, sender.getPlayerCoordinates().posX, sender.getPlayerCoordinates().posZ, args[0].equals("ancient_city"));
		if (point == null) sender.addChatMessage(new ChatComponentText("No viable " + args[0] + " found within two placement regions. Check generation/content switches and native Overworld terrain support."));
		else sender.addChatMessage(new ChatComponentText(args[0] + " at " + point.posX + ", " + point.posY + ", " + point.posZ + " (saved structure or viable new-terrain candidate)."));
	}
}

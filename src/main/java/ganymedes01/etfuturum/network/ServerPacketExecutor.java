package ganymedes01.etfuturum.network;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.World;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Consumer;

/** Forge 1.7.10 invokes simple-channel handlers on Netty threads, not the server thread. */
public final class ServerPacketExecutor {

	public static final ServerPacketExecutor INSTANCE = new ServerPacketExecutor();
	private static final ArrayBlockingQueue<Runnable> PENDING = new ArrayBlockingQueue<>(4096);
	private static boolean accepting;

	private ServerPacketExecutor() {
	}

	public static synchronized void start() {
		PENDING.clear();
		accepting = true;
	}

	public static synchronized void stop() {
		accepting = false;
		PENDING.clear();
	}

	public static synchronized void execute(MessageContext context, Consumer<EntityPlayerMP> action) {
		if (!accepting) return;
		NetHandlerPlayServer handler = context.getServerHandler();
		EntityPlayerMP player = handler.playerEntity;
		World world = player.worldObj;
		// Bound retained work, and discard packets from an old connection, respawn or dimension.
		PENDING.offer(() -> {
			if (handler.netManager.isChannelOpen() && handler.playerEntity == player
					&& player.worldObj == world && !player.isDead) {
				action.accept(player);
			}
		});
	}

	@SubscribeEvent
	public void onServerTick(TickEvent.ServerTickEvent event) {
		if (event.phase != TickEvent.Phase.START) return;
		int count = PENDING.size();
		for (int i = 0; i < count; i++) {
			Runnable task = PENDING.poll();
			if (task == null) break;
			task.run();
		}
	}
}

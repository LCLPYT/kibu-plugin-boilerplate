package org.example.event;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.slf4j.Logger;
import work.lclpnet.kibu.hook.ServerPlayConnectionHooks;
import work.lclpnet.mplugins.hook.HookListenerModule;
import work.lclpnet.mplugins.hook.HookRegistrar;
import work.lclpnet.mplugins.hook.builtin.PluginLifecycleHooks;
import work.lclpnet.plugin.load.LoadedPlugin;

public class ExampleListener implements HookListenerModule {

    private final Logger logger;

    public ExampleListener(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void registerListeners(HookRegistrar registrar) {
        // register hook listeners here
        registrar.registerHook(PluginLifecycleHooks.LOADED, this::onPluginLoaded);

        // instead of fabric-api events, use kibu-fabric-hooks equivalents:
        registrar.registerHook(ServerPlayConnectionHooks.JOIN, this::onJoin);
    }

    private void onJoin(ServerPlayNetworkHandler serverPlayNetworkHandler, PacketSender packetSender, MinecraftServer minecraftServer) {
        logger.info("Player joined: {}", serverPlayNetworkHandler.player.getName().getString());
    }

    private void onPluginLoaded(LoadedPlugin plugin) {
        // called when a plugin was loaded
    }
}

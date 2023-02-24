package org.example;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import net.minecraft.MinecraftVersion;
import org.example.event.ExampleListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.lclpnet.mplugins.MPlugins;
import work.lclpnet.mplugins.ext.FabricPlugin;
import work.lclpnet.mplugins.ext.WorldStateListener;

public class ExamplePlugin extends FabricPlugin implements WorldStateListener {

    public static final String ID = "testPlugin";
    private static final Logger logger = LoggerFactory.getLogger(ID);

    // if you add a constructor, make sure to make give it no arguments so that the plugin can be loaded

    @Override
    public void loadFabricPlugin() {
        // do initialization here

        // plugins have full access to Minecraft, Fabric and MPlugins
        var fabric = FabricLoader.getInstance();
        var mplugins = fabric.getModContainer(MPlugins.MOD_ID).orElseThrow();

        logger.info("Running Minecraft {} with Fabric {} and mplugins {}",
                MinecraftVersion.CURRENT.getName(), FabricLoaderImpl.VERSION, mplugins.getMetadata().getVersion());

        // when using hooks, they must be unregistered when the plugin unloads.
        // plugins can use the registerHook and registerHooks methods for comfort.
        // these methods take care of unregistering the hooks at plugin unload
        registerHooks(new ExampleListener());

        // if you have other resources that need to be unloaded when the plugin unloads, let them implement
        // work.lclpnet.mplugins.ext.Unloadable so that you can register it via registerUnloadable()
        // registered unloadables will be automatically unloaded on plugin unload
    }

    @Override
    public void onWorldReady() {
        // called when the main world is loaded
    }

    @Override
    public void onWorldUnready() {
        // called when the main world or this plugin is unloading

        // unregister hooks here, if you registered them in onWorldReady()
        // otherwise, all events are unregistered at plugin unload automatically
        // unregisterAllHooks();
    }

    @Override
    public void unloadFabricPlugin() {
        // perform manual cleanup here
    }
}
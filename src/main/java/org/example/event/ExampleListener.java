package org.example.event;

import work.lclpnet.mplugins.hook.HookListenerModule;
import work.lclpnet.mplugins.hook.HookRegistrar;
import work.lclpnet.mplugins.hook.builtin.PluginLifecycleHooks;
import work.lclpnet.plugin.load.LoadedPlugin;

public class ExampleListener implements HookListenerModule {

    @Override
    public void registerListeners(HookRegistrar registrar) {
        // register hook listeners here
        registrar.registerHook(PluginLifecycleHooks.LOADED, this::onPluginLoaded);
    }

    private void onPluginLoaded(LoadedPlugin plugin) {
        // called when a plugin was loaded
    }
}

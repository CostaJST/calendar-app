package edu.curtin.calenderApp;

import java.util.Map;

public class PluginManager {

    private Map<String, Notify> plugins = new HashMap<>();

    public void  enablePlugin(String NotifyPlugin) {
        Notify notify = plugins.get(NotifyPlugin);
        if(notify != null) {
            plugin.enable();
        }
    }
    
}

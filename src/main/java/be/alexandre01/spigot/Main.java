package be.alexandre01.spigot;


import be.alexandre01.dnplugin.api.objects.server.DNServer;
import be.alexandre01.dnplugin.plugins.spigot.api.DNSpigotAPI;
import be.alexandre01.dnplugin.plugins.spigot.api.events.server.ServerAttachedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    DNSpigotAPI dnSpigotAPI;
    public void onEnable() {
        System.out.println("Hello World!");
        getServer().getPluginManager().registerEvents(this,this);
    }

    @EventHandler
    public void onInitialize(ServerAttachedEvent e){
        dnSpigotAPI = DNSpigotAPI.getInstance();

        dnSpigotAPI.registerRequestType("Test",new CustomRequestType());

        dnSpigotAPI.getResponseManager().addResponse(new SpigotRequestResponse());
        dnSpigotAPI.getRequestManager().getRequestBuilder().addRequestBuilder(new RequestHandler());
    }
}


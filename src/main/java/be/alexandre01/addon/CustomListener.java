package be.alexandre01.addon;

import be.alexandre01.addon.request.RequestResponse;
import be.alexandre01.dreamnetwork.api.events.EventCatcher;
import be.alexandre01.dreamnetwork.api.events.Listener;
import be.alexandre01.dreamnetwork.api.events.list.CoreInitEvent;
import be.alexandre01.dreamnetwork.api.events.list.commands.CoreCommandExecuteEvent;
import be.alexandre01.dreamnetwork.api.events.list.services.CoreServiceStartEvent;
import be.alexandre01.dreamnetwork.api.events.list.services.CoreServiceStopEvent;
import be.alexandre01.dreamnetwork.api.service.IContainer;

import java.util.Timer;
import java.util.TimerTask;

public class CustomListener implements Listener {
    @EventCatcher
    public void onInit(CoreInitEvent event) {
        System.out.println("DNAPI >> "+event.getDnClientAPI());
        event.getDnClientAPI().getGlobalResponses().add(new RequestResponse());
    }



    @EventCatcher
    public void onServiceStart(CoreServiceStopEvent e){
        if(e.getService().getJvmExecutor().getName().equals("Lobby")){
            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    e.getService().getJvmExecutor().startServer();
                    cancel();
                }
            },1000,1000);
        }
    }
}

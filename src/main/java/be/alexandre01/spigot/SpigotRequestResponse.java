package be.alexandre01.spigot;

import be.alexandre01.dnplugin.api.request.communication.ClientResponse;
import be.alexandre01.dnplugin.netty.channel.ChannelHandlerContext;
import be.alexandre01.dnplugin.plugins.spigot.api.DNSpigotAPI;
import be.alexandre01.dnplugin.utils.messages.Message;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class SpigotRequestResponse extends ClientResponse {
    public SpigotRequestResponse() {
       addRequestInterceptor(CustomRequestType.DREAMYREQUEST,(message, channelHandlerContext) -> {
           System.out.println(message);
              System.out.println("I am a custom spigot request");
       });
    }

    @Override
    protected void onResponse(Message message, ChannelHandlerContext ctx) {
        System.out.println("Je reçois des réponses ! >> "+ message);
        if(message.getHeader() != null){
            if(message.getHeader().equals("SusHeader")){
                System.out.println("J'ai reçu le custom header, youpi");
                System.out.println("I'm sus i think: "+ message.getString("QUESTION"));
                if(message.getString("QUESTION").equals("ARE YOU SUS ?")){
                    message.remove("DN-QUESTION");
                    message.set("SUSSY","AMOGUS!");
                    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                        try {
                            Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=-HpVqK89E7M"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                }
                DNSpigotAPI.getInstance().getClientHandler().writeAndFlush(message);
            }
        }
    }
}

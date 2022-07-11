package be.alexandre01.spigot;

import be.alexandre01.dnplugin.api.request.communication.ClientResponse;

public class SpigotRequestResponse extends ClientResponse {
    public SpigotRequestResponse() {
       addRequestInterceptor(CustomRequestType.DREAMYREQUEST,(message, channelHandlerContext) -> {
           System.out.println(message);

              System.out.println("I am a custom spigot request");
       });
    }
}

package be.alexandre01.addon.request;

import be.alexandre01.dreamnetwork.api.connection.core.communication.CoreResponse;

public class RequestResponse extends CoreResponse {
    public RequestResponse() {
         addRequestInterceptor(CustomType.DREAMYREQUEST,(message, channelHandlerContext, iClient) -> {
             System.out.println(message);
             System.out.println("I am a custom request");
         });
    }
}

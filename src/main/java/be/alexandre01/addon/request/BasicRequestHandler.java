package be.alexandre01.addon.request;

import be.alexandre01.dreamnetwork.api.connection.request.RequestBuilder;
import be.alexandre01.dreamnetwork.api.connection.request.RequestType;

public class BasicRequestHandler extends RequestBuilder {
    public BasicRequestHandler() {

       requestData.put(CustomType.DREAMYREQUEST,(message, iClient, objects) -> {
           message.set("REQUEST", "I am a custom request");
           return message;
       });
    }
}

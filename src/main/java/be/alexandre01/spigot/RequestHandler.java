package be.alexandre01.spigot;

import be.alexandre01.addon.request.CustomType;
import be.alexandre01.dnplugin.api.request.CustomRequestInfo;
import be.alexandre01.dnplugin.api.request.RequestBuilder;
import be.alexandre01.dnplugin.utils.messages.Message;


public class RequestHandler extends RequestBuilder {
    public RequestHandler() {

        getRequestData().put(CustomRequestType.DREAMYREQUEST, (message, objects) -> {
            message.set("REQUEST", "I am a custom plugin request");
            return message;
        });

    }
}


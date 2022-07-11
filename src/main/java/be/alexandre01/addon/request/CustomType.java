package be.alexandre01.addon.request;

import be.alexandre01.dreamnetwork.api.connection.request.CustomRequestInfo;
import be.alexandre01.dreamnetwork.api.connection.request.RequestInfo;
import be.alexandre01.dreamnetwork.api.connection.request.RequestType;

public class CustomType extends RequestType {
    public static final RequestInfo CUSTOM_TYPE = new CustomRequestInfo("CUSTOM");
    public static final RequestInfo DREAMYREQUEST = new CustomRequestInfo("DREAMYREQUEST");

}



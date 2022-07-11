package be.alexandre01.spigot;


import be.alexandre01.dnplugin.api.request.CustomRequestInfo;
import be.alexandre01.dnplugin.api.request.RequestInfo;
import be.alexandre01.dnplugin.api.request.RequestType;

public class CustomRequestType extends RequestType {
    public static final RequestInfo CUSTOM_TYPE = new CustomRequestInfo("CUSTOM");
    public static final RequestInfo DREAMYREQUEST = new CustomRequestInfo("DREAMYREQUEST");
}

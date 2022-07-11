package be.alexandre01.addon.request;

import be.alexandre01.dreamnetwork.api.connection.core.communication.CoreResponse;
import be.alexandre01.dreamnetwork.api.connection.core.communication.IClient;
import be.alexandre01.dreamnetwork.client.utils.messages.Message;
import io.netty.channel.ChannelHandlerContext;

public class RequestResponse extends CoreResponse {
    public RequestResponse() {
         addRequestInterceptor(CustomType.DREAMYREQUEST, new RequestInterceptor() {
             @Override
             public void onRequest(Message message, ChannelHandlerContext channelHandlerContext, IClient iClient) throws Exception {

             }
         }(message, channelHandlerContext, iClient) -> {
             System.out.println(message);
             System.out.println("I am a custom request");
         });
    }
}

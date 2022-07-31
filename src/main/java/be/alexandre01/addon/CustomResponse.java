package be.alexandre01.addon;

import be.alexandre01.dreamnetwork.api.connection.core.communication.CoreResponse;
import be.alexandre01.dreamnetwork.api.connection.core.communication.IClient;
import be.alexandre01.dreamnetwork.client.utils.messages.Message;
import io.netty.channel.ChannelHandlerContext;

public class CustomResponse extends CoreResponse {
    @Override
    protected void onResponse(Message message, ChannelHandlerContext ctx, IClient client) {
        if(message.getHeader() != null){
            if(message.getHeader().equals("SusHeader")){
                System.out.println("J'ai reçu le custom header, youpi");
                System.out.println("Voici un message venant du serveur "+client.getJvmService().getJvmExecutor().getName()+": "+ message.getString("SUSSY"));
            }
        }

    }
}

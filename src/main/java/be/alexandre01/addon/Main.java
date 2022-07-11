package be.alexandre01.addon;


import be.alexandre01.addon.commands.TestCommand;
import be.alexandre01.addon.request.CustomType;
import be.alexandre01.addon.request.RequestResponse;
import be.alexandre01.dreamnetwork.api.DNClientAPI;
import be.alexandre01.dreamnetwork.api.addons.DreamExtension;
import be.alexandre01.dreamnetwork.api.connection.core.channels.AChannelPacket;
import be.alexandre01.dreamnetwork.api.connection.core.channels.IDNChannel;
import be.alexandre01.dreamnetwork.api.connection.request.RequestBuilder;
import be.alexandre01.dreamnetwork.api.connection.request.RequestType;
import be.alexandre01.dreamnetwork.api.service.*;
import be.alexandre01.dreamnetwork.client.console.Console;
import be.alexandre01.dreamnetwork.client.utils.messages.Message;

public class Main extends DreamExtension {
    @Override
    public void onLoad() {
        System.out.println("Loaded");
        registerRequestType(new CustomType());
        registerPluginToServers(this);
    }



    @Override
    public void start() {
        System.out.println("Start");

        DNClientAPI dnClientAPI = DNClientAPI.getInstance(); // GET API
        dnClientAPI.getCoreHandler().getResponses().add(new RequestResponse()); // ADD RESPONSE


        dnClientAPI.getCommandReader().getCommands().addCommands(new TestCommand("test")); //ADD COMMAND
    }

    @Override
    public void stop() {
        System.out.println("Stop");
    }
    public static void main(String[] args) {
        System.out.println("Hello World!");
        DNClientAPI dnClientAPI = DNClientAPI.getInstance(); // GET API
        IContainer container = dnClientAPI.getContainer();
        IJVMExecutor executor = container.getJVMExecutor("test", IContainer.JVMType.SERVER);

        ///executor.startServer();  //START
  /*      IStartupConfig iStartupConfig = IStartupConfig.builder()  //CUSTOM START
                .javaVersion("8")
                .exec("paperspigot_1_8_8")
                .proxy(false)
                .pathName(IJVMExecutor.Mods.STATIC.getPath())
                .name("test")
                .xms("1024M")
                .xmx("2048M")
                .build();*/

        //executor.startServer(iStartupConfig);

        //STOP
       // executor.getService(0).stop();

        //STOP ALL
       // executor.getServices().forEach(IService::stop);

        //COMMUNICATION
         IService service = executor.getService(0);
        if(service.getClient() != null){
            //SEND REQUEST
            service.getClient().getRequestManager().sendRequest(RequestType.SPIGOT_EXECUTE_COMMAND,"say hello");

            //SEND CUSTOM MESSAGE
            Message message = new Message();
            message.set("pommes", 10);
            message.set("poires",12);
            service.getClient().writeAndFlush(message);
        }

        IDNChannel channel = dnClientAPI.getChannelManager().getChannel("test");    //GET CHANNEL
        channel.addInterceptor(new AChannelPacket.DNChannelInterceptor() {
            @Override
            public void received(AChannelPacket aChannelPacket) {
                System.out.println("received data");
            }
        });

        dnClientAPI.getCommandReader().getCommands().addCommands(new TestCommand("test")); //ADD COMMAND

    }
}

package be.alexandre01.addon.commands;

import be.alexandre01.dreamnetwork.api.DNClientAPI;
import be.alexandre01.dreamnetwork.api.commands.Command;
import be.alexandre01.dreamnetwork.api.commands.ICommand;
import be.alexandre01.dreamnetwork.api.commands.sub.SubCommandExecutor;
import be.alexandre01.dreamnetwork.api.service.IContainer;
import be.alexandre01.dreamnetwork.api.service.IJVMExecutor;
import be.alexandre01.dreamnetwork.api.service.IService;
import be.alexandre01.dreamnetwork.client.utils.messages.Message;
import lombok.NonNull;

public class TestCommand extends Command {
    public TestCommand(String name) {
        super(name);
        addSubCommand("hello", new SubCommandExecutor() {
            @Override
            public boolean onSubCommand(@NonNull String[] args) {
                System.out.println("Hello World");
                return false;
            }
        });
        addSubCommand("sus", new SubCommandExecutor() {
            @Override
            public boolean onSubCommand(@NonNull String[] args) {
                if(args.length > 1){
                    String server = args[1];
                    int id;
                    try{
                        id = Integer.parseInt(server.split("-")[1]);
                    }catch (Exception e){
                        System.out.println("ID pas valable");
                        return true;
                    }
                    server = server.split("-")[0];

                    DNClientAPI dnClientAPI = DNClientAPI.getInstance();

                    IJVMExecutor executor = dnClientAPI.getContainer().getJVMExecutor(server, IContainer.JVMType.SERVER);

                    if(executor == null){
                        System.out.println("Executor is null, your server cannot be sussied");
                        return true;
                    }

                    IService iService = executor.getService(id);

                    if(iService == null){
                        System.out.println("server with ID cannot be found, your server cannot be sussied, sad");
                        return true;
                    }

                    if(iService.getClient() == null){
                        System.out.println("Communication is not available, SUS I think");
                        return true;
                    }

                    Message message = new Message().setHeader("SusHeader").set("QUESTION","ARE YOU SUS ?");

                    iService.getClient().writeAndFlush(message,future -> {
                        System.out.println("La requete a bien été envoyé.");
                    });

                }
                System.out.println("ඞඞ YOU'RE SUSSY BAKA");
                return true;
            }
        });


        addSubCommand("stopAll", new SubCommandExecutor() {
            @Override
            public boolean onSubCommand(@NonNull String[] args) {
                System.out.println("Stopping all services");
                IContainer container = DNClientAPI .getInstance().getContainer();
                System.out.println("Log1");

                for (IJVMExecutor e : container.getJVMExecutorsServers().values()) {
                    e.getServices().forEach(IService::stop);
                }
                for (IJVMExecutor e : container.getJVMExecutorsProxy().values()) {
                    e.getServices().forEach(IService::stop);
                }

                return true;
            }
        });
        getHelpBuilder().setTitleUsage("This is a test command");
        getHelpBuilder().setCmdUsage("Show a default test message","test");
        getHelpBuilder().setCmdUsage("Show an Hello World","test","hello");
        getHelpBuilder().setCmdUsage("Amogus mode","test","sus");
    }
}

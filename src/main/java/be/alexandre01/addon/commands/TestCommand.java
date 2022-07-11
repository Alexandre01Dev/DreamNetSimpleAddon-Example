package be.alexandre01.addon.commands;

import be.alexandre01.dreamnetwork.api.DNClientAPI;
import be.alexandre01.dreamnetwork.api.commands.Command;
import be.alexandre01.dreamnetwork.api.commands.ICommand;
import be.alexandre01.dreamnetwork.api.commands.sub.SubCommandExecutor;
import be.alexandre01.dreamnetwork.api.service.IContainer;
import be.alexandre01.dreamnetwork.api.service.IJVMExecutor;
import be.alexandre01.dreamnetwork.api.service.IService;
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
                System.out.println("ඞඞ YOU'RE SUSSY BAKA");
                return false;
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

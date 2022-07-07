package be.alexandre01.addon.commands;

import be.alexandre01.dreamnetwork.api.commands.Command;
import be.alexandre01.dreamnetwork.api.commands.ICommand;
import be.alexandre01.dreamnetwork.api.commands.sub.SubCommandExecutor;
import lombok.NonNull;

public class TestCommand extends Command {
    public TestCommand(String name) {
        super(name);
        commandExecutor = new CommandExecutor() {
            @Override
            public boolean execute(@NonNull String[] args) {
                System.out.println("TestCommand Executed");
                return true;
            }
        };
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
        getHelpBuilder().setTitleUsage("This is a test command");
        getHelpBuilder().setCmdUsage("Show a default test message","test");
        getHelpBuilder().setCmdUsage("Show an Hello World","test","hello");
        getHelpBuilder().setCmdUsage("Amogus mode","test","sus");
    }
}

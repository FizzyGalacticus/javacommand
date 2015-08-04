package command.driver;

import command.Command;

public class CommandDriver {
    private CommandDriver() {

    }

    public static void main(String[] args) {
        String command = "";

        for(int i = 0; i < args.length; i++) {
            command += (args[i] + " ");
        }

        Command.run(command);
    }
}
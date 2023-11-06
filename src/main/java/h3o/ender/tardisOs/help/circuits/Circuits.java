package h3o.ender.tardisOs.help.circuits;

import java.util.HashMap;
import java.util.Arrays;

import h3o.ender.tardisOs.Command;
import h3o.ender.tardisOs.FormattedText;
import net.minecraft.text.MutableText;

public class Circuits implements Command {
    private static final HashMap<String, Command> commandMap = new HashMap<>();

    @Override
    public MutableText execute(String[] parts) {
        String commandName = parts.length == 0 ? "" : parts[0];
        String[] args = null;
        if (!(parts.length == 0)) {
            args = Arrays.copyOfRange(parts, 1, parts.length);
        }

        Command command = commandMap.get(commandName.toLowerCase());
        if (command == null) {
            return FormattedText.empty().error("No such circuit : ").info(commandName)
                    .error(". Use HELP CIRCUITS to list all entries").assemble();
        }
        return command.execute(args);
    }

    @Override
    public void parse(String[] ars) {

    }

    static {
        commandMap.put("", new ListCircuits());
        commandMap.put("MSTE", new MainSpaceTimeElement());
        commandMap.put("2LO_E_D", new LLOEnergyDistributor());
    }

}

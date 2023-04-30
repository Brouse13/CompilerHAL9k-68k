package es.brouse;

import es.brouse.menu.Action;
import es.brouse.menu.Menu;
import es.brouse.menu.MenuOption;
import es.brouse.utils.BinCompiler;
import es.brouse.utils.HexDump;

public class CompilerMenu extends Menu {
    public CompilerMenu() {
        super("Hal9000 to 68k compiler");

        addOption(new MenuOption("Compile", compile()));
        addOption(new MenuOption("HexDump", hexDump()));
        addOption(new MenuOption("Close", close()));
    }

    public Action compile() {
        return () -> {
            stream.println("Introduce the file name");
            final String fileName = super.getReader().nextLine();
            new BinCompiler(fileName, stream).compile();
            stream.println("File successfully compiled");
        };
    }

    public Action hexDump() {
        return () -> {
            stream.println("Introduce the binary file name");
            final String fileName = super.getReader().nextLine();

            stream.println("File content: ");
            stream.println(new HexDump(fileName, stream).getContent());
        };
    }

    public Action close() {
        return () -> super.close(false);
    }
}

package es.brouse.utils;

import es.brouse.io.AssemblerReader;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HexDump {
    private final String fileName;
    private final PrintStream stream;

    public HexDump(String fileName, PrintStream stream) {
        this.fileName = fileName;
        this.stream = stream;
    }


    /**
     * Transform all the instruction on the binary file into
     * its representation on hex to use it on the 68k projects.
     *
     * @return the hex values of all the file instructions
     */
    public String getContent() {
        if (!fileName.endsWith(".dat")) {
            stream.println("File " + fileName + " is not a binary file");
            return "";
        }

        List<String> result = new ArrayList<>();

        try(AssemblerReader reader = new AssemblerReader(fileName)) {
            while (reader.hasNext()) {
                final int bin = Integer.parseInt(reader.getInstruction(), 2);

                result.add( "#$" + Integer.toHexString(bin));
            }
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Unable to open file " + fileName);
        }
        return String.join(", ", result);
    }
}

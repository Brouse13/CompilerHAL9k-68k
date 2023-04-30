package es.brouse.utils;

import es.brouse.instructions.Instruction;
import es.brouse.instructions.InstructionParser;
import es.brouse.io.AssemblerReader;
import es.brouse.io.AssemblerWriter;

import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BinCompiler {
    private final String fileName;
    private final PrintStream stream;

    public BinCompiler(String fileName, PrintStream stream) {
        this.fileName = fileName;
        this.stream = stream;
    }

    /**
     * Compile the file into a .bin file with the machine code
     * of all the given instructions.
     */
    public void compile() {
        if (fileName.endsWith(".dat")) {
            stream.println("File is already a bin file");
            return;
        }

        final InstructionParser parser = new InstructionParser();
        final String rawName = fileName.split("\\.")[0];

        try(AssemblerReader reader = new AssemblerReader(fileName);
            AssemblerWriter writer = new AssemblerWriter(rawName + ".dat")) {

            while (reader.hasNext()) {
                final Instruction instruction = parser.parseInstruction(reader.getInstruction());
                if (instruction != null) writer.write(instruction);
            }

        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Error while opening file " + fileName);
        }
    }
}

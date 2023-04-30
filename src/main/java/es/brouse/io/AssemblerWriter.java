package es.brouse.io;

import es.brouse.instructions.Instruction;

import java.io.*;

public class AssemblerWriter implements Closeable {
    private final String fileName;
    private final BufferedWriter writer;

    /**
     * Class main constructor used to create a new {@link AssemblerWriter}
     * instance.
     *
     * @param fileName name of the file
     * @throws IOException if any exception happen with the file
     */
    public AssemblerWriter(String fileName) throws IOException {
        this.fileName = fileName;
        this.writer = new BufferedWriter(new FileWriter(fileName));
    }

    /**
     * Write a new binary machine code instruction.
     *
     * @param instruction instruction to write
     * @return if the object aws stored
     */
    public boolean write(Instruction instruction) {
        ensureOpen();

        try {
            writer.write(instruction.compile());
            writer.write("\n");
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Make sure the writer is open.
     * @throws RuntimeException If the writer is not open.
     */
    public void ensureOpen() {
        if (writer == null) throw new RuntimeException("Out stream closed");
    }

    @Override
    public void close() {
        ensureOpen();
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("Unable to close file" + fileName);
        }
    }
}

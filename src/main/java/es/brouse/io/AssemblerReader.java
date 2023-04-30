package es.brouse.io;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AssemblerReader implements Closeable {

    private final BufferedReader reader;
    private String lastInstruction;

    /**
     * Class main constructor used to create a new {@link AssemblerReader}
     * instance.
     *
     * @param fileName name of the file
     * @throws IOException if any exception happen with the file
     */
    public AssemblerReader(String fileName) throws IOException {
        this.reader = new BufferedReader(new FileReader(fileName));
    }

    /**
     * Move the pointer to the next index and store the
     * pointer.
     * This pointer can be retrieved by using {@link #getInstruction()}.
     *
     * @return if there's a line to read
     */
    public boolean hasNext() {
        ensureOpen();

        try {
            lastInstruction = reader.readLine();
        } catch (IOException e) {
            return false;
        }

        return lastInstruction != null;
    }

    /**
     * Get the next instruction to read.
     *
     * @return the next line read
     */
    public String getInstruction() {
        return lastInstruction;
    }

    /**
     * Make sure the writer is open.
     * @throws RuntimeException If the writer is not open.
     */
    public void ensureOpen() {
        if (reader == null) {
            throw new RuntimeException("Out stream closed");
        }
    }

    @Override
    public void close() {
        ensureOpen();
        try {
            reader.close();
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Unable to close fle");
        }
    }
}

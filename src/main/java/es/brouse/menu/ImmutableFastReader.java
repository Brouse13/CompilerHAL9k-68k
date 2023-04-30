package es.brouse.menu;

import es.brouse.io.FastReader;

public class ImmutableFastReader extends FastReader {
    /**
     * Instance of the reader which can't be closed.
     * Used to pass a reader instance by parameter.
     *
     * @param reader reader to instance
     */
    public ImmutableFastReader(FastReader reader) {
        super(reader);
    }

    @Override
    public void close() {
        throw new IllegalStateException("Immutable copies cannot be closed");
    }
}

package es.brouse.io;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Clase para poder optimizar la lectura desde la entrada estándar.
 * Se usa esta clase en vez de {@link java.util.Scanner} ya que esta
 * es mucho más rápida y requiere de la instantiación de menos objetos.
 */
public class FastReader implements Closeable {
    /*----------PRIVATE---------*/
    private final BufferedReader reader;
    private StringTokenizer tokenizer;

    /**
     * Constructor principal de la clase {@link FastReader} que crea
     * una nueva instancia del lector rápido.
     */
    public FastReader() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Constructor privado de la clase {@link FastReader} usado para crear
     * instancias con un reader predefinido
     */
    protected FastReader(FastReader reader) {
        this.reader = reader.reader;
        this.tokenizer = reader.tokenizer;
    }

    /**
     * Devuelve el siguiente {@link String} contenido en el buffer de entrada
     * haciendo uso de un {@link StringTokenizer}.
     *
     * @return el siguiente String de la entrada estándar
     */
    public String next() {
        //Leer el siguiente string y meterlo en el tokenizer
        while (tokenizer == null || !tokenizer.hasMoreElements()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        //Devolver el token leído
        return tokenizer.nextToken();
    }

    /**
     * Lee el siguiente {@link Integer} de la entrada estándar.
     *
     * @return el siguiente {@link Integer} leído
     */
    public Integer nextInt() {
        try {
            return Integer.parseInt(next());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Lee el siguiente {@link Double} de la entrada estándar.
     *
     * @return el siguiente {@link Double} leído
     */
    public Double nextDouble() {
        try {
            return Double.parseDouble(next());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Lee el siguiente {@link Long} de la entrada estándar.
     *
     * @return el siguiente {@link Long} leído
     */
    public Long nextLong() {
        try {
            return Long.parseLong(next());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Lee la siguiente línea de la entrada estándar.
     *
     * @return la siguiente línea leída
     */
    public String nextLine() {
        String line = "";
        try {
            if (tokenizer != null && tokenizer.hasMoreTokens()) {
                line = tokenizer.nextToken("\n");
            } else {
                line = reader.readLine();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return line;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        try {
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
package es.brouse;

import es.brouse.io.FastReader;
import es.brouse.menu.Menu;

public class Compiler {
    public static void main(String[] args) {
        //Start reader
        final FastReader reader = new FastReader();
        Menu.setReader(reader);

        //Start the menu render
        new CompilerMenu().renderStart();

        //CLose reader
        reader.close();
    }
}
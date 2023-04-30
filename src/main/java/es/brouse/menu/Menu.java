package es.brouse.menu;

import es.brouse.io.FastReader;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase usada para mostrar menus por la salida definida en el constructor.
 * Estos menus tienen la ventaja de evitar al programador definir la lógica
 * utilizada para seleccionar cada opción.
 * <br/>
 * Ejemplo:
 * Menu menu = new Menu(System.out, "Nombre del menu");
 * menu.addOption(new MenuOption("Acción 1", () -> System.out.println("Mensaje 1"));
 * menu.addOption(new MenuOption("Salir", () -> menu.close(false));
 * <br/>
 * //Inicia el menú
 * menu.renderStart();
 */
public class Menu {

    /*----------STATIC----------*/
    private static FastReader reader;

    /*----------PROTECTED-------*/
    protected final String name;
    protected final PrintStream stream;
    protected final List<MenuOption> options = new ArrayList<>();
    protected Menu parent = null;

    /*----------PRIVATE---------*/
    private boolean active = true;
    private boolean render = false;

    /**
     * Constructor de la clase {@link Menu} usado para crear nuevos
     * menús y mostrarlos en un {@link PrintStream}.
     *
     * @param stream lugar donde mostrar el menú
     * @param name nombre del menú
     */
    public Menu(PrintStream stream, String name) {
        this.stream = stream;
        this.name = name;
    }

    /**
     * Constructor de la clase {@link Menu} usado para crear nuevos
     * menús y mostrarlos en un {@link PrintStream}.
     * Por defecto se usará {@link System#out}
     *
     * @param name nombre del menú
     */
    public Menu(String name) {
        this.stream = System.out;
        this.name = name;
    }

    /**
     * Establecer el lector por defecto para todas las instancias de {@link Menu}.
     * Este comportamiento sigue el patrón de diseño Singleton (Una sola instancia para
     * todas las clases)
     *
     * @param reader reader a asociar
     */
    public static void setReader(FastReader reader) {
        if (Menu.reader != null)
            throw new IllegalStateException("FastReader instance has already been initialized");
        Menu.reader = reader;
    }

    public FastReader getReader() {
        return new ImmutableFastReader(reader);
    }

    /**
     * Añadir una nueva acción al menú.
     *
     * @param option ación a añadir
     */
    public void addOption(final MenuOption option) {
        options.add(option);
    }

    /**
     * Establece el padre del menú para una vez cerrada poder retroceder
     * al menú anterior.
     *
     * @param menu menú padre
     */
    public void setParent(Menu menu) {
        this.parent = menu;
    }

    /**
     * Inicia el renderizado del menú. Una vez llamado este método la entrada definida
     * en {@link Menu#stream} quedará bloqueada y todos aquello que se le escriba será
     * procesado por el meú actual.
     * Esto es muy útil para evitar manejar las opciones con switches.
     */
    public void renderStart() {
        //If the renderer has been started return function
        if (render) return;

        //Set started rendering to true
        render = true;

        //Logic to handle inputs for the menu
        do {
            //Render a menu and ask for the input
            render();
            Integer option = reader.nextInt();

            if (option == null) {
                stream.println("Número no válido \n");
                continue;
            }

            //If number is valid we execute action (N - 1), if it exists
            try {
                options.get(option - 1).getAction().execute();
            } catch (IndexOutOfBoundsException e) {
                stream.println("Operación inválida");
            }
        } while (active);
    }

    /**
     * Método interno usado para renderizar el menú y mostrar todas las opciones
     */
    protected void render() {
        //Print the name of the menu as a first step
        stream.println(name);

        int index = 1;

        //Print all the menu options with format "index) name"
        for (MenuOption option : options) {
            stream.printf("\t%d) %s\n", index, option.getName());
            index++;
        }
        stream.print("Elige la opción: ");
    }

    /**
     * Cierra el menú actual y en el caso de que {@param openParent} fuera true
     * abriría el menú establecido como padre.
     *
     * @param openParent si se ha de abrir el menú padre
     */
    public void close(boolean openParent) {
        active = false;

        /* If the menu has a parent associated, set this as the active menu
            and then render the parent menu */
        if (parent != null && !openParent) {
            parent.active = true;
            parent.render();
        }
    }
}


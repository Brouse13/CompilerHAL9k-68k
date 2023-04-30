package es.brouse.menu;


/**
 * Clase que representa cada una de las acciones que van a
 * ejecutarse dentro de un {@link Menu}. Estas están identificadas
 * con un {@link String} que es el que se mostrará en el menú.
 */
public class MenuOption {
    /*--------PRIVATE------*/
    private final String name;
    private final Action action;

    /**
     * Constructor principal de la clase {@link MenuOption} usado para crear
     * instancias de las opciones de los menús.
     *
     * @param name nombre en el menú
     * @param action ación que se ejecutará en el menú
     */
    public MenuOption(String name, Action action) {
        this.name = name;
        this.action = action;
    }

    /**
     * Devuelve el nombre del menú.
     *
     * @return el nombre del menú
     */
    public String getName() {
        return name;
    }

    /**
     * Devuelve la acción que se ejecutará una vez seleccionado esta
     * opción dentro del menú
     *
     * @return la acción a ejecutar
     */
    public Action getAction() {
        return action;
    }
}

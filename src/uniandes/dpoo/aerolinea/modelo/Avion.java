package uniandes.dpoo.aerolinea.modelo;

/**
 * Representa un avión en el sistema de la aerolínea.
 * Según el UML, contiene el nombre y la capacidad de pasajeros.
 */
public class Avion {

    // --- Atributos ---
    // El signo '-' en el UML indica que son privados
    private String nombre;
    private int capacidad;

    // --- Constructor ---
    /**
     * Crea un nuevo avión con su nombre e identificación de capacidad.
     * @param nombre El nombre del avión.
     * @param capacidad La capacidad de pasajeros.
     */
    public Avion(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    // --- Métodos ---
    // El signo '+' en el UML indica que son públicos

    /**
     * Retorna el nombre del avión.
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna la capacidad total de pasajeros del avión.
     * @return capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }
}

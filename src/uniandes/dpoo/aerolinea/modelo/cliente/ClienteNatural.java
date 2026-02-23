package uniandes.dpoo.aerolinea.modelo.cliente;

/**
 * Esta clase se usa para representar a los clientes que son personas naturales.
 */
public class ClienteNatural extends Cliente {

    // --- Constantes ---
    
    /**
     * Constante que identifica el tipo de cliente.
     */
    public static final String NATURAL = "Natural";

    // --- Atributos ---
    
    /**
     * El nombre del cliente.
     */
    private String nombre;

    // --- Constructor ---

    /**
     * Crea un nuevo cliente natural con el nombre dado.
     * @param nombre El nombre del cliente.
     */
    public ClienteNatural(String nombre) {
        super(); // Llama al constructor de Cliente para inicializar la lista de tiquetes
        this.nombre = nombre;
    }

    // --- Métodos Sobrescritos ---

    /**
     * Retorna el tipo de cliente, que siempre es "Natural".
     */
    @Override
    public String getTipoCliente() {
        return NATURAL;
    }

    /**
     * Retorna el identificador del cliente. 
     * En el caso de personas naturales, se usa su nombre como identificador único.
     */
    @Override
    public String getIdentificador() {
        return nombre;
    }
}
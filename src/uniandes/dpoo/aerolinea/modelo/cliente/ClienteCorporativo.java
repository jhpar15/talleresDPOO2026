package uniandes.dpoo.aerolinea.modelo.cliente;

import org.json.JSONObject;

/**
 * Esta clase se usa para representar a los clientes de la aerolínea que son empresas.
 * Hereda de la clase Cliente para integrarse con el sistema de tiquetes y vuelos.
 */
public class ClienteCorporativo extends Cliente
{
    // --- Constantes ---

    /**
     * Identificador del tipo de cliente corporativo.
     */
    public static final String CORPORATIVO = "Corporativo";

    /**
     * Constante para representar una empresa grande.
     */
    public static final int GRANDE = 1;

    /**
     * Constante para representar una empresa mediana.
     */
    public static final int MEDIANA = 2;

    /**
     * Constante para representar una empresa pequeña.
     */
    public static final int PEQUENA = 3;

    // --- Atributos ---

    /**
     * El nombre de la empresa.
     */
    private String nombreEmpresa;

    /**
     * El tamaño de la empresa (1, 2 o 3).
     */
    private int tamanoEmpresa;

    // --- Constructor ---

    /**
     * Crea un nuevo cliente corporativo con el nombre y tamaño de empresa especificados.
     * @param nombreEmpresa El nombre de la empresa.
     * @param tamano El tamaño de la empresa (GRANDE, MEDIANA o PEQUENA).
     */
    public ClienteCorporativo(String nombreEmpresa, int tamano) {
        super(); // Inicializa la lista de tiquetes en la clase padre Cliente
        this.nombreEmpresa = nombreEmpresa;
        this.tamanoEmpresa = tamano;
    }

    // --- Métodos Requeridos por el UML ---

    /**
     * Retorna el nombre de la empresa.
     * @return El nombre de la empresa.
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * Retorna el tamaño de la empresa.
     * @return El tamaño de la empresa (1, 2 o 3).
     */
    public int getTamanoEmpresa() {
        return tamanoEmpresa;
    }

    /**
     * Implementación del método abstracto de Cliente para identificar el tipo.
     * @return La constante CORPORATIVO.
     */
    @Override
    public String getTipoCliente() {
        return CORPORATIVO;
    }

    /**
     * Implementación del método abstracto de Cliente para el identificador único.
     * @return El nombre de la empresa como identificador.
     */
    @Override
    public String getIdentificador() {
        return nombreEmpresa;
    }

    // --- Métodos de Persistencia JSON ---

    /**
     * Crea un nuevo objeto de tipo a partir de un objeto JSON.
     * * El objeto JSON debe tener dos atributos: nombreEmpresa (una cadena) y tamanoEmpresa (un número).
     * @param cliente El objeto JSON que contiene la información
     * @return El nuevo objeto inicializado con la información
     */
    public static ClienteCorporativo cargarDesdeJSON( JSONObject cliente )
    {
        String nombreEmpresa = cliente.getString( "nombreEmpresa" );
        int tam = cliente.getInt( "tamanoEmpresa" );
        return new ClienteCorporativo( nombreEmpresa, tam );
    }

    /**
     * Salva este objeto de tipo ClienteCorporativo dentro de un objeto JSONObject para que ese objeto se almacene en un archivo
     * @return El objeto JSON con toda la información del cliente corporativo
     */
    public JSONObject salvarEnJSON( )
    {
        JSONObject jobject = new JSONObject( );
        jobject.put( "nombreEmpresa", this.nombreEmpresa );
        jobject.put( "tamanoEmpresa", this.tamanoEmpresa );
        jobject.put( "tipo", CORPORATIVO );
        return jobject;
    }
}
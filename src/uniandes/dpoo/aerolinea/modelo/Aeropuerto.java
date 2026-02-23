package uniandes.dpoo.aerolinea.modelo;

import java.util.HashSet;
import java.util.Set;

import uniandes.dpoo.aerolinea.exceptions.AeropuertoDuplicadoException;

/**
 * Esta clase encapsula la información sobre los aeropuertos e implementa algunas operaciones relacionadas con la ubicación geográfica de los aeropuertos.
 */
public class Aeropuerto
{
    // --- Atributos de Instancia ---
    
    private String nombre;
    private String codigo;
    private String nombreCiudad;
    private double latitud;
    private double longitud;

    // --- Atributos Estáticos ---

    /**
     * Conjunto de códigos de aeropuertos ya creados para garantizar unicidad.
     */
    private static Set<String> codigos = new HashSet<>();

    /**
     * Radio de la tierra en kilómetros para el cálculo de distancias.
     */
    public static final int RADIO_TERRESTRE = 6371;

    // --- Constructor ---

    /**
     * Crea un nuevo aeropuerto.
     * @throws AeropuertoDuplicadoException Si ya existe un aeropuerto con el mismo código.
     */
    public Aeropuerto(String nombre, String codigo, String nombreCiudad, double latitud, double longitud) throws AeropuertoDuplicadoException 
    {
        if (codigos.contains(codigo)) 
        {
            throw new AeropuertoDuplicadoException(codigo);
        }
        
        this.nombre = nombre;
        this.codigo = codigo;
        this.nombreCiudad = nombreCiudad;
        this.latitud = latitud;
        this.longitud = longitud;
        
        // Registrar el código para evitar duplicados en el futuro
        codigos.add(codigo);
    }

    // --- Getters ---

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    // --- Métodos Estáticos ---

    /**
     * Calcula la distancia aproximada entre dos aeropuertos usando coordenadas geográficas.
     */
    public static int calcularDistancia( Aeropuerto aeropuerto1, Aeropuerto aeropuerto2 )
    {
        // Convertir los ángulos a radianes
        double latAeropuerto1 = Math.toRadians( aeropuerto1.getLatitud( ) );
        double lonAeropuerto1 = Math.toRadians( aeropuerto1.getLongitud( ) );
        double latAeropuerto2 = Math.toRadians( aeropuerto2.getLatitud( ) );
        double lonAeropuerto2 = Math.toRadians( aeropuerto2.getLongitud( ) );

        // Calcular la distancia debido a la diferencia de latitud y de longitud
        double deltaX = ( lonAeropuerto2 - lonAeropuerto1 ) * Math.cos( ( latAeropuerto1 + latAeropuerto2 ) / 2 );
        double deltaY = ( latAeropuerto2 - latAeropuerto1 );

        // Calcular la distancia real en kilómetros
        double distancia = Math.sqrt( deltaX * deltaX + deltaY * deltaY ) * RADIO_TERRESTRE;

        return ( int )Math.round( distancia );
    }
}

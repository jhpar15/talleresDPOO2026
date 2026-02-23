package uniandes.dpoo.aerolinea.persistencia;

/**
 * Esta clase centraliza el acceso a los implementadores de la persistencia.
 */
public class CentralPersistencia
{
    // --- Constantes ---

    /**
     * Cadena que identifica al tipo de persistencia en formato JSON.
     */
    public static final String JSON = "JSON";

    /**
     * Cadena que identifica al tipo de persistencia en formato de texto plano.
     */
    public static final String PLAIN = "PLAIN";

    // --- Métodos Estáticos ---

    /**
     * Retorna un objeto encargado de la persistencia de la aerolínea según el tipo.
     * @param tipoArchivo El tipo de persistencia (JSON o PLAIN).
     * @return El objeto que implementa la interfaz IPersistenciaAerolinea.
     * @throws TipoInvalidoException Si el tipo indicado no es válido.
     */
    public static IPersistenciaAerolinea getPersistenciaAerolinea( String tipoArchivo ) throws TipoInvalidoException
    {
        if ( tipoArchivo.equals( JSON ) )
        {
            return new PersistenciaAerolineaJson( );
        }
        else if ( tipoArchivo.equals( PLAIN ) )
        {
            // Nota: Si el taller no requiere implementar Plain, este podría retornar null o lanzar excepción
            return  null; 
        }
        else
        {
            throw new TipoInvalidoException( tipoArchivo );
        }
    }

    /**
     * Retorna un objeto encargado de la persistencia de los tiquetes según el tipo.
     * @param tipoArchivo El tipo de persistencia (JSON o PLAIN).
     * @return El objeto que implementa la interfaz IPersistenciaTiquetes.
     * @throws TipoInvalidoException Si el tipo indicado no es válido.
     */
    public static IPersistenciaTiquetes getPersistenciaTiquetes( String tipoArchivo ) throws TipoInvalidoException
    {
        if ( tipoArchivo.equals( JSON ) )
        {
            return new PersistenciaTiquetesJson( );
        }
        else if ( tipoArchivo.equals( PLAIN ) )
        {
            return null;
        }
        else
        {
            throw new TipoInvalidoException( tipoArchivo );
        }
    }
}

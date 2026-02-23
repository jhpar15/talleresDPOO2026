package uniandes.dpoo.aerolinea.persistencia;

import java.io.IOException;
import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;

/**
 * Interfaz que define los métodos necesarios para persistir la información de una aerolínea.
 * Cualquier implementador (JSON, Plain, etc.) debe cumplir con estos métodos.
 */
public interface IPersistenciaAerolinea
{
    /**
     * Carga la información de la aerolínea a partir de un archivo.
     * @param archivo El nombre o ruta del archivo de donde se leerá la información.
     * @param aerolinea La instancia de la aerolínea donde se cargará la información.
     * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
     * @throws InformacionInconsistenteException Si los datos en el archivo no son válidos o son inconsistentes.
     */
    public void cargarAerolinea( String archivo, Aerolinea aerolinea ) throws IOException, InformacionInconsistenteException;

    /**
     * Salva la información de la aerolínea en un archivo.
     * @param archivo El nombre o ruta del archivo donde se guardará la información.
     * @param aerolinea La instancia de la aerolínea cuya información se desea salvar.
     * @throws IOException Si ocurre un error al intentar escribir en el archivo.
     */
    public void salvarAerolinea( String archivo, Aerolinea aerolinea ) throws IOException;
}
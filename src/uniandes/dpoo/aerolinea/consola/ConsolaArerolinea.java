package uniandes.dpoo.aerolinea.consola;

import java.io.IOException;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.persistencia.CentralPersistencia;
import uniandes.dpoo.aerolinea.persistencia.TipoInvalidoException;

/**
 * Consola de la aplicación para probar la carga de datos y el funcionamiento básico.
 */
public class ConsolaArerolinea extends ConsolaBasica
{
    private Aerolinea unaAerolinea;


    public void correrAplicacion( )
    {
        try
        {
            System.out.println("Iniciando aplicación de Aerolínea...");
            unaAerolinea = new Aerolinea( );

            // 1. CARGAR INFRAESTRUCTURA (Aviones, Rutas y Vuelos)
            System.out.println("Cargando infraestructura desde aerolinea.json...");
            unaAerolinea.cargarAerolinea( "./datos/aerolinea.json", CentralPersistencia.JSON );
            
            // 2. CARGAR TIQUETES Y CLIENTES

            System.out.println("Cargando tiquetes y clientes desde tiquetes.json...");
            unaAerolinea.cargarTiquetes( "./datos/tiquetes.json", CentralPersistencia.JSON );

            // 3. VERIFICACIÓN DE CARGA 
            System.out.println("\n--- Resumen de Carga ---");
            System.out.println("Rutas cargadas: " + unaAerolinea.getRutas().size());
            System.out.println("Vuelos programados: " + unaAerolinea.getVuelos().size());
            System.out.println("Clientes registrados: " + unaAerolinea.getClientes().size());
            System.out.println("Tiquetes totales: " + unaAerolinea.getTiquetes().size());
            System.out.println("------------------------\n");
            
            System.out.println("La aplicación se ejecutó correctamente.");

        }
        catch( TipoInvalidoException e )
        {
            System.err.println("Error: El tipo de persistencia especificado no es válido.");
            e.printStackTrace( );
        }
        catch( IOException e )
        {
            System.err.println("Error de entrada/salida: Verifica que los archivos existan en la carpeta ./datos/");
            e.printStackTrace();
        }
        catch( InformacionInconsistenteException e )
        {
            System.err.println("Error de consistencia de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Punto de entrada principal.
     */
    public static void main( String[] args )
    {
        ConsolaArerolinea ca = new ConsolaArerolinea( );
        ca.correrAplicacion( );
    }
}


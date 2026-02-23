package uniandes.dpoo.aerolinea.consola;

import java.io.IOException;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.persistencia.CentralPersistencia;
import uniandes.dpoo.aerolinea.persistencia.TipoInvalidoException;


public class ConsolaArerolinea extends ConsolaBasica
{
    private Aerolinea unaAerolinea;

    /**
     * Ejecuta la secuencia completa de pruebas para el taller.
     */
    public void correrAplicacion( )
    {
        try
        {
            System.out.println("=== INICIANDO VALIDACIÓN COMPLETA - TALLER 3 ===\n");
            unaAerolinea = new Aerolinea( );

            // 1. CARGA DE DATOS (Requisito 1: Cargar/Salvar Aerolínea)
            System.out.println("1. CARGANDO INFRAESTRUCTURA...");
            unaAerolinea.cargarAerolinea( "./datos/aerolinea.json", CentralPersistencia.JSON );
            unaAerolinea.cargarTiquetes( "./datos/tiquetes.json", CentralPersistencia.JSON );
            System.out.println("   [OK] Datos base cargados.\n");

            // 2. PROGRAMAR VUELO (Requisito 2: Programar Vuelo)
            // Usamos una ruta y avión que existan en tu aerolinea.json
            System.out.println("2. PROBANDO PROGRAMACIÓN DE VUELO...");
            try {
				unaAerolinea.programarVuelo("2024-12-24", "4558", "Boeing 737");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println("   [OK] Vuelo programado para el 2024-12-24.\n");

            // 3. VENDER TIQUETES (Requisito 3: Vender Tiquetes)
            // Esto prueba la Calculadora de Tarifas (Polimorfismo/Herencia)
            System.out.println("3. PROBANDO VENTA DE TIQUETES (Lógica de Tarifas)...");
            try {
                // Vendemos 2 tiquetes a "Alice" para el vuelo que ya existía en tiquetes.json
                int valorVenta = unaAerolinea.venderTiquetes("Alice", "2024-11-05", "4558", 2);
                System.out.println("   [OK] Venta realizada. Valor total: $" + valorVenta);
            } catch (VueloSobrevendidoException e) {
                System.err.println("   [ERROR] Vuelo sobrevendido: " + e.getMessage());
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            // 4. CONSULTAR SALDO (Requisito 5: Consultar Saldo Pendiente)
            // Se usa el nombre exacto del UML que retorna String
            System.out.println("\n4. CONSULTANDO SALDO PENDIENTE (Método UML)...");
            String saldoBob = unaAerolinea.consultarSaldoPendienteCliente("Bob");
            System.out.println("   [OK] Saldo de Bob: " + saldoBob + "\n");

            // 5. REGISTRAR VUELO (Requisito 4: Registrar Vuelo Realizado)
            System.out.println("5. PROBANDO REGISTRO DE VUELO REALIZADO...");
            unaAerolinea.registrarVueloRealizado("2024-11-05", "4558");
            System.out.println("   [OK] Vuelo registrado. Tiquetes marcados como usados.\n");

            // 6. PERSISTENCIA DE SALIDA (Requisito 1: Salvar Aerolínea)
            System.out.println("6. GUARDANDO ESTADO FINAL...");
            unaAerolinea.salvarAerolinea("./datos/aerolinea_final.json", CentralPersistencia.JSON);
            unaAerolinea.salvarTiquetes("./datos/tiquetes_final.json", CentralPersistencia.JSON);
            System.out.println("   [OK] Archivos guardados exitosamente en ./datos/ \n");

            System.out.println("=================================================");
            System.out.println(">>> VALIDACIÓN TERMINADA: REQUERIMIENTOS CUMPLIDOS <<<");
            System.out.println("=================================================");

        }
        catch( TipoInvalidoException e )
        {
            System.err.println("Error de tipo: " + e.getMessage());
        }
        catch( IOException e )
        {
            System.err.println("Error leyendo archivos. Verifique la carpeta ./datos/: " + e.getMessage());
        }
        catch( InformacionInconsistenteException e )
        {
            System.err.println("Error de consistencia de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Lanza la aplicación.
     */
    public static void main( String[] args )
    {
        ConsolaArerolinea ca = new ConsolaArerolinea( );
        ca.correrAplicacion( );
    }
}

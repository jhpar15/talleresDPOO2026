package uniandes.dpoo.aerolinea.tiquetes;

import java.util.HashSet;
import java.util.Set;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

/**
 * Esta clase se encarga de generar tiquetes únicos y de llevar un registro de los tiquetes creados.
 */
public class GeneradorTiquetes {

    // --- Atributos ---

    /**
     * Conjunto de códigos de tiquetes ya generados para garantizar unicidad.
     * Según UML: -codigos = new HashSet<String>(): Set<String>
     */
    private static Set<String> codigos = new HashSet<String>();

    /**
     * Contador interno para generar números secuenciales.
     */
    private static int contador = 0;

    // --- Métodos ---

    /**
     * Construye un nuevo tiquete con un código único, lo registra y lo retorna.
     * Según UML: +generarTiquete(Vuelo, Cliente, int): Tiquete
     */
    public static Tiquete generarTiquete(Vuelo vuelo, Cliente cliente, int tarifa) {
        String codigo;
        
        // Buscamos un código que no exista en el Set
        do {
            contador++;
            codigo = String.valueOf(contador);
        } while (validarTiquete(codigo));

        Tiquete nuevoTiquete = new Tiquete(codigo, vuelo, cliente, tarifa);
        
        // Registrar el código en el Set para que no se repita
        codigos.add(codigo);
        
        return nuevoTiquete;
    }

    /**
     * Registra un tiquete existente en el sistema de control de códigos.
     * Según UML: +registrarTiquete(Tiquete): void
     */
    public static void registrarTiquete(Tiquete unTiquete) {
        codigos.add(unTiquete.getCodigo());
    }

    /**
     * Valida si un código de tiquete ya existe.
     * Según UML: +validarTiquete(String): boolean
     */
    public static boolean validarTiquete(String codigoTiquete) {
        return codigos.contains(codigoTiquete);
    }
}

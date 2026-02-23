package uniandes.dpoo.aerolinea.modelo.cliente;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

import java.util.ArrayList;
import java.util.List;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

/**
 * Clase abstracta que representa un cliente de la aerolínea.
 * Centraliza la gestión de tiquetes comprados.
 */
public abstract class Cliente {

    // --- Atributos ---
    
    /**
     * Lista de tiquetes asociados al cliente.
     */
    protected List<Tiquete> tiquetes;

    // --- Constructor ---

    /**
     * Inicializa la lista de tiquetes del cliente.
     */
    public Cliente() {
        this.tiquetes = new ArrayList<>();
    }

    // --- Métodos Abstractos ---

    /**
     * Retorna el tipo de cliente (Corporativo o Natural).
     * @return Cadena con el tipo de cliente.
     */
    public abstract String getTipoCliente();

    /**
     * Retorna el identificador único del cliente.
     * @return El identificador del cliente.
     */
    public abstract String getIdentificador();

    // --- Métodos de Instancia ---

    /**
     * Agrega un tiquete a la colección del cliente.
     * @param tiquete El tiquete a agregar.
     */
    public void agregarTiquete(Tiquete tiquete) {
        this.tiquetes.add(tiquete);
    }

    /**
     * Calcula la suma de los valores de todos los tiquetes del cliente.
     * @return Valor total de los tiquetes.
     */
    public int calcularValorTotalTiquetes() {
        int total = 0;
        for (Tiquete t : tiquetes) {
            total += t.getTarifa();
        }
        return total;
    }

    /**
     * Marca como usados todos los tiquetes del cliente que correspondan al vuelo dado.
     * @param vuelo El vuelo realizado.
     */
    public void usarTiquetes(Vuelo vuelo) {
        for (Tiquete t : tiquetes) {
            // Se asume que Tiquete tiene un método getVuelo() y marcarComoUsado() según el UML
            if (t.getVuelo().equals(vuelo)) {
                t.marcarComoUsado();
            }
        }
    }
}
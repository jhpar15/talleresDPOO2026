package uniandes.dpoo.aerolinea.tiquetes;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

/**
 * Representa un tiquete comprado por un cliente para un vuelo específico.
 */
public class Tiquete {

    // --- Atributos ---
    
    /**
     * El código único del tiquete. 
     */
    private String codigo;

    /**
     * La tarifa que se le cobró al cliente por este tiquete. 
     */
    private int tarifa;

    /**
     * Indica si el tiquete ya fue utilizado para realizar un vuelo. 
     */
    private boolean usado;

    /**
     * El vuelo para el cual se emitió el tiquete. 
     */
    private Vuelo vuelo;

    /**
     * El cliente que compró y es dueño del tiquete. 
     */
    private Cliente cliente;

    // --- Constructor ---

    /**
     * Construye un nuevo tiquete con la información del vuelo y el cliente. 
     * @param codigo Identificador único del tiquete.
     * @param vuelo Vuelo asociado.
     * @param clienteComprador Cliente que adquiere el tiquete.
     * @param tarifa Valor final cobrado.
     */
    public Tiquete(String codigo, Vuelo vuelo, Cliente clienteComprador, int tarifa) {
        this.codigo = codigo;
        this.vuelo = vuelo;
        this.cliente = clienteComprador;
        this.tarifa = tarifa;
        this.usado = false; // Por defecto, un tiquete nuevo no ha sido usado.
    }

    // --- Métodos ---

    /**
     * Retorna el cliente asociado al tiquete. 
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Retorna el vuelo asociado al tiquete. 
     */
    public Vuelo getVuelo() {
        return vuelo;
    }

    /**
     * Retorna el código único del tiquete. 
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Retorna la tarifa pagada por el tiquete. 
     */
    public int getTarifa() {
        return tarifa;
    }

    /**
     * Marca el tiquete como usado. 
     */
    public void marcarComoUsado() {
        this.usado = true;
    }

    /**
     * Indica si el tiquete ya fue usado o no. 
     */
    public boolean esUsado() {
        return usado;
    }
}
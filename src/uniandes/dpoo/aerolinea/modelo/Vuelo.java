package uniandes.dpoo.aerolinea.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

/**
 * Representa un vuelo programado por la aerolínea.
 */
public class Vuelo {

    // --- Atributos ---

    private String fecha;
    private Ruta ruta;
    private Avion avion;
    private Collection<Tiquete> tiquetes;

    // --- Constructor ---

    /**
     * Crea un nuevo vuelo con su ruta, fecha y avión asignado.
     */
    public Vuelo(Ruta ruta, String fecha, Avion avion) {
        this.ruta = ruta;
        this.fecha = fecha;
        this.avion = avion;
        this.tiquetes = new ArrayList<>(); // Inicializa la colección de tiquetes vendidos
    }

    // --- Getters ---

    public Ruta getRuta() {
        return ruta;
    }

    public String getFecha() {
        return fecha;
    }

    public Avion getAvion() {
        return avion;
    }

    public Collection<Tiquete> getTiquetes() {
        return tiquetes;
    }

    // --- Métodos de Lógica de Negocio ---

    /**
     * Vende una cantidad de tiquetes para este vuelo. 
     * Verifica la capacidad del avión y utiliza la calculadora para determinar el costo.
     * @return El valor total de los tiquetes vendidos.
     * @throws VueloSobrevendidoException Si no hay cupos suficientes.
     */
    public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) throws VueloSobrevendidoException {
        // 1. Verificar capacidad del avión
        int cuposDisponibles = avion.getCapacidad() - tiquetes.size();
        if (cantidad > cuposDisponibles) {
            throw new VueloSobrevendidoException(this);
        }

        // 2. Calcular tarifa unitaria
        int tarifaUnitaria = calculadora.calcularTarifa(this, cliente);
        int valorTotal = 0;

        // 3. Generar y asociar tiquetes
        for (int i = 0; i < cantidad; i++) {
            Tiquete nuevoTiquete = GeneradorTiquetes.generarTiquete(this, cliente, tarifaUnitaria);
            this.tiquetes.add(nuevoTiquete);
            cliente.agregarTiquete(nuevoTiquete);
            valorTotal += tarifaUnitaria;
        }

        return valorTotal;
    }

    /**
     * Compara si dos vuelos son iguales basándose en la ruta, fecha y avión.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vuelo other = (Vuelo) obj;
        return Objects.equals(fecha, other.fecha) && 
               Objects.equals(ruta, other.ruta) && 
               Objects.equals(avion, other.avion);
    }
}

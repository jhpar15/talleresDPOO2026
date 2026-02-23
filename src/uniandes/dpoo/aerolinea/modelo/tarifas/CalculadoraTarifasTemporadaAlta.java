package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

/**
 * Calcula las tarifas para la temporada alta.
 * En esta temporada, el costo por kilómetro es fijo y no se aplican descuentos.
 */
public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas {

    // --- Constantes ---

    /**
     * Costo fijo por kilómetro en temporada alta (independientemente del tipo de cliente).
     */
    protected static final int COSTO_POR_KM = 1000;

    // --- Métodos Sobrescritos ---

    /**
     * Calcula el costo base multiplicando la distancia por el costo de temporada alta.
     */
    @Override
    protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
        int distancia = calcularDistanciaViaje(vuelo.getRuta());
        return distancia * COSTO_POR_KM;
    }

    /**
     * En temporada alta, no se aplican descuentos a ningún cliente.
     * @return 0.0 (0% de descuento).
     */
    @Override
    protected double calcularPorcentajeDescuento(Cliente cliente) {
        return 0.0;
    }
}
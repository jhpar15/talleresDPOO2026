package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteNatural;

/**
 * Calcula las tarifas para la temporada baja.
 * Implementa las reglas de costos por kilómetro y descuentos para empresas.
 */
public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas {

    // --- Constantes de Costos ---
    
    protected static final int COSTO_POR_KM_NATURAL = 600;
    protected static final int COSTO_POR_KM_CORPORATIVO = 900;

    // --- Constantes de Descuentos Corporativos ---
    
    protected static final double DESCUENTO_GRANDES = 0.2;
    protected static final double DESCUENTO_MEDIANAS = 0.1;
    protected static final double DESCUENTO_PEQUENAS = 0.05;

    // --- Métodos Sobrescritos ---

    /**
     * Calcula el costo base multiplicando la distancia del vuelo por el costo por kilómetro.
     * El costo por kilómetro depende de si el cliente es Natural o Corporativo.
     */
    @Override
    protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
        int distancia = calcularDistanciaViaje(vuelo.getRuta());
        int costoBase = 0;

        if (cliente instanceof ClienteNatural) {
            costoBase = distancia * COSTO_POR_KM_NATURAL;
        } else if (cliente instanceof ClienteCorporativo) {
            costoBase = distancia * COSTO_POR_KM_CORPORATIVO;
        }

        return costoBase;
    }

    /**
     * Determina el porcentaje de descuento.
     * Los clientes naturales no tienen descuento.
     * Los corporativos tienen descuento según su tamaño (Grande, Mediana, Pequeña).
     */
    @Override
    protected double calcularPorcentajeDescuento(Cliente cliente) {
        double descuento = 0.0;

        if (cliente instanceof ClienteCorporativo) {
            ClienteCorporativo corp = (ClienteCorporativo) cliente;
            int tamano = corp.getTamanoEmpresa();

            if (tamano == ClienteCorporativo.GRANDE) {
                descuento = DESCUENTO_GRANDES;
            } else if (tamano == ClienteCorporativo.MEDIANA) {
                descuento = DESCUENTO_MEDIANAS;
            } else if (tamano == ClienteCorporativo.PEQUENA) {
                descuento = DESCUENTO_PEQUENAS;
            }
        }
        
        // Los clientes naturales entran aquí con 0.0 de descuento
        return descuento;
    }
}
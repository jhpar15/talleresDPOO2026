package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

/**
 * Clase abstracta que define la estructura para calcular las tarifas de los tiquetes.
 */
public abstract class CalculadoraTarifas {

    // --- Constantes ---
    
    /**
     * El porcentaje de impuestos que se aplica sobre el costo base (28%).
     */
    public static final double IMPUESTO = 0.28;

    // --- Métodos Abstractos ---

    /**
     * Calcula el costo base del tiquete antes de aplicar descuentos o impuestos.
     */
    protected abstract int calcularCostoBase(Vuelo vuelo, Cliente cliente);

    /**
     * Calcula el porcentaje de descuento que se le aplica a un cliente.
     */
    protected abstract double calcularPorcentajeDescuento(Cliente cliente);

    // --- Métodos de Instancia ---

    /**
     * Calcula la tarifa final que debe pagar un cliente por un vuelo.
     * Fórmula: (CostoBase * (1 - Descuento)) + ValorImpuestos
     */
    public int calcularTarifa(Vuelo vuelo, Cliente cliente) {
        int costoBase = calcularCostoBase(vuelo, cliente);
        double descuento = calcularPorcentajeDescuento(cliente);
        
        int costoConDescuento = (int) (costoBase * (1 - descuento));
        int impuestos = calcularValorImpuestos(costoConDescuento);
        
        return costoConDescuento + impuestos;
    }

    /**
     * Calcula la distancia entre los dos aeropuertos de una ruta.
     * Se usa para determinar costos basados en longitud de trayecto.
     */
    protected int calcularDistanciaViaje(Ruta ruta) {
        Aeropuerto origen = ruta.getOrigen();
        Aeropuerto destino = ruta.getDestino();
        
        // Se utiliza la lógica de cálculo de distancia entre coordenadas (Haversine simplificado o similar)
        // proporcionada usualmente en la clase Aeropuerto.
        return Aeropuerto.calcularDistancia(origen, destino);
    }

    /**
     * Calcula el valor de los impuestos dado un costo base.
     */
    protected int calcularValorImpuestos(int costoBase) {
        return (int) (costoBase * IMPUESTO);
    }
}

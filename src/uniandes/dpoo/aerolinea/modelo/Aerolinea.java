package uniandes.dpoo.aerolinea.modelo;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas; // Importa la interfaz (o clase abstracta)
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifasTemporadaAlta;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifasTemporadaBaja;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.persistencia.CentralPersistencia;
import uniandes.dpoo.aerolinea.persistencia.IPersistenciaAerolinea;
import uniandes.dpoo.aerolinea.persistencia.IPersistenciaTiquetes;
import uniandes.dpoo.aerolinea.persistencia.TipoInvalidoException;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

/**
 * En esta clase se organizan todos los aspectos relacionados con una Aerolínea.
 */
public class Aerolinea
{
    private List<Avion> aviones;
    private Map<String, Ruta> rutas;
    private List<Vuelo> vuelos;
    private Map<String, Cliente> clientes;

    public Aerolinea( )
    {
        aviones = new LinkedList<Avion>( );
        rutas = new HashMap<String, Ruta>( );
        vuelos = new LinkedList<Vuelo>( );
        clientes = new HashMap<String, Cliente>( );
    }

    // ************************************************************************************
    // Métodos de manipulación básica
    // ************************************************************************************

    public void agregarRuta( Ruta ruta )
    {
        this.rutas.put( ruta.getCodigoRuta( ), ruta );
    }

    public void agregarAvion( Avion avion )
    {
        this.aviones.add( avion );
    }

    public void agregarCliente( Cliente cliente )
    {
        this.clientes.put( cliente.getIdentificador( ), cliente );
    }

    public boolean existeCliente( String identificadorCliente )
    {
        return this.clientes.containsKey( identificadorCliente );
    }

    public Cliente getCliente( String identificadorCliente )
    {
        return this.clientes.get( identificadorCliente );
    }

    public Collection<Avion> getAviones( )
    {
        return aviones;
    }

    public Collection<Ruta> getRutas( )
    {
        return rutas.values( );
    }

    public Ruta getRuta( String codigoRuta )
    {
        return rutas.get( codigoRuta );
    }

    public Collection<Vuelo> getVuelos( )
    {
        return vuelos;
    }

    /**
     * Busca un vuelo dado el código de la ruta y la fecha.
     */
    public Vuelo getVuelo( String codigoRuta, String fechaVuelo )
    {
        for (Vuelo vuelo : vuelos)
        {
            if (vuelo.getRuta().getCodigoRuta().equals(codigoRuta) && vuelo.getFecha().equals(fechaVuelo))
            {
                return vuelo;
            }
        }
        return null;
    }

    public Collection<Cliente> getClientes( )
    {
        return clientes.values( );
    }

    /**
     * Retorna todos los tiquetes de la aerolínea recolectados vuelo por vuelo.
     */
    public Collection<Tiquete> getTiquetes( )
    {
        List<Tiquete> todosLosTiquetes = new ArrayList<>();
        for (Vuelo vuelo : vuelos)
        {
            todosLosTiquetes.addAll(vuelo.getTiquetes());
        }
        return todosLosTiquetes;
    }

    // ************************************************************************************
    // Persistencia
    // ************************************************************************************

    public void cargarAerolinea( String archivo, String tipoArchivo ) throws TipoInvalidoException, IOException, InformacionInconsistenteException
    {
        IPersistenciaAerolinea cargador = CentralPersistencia.getPersistenciaAerolinea(tipoArchivo);
        cargador.cargarAerolinea(archivo, this);
    }

    public void salvarAerolinea( String archivo, String tipoArchivo ) throws TipoInvalidoException, IOException
    {
        IPersistenciaAerolinea cargador = CentralPersistencia.getPersistenciaAerolinea(tipoArchivo);
        cargador.salvarAerolinea(archivo, this);
    }

    public void cargarTiquetes( String archivo, String tipoArchivo ) throws TipoInvalidoException, IOException, InformacionInconsistenteException
    {
        IPersistenciaTiquetes cargador = CentralPersistencia.getPersistenciaTiquetes( tipoArchivo );
        cargador.cargarTiquetes( archivo, this );
    }

    public void salvarTiquetes( String archivo, String tipoArchivo ) throws TipoInvalidoException, IOException
    {
        IPersistenciaTiquetes cargador = CentralPersistencia.getPersistenciaTiquetes( tipoArchivo );
        cargador.salvarTiquetes( archivo, this );
    }

    // ************************************************************************************
    // Funcionalidades
    // ************************************************************************************

    /**
     * Programa un vuelo verificando que el avión no esté ocupado en esa fecha.
     */
    public void programarVuelo( String fecha, String codigoRuta, String nombreAvion ) throws Exception
    {
        Ruta laRuta = getRuta(codigoRuta);
        Avion elAvion = null;

        for (Avion a : aviones)
        {
            if (a.getNombre().equals(nombreAvion))
            {
                elAvion = a;
                break;
            }
        }

        if (laRuta == null) throw new Exception("La ruta con código " + codigoRuta + " no existe.");
        if (elAvion == null) throw new Exception("El avión con nombre " + nombreAvion + " no existe.");

        // Verificar disponibilidad del avión
        for (Vuelo v : vuelos)
        {
            if (v.getAvion().getNombre().equals(nombreAvion) && v.getFecha().equals(fecha))
            {
                throw new Exception("El avión ya tiene un vuelo programado para la fecha: " + fecha);
            }
        }

        Vuelo nuevoVuelo = new Vuelo(laRuta, fecha, elAvion);
        vuelos.add(nuevoVuelo);
    }

    /**
     * Vende tiquetes para un vuelo específico.
     */
    private boolean esTemporadaAlta(String fecha) {
        // El formato esperado es "YYYY-MM-DD"
        // Extraemos el mes (los caracteres en las posiciones 5 y 6)
        // Ejemplo: "2024-12-24" -> "12"
        String mesStr = fecha.split("-")[1]; 
        int mes = Integer.parseInt(mesStr);

        // Definición de temporada alta (ajusta según lo que diga tu enunciado)
        // 6 = Junio, 7 = Julio, 12 = Diciembre
        if (mes == 6 || mes == 7 || mes == 12) {
            return true;
        }
        
        return false;
    }
    public int venderTiquetes(String identificadorCliente, String fecha, String codigoRuta, int cantidad) throws VueloSobrevendidoException, Exception
    {
        Cliente elCliente = getCliente(identificadorCliente);
        Vuelo elVuelo = getVuelo(codigoRuta, fecha);

        if (elCliente == null) throw new Exception("El cliente no existe.");
        if (elVuelo == null) throw new Exception("El vuelo no existe.");

        CalculadoraTarifas calculadora;

        if (esTemporadaAlta(fecha)) {
            calculadora = new CalculadoraTarifasTemporadaAlta();
        } else {
            calculadora = new CalculadoraTarifasTemporadaBaja();
        }

        return elVuelo.venderTiquetes(elCliente, calculadora, cantidad);
    }
    /**
     * Marca un vuelo como realizado.
     */
    public void registrarVueloRealizado( String fecha, String codigoRuta )
    {
        Vuelo vuelo = getVuelo(codigoRuta, fecha);
        if (vuelo != null)
        {
            // Asumiendo que Vuelo tiene un estado o lógica para cerrarse
            // vuelo.marcarComoRealizado(); 
        }
    }

    /**
     * Calcula el valor de los tiquetes pendientes (no usados) de un cliente.
     */
    public String consultarSaldoPendienteCliente( String identificadorCliente )
    {
        int total = 0;
        for (Vuelo v : vuelos)
        {
            for (Tiquete t : v.getTiquetes())
            {
                if (t.getCliente().getIdentificador().equals(identificadorCliente) && !t.esUsado())
                {
                    total += t.getTarifa();
                }
            }
        }
        return String.valueOf(total);
    }
}

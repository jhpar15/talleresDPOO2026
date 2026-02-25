package uniandes.dpoo.aerolinea.persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Avion;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;

/**
 * Clase encargada de cargar y salvar la información de la aerolínea en formato JSON.
 */
public class PersistenciaAerolineaJson implements IPersistenciaAerolinea {

    @Override
    public void cargarAerolinea(String archivo, Aerolinea aerolinea) throws IOException, InformacionInconsistenteException {
        try {
            String contenido = new String(Files.readAllBytes(new File(archivo).toPath()));
            JSONObject root = new JSONObject(contenido);

            cargarAviones(aerolinea, root.getJSONArray("aviones"));
            cargarRutas(aerolinea, root.getJSONArray("rutas"));
            cargarVuelos(aerolinea, root.getJSONArray("vuelos"));

        } catch (JSONException e) {
            throw new InformacionInconsistenteException("El formato del archivo JSON es inválido: " + e.getMessage());
        }
    }

    @Override
    public void salvarAerolinea(String archivo, Aerolinea aerolinea) throws IOException {
        JSONObject root = new JSONObject();

        // 1. Salvar Aviones
        JSONArray jAviones = new JSONArray();
        for (Avion avion : aerolinea.getAviones()) {
            JSONObject jAvion = new JSONObject();
            jAvion.put("nombre", avion.getNombre());
            jAvion.put("capacidad", avion.getCapacidad());
            jAviones.put(jAvion);
        }
        root.put("aviones", jAviones);

        // 2. Salvar Rutas
        JSONArray jRutas = new JSONArray();
        for (Ruta ruta : aerolinea.getRutas()) {
            JSONObject jRuta = new JSONObject();
            jRuta.put("codigo", ruta.getCodigoRuta());
            jRuta.put("salida", ruta.getHoraSalida());
            jRuta.put("llegada", ruta.getHoraLlegada());
            // Salvar aeropuertos (se asume que se guardan los datos básicos)
            jRuta.put("origen", salvarAeropuerto(ruta.getOrigen()));
            jRuta.put("destino", salvarAeropuerto(ruta.getDestino()));
            jRutas.put(jRuta);
        }
        root.put("rutas", jRutas);

        // 3. Salvar Vuelos (solo referencias a avión y ruta)
        JSONArray jVuelos = new JSONArray();
        for (Vuelo vuelo : aerolinea.getVuelos()) {
            JSONObject jVuelo = new JSONObject();
            jVuelo.put("fecha", vuelo.getFecha());
            jVuelo.put("codigoRuta", vuelo.getRuta().getCodigoRuta());
            jVuelo.put("nombreAvion", vuelo.getAvion().getNombre());
            jVuelos.put(jVuelo);
        }
        root.put("vuelos", jVuelos);

        // Escribir al archivo
        try (PrintWriter pw = new PrintWriter(archivo)) {
            pw.print(root.toString(4)); // Indentación de 4 espacios
        }
    }

    // --- Métodos Auxiliares para Cargar ---

    private void cargarAviones(Aerolinea aerolinea, JSONArray jAviones) {
        for (int i = 0; i < jAviones.length(); i++) {
            JSONObject jAvion = jAviones.getJSONObject(i);
            Avion avion = new Avion(jAvion.getString("nombre"), jAvion.getInt("capacidad"));
            aerolinea.agregarAvion(avion);
        }
    }

    private void cargarRutas(Aerolinea aerolinea, JSONArray jRutas) throws InformacionInconsistenteException {
        for (int i = 0; i < jRutas.length(); i++) {
            JSONObject jRuta = jRutas.getJSONObject(i);
            
            // Reconstruir aeropuertos
            Aeropuerto origen = cargarAeropuerto(jRuta.getJSONObject("origen"));
            Aeropuerto destino = cargarAeropuerto(jRuta.getJSONObject("destino"));
            
            Ruta ruta = new Ruta(origen, destino, jRuta.getString("salida"), jRuta.getString("llegada"), jRuta.getString("codigo"));
            aerolinea.agregarRuta(ruta);
        }
    }

    private void cargarVuelos(Aerolinea aerolinea, JSONArray jVuelos) throws InformacionInconsistenteException {
        for (int i = 0; i < jVuelos.length(); i++) {
            JSONObject jVuelo = jVuelos.getJSONObject(i);
            String fecha = jVuelo.getString("fecha");
            String codigoRuta = jVuelo.getString("codigoRuta");
            String nombreAvion = jVuelo.getString("nombreAvion");

            try {
                aerolinea.programarVuelo(fecha, codigoRuta, nombreAvion);
            } catch (Exception e) {
                throw new InformacionInconsistenteException("No se pudo programar el vuelo cargado: " + e.getMessage());
            }
        }
    }

    private Aeropuerto cargarAeropuerto(JSONObject jAeropuerto) {
        // Nota: El constructor de Aeropuerto lanza excepción si hay duplicados
        try {
            return new Aeropuerto(jAeropuerto.getString("nombre"), jAeropuerto.getString("codigo"), 
                                  jAeropuerto.getString("ciudad"), jAeropuerto.getDouble("latitud"), 
                                  jAeropuerto.getDouble("longitud"));
        } catch (Exception e) {
            // Si ya existía, lo buscamos o manejamos (aquí simplificado)
            return null; 
        }
    }

    private JSONObject salvarAeropuerto(Aeropuerto aeropuerto) {
        JSONObject jAero = new JSONObject();
        jAero.put("nombre", aeropuerto.getNombre());
        jAero.put("codigo", aeropuerto.getCodigo());
        jAero.put("ciudad", aeropuerto.getNombreCiudad());
        jAero.put("latitud", aeropuerto.getLatitud());
        jAero.put("longitud", aeropuerto.getLongitud());
        return jAero;
    }
}
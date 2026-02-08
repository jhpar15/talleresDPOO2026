package uniandes.dpoo.estructuras.logica;

import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.Iterator;

/**
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre mapas.
 *
 * Todos los métodos deben operar sobre el atributo mapaCadenas que se declara como un Map.
 * 
 * En este mapa, las llaves serán cadenas y los valores serán también cadenas. La relación entre los dos será que cada llave será igual a la cadena del valor, pero invertida.
 * 
 * El objetivo de usar el tipo Map es que sólo puedan usarse métodos de esa interfaz y no métodos adicionales provistos por la implementación concreta (HashMap).
 * 
 * No pueden agregarse nuevos atributos.
 */
public class SandboxMapas
{
    /**
     * Un mapa de cadenas para realizar varias de las siguientes operaciones.
     * 
     * Las llaves del mapa son cadenas, así como los valores.
     * 
     * Las llaves corresponden a invertir la cadena que aparece asociada a cada llave.
     */
    private Map<String, String> mapaCadenas;

    /**
     * Crea una nueva instancia de la clase con el mapa inicializado pero vacío.
     */
    public SandboxMapas( )
    {
        mapaCadenas = new HashMap<String, String>( );
    }
    
    /**
     * Método auxiliar para invertir una cadena.
     * @param cadena La cadena a invertir.
     * @return La cadena invertida.
     */
    private String invertir(String cadena) {
        return new StringBuilder(cadena).reverse().toString();
    }

    /**
     * Retorna una lista con las cadenas del mapa (los valores) ordenadas lexicográficamente.
     * @return Una lista ordenada con las cadenas que conforman los valores del mapa.
     */
    public List<String> getValoresComoLista( )
    {
        List<String> valores = new ArrayList<>(mapaCadenas.values());
        Collections.sort(valores);
        return valores;
    }

    /**
     * Retorna una lista con las llaves del mapa ordenadas lexicográficamente de mayor a menor.
     * @return Una lista ordenada con las cadenas que conforman las llaves del mapa.
     */
    public List<String> getLlavesComoListaInvertida( )
    {
        List<String> llaves = new ArrayList<>(mapaCadenas.keySet());
        Collections.sort(llaves, Collections.reverseOrder());
        return llaves;
    }

    /**
     * Retorna la cadena que sea lexicográficamente menor dentro de las llaves del mapa.
     * 
     * Si el mapa está vacío, debe retornar null.
     * @return La llave lexicográficamente menor o null si el mapa está vacío.
     */
    public String getPrimera( )
    {
        if(mapaCadenas.isEmpty()){
            return null;
        }
        return Collections.min(mapaCadenas.keySet());
    }

    /**
     * Retorna la cadena que sea lexicográficamente mayor dentro de los valores del mapa.
     * 
     * Si el mapa está vacío, debe retornar null.
     * @return El valor lexicográficamente mayor o null si el mapa está vacío.
     */
    public String getUltima( )
    {
        if(mapaCadenas.isEmpty()){
            return null;
        }
        return Collections.max(mapaCadenas.values());
    }

    /**
     * Retorna una colección con las llaves del mapa, convertidas a mayúsculas.
     * 
     * El orden de las llaves retornadas no importa.
     * @return Una colección de cadenas donde todas las cadenas están en mayúsculas.
     */
    public Collection<String> getLlaves( )
    {
        List<String> llavesMayus = new ArrayList<>();
        for(String llave : mapaCadenas.keySet()){
            llavesMayus.add(llave.toUpperCase());
        }
        return llavesMayus;
    }

    /**
     * Retorna la cantidad de *valores* diferentes en el mapa.
     * @return La cantidad de cadenas diferentes contenidas en el mapa.
     */
    public int getCantidadCadenasDiferentes( )
    {
        // Se usa un conjunto para obtener los valores únicos.
        return new java.util.HashSet<>(mapaCadenas.values()).size();
    }

    /**
     * Agrega un nuevo valor al mapa de cadenas: el valor será el recibido por parámetro, y la llave será la cadena invertida.
     * 
     * Este método podría o no aumentar el tamaño del mapa, dependiendo de si ya existía la cadena en el mapa.
     * 
     * @param cadena La cadena que se va a agregar al mapa.
     */
    public void agregarCadena( String cadena )
    {
        String llave = invertir(cadena);
        mapaCadenas.put(llave, cadena);
    }

    /**
     * Elimina una cadena del mapa, dada la llave.
     * @param llave La llave para identificar el valor que se debe eliminar.
     */
    public void eliminarCadenaConLLave( String llave )
    {
        mapaCadenas.remove(llave);
    }

    /**
     * Elimina una cadena del mapa, dado el valor.
     * @param valor El valor que se debe eliminar.
     */
    public void eliminarCadenaConValor( String valor )
    {
        Iterator<Map.Entry<String, String>> it = mapaCadenas.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, String> entry = it.next();
            if(entry.getValue().equals(valor)){
                it.remove();
            }
        }
    }

    /**
     * Reinicia el mapa de cadenas con las representaciones como Strings de los objetos contenidos en la lista del parámetro 'objetos'.
     * 
     * Use el método toString para convertir los objetos a cadenas.
     * @param objetos Una lista de objetos.
     */
    public void reiniciarMapaCadenas( List<Object> objetos )
    {
        mapaCadenas.clear();
        for(Object obj : objetos){
            String cadena = obj.toString();
            String llave = invertir(cadena);
            mapaCadenas.put(llave, cadena);
        }
    }

    /**
     * Modifica el mapa de cadenas reemplazando las llaves para que ahora todas estén en mayúsculas pero sigan conservando las mismas cadenas asociadas.
     */
    public void volverMayusculas( )
    {
        Map<String, String> nuevoMapa = new HashMap<>();
        for(Map.Entry<String, String> entry : mapaCadenas.entrySet()){
            String nuevaLlave = entry.getKey().toUpperCase();
            nuevoMapa.put(nuevaLlave, entry.getValue());
        }
        mapaCadenas.clear();
        mapaCadenas.putAll(nuevoMapa);
    }

    /**
     * Verifica si todos los elementos en el arreglo de cadenas del parámetro hacen parte del mapa de cadenas (de los valores).
     * @param otroArreglo El arreglo de cadenas con el que se debe comparar.
     * @return True si todos los elementos del arreglo están dentro de los valores del mapa; false de lo contrario.
     */
    public boolean compararValores( String[] otroArreglo )
    {
        if(otroArreglo == null){
            return false;
        }
        for(String s : otroArreglo){
            if(!mapaCadenas.containsValue(s)){
                return false;
            }
        }
        return true;
    }
}


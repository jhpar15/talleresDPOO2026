package uniandes.dpoo.estructuras.logica;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.Collections;
import java.util.Iterator;

/**
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre conjuntos implementados usando un árbol (TreeSet).
 *
 * Todos los métodos deben operar sobre el atributo arbolCadenas que se declara como un NavigableSet.
 * 
 * El objetivo de usar el tipo NavigableSet es que sólo puedan usarse métodos de esa interfaz y no métodos adicionales provistos por la implementación concreta (TreeSet).
 * 
 * A diferencia de un Set, en un NavigableSet existe una noción de orden que en este caso corresponde al órden lexicográfico.
 * 
 * No pueden agregarse nuevos atributos.
 */
public class SandboxConjuntos
{
    /**
     * Un conjunto (set) de cadenas para realizar varias de las siguientes operaciones.
     * 
     * Por defecto, los elementos del conjunto están ordenados lexicográficamente.
     */
    private NavigableSet<String> arbolCadenas;

    /**
     * Crea una nueva instancia de la clase con el conjunto inicializado pero vacío.
     */
    public SandboxConjuntos( )
    {
        arbolCadenas = new TreeSet<String>( );
    }

    /**
     * Retorna una lista con las cadenas del conjunto ordenadas lexicográficamente.
     * @return Una lista con las cadenas ordenadas.
     */
    public List<String> getCadenasComoLista( )
    {
        // Dado que arbolCadenas ya está ordenado lexicográficamente, basta con crear una nueva lista a partir del conjunto.
        return new ArrayList<>(arbolCadenas);
    }

    /**
     * Retorna una lista con las cadenas del conjunto, ordenadas lexicográficamente de mayor a menor.
     * @return Una lista con las cadenas ordenadas de mayor a menor.
     */
    public List<String> getCadenasComoListaInvertida( )
    {
        // Se utiliza el método descendingSet() de NavigableSet para obtener el conjunto en orden inverso.
        return new ArrayList<>(arbolCadenas.descendingSet());
    }

    /**
     * Retorna la cadena que sea lexicográficamente menor en el conjunto de cadenas.
     * 
     * Si el conjunto está vacío, debe retornar null.
     * @return La primera cadena del conjunto, o null si está vacío.
     */
    public String getPrimera( )
    {
        if(arbolCadenas.isEmpty()){
            return null;
        }
        return arbolCadenas.first();
    }

    /**
     * Retorna la cadena que sea lexicográficamente mayor en el conjunto de cadenas.
     * 
     * Si el conjunto está vacío, debe retornar null.
     * @return La última cadena del conjunto, o null si está vacío.
     */
    public String getUltima( )
    {
        if(arbolCadenas.isEmpty()){
            return null;
        }
        return arbolCadenas.last();
    }

    /**
     * Retorna una colección con las cadenas que hacen parte del conjunto de cadenas y son mayores o iguales a la cadena que se recibe por parámetro.
     * 
     * Si la cadena recibida forma parte del conjunto, debe incluirse en la colección retornada.
     * 
     * @param cadena La cadena a partir de la cual se seleccionan los elementos.
     * @return Una colección de cadenas mayores o iguales a la cadena dada.
     */
    public Collection<String> getSiguientes( String cadena )
    {
        // Se utiliza tailSet(elemento, true) para incluir la cadena si está presente.
        return arbolCadenas.tailSet(cadena, true);
    }

    /**
     * Retorna la cantidad de valores en el conjunto de cadenas.
     * @return La cantidad de cadenas en el conjunto.
     */
    public int getCantidadCadenas( )
    {
        return arbolCadenas.size();
    }

    /**
     * Agrega un nuevo valor al conjunto de cadenas.
     * 
     * Este método podría o no aumentar el tamaño del conjunto, dependiendo de si la cadena ya se encontraba presente o no.
     * 
     * @param cadena La cadena que se va a agregar.
     */
    public void agregarCadena( String cadena )
    {
        arbolCadenas.add(cadena);
    }

    /**
     * Elimina una cadena del conjunto de cadenas.
     * 
     * @param cadena La cadena que se va a eliminar.
     */
    public void eliminarCadena( String cadena )
    {
        arbolCadenas.remove(cadena);
    }

    /**
     * Elimina una cadena del conjunto de cadenas, independientemente de las mayúsculas o minúsculas.
     * 
     * @param cadena La cadena que se va a eliminar, sin tener en cuenta las mayúsculas o minúsculas.
     */
    public void eliminarCadenaSinMayusculasOMinusculas( String cadena )
    {
        // Se recorre el conjunto y se elimina cualquier cadena que sea igual ignorando mayúsculas/minúsculas.
        Iterator<String> it = arbolCadenas.iterator();
        while(it.hasNext()){
            String s = it.next();
            if(s.equalsIgnoreCase(cadena)){
                it.remove();
            }
        }
    }

    /**
     * Elimina la primera cadena del conjunto.
     */
    public void eliminarPrimera( )
    {
        // pollFirst() remueve y retorna el primer elemento, o retorna null si el conjunto está vacío.
        arbolCadenas.pollFirst();
    }

    /**
     * Reinicia el conjunto de cadenas con las representaciones como Strings de los objetos contenidos en la lista del parámetro 'objetos'.
     * 
     * Use el método toString para convertir los objetos a cadenas.
     * @param objetos Una lista de objetos.
     */
    public void reiniciarConjuntoCadenas( List<Object> objetos )
    {
        arbolCadenas.clear();
        for(Object obj : objetos){
            arbolCadenas.add(obj.toString());
        }
    }

    /**
     * Modifica el conjunto de cadenas para que todas las cadenas estén en mayúsculas.
     * 
     * Note que esta operación podría modificar el órden de los elementos dentro del conjunto.
     */
    public void volverMayusculas( )
    {
        // Se crea una lista temporal con las cadenas en mayúsculas.
        List<String> mayusculas = new ArrayList<>();
        for(String s : arbolCadenas){
            mayusculas.add(s.toUpperCase());
        }
        arbolCadenas.clear();
        arbolCadenas.addAll(mayusculas);
    }

    /**
     * Construye un árbol de cadenas donde todas las cadenas están organizadas de MAYOR a MENOR.
     * 
     * @return Un TreeSet con las cadenas en orden descendente.
     */
    public TreeSet<String> invertirCadenas( )
    {
        // Se crea un nuevo TreeSet con un comparador que invierte el orden natural (lexicográfico).
        TreeSet<String> arbolInvertido = new TreeSet<>(Collections.reverseOrder());
        arbolInvertido.addAll(arbolCadenas);
        return arbolInvertido;
    }

    /**
     * Verifica si todos los elementos en el arreglo de cadenas del parámetro hacen parte del conjunto de cadenas.
     * 
     * @param otroArreglo El arreglo de cadenas con el que se debe comparar.
     * @return True si todos los elementos del arreglo están dentro del conjunto; false de lo contrario.
     */
    public boolean compararElementos( String[] otroArreglo )
    {
        if(otroArreglo == null){
            return false;
        }
        for(String s : otroArreglo){
            if(!arbolCadenas.contains(s)){
                return false;
            }
        }
        return true;
    }
}

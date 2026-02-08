package uniandes.dpoo.estructuras.logica;

import java.util.HashMap;

public class SandboxArreglos {
    private int[] arregloEnteros;
    private String[] arregloCadenas;

    public SandboxArreglos() {
        arregloEnteros = new int[]{};
        arregloCadenas = new String[]{};
    }

    public int[] getCopiaEnteros() {
        int[] copia = new int[arregloEnteros.length];
        System.arraycopy(arregloEnteros, 0, copia, 0, arregloEnteros.length);
        return copia;
    }

    public String[] getCopiaCadenas() {
        String[] copia = new String[arregloCadenas.length];
        System.arraycopy(arregloCadenas, 0, copia, 0, arregloCadenas.length);
        return copia;
    }

    public int getCantidadEnteros() {
        return arregloEnteros.length;
    }

    public int getCantidadCadenas() {
        return arregloCadenas.length;
    }

    public void agregarEntero(int entero) {
        int[] nuevo = new int[arregloEnteros.length + 1];
        System.arraycopy(arregloEnteros, 0, nuevo, 0, arregloEnteros.length);
        nuevo[arregloEnteros.length] = entero;
        arregloEnteros = nuevo;
    }

    public void agregarCadena(String cadena) {
        String[] nuevo = new String[arregloCadenas.length + 1];
        System.arraycopy(arregloCadenas, 0, nuevo, 0, arregloCadenas.length);
        nuevo[arregloCadenas.length] = cadena;
        arregloCadenas = nuevo;
    }

    public void eliminarEntero(int valor) {
        int count = 0;
        for (int num : arregloEnteros) {
            if (num == valor) count++;
        }
        int[] nuevo = new int[arregloEnteros.length - count];
        int index = 0;
        for (int num : arregloEnteros) {
            if (num != valor) nuevo[index++] = num;
        }
        arregloEnteros = nuevo;
    }

    public void eliminarCadena(String cadena) {
        int count = 0;
        for (String s : arregloCadenas) {
            if (s.equals(cadena)) count++;
        }
        String[] nuevo = new String[arregloCadenas.length - count];
        int index = 0;
        for (String s : arregloCadenas) {
            if (!s.equals(cadena)) nuevo[index++] = s;
        }
        arregloCadenas = nuevo;
    }

    public void insertarEntero(int entero, int posicion) {
        int pos = posicion < 0 ? 0 : Math.min(posicion, arregloEnteros.length);
        int[] nuevo = new int[arregloEnteros.length + 1];
        System.arraycopy(arregloEnteros, 0, nuevo, 0, pos);
        nuevo[pos] = entero;
        System.arraycopy(arregloEnteros, pos, nuevo, pos + 1, arregloEnteros.length - pos);
        arregloEnteros = nuevo;
    }

    public void eliminarEnteroPorPosicion(int posicion) {
        if (posicion < 0 || posicion >= arregloEnteros.length) return;
        int[] nuevo = new int[arregloEnteros.length - 1];
        System.arraycopy(arregloEnteros, 0, nuevo, 0, posicion);
        System.arraycopy(arregloEnteros, posicion + 1, nuevo, posicion, arregloEnteros.length - posicion - 1);
        arregloEnteros = nuevo;
    }

    public void reiniciarArregloEnteros(double[] valores) {
        int[] nuevo = new int[valores.length];
        for (int i = 0; i < valores.length; i++) {
            nuevo[i] = (int) valores[i];
        }
        arregloEnteros = nuevo;
    }

    public void reiniciarArregloCadenas(Object[] objetos) {
        String[] nuevo = new String[objetos.length];
        for (int i = 0; i < objetos.length; i++) {
            nuevo[i] = objetos[i].toString();
        }
        arregloCadenas = nuevo;
    }

    public void volverPositivos() {
        for (int i = 0; i < arregloEnteros.length; i++) {
            if (arregloEnteros[i] < 0) arregloEnteros[i] *= -1;
        }
    }

    public void organizarEnteros() {
        for (int i = 0; i < arregloEnteros.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arregloEnteros.length; j++) {
                if (arregloEnteros[j] < arregloEnteros[minIndex]) minIndex = j;
            }
            int temp = arregloEnteros[minIndex];
            arregloEnteros[minIndex] = arregloEnteros[i];
            arregloEnteros[i] = temp;
        }
    }

    public void organizarCadenas() {
        for (int i = 0; i < arregloCadenas.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arregloCadenas.length; j++) {
                if (arregloCadenas[j].compareTo(arregloCadenas[minIndex]) < 0) minIndex = j;
            }
            String temp = arregloCadenas[minIndex];
            arregloCadenas[minIndex] = arregloCadenas[i];
            arregloCadenas[i] = temp;
        }
    }

    public int contarApariciones(int valor) {
        int count = 0;
        for (int num : arregloEnteros) {
            if (num == valor) count++;
        }
        return count;
    }

    public int contarApariciones(String cadena) {
        if (cadena == null) return 0;
        String target = cadena.toLowerCase();
        int count = 0;
        for (String s : arregloCadenas) {
            if (s.toLowerCase().equals(target)) count++;
        }
        return count;
    }

    public int[] buscarEntero(int valor) {
        int count = contarApariciones(valor);
        int[] posiciones = new int[count];
        int index = 0;
        for (int i = 0; i < arregloEnteros.length; i++) {
            if (arregloEnteros[i] == valor) posiciones[index++] = i;
        }
        return posiciones;
    }

    public int[] calcularRangoEnteros() {
        if (arregloEnteros.length == 0) return new int[0];
        int min = arregloEnteros[0];
        int max = arregloEnteros[0];
        for (int num : arregloEnteros) {
            if (num < min) min = num;
            if (num > max) max = num;
        }
        return new int[]{min, max};
    }

    public HashMap<Integer, Integer> calcularHistograma() {
        HashMap<Integer, Integer> histograma = new HashMap<>();
        for (int num : arregloEnteros) {
            histograma.put(num, histograma.getOrDefault(num, 0) + 1);
        }
        return histograma;
    }

    public int contarEnterosRepetidos() {
        HashMap<Integer, Integer> counts = calcularHistograma();
        int repetidos = 0;
        for (int count : counts.values()) {
            if (count > 1) repetidos++;
        }
        return repetidos;
    }

    public boolean compararArregloEnteros(int[] otroArreglo) {
        if (arregloEnteros.length != otroArreglo.length) return false;
        for (int i = 0; i < arregloEnteros.length; i++) {
            if (arregloEnteros[i] != otroArreglo[i]) return false;
        }
        return true;
    }

    public boolean mismosEnteros(int[] otroArreglo) {
        if (arregloEnteros.length != otroArreglo.length) return false;
        HashMap<Integer, Integer> mapThis = new HashMap<>();
        for (int num : arregloEnteros) mapThis.put(num, mapThis.getOrDefault(num, 0) + 1);
        HashMap<Integer, Integer> mapOther = new HashMap<>();
        for (int num : otroArreglo) mapOther.put(num, mapOther.getOrDefault(num, 0) + 1);
        return mapThis.equals(mapOther);
    }

    public void generarEnteros(int cantidad, int minimo, int maximo) {
        if (minimo > maximo) {
            int temp = minimo;
            minimo = maximo;
            maximo = temp;
        }
        int rango = maximo - minimo + 1;
        int[] nuevo = new int[cantidad];
        for (int i = 0; i < cantidad; i++) {
            nuevo[i] = (int) (Math.random() * rango) + minimo;
        }
        arregloEnteros = nuevo;
    }
}

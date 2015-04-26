package librerias.util;

import java.util.*;

/** 
 *  La clase Ordenacion contiene la implementacion de varios algoritmos de ordenacion:
 *  Insercion Directa, Quick Sort y Merge Sort. Proporciona ademas un metodo para 
 *  comprobar si dos arrays genericos son iguales.
 *  @author Profesores de EDA, ETSInf, Grado Ing. Informatica
 *  @date Curso 2014-2015
 */

public class Ordenacion {

    // QUICK SORT ------------------------------------------------------------------------------------
    /** 
     *  Algoritmo de ordenacion quicksort (Hoare -1963-). 
     *  Utiliza el algoritmo de particion debido a Weiss, con mediana de 3
     *  para el calculo del pivote. 
     *  @param a Sus elementos implementan la interfaz Comparable
     */
    public static <T extends Comparable<T>> void quickSort(T[]  a) {
        quickSort(a, 0, a.length - 1);
    }

    // Ordena el array a[izq..der] por quickSort, izq <= der
    private static <T extends Comparable<T>> void quickSort(T[] a, int izq, int der ) {
        if (izq < der) {
            T pivot = mediana3(a, izq, der);
            int i = izq;
            int j = der - 1;
            for( ; i < j; ) {    
                while (pivot.compareTo(a[++i]) > 0);
                while (pivot.compareTo(a[--j]) < 0);
                intercambiar(a, i, j);
            }
            intercambiar(a, i, j);        // Deshacer el ultimo cambio
            intercambiar(a, i, der - 1);  // Restaurar el pivote
            quickSort(a, izq, i - 1);     // Ordenar recursivamente los elementos menores
            quickSort(a, i + 1, der);     // Ordenar recursivamente los elementos mayores
        }
    }

    // Metodo para intercambiar dos elementos de un array
    private static <T> void intercambiar(T[] a, int ind1, int ind2) {
        T tmp = a[ind1];    
        a[ind1] = a[ind2];
        a[ind2] = tmp;   
    }

    //Metodo para el calculo de la Mediana de 3, que devuelve el valor del pivote
    private static <T extends Comparable<T>> T mediana3(T[] a, int izq, int der) {    
        int c = (izq + der) / 2;   
        if (a[c].compareTo(a[izq]) < 0)   intercambiar(a, izq, c);
        if (a[der].compareTo(a[izq]) < 0) intercambiar(a, izq, der);
        if (a[der].compareTo(a[c]) < 0)   intercambiar(a, c, der);
        // ocultar el pivote en la posicion der-1
        intercambiar(a, c, der - 1);
        return a[der - 1];
    }

    // MERGE SORT --------------------------------------------   
    // VERSION 1 (vista en teoria):   
    /**
     * Ordena ascendentemente el array v
     * @param v Sus elementos deben implementar el interfaz Comparable
     */
    public static <T extends Comparable<T>> void mergeSort1(T[] v) {
        mergeSort1(v, 0, v.length - 1);
    }

    /**
     * Ordena ascendentemente el array v desde i hasta f, ambos inclusive
     * @param v Sus elementos implementan la interfaz Comparable
     * @param i, extremo inferior del intervalo a ordenar
     * @param f, extremo superior del intervalo a ordenar
     * PRECONDICION: i<=f
     */
    private static <T extends Comparable<T>> void mergeSort1(T[] v, int i, int f) {
        if (i < f) {
            int m = (i + f) / 2;
            mergeSort1(v, i, m);
            mergeSort1(v, m + 1, f);
            merge1(v, i, f, m);
        }
    }        

    /**
    * Mezcla internamente los subarrays v[i, m] y v[m + 1, f] ordenados asc.
    * @param v Sus elementos implementan la interfaz Comparable
    * @param i, extremo inferior del intervalo a mezclar
    * @param f, extremo superior del intervalo a mezclar
    */
    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> void merge1(T[] v, int i, int f, int m) {
        int a = i, b = m + 1, k = 0;
        T[] res = (T[]) new Comparable[f - i + 1];
        while (a <= m && b <= f)
            if (v[a].compareTo(v[b]) < 0) res[k++] = v[a++];
            else res[k++] = v[b++];
        while (a <= m) res[k++] = v[a++];
        while (b <= f) res[k++] = v[b++];

        for(a = i, k = 0; a <= f; a++, k++) v[a] = res[k];
    }   

    // VERSION 2
    // A COMPLETAR:

    /**
     * Ordena ascendentemente el array v
     * @param v Sus elementos deben implementar el interfaz Comparable
     */
    public static <T extends Comparable<T>> T[] mergeSort2(T[] v) {
        return mergeSort2(v, 0, v.length - 1);
    }

    /**
     * Devuelve un array cuyos elementos del i al f, ambos inclusive, están ordenados ascendentemente.
     * @param v, Elementos que implementan la interfaz Comparable.
     * @param i, extremo inferior del subarray a ordenar.
     * @param f, extremo superior del subarray a ordenar.
     * @return T[]. El array resultante.
     * PRECONDICION: i<=f
     */
    private static <T extends Comparable<T>> T[] mergeSort2(T[] v, int i, int f) {  
        if(i < f) {
            int m = (i+f)/2;
            T[] mitad1 = mergeSort2(Arrays.copyOfRange(v,i,m+1));
            T[] mitad2 = mergeSort2(Arrays.copyOfRange(v,m+1,f+1));
            T[] resultado = merge2(mitad1,mitad2);
            return resultado;
        }
        else return v;
    }

    /**
     * Devuelve el array resultado de la mezcla o fusión de los arrays v1 y v2.
     * @param v1. Elementos ordenados ascendentemente y que implementan la interfaz Comparable
     * @param v2. Elementos ordenados ascendentemente y que implementan la interfaz Comparable
     * @return T[]. El array resultante.
     */
    private static <T extends Comparable<T>> T[] merge2(T[] v1, T[] v2) {
        int i = 0, j = 0, k = 0;
        T[] res = (T[]) new Comparable[v1.length+v2.length];
        while(i < v1.length && j < v2.length){
            if(v1[i].compareTo(v2[j]) < 0){
                res[k] = v1[i];i++;
            }
            else {
                res[k] = v2[j];j++;
            }
            k++;
        } 
        while(i < v1.length) {
            res[k] = v1[i];i++;k++;
        }
        while(j < v2.length) {
            res[k] = v2[j];j++;k++;
        }
        return res;
    }

    // Metodo auxiliar -----------------------------------------------------------------
    /** 
     *  Comprueba si los arrays a y b son iguales elemento a elemento
     *  @param a Sus elementos implementan la interfaz Comparable
     *  @return boolean, el resultado de la comprobacion
     */
    public static <T extends Comparable<T>> boolean sonIguales(T[] a, T[] b) {
        boolean iguales = true;
        if (a.length != b.length) iguales = false;
        else {
            for (int i = 0; i < a.length && iguales; i++)
                iguales = (a[i].compareTo(b[i]) == 0);
        }    
        return iguales;
    }    
}
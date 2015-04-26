package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.*;

/** Implementa la interfaz ListaConPI mediante una LEG ...
 *  (a) Con Nodo ficticio cabecera 
 *  (b) Una referencia al primer Nodo  
 *  (c) Una referencia al ultimo Nodo
 *  (d) Para representar el Punto de Interes, una referencia al Nodo anterior al que
 *      ocupa el punto de interes
 *  (e) Un int talla que representa la talla de la LEG
 *
 *  @Version Febrero 2014
 */ 

public class LEGListaConPI<E> implements ListaConPI<E> {

    protected NodoLEG<E> pri, pI, ult, ant; 
    // En principio, una LEGListaConPI NO tendria una talla 
    // ... PERO se incluye pensando en la futura reutilizacion del Modelo
    protected int talla;

    /** construye una Lista Con PI vacia **/
    public LEGListaConPI() {
        pri = ult = ant = new NodoLEG<E>(null);
        talla = 0;
    }

    /** inserta e en una Lista antes del Elemento que ocupa su PI, que permanece inalterado
     * @param e Elemento a insertar
     */
    public void insertar(E e) { 
        NodoLEG<E> nuevo = new NodoLEG<E>(e); talla++;
        // Se inserta nuevo DETRAS de ant, i.e. ANTES del Dato que ocupa el PI
        nuevo.siguiente = pI;
        ant.siguiente = nuevo;
        // OJO: al insertar en fin de Lista ... actualizar ult!
        if (esFin()) ult = nuevo;   
        // OJO: tras la insercion el PI de la Lista permanece inalterad 
        ant = nuevo;
    } 

    /** SII !esFin(): elimina de una Lista el Elemento que ocupa su PI, que permanece inalterado **/  
    public void eliminar() {
        talla--;
        // OJO: al eliminar el ultimo Elemento ... actualizar ult!; el PI esta en fin()
        ant.siguiente = pI.siguiente;
        pI = pI.siguiente;        
        if (pI == null) ult = ant;
    }

    /** situa el PI de una Lista en su inicio, sobre su primer Elemento si no esta vacia **/
    public void inicio() { ant = pri; pI = pri.siguiente;  }

    /** SII !esFin(): situa sobre el siguiente Elemento el PI de una Lista  **/
    public void siguiente() { 
        ant = pI;
        pI = pI.siguiente;
    }

    /** situa el PI de una Lista en su fin, detras de su ultimo Elemento si no esta vacia **/
    public void fin() { 
        ant = ult;
        pI = ult.siguiente;
    }

    /** SII !esFin(): obtiene el Elemento que ocupa el PI de una Lista
     *  @return E, el Elemento que ocupa el PI de una Lista
     */
    public E recuperar() { return pI.dato; }

    /** comprueba si el PI de una Lista esta en su fin
     *  @return true si el PI de una Lista esta en su fin y false en caso contrario
     */
    public boolean esFin() { return ant == ult; }

    /** comprueba si una Lista Con PI esta vacia
     *  @return true si una Lista Con PI esta vacia y false en caso contrario
     */
    public boolean esVacia() { return pri == ult;}

    /** devuelve la talla de una Lista Con PI, i.e. su numero de Elementos 
     *  @return int, el numero de Elementos de una Lista con PI
     */
    public int talla() { return talla; }

    /** devuelve el String con los Elementos de una Lista con PI en orden de insercion 
     *   @return String que contiene los Elementos de una Lista con PI, en el mismo 
     *   formato que usa el estandar de Java para los arrays
     */
    public String toString() {
        // NOTA: se usa la clase StringBuilder, en lugar de String, por motivos de eficiencia 
        StringBuilder s = new StringBuilder();
        s.append("[");
        NodoLEG<E> aux = pri.siguiente; 
        for (int i = 0, j = talla - 1; i < j; i++, aux = aux.siguiente)
            s.append(aux.dato.toString() + ", ");
        if (talla != 0) s.append(aux.dato.toString() + "]"); else s.append("]");
        return s.toString();
    }
}

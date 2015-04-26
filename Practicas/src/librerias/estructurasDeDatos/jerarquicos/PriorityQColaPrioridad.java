package librerias.estructurasDeDatos.jerarquicos;

import librerias.estructurasDeDatos.modelos.*;
import java.util.*;

/** Implementacion de una Cola de Prioridad mediante una PriorityQueue (Heap) 
 *
 * @version Febrero 2014 
 */

public class PriorityQColaPrioridad<E extends Comparable<E>> 
extends PriorityQueue<E>
implements ColaPrioridad<E>{

/** crea una Cola de Prioridad (CP) vacia */
public PriorityQColaPrioridad() { super(); }

/** atendiendo a su prioridad, inserta el Elemento e en una Cola de Prioridad (CP)
 *  @param e Elemento a agnadir a una Cola de Prioridad
 */
public void insertar(E e) { this.add(e); }

/** SII !esVacia(): obtiene el Elemento con maxima prioridad de una CP 
 * @return E Elemento con maxima prioridad de una CP
 */
public E recuperarMin() { return this.peek(); }

/** SII !esVacia(): obtiene y elimina el Elemento con maxima prioridad de una CP 
 *  @return E Elemento con maxima prioridad de una CP
 */
public E eliminarMin() { return this.poll(); }

/** comprueba si una Cola de Prioridad esta vacia
 * @return true si una CP esta vacia y false en caso contrario
 */
public boolean esVacia() { return this.size() == 0; }
}
package librerias.estructurasDeDatos.jerarquicos;

/** Un Nodo de un ABB tiene un dato y dos referencias a hijos 
  * izquierdo y derecho */
class NodoABB<E> {
     // atributos
	 E dato;
	 NodoABB<E> izq;
	 NodoABB<E> der;
	 
	 /** Constructor de un nodo sin hijos
       * @param  valor  Dato a almacenar en el nodo
       */
	 public NodoABB(E valor){
	    this(valor, null, null);  
	 }
	 
	 /**  Constructor de un nodo con un hijo izquierdo y derecho dado
       * @param  dato  Dato a almacenar en el nodo
       * @param  izq   Hijo izquierdo del nodo
       * @param  der   Hijo derecho del nodo
       */
	 public NodoABB(E valor, NodoABB<E> izq, NodoABB<E> der){
	    dato = valor; 
	    this.izq = izq;
	    this.der = der;  
	 }
}

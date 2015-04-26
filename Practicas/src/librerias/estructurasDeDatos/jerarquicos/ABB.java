package librerias.estructurasDeDatos.jerarquicos;

/** Clase ABB<E> que representa un Arbol Binario mediante un enlace a su nodo raiz.
 *  Sus caracteristicas son las siguientes: 
 *  1.- El tipo de sus elementos es E extends Comparable<E>
 *  2.- ATRIBUTOS (protected para la herencia): 
 *      TIENE UN NodoABB<E> raiz
 *      TIENE UNA int  talla
 * @author (profesores EDA 13-14) 
 * @version (03 2013)
 **/

public class ABB<E extends Comparable<E>> {
    // Atributos de la classe ABB
    protected NodoABB<E> raiz;
    protected int talla;

    /** Constructor de un ABB vacio **/
    public ABB() {
        raiz = null; 
        talla = 0;
    }
  
    ///////////////////////////////////////////////////
    //  EJERCICIO DE PRACTICAS SESION 1
    ///////////////////////////////////////////////////
    /** Devuelve el numero de hojas del ABB
      * @return   numero de hojas del ABB
      */
    public int numHojas(){ 
        return numHojas(this.raiz);  
    }

    // Devuelve el numero de hojas del nodo r
    
    private int numHojas(NodoABB<E> r) {
        if (r == null) return 0;
        if (r.izq == null && r.der == null) return 1;
        
        if (r.izq == null) return numHojas(r.der);
        else if (r.der == null) return numHojas(r.izq);
        
        return numHojas(r.izq) + numHojas(r.der);  
    }    
    
   
    ///////////////////////////////////////////////////
    //  EJERCICIO DE PRACTICAS SESION 1
    ///////////////////////////////////////////////////
    /** Devuelve la altura del ABB
      * @return   Altura del ABB
      */
    public int altura() { 
        return altura(this.raiz); 
    }

    // Devuelve la altura del nodo r
    private int altura(NodoABB<E> r){  
        int aux1;
        int aux2;
        if(r == null) return -1;                
        aux1 = 1 + altura(r.der);
        aux2 = 1 + altura(r.izq);
        if(aux1 < aux2) return aux2;
        else return aux1;
    }
    
    ///////////////////////////////////////////////////
    //  EJERCICIO DE PRACTICAS SESION 1
    ///////////////////////////////////////////////////
    /** 
     * Reconstruye el ABB, con los mismos datos, de forma que quede equilibrado        
     */
    public void reconstruirEquilibrado(){ 
        E[] vec =  inOrden();  // el recorrido de un ABB en InOrden devuelve una secuencia ordenada de menor a mayor
        raiz = null;           // vaciar el ABB 
        talla=0;  
        reconstruirEquilibrado(vec, 0, vec.length - 1);
    }

    // Reconstruye el ABB a partir de los datos contenidos en el array vec,
    // datos que se encuentran ordenados de forma creciente
    private void reconstruirEquilibrado(E[] vec, int ini, int fin){
        int talla = fin - ini + 1;
        if (talla > 0) {
            int mediana = (fin + ini) / 2;
            
            insertar(vec[mediana]);
            reconstruirEquilibrado(vec, ini, mediana - 1);
            reconstruirEquilibrado(vec, mediana + 1, fin);
        }
    }

    ///////////////////////////////////////////////////

    /** Inserta un elemento dado x en el ABB sin permitir duplicados.
      * No hace nada si el dato a buscar ya se encuentra en el ABB
      * @param    valor   Elemento a insertar 
      */
    public void insertar(E x) {
        NodoABB<E> ant = null, nuevo, nodo = raiz;
        int resC = -1;
        while (nodo != null && ((resC = x.compareTo(nodo.dato)) != 0)) {
            ant = nodo;
            nodo = resC < 0 ? nodo.izq: nodo.der;
        }
        if (nodo == null) {
            nuevo = new NodoABB<E>(x);
            if (raiz == null) raiz = nuevo;
            else if (resC < 0) ant.izq = nuevo;
            else ant.der = nuevo;
            talla++;
        }
    } 
     
    /** Busca el valor dado en el ABB
      * @param    x       Elemento a buscar
      * @return   dato en el ABB que coincide con x, null si no hay ninguno          
      */
    public E recuperar(E x) {
         NodoABB<E> nodo = raiz;
         while (nodo != null) {
             int resC = x.compareTo(nodo.dato);
             if (resC == 0) return nodo.dato;
             nodo = resC < 0 ? nodo.izq : nodo.der;
         }
         return null;
    }

    /** Actualiza el dato x en el ABB, si no esta lo inserta 
      * @param    x       Elemento a insertar/actualizar
      * @return   dato anterior en el ABB que coincidia con x, null si no habia ninguno          
      */
    public E actualizar(E x) {
        E res = null;
        NodoABB<E> ant = null, nuevo, nodo = raiz;
        int resC = -1; 
        while (nodo != null && ((resC = x.compareTo(nodo.dato)) != 0)) {
            ant = nodo;
            nodo = resC < 0 ? nodo.izq: nodo.der;
        }
        if (nodo == null) {
            nuevo = new NodoABB<E>(x);
            if (raiz == null) raiz = nuevo;
            else if (resC < 0) ant.izq = nuevo;
            else ant.der = nuevo;
            talla++;
        } else {
            res = nodo.dato;
            nodo.dato = x;
        }
        return res;
    }
  
    /** Devuelve el elemento minimo del ABB
      * @return Elemento minimo  
      */
    public E recuperarMin() {
        NodoABB<E> nodo = raiz;
        if (nodo == null) return null;
        while (nodo.izq != null)
            nodo = nodo.izq;
        return nodo.dato;
    }

    /** Elimina el minimo del ABB 
      * @return Elemento minimo  
      */
    public E eliminarMin() {
        if (raiz == null) return null;
        return eliminarMin(raiz, null).dato;
    }
 
    // Elimina el nodo minimo a partir de uno dado
    protected NodoABB<E> eliminarMin(NodoABB<E> nodo, NodoABB<E> padre) {
        NodoABB<E> ant = padre;
        while (nodo.izq != null) {
            ant = nodo;
            nodo = nodo.izq;
        }
        if (ant == null) raiz = raiz.der;
        else if (ant.izq == nodo) ant.izq = nodo.der;
        else ant.der = nodo.der;
        talla--;
        return nodo;
    }
  
    /** Elimina el nodo que contiene el dato x 
      * @param  x   dato a eliminar
      * @return dato eliminado
      */
    public E eliminar(E x) {
        E res;
        NodoABB<E> nodo = raiz, ant = null;
        int resC = -1;
        while (nodo != null && ((resC = x.compareTo(nodo.dato)) != 0)) {
            ant = nodo;
            nodo = resC < 0 ? nodo.izq : nodo.der;
        }
        if (nodo == null) return null;                     // No encontrado
        res = nodo.dato;
        if (nodo.izq != null && nodo.der != null) {   // 2 hijos
            nodo.dato = eliminarMin(nodo.der, nodo).dato;
        } else {                                      // 1 hijo o ninguno
            NodoABB<E> aux = nodo.izq != null ? nodo.izq : nodo.der;
            if (ant == null) raiz = aux;
            else if (ant.izq == nodo) ant.izq = aux;
            else ant.der = aux;
            talla--;
        }
        return res;
    }
  
    /** Construye un array ordenado de forma creciente con todos los valores del ABB
      * resultado del recorrido en InOrden del mismo 
      * @return   E[], array con los valores del ABB segun el recorrido en InOrden          
      */ 
    @SuppressWarnings("unchecked")
    public E [] inOrden() {
        if (raiz == null) return null;
        else {
           E [] vec = (E[]) new Comparable[talla];
           inOrden(vec, raiz, 0);
           return vec;
        }
    }
    
    // Asigna el resultado del recorrido en InOrden de r en el array vec a partir de la posicion pos
    private int inOrden(E [] vec, NodoABB<E> r, int pos) {
       if (r == null) return pos;
       else {
           pos = inOrden(vec, r.izq, pos);
           vec[pos++] = r.dato;
           return inOrden(vec,r.der,pos);    
       }
    }

    /**
     * Devuelve true si el ABB esta vacio
     * @return true si esta vacio, false en caso contrario
     */
    public boolean esVacio() {
        return talla == 0;
    }
  
    /**
     * Devuelve el numero de elementos del ABB
     * @return Talla del ABB
     */
    public int talla() {
        return talla;
    }
    
    /**
     * Recorrido inOrden del ABB
     * @return String con los elementos segun el recorrido inOrden
     */
    public String toStringInOrden() {
        return toStringInOrden(raiz);
    }
    
    // Recorrido inOrden a partir del nodo r
    private String toStringInOrden(NodoABB<E> r) {
        if (r == null) return "";
        StringBuilder sb = new StringBuilder();
        String izq = toStringInOrden(r.izq);
        if (izq.length() > 0) {
            sb.append(izq);
            sb.append(",");
        }
        sb.append(r.dato.toString());
        String der = toStringInOrden(r.der);
        if (der.length() > 0) {
            sb.append(",");
            sb.append(der);
        }
        return sb.toString();
    }

    /**
     * Recorrido en preOrden del ABB
     * @return String con los elementos segun el recorrido preOrden
     */
    public String toStringPreOrden() {
        return toStringPreOrden(raiz);
    }
    
    // Recorrido preOrden a partir del nodo r
    private String toStringPreOrden(NodoABB<E> r) {
        if (r == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(r.dato.toString());
        String izq = toStringPreOrden(r.izq);
        if (izq.length() > 0) {
            sb.append(",");
            sb.append(izq);
        }
        String der = toStringPreOrden(r.der);
        if (der.length() > 0) {
            sb.append(",");
            sb.append(der);
        }
        return sb.toString();
    }

    /**
     * Recorrido en postOrden del ABB
     * @return String con los elementos segun el recorrido postOrden
     */
    public String toStringPostOrden() {
        return toStringPostOrden(raiz);
    }
    
    // Recorrido preOrden a partir del nodo r
    private String toStringPostOrden(NodoABB<E> r) {
        if (r == null) return "";
        StringBuilder sb = new StringBuilder();
        String izq = toStringPostOrden(r.izq);
        if (izq.length() > 0) {
            sb.append(izq);
            sb.append(",");
        }
        String der = toStringPostOrden(r.der);
        if (der.length() > 0) {
            sb.append(der);
            sb.append(",");
        }
        sb.append(r.dato.toString());
        return sb.toString();
    }
    
    /**
     * Recorrido por niveles del ABB
     * @return String con los elementos segun el recorrido por niveles
     */
    public String toStringPorNiveles() {
        if (raiz == null) return "";
        StringBuilder sb = new StringBuilder();             
        java.util.Queue<NodoABB<E>> q = new java.util.ArrayDeque<NodoABB<E>>();
        q.add(raiz);
        int n = 0;
        while (!q.isEmpty()) {
            n++;
            NodoABB<E> actual = q.poll();
            sb.append(actual.dato.toString());
            if (n < talla) sb.append(",");
            if (actual.izq != null) q.add(actual.izq);
            if (actual.der != null) q.add(actual.der);
        }
        return sb.toString();
    }
}
package librerias.estructurasDeDatos.deDispersion;
import librerias.estructurasDeDatos.modelos.Map;
import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import java.lang.Math;
/**
 * Implementacion de una TablaHash Enlazada
 * @author (profesores EDA) 
 * @version (Curso 2014/15)
 */
public class TablaHash<C, V> implements Map<C, V> {
   
    private EntradaHash<C,V>[] elArray;  // Array de entradas
    private int talla; // Número de datos almacenados en la tabla
    
    @SuppressWarnings("unchecked")
    public TablaHash(int tallaMaximaEstimada) {
            int capacidad = siguientePrimo((int)(tallaMaximaEstimada/0.75));
            elArray = new EntradaHash[capacidad];
            talla = 0;
    }
    
    /**  Obtiene un numero primo MAYOR o IGUAL a n, i.e. el primo que sigue a n
      *  @param n   Numero a partir del cual se buscara el siguiente primo
      *  @return    El siguiente numero primo apartir de n
      */
     protected static final int siguientePrimo(int n){
         if ( n % 2 == 0 ) n++;
         for ( ; !esPrimo(n); n += 2 ) ;
         return n;
     } 

     /**  Cromprueba si un numero es primo
       *  @param n   Numero a comprobar
       *  @return    true si el numero es primo, false al contrario
       */
     protected static final boolean esPrimo(int n){
         for (int i = 3 ; i*i <= n; i += 2 ) 
            if ( n % i == 0 ) return false;
         return true;
     } 

    /** Calcula la cubeta en la que debe estar un elemento
      * con clave c. Para ello primero obtiene el valor de
      * hash (hashCode) y a continuacion su indice hash
      * @param c Clave del dato a localizar
      * @return Cubeta en la que se encuentra el dato
      */
    private int indiceHash(C c) {
        int indice = c.hashCode() % this.elArray.length;
        if (indice < 0)
            indice += this.elArray.length;
        return indice;
    }
    
    /** Añade la entrada (clave,valor) y devuelve el antiguo valor
      * que tenia dicha clave, o null si no tenia ningun valor asociado
      */
    public V insertar(C c, V v) {
        V antiguoValor = null;
        int pos = indiceHash(c);
        EntradaHash<C, V> e = elArray[pos];
        while (e != null && !e.clave.equals(c))
            e = e.siguiente;
        if (e == null) { // Nueva entrada
             elArray[pos] = new EntradaHash<C,V>(c, v,elArray[pos]);
             talla++;
             if (factorCarga() > 0.75) rehashing();
        } else { // Entrada existente
             antiguoValor = e.valor;
             e.valor = v;
        }
        return antiguoValor;
    }
    
    /** Elimina la entrada con clave c y devuelve su valor asociado,
      *  o null si no hay ninguna entrada con dicha clave.
      */
    public V eliminar(C c) {
        int pos = indiceHash(c);
        EntradaHash<C, V> e = elArray[pos], ant = null;
        while (e != null && !e.clave.equals(c)) {
            ant = e;
            e = e.siguiente;
        }
        if (e == null) return null; // No encontrado
        if (ant == null) elArray[pos] = e.siguiente;
        else ant.siguiente = e.siguiente;
        talla--;
        return e.valor;
    }
    
    /** Busca la clave c y devuelve su informacion asociada
      * o null si no hay una entrada con dicha clave.
      */
    public V recuperar(C c) {
        EntradaHash<C, V> e = elArray[indiceHash(c)];
        while (e != null && !e.clave.equals(c))
            e = e.siguiente;
        if (e == null) return null;
        return e.valor;
    }
    
    /** Devuelve true si el Map esta vacío, sin datos.
     */
    public boolean esVacio() { return talla == 0; }
    
    /** Devuelve el numero de entradas que contiene el Map.
     */
    public int talla() { return talla; } 
    
    /** Rehashing
      **/
    @SuppressWarnings("unchecked")
    protected final void rehashing() {
        int nPrimo = siguientePrimo(elArray.length * 2);         
        EntradaHash<C, V>[] copiaAnt = elArray;
        elArray = new EntradaHash[nPrimo];
        //talla = 0;
        for(int i = 0; i < copiaAnt.length; i++){
           EntradaHash<C,V> actual = copiaAnt[i];
           while (actual != null){
              int pos = indiceHash(actual.clave);
              EntradaHash<C, V> aux2 = actual.siguiente;
              actual.siguiente = elArray[pos];
              elArray[pos] = actual;
              actual = aux2;
            }
        }  
     }  

    /** Devuelve una ListaConPI con las talla() claves del Map.
     */
    public ListaConPI<C> claves() {
       ListaConPI<C> lista = new LEGListaConPI<C>();
       for(int i = 0; i < elArray.length; i++){
           EntradaHash<C,V> actual = elArray[i];
           while (actual != null){
               lista.insertar(actual.clave);
               actual = actual.siguiente;
            }
                
        }      
        return lista;
    }
    
    /** Factor de carga = longitud media de las listas = numero de elementos / longitud del array
      */
    public final double factorCarga() {
         return (double)talla/(double)elArray.length;
    }
    /** Calcula la desviacion tipica de las longitudes de las listas 
        sOLUCIÓN: Raiz cuadrada de el sumatorio de las longitudes menos el factor de carga de cada cubeta y todo dividido entre las n cubetas*/
     public final double desviacionTipica() {
         
        double res = 0;
        int cont;  
        double fc = factorCarga();
        for(int i = 0; i < elArray.length; i++){
            cont = 0;
            EntradaHash<C,V> actual = elArray[i];
             while (actual != null){
               cont++;
               actual = actual.siguiente;
            }     
            double aux = cont - fc;
            res += aux * aux;
        }
        return Math.sqrt(res/elArray.length);
     }
     
    /** Devuelve un String que representa el histograma de ocupacion:
      * lineas, cada una de ellas con dos valores: longitudCubeta NumeroDeCubetas donde:
      * las lineas 0 a 8 contienen el número de cubetas de esa longitud, 0<=longitud<9
      * la ultima linea (9) contiene el número de cubetas de longitud 9 o más*/ 
    public String histograma(){
        String res = "";
        int [] longCubetas = new int[10];
        for(int i = 0; i < elArray.length; i++){
            int cont = 0;
            EntradaHash<C,V> actual = elArray[i];
             while (actual != null){
               cont++;
               actual = actual.siguiente;
            }
            if(cont > 8) longCubetas[9]++;
            else longCubetas[cont]++;
        }       
        for(int i = 0; i <= 9; i++){
            res += i+" "+longCubetas[i]+"\n";
        }
        return res;        
    }
}

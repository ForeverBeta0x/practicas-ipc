package librerias.estructurasDeDatos.deDispersion;

/**
 * Cada elemento de una cubeta
 * @author (profesores EDA) 
 * @version (Curso 2014/15)
 */
class EntradaHash<C, V> {
    C clave;
    V valor;
    EntradaHash<C, V> siguiente;
    
    public EntradaHash(C clave, V valor, EntradaHash<C, V> siguiente) {
        this.clave = clave;
        this.valor = valor;
        this.siguiente = siguiente;
    }
} 

package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Receta {

    private StringProperty titulo;
    private StringProperty instrucciones;
    private IntegerProperty valoracion;
    private IntegerProperty comensales;
    private IntegerProperty dificultad;
    private StringProperty ingredientes;
    private StringProperty plato;
    private StringProperty categoria;
    

     public Receta() {
        this(null);
    }

   
    public Receta(String titulo) {
        
    	this.titulo = new SimpleStringProperty(titulo);
        this.instrucciones = new SimpleStringProperty("Intrucciones de prueba");
        this.valoracion = new SimpleIntegerProperty(4);
        this.comensales = new SimpleIntegerProperty(2);
        this.dificultad = new SimpleIntegerProperty(3);
        this.ingredientes = new SimpleStringProperty("Pepinillo y mostaza");
        this.plato = new SimpleStringProperty("Primero");
        this.categoria = new SimpleStringProperty("Carnes");
        
    }


	public StringProperty getTitulo() {
		return titulo;
	}


	public void setTitulo(StringProperty titulo) {
		this.titulo = titulo;
	}


	public StringProperty getInstrucciones() {
		return instrucciones;
	}


	public void setInstrucciones(StringProperty instrucciones) {
		this.instrucciones = instrucciones;
	}


	public IntegerProperty getValoracion() {
		return valoracion;
	}


	public void setValoracion(IntegerProperty valoracion) {
		this.valoracion = valoracion;
	}


	public IntegerProperty getComensales() {
		return comensales;
	}


	public void setComensales(IntegerProperty comensales) {
		this.comensales = comensales;
	}


	public IntegerProperty getDificultad() {
		return dificultad;
	}


	public void setDificultad(IntegerProperty dificultad) {
		this.dificultad = dificultad;
	}


	public StringProperty getIngredientes() {
		return ingredientes;
	}


	public void setIngredientes(StringProperty ingredientes) {
		this.ingredientes = ingredientes;
	}


	public StringProperty getPlato() {
		return plato;
	}


	public void setPlato(StringProperty plato) {
		this.plato = plato;
	}


	public StringProperty getCategoria() {
		return categoria;
	}


	public void setCategoria(StringProperty categoria) {
		this.categoria = categoria;
	}


	
}
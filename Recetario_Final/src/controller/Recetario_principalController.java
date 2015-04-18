package controller;

import java.awt.TextArea;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import application.Main;
import application.Receta;;


public class Recetario_principalController {

	@FXML
	private TableView<Receta> tabla_recetas;
	@FXML
	private TableColumn<Receta,String> columna_recetas;
    @FXML
	private Label plato;
    @FXML
	private Label categoria;
    @FXML
	private Label titulo;
    @FXML
	private Label dificultad;
    @FXML
	private Label valoracion;
    @FXML
	private Label comensales;
    @FXML
	private TextArea ingredientes;
    
    private Main principal;
    
    public Recetario_principalController(){
    	
    }
	
    @FXML
    private void initialize(){
    	columna_recetas.setCellValueFactory(cellData -> cellData.getValue().getTitulo());
    }
    
    public void setMain(Main principal){
    	this.principal = principal;
    }
}

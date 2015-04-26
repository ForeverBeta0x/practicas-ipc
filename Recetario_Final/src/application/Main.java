package application;

import java.io.IOException;

import controller.Recetario_principalController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    //private BorderPane rootLayout;
    private ObservableList<Receta> recetaData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Recetario");

        //initRootLayout();

        showRecetario_Principal();
    }
    
    public Main() {
        
        recetaData.add(new Receta("Receta test 1"));
        recetaData.add(new Receta("Receta test 2"));
        recetaData.add(new Receta("Receta test 3"));
        recetaData.add(new Receta("Receta test 4"));
        recetaData.add(new Receta("Receta test 5"));
        recetaData.add(new Receta("Receta test 6"));
        recetaData.add(new Receta("Receta test 7"));
        
    }
    
    public ObservableList<Receta> getRecetaDta() {
        return recetaData;
    }

    /**
     * Initializes the root layout.
     */
   /* public void initRootLayout() {
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/rootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    
    public void showRecetario_Principal() {
    	try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/Recetario_principal.fxml"));
            AnchorPane recetario_principal = loader.load();

            
           // rootLayout.setCenter(recetario_principal);

            
            Recetario_principalController controller = loader.getController();
            controller.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
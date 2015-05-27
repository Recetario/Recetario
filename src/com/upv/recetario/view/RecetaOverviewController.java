package com.upv.recetario.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.controlsfx.dialog.Dialogs;

import com.upv.recetario.MainApp;
import com.upv.recetario.model.Receta;


public class RecetaOverviewController {
    @FXML
    private TableView<Receta> recetaTable;
    @FXML
    private TableColumn<Receta, String> nombrePlatoColumn;
    @FXML
    private TableColumn<Receta, String> categoriaColumn;

    @FXML
    private Label nombrePlatoLabel;
    @FXML
    private Label categoriaLabel;


    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public RecetaOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
    	nombrePlatoColumn.setCellValueFactory(cellData -> cellData.getValue().nombrePlatoProperty());
    	categoriaColumn.setCellValueFactory(cellData -> cellData.getValue().categoriaProperty());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp main) {
        this.mainApp = main;

        // Add observable list data to the table
        recetaTable.setItems(mainApp.getRecetaData());
    }
    
    @FXML
    private void handleDeleteReceta() {
    	 int selectedIndex = recetaTable.getSelectionModel().getSelectedIndex();
    	    if (selectedIndex >= 0) {
    	    	recetaTable.getItems().remove(selectedIndex);
    	    } else {
    	        // Nothing selected.
    	        Dialogs.create()
    	            .title("Advertencia")
    	            .masthead("No se ha seleccionado ninguna receta")
    	            .message("Por favor, seleccione una receta de la tabla")
    	            .showWarning();
    	    }
    }
    
    
    @FXML
    private void handleNewReceta() {
    	Receta tempReceta = new Receta();
        boolean okClicked = mainApp.showRecetaEditDialog(tempReceta);
        if (okClicked) {
            mainApp.getRecetaData().add(tempReceta);
        }
    }
    
    @FXML
    private void handleEditReceta() {
    	Receta selectedPerson = recetaTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
//            boolean okClicked = mainApp.showRecetaEditDialog(selectedPerson);
        	mainApp.showRecetaEditDialog(selectedPerson);
//            if (okClicked) {
//                showRecetaDetails(selectedPerson);
//            }

        } else {
            // Nothing selected.
        	Dialogs.create()
            .title("Advertencia")
            .masthead("No se ha seleccionado ninguna receta")
            .message("Por favor, seleccione una receta de la tabla")
            .showWarning();
        }
    }
    
    
    private void showRecetaDetails(Receta receta) {
        if (receta != null) {
            // Fill the labels with info from the person object.
            nombrePlatoLabel.setText(receta.getNombrePlato());
            categoriaLabel.setText(receta.getCategoria());

        } else {
            // Person is null, remove all the text.
        	nombrePlatoLabel.setText("");
            categoriaLabel.setText("");
            
        }
    }
}

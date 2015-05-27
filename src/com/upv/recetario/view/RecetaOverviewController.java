package com.upv.recetario.view;

import com.upv.recetario.MainApp;
import com.upv.recetario.model.Receta;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


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
    
    
}

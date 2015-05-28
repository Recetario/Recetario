package com.upv.recetario.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import com.upv.recetario.model.Receta;

public class RecetaEditDialogController {

    @FXML
    private TextField nombrePlatoField;

    @FXML
    private TextField comensalesField;

    @FXML
    private ComboBox<String> comboDificultad;
    
    @FXML
    private ComboBox<String> comboCategoria;
    
    ObservableList<String> itemsDificultad = FXCollections.observableArrayList("Alta", "Media", "Baja");
    ObservableList<String> itemsCategoria = FXCollections.observableArrayList("Entrante", "Principal", "Postre");
    
    private Stage dialogStage;
    private Receta receta;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	comboDificultad.setItems(itemsDificultad);    	
    	comboCategoria.setItems(itemsCategoria);
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void setReceta(Receta receta) {
        this.receta = receta;

        nombrePlatoField.setText(receta.getNombrePlato());
        comboCategoria.setValue(receta.getCategoria());
        comboDificultad.setValue(receta.getDificultad());
        comensalesField.setText(String.valueOf(receta.getComensales()));
    }

  
    public boolean isOkClicked() {
        return okClicked;
    }

   
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        	receta.setNombrePlato(nombrePlatoField.getText());
        	receta.setDificultad(comboDificultad.getValue());
        	receta.setCategoria(comboCategoria.getValue());
        	receta.setComensales(Integer.valueOf(comensalesField.getText()));
            okClicked = true;
            dialogStage.close();
        }
    }

    
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nombrePlatoField.getText() == null || nombrePlatoField.getText().length() == 0) {
            errorMessage += "Debe rellenar el nombre del plato \n"; 
        }       
        

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Dialogs.create()
                .title("Campos incorrectos")
                .masthead("Por favor, revise los campos incorrectos")
                .message(errorMessage)
                .showError();
            return false;
        }
    }
}
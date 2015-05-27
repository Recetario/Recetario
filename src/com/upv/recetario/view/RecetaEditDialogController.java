package com.upv.recetario.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import com.upv.recetario.model.Receta;

public class RecetaEditDialogController {

    @FXML
    private TextField nombrePlatoField;
    @FXML
    private TextField categoriaField;
    @FXML
    private TextField dificultadField;
    @FXML
    private TextField comensalesField;

    private Stage dialogStage;
    private Receta receta;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setReceta(Receta receta) {
        this.receta = receta;

        nombrePlatoField.setText(receta.getNombrePlato());
        categoriaField.setText(receta.getCategoria());
        dificultadField.setText(receta.getDificultad());
        comensalesField.setText(String.valueOf(receta.getComensales()));
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        	receta.setNombrePlato(nombrePlatoField.getText());
        	receta.setCategoria(categoriaField.getText());
        	receta.setDificultad(dificultadField.getText());
        	receta.setComensales(Integer.valueOf(comensalesField.getText()));
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
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
        if (categoriaField.getText() == null || categoriaField.getText().length() == 0) {
            errorMessage += "Debe rellenar la categoría\n"; 
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
package com.upv.recetario.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    
    @FXML
    private ComboBox<String> comboValoracion;
    
    @FXML
    private TextField tiempoPreparacionField;
    
    @FXML
    private TextField tiempoCoccionField;
    
    @FXML
    private TextArea ingredientesField;
    
    @FXML
    private TextArea formaPreparacionField;
    
    @FXML
    private TextArea observacionesField;
    
    @FXML
    private ImageView imageView;
    
    @FXML
    private TextField imageField;
    
    ObservableList<String> itemsDificultad = FXCollections.observableArrayList("Alta", "Media", "Baja");
    ObservableList<String> itemsCategoria = FXCollections.observableArrayList("Principal", "Primero", "Segundo", "Postre");
    ObservableList<String> itemsValoracion = FXCollections.observableArrayList("1", "2", "3", "4", "5");
    
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
    	comboValoracion.setItems(itemsValoracion);
//    	imageView.setImage( new Image("http://www.cochesdeocasion.com/images/sin_imagen_grande.jpg"));        
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void setReceta(Receta receta, boolean readOnly) {
        this.receta = receta;

        nombrePlatoField.setText(receta.getNombrePlato());
        comboCategoria.setValue(receta.getCategoria());        
        comboDificultad.setValue(receta.getDificultad());
        comensalesField.setText(String.valueOf(receta.getComensales()));
        comboValoracion.setValue(receta.getValoracion());
        tiempoPreparacionField.setText(String.valueOf(receta.getTiempoPreparacion()));
        tiempoCoccionField.setText(String.valueOf(receta.getTiempoCoccion()));
        ingredientesField.setText(receta.getIngredientes());
        formaPreparacionField.setText(receta.getPreparacion());
        observacionesField.setText(receta.getObservaciones());
        imageField.setText(receta.getImagenPlato());
        Image image1;
        if(receta.getImagenPlato() != null && !receta.getImagenPlato().equals("")){
        	image1 = new Image(receta.getImagenPlato());
        } else {
        	image1 = new Image("http://www.cochesdeocasion.com/images/sin_imagen_grande.jpg");        	
        }
        imageView.setImage(image1);
        
        
        
        if(readOnly){
        	nombrePlatoField.setDisable(true);
        	comboCategoria.setDisable(true);
        	comboDificultad.setDisable(true);
        	comensalesField.setDisable(true);
        	comboValoracion.setDisable(true);
        	tiempoPreparacionField.setDisable(true);
        	tiempoCoccionField.setDisable(true);
        	ingredientesField.setDisable(true);
            formaPreparacionField.setDisable(true);
            observacionesField.setDisable(true);
            imageField.setDisable(true);
        }
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
        	receta.setValoracion(comboValoracion.getValue());
        	receta.settTempoPreparacion(Integer.valueOf(tiempoPreparacionField.getText()));
        	receta.setTiempoCoccion(Integer.valueOf(tiempoCoccionField.getText()));
        	receta.setIngredientes(ingredientesField.getText());
        	receta.setPreparacion(formaPreparacionField.getText());
        	receta.setObservaciones(observacionesField.getText());
        	receta.setImagenPlato(imageField.getText());
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
        
        if (comensalesField.getText() == null || comensalesField.getText().length() == 0) {
            errorMessage += "El número de comensales de ser un número mayor que 1 \n"; 
        } else {
        	try{
        		if(!(Integer.valueOf(comensalesField.getText()).compareTo(1) >= 0)){
        			errorMessage += "El número de comensales de ser un número mayor que 1 \n"; 
        		}
        	} catch (Exception ex) {
        		errorMessage += "El número de comensales de ser un número mayor que 1 \n"; 
        	}
        }
        
        if (tiempoPreparacionField.getText() == null || tiempoPreparacionField.getText().length() == 0) {
            errorMessage += "El tiempo de preparación debe ser un número mayor que 1 \n"; 
        } else {
        	try{
        		if(!(Integer.valueOf(tiempoPreparacionField.getText()).compareTo(1) >= 0)){
        			errorMessage += "El tiempo de preparación debe ser un número mayor que 1 \n"; 
        		}
        	} catch (Exception ex) {
        		errorMessage += "El tiempo de preparación debe ser un número mayor que 1 \n"; 
        	}
        }
        
        if (tiempoCoccionField.getText() == null || tiempoCoccionField.getText().length() == 0) {
            errorMessage += "El tiempo de cocción debe ser un número mayor que 1 \n"; 
        } else {
        	try{
        		if(!(Integer.valueOf(tiempoCoccionField.getText()).compareTo(1) >= 0)){
        			errorMessage += "El tiempo de cocción debe ser un número mayor que 1 \n"; 
        		}
        	} catch (Exception ex) {
        		errorMessage += "El tiempo de cocción debe ser un número mayor que 1 \n"; 
        	}
        }
        
        if (ingredientesField.getText() == null || ingredientesField.getText().length() == 0) {
            errorMessage += "Debe rellenar los ingredientes \n"; 
        }  
        
        if (formaPreparacionField.getText() == null || formaPreparacionField.getText().length() == 0) {
            errorMessage += "Debe rellenar la forma de preparación \n"; 
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
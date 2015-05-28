package com.upv.recetario;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.controlsfx.dialog.Dialogs;

import com.upv.recetario.model.Receta;
import com.upv.recetario.model.RecetaListWrapper;
import com.upv.recetario.view.RecetaEditDialogController;
import com.upv.recetario.view.RecetaOverviewController;
import com.upv.recetario.view.RootLayoutController;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Receta> recetaData = FXCollections.observableArrayList();

    public MainApp() {
        // Add some sample data
//    	recetaData.add(new Receta("Tarta de queso", "Postre", "Media"));
//    	recetaData.add(new Receta("Paella", "Principal", "Media"));
//    	recetaData.add(new Receta("Ensalada mediterránea", "Entrante", "Baja"));        
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Recetario");

        this.primaryStage.getIcons().add(new Image("file:resources/images/icon_recetario_32.png"));
        
        initRootLayout();

        showRecetaOverview();
    }
    

    /**
     * Initializes the root layout.
     */    
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Try to load last opened person file.
        File file = getRecetaFilePath();
        if (file != null) {
            loadRecetaDataFromFile(file);
        }
    }

    
    public void showRecetaOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RecetaOverview.fxml"));
            AnchorPane recetaOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(recetaOverview);
            
            RecetaOverviewController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public ObservableList<Receta> getRecetaData() {
        return recetaData;
    }
    
    
    public boolean showRecetaEditDialog(Receta receta, String windowTitle, boolean readOnly) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RecetaEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(windowTitle);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            RecetaEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setReceta(receta, readOnly);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public File getRecetaFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }
    
    public void setRecetaFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("Recetario - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("Recetario");
        }
    }
    
    
    /**
     * Loads person data from the specified file. The current person data will
     * be replaced.
     * 
     * @param file
     */
    public void loadRecetaDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(RecetaListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            RecetaListWrapper wrapper = (RecetaListWrapper) um.unmarshal(file);

            recetaData.clear();
            recetaData.addAll(wrapper.getRecetas());

            // Save the file path to the registry.
            setRecetaFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Dialogs.create()
                    .title("Error")
                    .masthead("No se ha podido cargar el fichero:\n" + file.getPath())
                    .showException(e);
        }
    }
    
    
    public void saveRecetaDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(RecetaListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            RecetaListWrapper wrapper = new RecetaListWrapper();
            wrapper.setRecetas(recetaData);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            setRecetaFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Dialogs.create().title("Error")
                    .masthead("No se ha podido guardar el fichero:\n" + file.getPath())
                    .showException(e);
        }
    }
}
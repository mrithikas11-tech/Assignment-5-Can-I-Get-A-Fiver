package canigetafiver.rockpaperscissors;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class SceneSwitch {
    private Stage stage;
    public SceneSwitch(Stage stage) {
        this.stage = stage;
    }
    public void switchScene(String fxml){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch( IOException e){

        }
    }



}

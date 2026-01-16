package fr.unicaen.iut.tpnote2025;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;



public class BreakerApplication extends Application {
	
  @Override
  public void start(Stage stage) throws IOException {
	    FXMLLoader fxmlLoader = new FXMLLoader(BreakerApplication.class.getResource("primary.fxml"));
	    fxmlLoader.setController(new BreakerController());
	    Scene scene = new Scene(fxmlLoader.load());
	    
	    
	    stage.setTitle("Breaker");
	    stage.setScene(scene);
	    stage.show();
	    
    stage.show();
  }

  
  

  
  public static void main(String[] args) {
    launch();
  }
}

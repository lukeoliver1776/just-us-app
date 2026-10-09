package com.example.app;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entry point for the Just Us app.
 */
public class App extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    Parent root = FXMLLoader.load(App.class.getResource("RoomPage.fxml"));
    stage.setScene(new Scene(root, 400, 300));
    stage.setTitle("Just Us");
    stage.show();
  }
  
  /**
   * Launches the application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
}

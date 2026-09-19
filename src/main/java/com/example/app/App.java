package com.example.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Entry point for the Just Us app.
 */
public class App extends Application {
  @Override
  public void start(Stage stage) {
    Label label = new Label("Hello, just-us!");
    StackPane root = new StackPane(label);
    stage.setScene(new Scene(root, 400, 300));
    stage.setTitle("just-us");
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}

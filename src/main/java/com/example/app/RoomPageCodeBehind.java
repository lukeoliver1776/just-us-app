package com.example.app;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Code behind for the room page. Connects controls to code.
 */
public class RoomPageCodeBehind {    
  @FXML
  private TextField playerOneField;

  @FXML
  private TextField playerTwoField;

  @FXML
  private Label errorLabel;

  @FXML
  private Button startGameButton;

  @FXML
  private void handleStartGame() throws IOException {
    Room room;
    try {
      room = new Room(playerOneField.getText(), playerTwoField.getText());
    } catch (IllegalArgumentException exception) {
      errorLabel.setText(exception.getMessage());
      return;
    }

    FXMLLoader loader = new FXMLLoader(getClass().getResource("GamePage.fxml"));
    Parent root = loader.load();
    GamePageCodeBehind gamePage = loader.getController();
    gamePage.setRoom(room);

    Stage stage = (Stage) startGameButton.getScene().getWindow();
    stage.setScene(new Scene(root, 400, 400));
  }
}

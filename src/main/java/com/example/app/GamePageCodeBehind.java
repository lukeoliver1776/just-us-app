package com.example.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Code behind for the game page. It connects the pages controls to the code.
 */
public class GamePageCodeBehind {
  @FXML
  private Button backButton;

  @FXML
  private Label statusLabel;

  private Room room;
  private final Game game = new Game();

  /**
   * Sets the page where the two players play.
   *
   * @param room the room holding the player names
   */
  public void setRoom(Room room) {
    this.room = room;
    statusLabel.setText(game.getStatusMessage(room));
  }

  @FXML
  private void handleCellClick(ActionEvent event) {
    Button clicked = (Button) event.getSource();
    int row = GridPane.getRowIndex(clicked);
    int col = GridPane.getColumnIndex(clicked);

    try {
      game.makeMove(row, col);
    } catch (IllegalArgumentException | IllegalStateException exception) {
      return;
    }

    clicked.setText(String.valueOf(game.getMarkAt(row, col)));
    statusLabel.setText(game.getStatusMessage(room));
  }
}

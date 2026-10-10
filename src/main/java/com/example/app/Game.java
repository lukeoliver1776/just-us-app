package com.example.app;

/**
 * Reperesents one game of tic tac tow between players 1 and 2.
 */
public class Game {
  private static final int SIZE = 3;
  private static final char EMPTY = ' ';
  private static final char PLAYER_ONE_MARK = 'X';
  private static final char PLAYER_TWO_MARK = 'O';

  private final char[][] grid;
  private char currentPlayer;
  private char winner;
  private int movesMade;

  /**
   * Creates a new game with an empty board. Player 1 moves first.
   */
  public Game() {
    this.grid = new char[SIZE][SIZE];
    for (int row = 0; row < SIZE; row++) {
      for (int col = 0; col < SIZE; col++) {
        this.grid[row][col] = EMPTY;
      }
    }
    this.currentPlayer = PLAYER_ONE_MARK;
    this.winner = EMPTY;
    this.movesMade = 0;
  }

  /**
   * Places the current player's mark on the board, then passes the turn to the other player.
   *
   * @param row the row of the square, from 0 to 2
   * @param col the column of the square, from 0 to 2
   * @throws IllegalArgumentException if the square is off the board or already taken
   * @throws IllegalStateException if the game is already over
   */
  public void makeMove(int row, int col) {
    if (isOver()) {
      throw new IllegalStateException("The game is already over.");
    }
    checkInBounds(row, col);
    if (grid[row][col] != EMPTY) {
      throw new IllegalArgumentException("That square is already taken.");
    }

    grid[row][col] = currentPlayer;
    movesMade++;

    if (hasWon(currentPlayer)) {
      winner = currentPlayer;
    } else if (currentPlayer == PLAYER_ONE_MARK) {
      currentPlayer = PLAYER_TWO_MARK;
    } else {
      currentPlayer = PLAYER_ONE_MARK;
    }
  }

  /**
   * Gets the mark in a square.
   *
   * @param row the row of the square, from 0 to 2
   * @param col the column of the square, from 0 to 2
   * @return x, o, or a space if the square is empty
   * @throws IllegalArgumentException if the square is off the board
   */
  public char getMarkAt(int row, int col) {
    checkInBounds(row, col);
    return grid[row][col];
  }

  /**
   * Gets the mark of the player whose turn is.
   *
   * @return x or o
   */
  public char getCurrentPlayer() {
    return currentPlayer;
  }

  /**
   * Gets the winner of the game.
   *
   * @return x or o if someone has won, or a space if there is no winner
   */
  public char getWinner() {
    return winner;
  }

  /**
   * Checks whether the game ended with a full board and no winner.
   *
   * @return true if the game is a draw
   */
  public boolean isDraw() {
    return winner == EMPTY && movesMade == SIZE * SIZE;
  }

  /**
   * Checks whether the game has ended, by a win or a draw.
   *
   * @return true if no more moves can be made
   */
  public boolean isOver() {
    return winner != EMPTY || movesMade == SIZE * SIZE;
  }

  private void checkInBounds(int row, int col) {
    if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
      throw new IllegalArgumentException("Row and column must be between 0 and 2.");
    }
  }

  private boolean hasWon(char mark) {
    for (int i = 0; i < SIZE; i++) {
      boolean rowWins = grid[i][0] == mark && grid[i][1] == mark && grid[i][2] == mark;
      boolean colWins = grid[0][i] == mark && grid[1][i] == mark && grid[2][i] == mark;
      if (rowWins || colWins) {
        return true;
      }
    }
    boolean diagonalWins = grid[0][0] == mark && grid[1][1] == mark && grid[2][2] == mark;
    boolean otherDiagonalWins = grid[0][2] == mark && grid[1][1] == mark && grid[2][0] == mark;
    return diagonalWins || otherDiagonalWins;
  }
}

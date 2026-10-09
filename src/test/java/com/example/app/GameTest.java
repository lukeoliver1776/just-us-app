package com.example.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests for the game class.
 */
public class GameTest {
    private void playMoves(Game game, int[][] moves) {
        for (int[] move : moves) {
          game.makeMove(move[0], move[1]);
        }
      }
    
      @Test
      void newGameStartsEmptyWithPlayerOneToMove() {
        Game game = new Game();
    
        assertEquals('X', game.getCurrentPlayer());
        assertEquals(' ', game.getWinner());
        assertEquals(' ', game.getMarkAt(1, 1));
        assertFalse(game.isOver());
        assertFalse(game.isDraw());
      }
    
      @Test
      void makeMovePlacesMarkAndPassesTurn() {
        Game game = new Game();
    
        game.makeMove(0, 0);
    
        assertEquals('X', game.getMarkAt(0, 0));
        assertEquals('O', game.getCurrentPlayer());
      }
    
      @Test
      void turnsAlternateBetweenPlayers() {
        Game game = new Game();
    
        game.makeMove(0, 0);
        game.makeMove(1, 1);
    
        assertEquals('O', game.getMarkAt(1, 1));
        assertEquals('X', game.getCurrentPlayer());
      }
    
      @Test
      void makeMoveRejectsTakenSquare() {
        Game game = new Game();
        game.makeMove(0, 0);
    
        assertThrows(IllegalArgumentException.class, () -> game.makeMove(0, 0));
      }
    
      @Test
      void makeMoveRejectsRowBelowZero() {
        Game game = new Game();
    
        assertThrows(IllegalArgumentException.class, () -> game.makeMove(-1, 0));
      }
    
      @Test
      void makeMoveRejectsRowAboveTwo() {
        Game game = new Game();
    
        assertThrows(IllegalArgumentException.class, () -> game.makeMove(3, 0));
      }
    
      @Test
      void makeMoveRejectsColumnBelowZero() {
        Game game = new Game();
    
        assertThrows(IllegalArgumentException.class, () -> game.makeMove(0, -1));
      }
    
      @Test
      void makeMoveRejectsColumnAboveTwo() {
        Game game = new Game();
    
        assertThrows(IllegalArgumentException.class, () -> game.makeMove(0, 3));
      }
    
      @Test
      void getMarkAtRejectsSquareOffTheBoard() {
        Game game = new Game();
    
        assertThrows(IllegalArgumentException.class, () -> game.getMarkAt(5, 5));
      }
    
      @Test
      void playerOneWinsWithARow() {
        Game game = new Game();
    
        playMoves(game, new int[][] {{0, 0}, {1, 0}, {0, 1}, {1, 1}, {0, 2}});
    
        assertEquals('X', game.getWinner());
        assertTrue(game.isOver());
        assertFalse(game.isDraw());
      }
    
      @Test
      void playerOneWinsWithAColumn() {
        Game game = new Game();
    
        playMoves(game, new int[][] {{0, 0}, {0, 1}, {1, 0}, {1, 1}, {2, 0}});
    
        assertEquals('X', game.getWinner());
      }
    
      @Test
      void playerOneWinsWithTheMainDiagonal() {
        Game game = new Game();
    
        playMoves(game, new int[][] {{0, 0}, {0, 1}, {1, 1}, {0, 2}, {2, 2}});
    
        assertEquals('X', game.getWinner());
      }
    
      @Test
      void playerOneWinsWithTheOtherDiagonal() {
        Game game = new Game();
    
        playMoves(game, new int[][] {{0, 2}, {0, 0}, {1, 1}, {0, 1}, {2, 0}});
    
        assertEquals('X', game.getWinner());
      }
    
      @Test
      void playerTwoCanWin() {
        Game game = new Game();
    
        playMoves(game, new int[][] {{0, 0}, {1, 0}, {0, 1}, {1, 1}, {2, 2}, {1, 2}});
    
        assertEquals('O', game.getWinner());
        assertTrue(game.isOver());
      }
    
      @Test
      void fullBoardWithNoWinnerIsADraw() {
        Game game = new Game();
    
        playMoves(game, new int[][] {
            {0, 0}, {0, 1}, {0, 2}, {1, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 0}, {2, 2}});
    
        assertEquals(' ', game.getWinner());
        assertTrue(game.isDraw());
        assertTrue(game.isOver());
      }
    
      @Test
      void makeMoveRejectsMovesAfterTheGameIsOver() {
        Game game = new Game();
        playMoves(game, new int[][] {{0, 0}, {1, 0}, {0, 1}, {1, 1}, {0, 2}});
    
        assertThrows(IllegalStateException.class, () -> game.makeMove(2, 2));
      }
}

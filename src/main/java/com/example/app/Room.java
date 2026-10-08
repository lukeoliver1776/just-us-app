package com.example.app;

/**
 * this class is a room for two players
 */
public class Room {
    private final String playerOneName;
    private final String playerTwoName;
  
    /**
     * creates a room for two players
     *
     * @param playerOneName the name of the first player
     * @param playerTwoName the name of the second player
     * @throws IllegalArgumentException if either name is blank
     */
    public Room(String playerOneName, String playerTwoName) {
      if (playerOneName == null || playerOneName.isBlank()) {
        throw new IllegalArgumentException("Player one name cannot be null or blank.");
      }
      if (playerTwoName == null || playerTwoName.isBlank()) {
        throw new IllegalArgumentException("Player two name cannot be null or blank.");
      }
      this.playerOneName = playerOneName;
      this.playerTwoName = playerTwoName;
    }
  
    /**
     * Gets the name of the first player
     *
     * @return the first player's name
     */
    public String getPlayerOneName() {
      return playerOneName;
    }
  
    /**
     * Gets the name of the second player.
     *
     * @return the second player's name
     */
    public String getPlayerTwoName() {
      return playerTwoName;
    }
}

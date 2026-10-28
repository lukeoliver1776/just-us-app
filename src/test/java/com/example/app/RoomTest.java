package com.example.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class RoomTest {
    
  @Test
  void constructorStoresBothPlayerNames() {
    Room room = new Room("Luke", "Sam");

    assertEquals("Luke", room.getPlayerOneName());
    assertEquals("Sam", room.getPlayerTwoName());
  }

  @Test
  void constructorRejectsNullPlayerOneName() {
    assertThrows(IllegalArgumentException.class, () -> new Room(null, "Sam"));
  }

  @Test
  void constructorRejectsBlankPlayerOneName() {
    assertThrows(IllegalArgumentException.class, () -> new Room("   ", "Sam"));
  }

  @Test
  void constructorRejectsNullPlayerTwoName() {
    assertThrows(IllegalArgumentException.class, () -> new Room("Luke", null));
  }

  @Test
  void constructorRejectsBlankPlayerTwoName() {
    assertThrows(IllegalArgumentException.class, () -> new Room("Luke", ""));
  }
}

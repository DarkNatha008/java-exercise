package fr.unicaen.iut.tpnote2025.model;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;

public class PlayerStats {
  private final ReadOnlyIntegerWrapper points = new ReadOnlyIntegerWrapper(0);
  private final ReadOnlyIntegerWrapper lives = new ReadOnlyIntegerWrapper(3);
  private float multiplier = 1;

  public void newGame() {
    points.set(0);
    lives.set(3);
    multiplier = 1;
  }

  public ReadOnlyIntegerProperty pointsProperty() { return points.getReadOnlyProperty(); }
  public ReadOnlyIntegerProperty livesProperty() { return lives.getReadOnlyProperty(); }

  public int getPoints() { return points.get(); }
  public int getLives() { return lives.get(); }
  public void setMultiplier(float multiplier) { this.multiplier = multiplier; }

  void addPoints(int points) {
    this.points.set((int) (getPoints() + points*multiplier));
  }

  boolean drainLives() {
    int left = lives.get() - 1;
    if (left < 0) return false;
    lives.set(left);
    return (left > 0);
  }
}

package fr.unicaen.iut.tpnote2025.model.entities;


import javafx.scene.paint.Color;

public class Drop extends MovingObject {
  private final Runnable onActivate;

  public Drop(float x, float y, Runnable onActivate, float radius, Color color) {
    super(x, y, radius, color);
    this.onActivate = onActivate;
    setVelX(0.0f);
    setVelY(0.0075f);
  }

  public void activate() {
    onActivate.run();
  }

}

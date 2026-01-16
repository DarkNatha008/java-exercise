package fr.unicaen.iut.tpnote2025.model.entities;

import javafx.scene.paint.Color;

public class Brick extends Block {

  public static final float WIDTH = .04f;
  public static final float HEIGHT = .015f;

  public Brick(float x, float y, Color color) {
    super(x, y, WIDTH, HEIGHT, color);
  }

}

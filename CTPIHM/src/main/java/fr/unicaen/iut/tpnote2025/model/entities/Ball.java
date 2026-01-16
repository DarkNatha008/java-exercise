package fr.unicaen.iut.tpnote2025.model.entities;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Ball extends MovingObject {

  private static final Color BALL_COLOR = Color.SILVER;

  public Ball(float x, float y, float radius) {
    super(x, y, radius, BALL_COLOR);
    setVelX((float) ((Math.random()-0.5)*0.03));
    setVelY((float) (-0.015f+Math.random()*0.0015));
  }

  public void updateVelocityAfterReflection(float normalX, float normalY, float velX, float velY) {
    updateVelocityAfterReflection(new Point2D(normalX, normalY), new Point2D(velX, velY));
  }

  public void updateVelocityAfterReflection(Point2D normal, Point2D otherVelocity) {
    Point2D norm = normal.normalize();
    Point2D velocity = new Point2D(getVelX(), getVelY());
    Point2D resVelocity;
    if (otherVelocity == null) {
      resVelocity = velocity.subtract(norm.multiply(2*norm.dotProduct(velocity)));
    } else {
      resVelocity = velocity.subtract(norm.multiply(norm.dotProduct(velocity)-norm.dotProduct(otherVelocity)));
    }
    setVelX(resVelocity.getX());
    setVelY(resVelocity.getY());
  }

}

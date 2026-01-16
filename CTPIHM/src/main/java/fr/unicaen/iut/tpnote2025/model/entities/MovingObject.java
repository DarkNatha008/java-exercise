package fr.unicaen.iut.tpnote2025.model.entities;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.scene.paint.Color;

public class MovingObject extends Entity {
  private final FloatProperty velX = new SimpleFloatProperty(this, "velX");
  private final FloatProperty velY = new SimpleFloatProperty(this, "velY");
  private final FloatProperty radius = new SimpleFloatProperty(this, "radius");

  public float getVelX() {
    return velX.get();
  }
  public float getVelY() {
    return velY.get();
  }
  public float getRadius() {
    return radius.get();
  }

  public FloatProperty velXProperty() {
    return velX;
  }
  public FloatProperty velYProperty() {
    return velY;
  }
  public FloatProperty radiusProperty() {
    return radius;
  }

  public void setVelX(float velX) {this.velX.set(velX);}
  public void setVelY(float velY) {this.velY.set(velY);}
  public void setVelX(double velX) {this.velX.setValue(velX);}
  public void setVelY(double velY) {this.velY.setValue(velY);}

  public MovingObject(float x, float y, float radius, Color color) {
    super(x, y, color);
    this.radius.set(radius);
    velX.setValue((Math.random()-0.5)*0.015);
    velY.setValue(-0.010f+Math.random()*0.0015);
  }

  public void updatePosition(float deltaTime) {
    x.set(getX() + getVelX() * deltaTime);
    y.set(getY() + getVelY() * deltaTime);
  }

  public void boostSpeed(float factor) {
    velX.setValue(velX.getValue()*factor);
    velY.setValue(velY.getValue()*factor);
  }

}

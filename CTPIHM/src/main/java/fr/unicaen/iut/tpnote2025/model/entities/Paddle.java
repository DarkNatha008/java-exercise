package fr.unicaen.iut.tpnote2025.model.entities;

import fr.unicaen.iut.tpnote2025.model.Impact;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Paddle extends Block {
  public static final float DEFAULT_PADDLE_WIDTH = 0.05f;
  public static final float PADDLE_HEIGHT = 0.01f;
  public static final Color PADDLE_COLOR = Color.RED;
  private final FloatProperty curvature = new SimpleFloatProperty(this, "curvature");
  private final DoubleBinding startAngle = Bindings.createDoubleBinding(
      ()->Math.toDegrees(Math.atan2(getCurvature(), getWidth())), curvatureProperty(), widthProperty());
  private final DoubleBinding endAngle = Bindings.createDoubleBinding(
      ()->Math.toDegrees(Math.atan2(getCurvature(), -getWidth())), curvatureProperty(), widthProperty());

  public FloatProperty curvatureProperty() {
    return curvature;
  }
  public DoubleBinding startAngleProperty() { return startAngle; }
  public DoubleBinding endAngleProperty() { return endAngle; }

  public float getCurvature() {
    return curvature.get();
  }
  public double getStartAngle() { return startAngle.get(); }
  public double getEndAngle() { return endAngle.get(); }

  public Paddle(float x, float y, float width, float height, float curvature) {
    super(x, y, width, height, PADDLE_COLOR);
    this.curvature.set(curvature);
  }

  public Impact hits(MovingObject ball) {
    if (ball.getVelY() < 0) return null; // going up
    if (ball.getY() > 1) return null; // out of screen
    if (ball.getY()+ball.getRadius() < getY()-getHeight()/2) return null; // too high
    if (ball.getX() < getX() - getWidth() - ball.getRadius()) return null; // too left
    if (ball.getX() > getX() + getWidth() + ball.getRadius()) return null; // too right

    float dx = ball.getX() - getX();
    float dy = ball.getY() - getY() - getCurvature();
    float radius = getHeight()/2 + getCurvature();
    double dh = Math.sqrt(dx*dx + dy*dy) - radius; // distance between the ball center and the upward edge of the paddle
    if (dh > 0 && dh < ball.getRadius()) {
      return new Impact(dh, 0, new Point2D(dx, dy).normalize(), this);
    } else
      return null;
  }

  public void setX(double newX) {
    xProperty().setValue(newX);
  }
}

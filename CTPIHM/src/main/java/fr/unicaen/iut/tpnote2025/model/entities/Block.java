package fr.unicaen.iut.tpnote2025.model.entities;

import fr.unicaen.iut.tpnote2025.model.Impact;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Block extends Entity {
  private final FloatProperty width = new SimpleFloatProperty(this, "width");
  private final FloatProperty height = new SimpleFloatProperty(this, "height");

  private record Datum(Point2D corner, Point2D side, Point2D normal, float length) {}
  private final Datum[] data = new Datum[4];

  public FloatProperty widthProperty() {
    return width;
  }
  public FloatProperty heightProperty() {
    return height;
  }

  public float getHeight() {
    return height.get();
  }
  public float getWidth() {
    return width.get();
  }

  public Block(float x, float y, float width, float height, Color color) {
    super(x, y, color);
    this.width.setValue(width);
    this.height.setValue(height);
    data[0] = new Datum(new Point2D(x-width, y-height), new Point2D(0, 1), new Point2D(-1, 0), height*2);
    data[1] = new Datum(new Point2D(x-width, y+height), new Point2D(1, 0), new Point2D(0, 1), width*2);
    data[2] = new Datum(new Point2D(x+width, y+height), new Point2D(0, -1), new Point2D(+1, 0), height*2);
    data[3] = new Datum(new Point2D(x+width, y-height), new Point2D(-1, 0), new Point2D(0, -1), width*2);
  }

  public Impact hits(MovingObject ball) {
    if (ball.getX() + ball.getRadius() < data[0].corner.getX() ||
        ball.getX() - ball.getRadius() > data[2].corner.getX() ||
        ball.getY() + ball.getRadius() < data[0].corner.getY() ||
        ball.getY() - ball.getRadius() > data[2].corner.getY())
      return null; // no collision chance
    Impact res = null;
    for (Datum datum : data) {
      Point2D oneCorner = datum.corner;
      Point2D oneSide = datum.side;
      Point2D oneNorm = datum.normal;
      Point2D AB = new Point2D(
          ball.getX()-oneCorner.getX(),
          ball.getY()-oneCorner.getY());
      double dH = AB.dotProduct(oneNorm);
      if (dH > 0 && dH <= ball.getRadius()) { // exterior side of the segment
        double dL = oneSide.dotProduct(AB);
        if (dL > 0 && dL < datum.length) { // collision with the segment body
          if (res == null) res = new Impact(dH, dL, oneNorm,this);
          else if (res.dh() > dH) res = new Impact(dH, dL, oneNorm,this);
        } else {
          res = hitsCorner(ball, datum, dL, AB, oneSide, res);
        }
      }
    }
    return res;
  }

  private Impact hitsCorner(MovingObject ball, Datum datum, double dL, Point2D AB, Point2D oneSide, Impact res) {
    double dH;
    Point2D theNorm = null;
    if (dL > -ball.getRadius() && dL <= 0) { // collision with the first corner
      theNorm = AB;
    } else if (dL >= datum.length && dL < datum.length+ ball.getRadius()) { // collision with the last corner
      theNorm = AB.subtract(oneSide.multiply(datum.length));
    }
    if (theNorm != null) {
      dH = theNorm.magnitude();
      if (dH > ball.getRadius()) return res;
      theNorm = AB.multiply(1. / dH);
      if (ball.getVelX() * theNorm.getX() + ball.getVelY() * theNorm.getY() < 0) {
        if (res == null) res = new Impact(dH, dL, theNorm, this);
        else if (res.dh() > dH) res = new Impact(dH, dL, theNorm, this);
      }
    }
    return res;
  }
}

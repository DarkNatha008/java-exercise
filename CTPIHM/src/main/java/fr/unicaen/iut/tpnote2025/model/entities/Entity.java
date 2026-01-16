package fr.unicaen.iut.tpnote2025.model.entities;

import javafx.beans.binding.ObjectExpression;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class Entity {
  protected final FloatProperty x = new SimpleFloatProperty(this, "x");
  protected final FloatProperty y = new SimpleFloatProperty(this, "y");
  private final ObjectProperty<Color> color = new SimpleObjectProperty<>(Color.LIMEGREEN);

  public float getX() {
    return x.get();
  }
  public float getY() {
    return y.get();
  }
  public Color getColor() {
    return color.get();
  }

  public FloatProperty xProperty() {
    return x;
  }
  public FloatProperty yProperty() {
    return y;
  }
  public ObjectExpression<Color> colorProperty() {
    return color;
  }

  public void setX(float x) {this.x.set(x);}
  public void setY(float y) {this.y.set(y);}
  public void setColor(Color color) {this.color.set(color);}

  public Entity(float x, float y, Color color) {
    this.x.set(x);
    this.y.set(y);
    this.color.set(color);
  }

  @Override
  public String toString() {
    return "%s{x=%s, y=%s, color=%s}".formatted(this.getClass().getSimpleName(),getX(), getY(), getColor());
  }
}

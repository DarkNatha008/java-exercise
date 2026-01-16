package fr.unicaen.iut.tpnote2025.model;

import fr.unicaen.iut.tpnote2025.model.entities.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.*;

public class GameModel {
  public static final double GRAVITY = 0.000004;
  public static final int POINTS_FOR_DROP = 100;
  private static final int POINTS_FOR_BRICK = 10;

  public final List<Block> walls = new ArrayList<>();
  public final ObservableList<Brick> bricks = FXCollections.observableArrayList();
  public final ObservableList<Ball> balls = FXCollections.observableArrayList();
  public final ObservableList<Drop> drops = FXCollections.observableArrayList();
  public final SimpleBooleanProperty paused = new SimpleBooleanProperty(true);
  public Paddle paddle;

  private final PlayerStats playerStats = new PlayerStats();

  public GameModel() {
    paddle = new Paddle(.50f,.98f, Paddle.DEFAULT_PADDLE_WIDTH,Paddle.PADDLE_HEIGHT,.25f);
    walls.addAll(List.of(
        new Block(0f, .50f, .01f, .50f, Color.GOLD),
        new Block(1.00f, .50f, .01f, .50f, Color.GOLD),
        new Block(.50f, 0, .51f, .01f, Color.GOLD)
    ));
    newGame();
  }

  public ReadOnlyIntegerProperty pointsProperty() {
    return playerStats.pointsProperty();
  }

  public ReadOnlyIntegerProperty livesProperty() {
    return playerStats.livesProperty();
  }

  public int getPoints() {
    return playerStats.getPoints();
  }

  public int getLives() {
    return playerStats.getLives();
  }

  public void newGame() {
    paused.set(true);
    playerStats.newGame();
    drops.clear();
    balls.clear();
    createNewBall(0.5f);
    createBricks(Brick.HEIGHT*2.1f, Brick.WIDTH*2.1f);
  }

  private void nextLevel() {
    paused.set(true);
    balls.clear();
    drops.clear();
    createNewBall(0.5f);
    createBricks((float) (0.04+0.09*Math.random()), (float) (0.1+0.1*Math.random()));
  }

  private void createBricks(float yStep, float xStep) {
    bricks.clear();
    for (float y=0.1f; y<0.4f; y+= yStep) {
      for (float x=0.1f; x<=0.91f; x+= xStep) {
        bricks.add(new Brick(x, y, new Color((0.2+x)/2, (0.29+2*x+3*y)/4, (0.6+y)/2, 1)));
      }
    }
  }

  private void createNewDrop(Brick destroyedBrick) {
    switch (new Random().nextInt(3)) {
      case 0 -> drops.add(new Drop(destroyedBrick.getX(), destroyedBrick.getY(),
          this::createNewBall, 0.025f, Color.GREEN));
      case 1 -> drops.add(new Drop(destroyedBrick.getX(), destroyedBrick.getY(),
          () -> {
            createGrowTimeLine( 0.1f, Color.BLUE).play();
            playerStats.setMultiplier(Paddle.DEFAULT_PADDLE_WIDTH/0.1f);
          }, 0.025f, Color.BLUE));
      case 2 -> drops.add(new Drop(destroyedBrick.getX(), destroyedBrick.getY(),
          () -> {
            createGrowTimeLine( 0.025f, Color.CORAL).play();
            playerStats.setMultiplier(Paddle.DEFAULT_PADDLE_WIDTH/0.025f);
          }, 0.025f, Color.CORAL));
      default -> {
      }
    }
  }

  public void createNewBall() {
    createNewBall(paddle.getX());
  }

  private void createNewBall(float atX) {
    balls.add(new Ball(atX, 0.96f, .01f));
  }

  public void update(long elapsedTime) {
    float delta = elapsedTime / 30000000f; // 30ms
    float trueDelta = delta;
    if (paused.get()) return;
    float Dmax = applyGravityAndComputeDeltaMax(delta);
    int cpt = 1;
    while (Dmax < delta) {
      delta /= 2;
      ++cpt;
    }
    if (cpt > 1) System.out.printf("delta: %f reduced to %d * %f\n", trueDelta, cpt, delta);
    while (cpt-- > 0) {
      updateDrops(delta);
      updateBalls(delta);
      if (balls.isEmpty()) {
        if (playerStats.drainLives()) {
          paused.set(true);
          balls.add(new Ball(.51f, .96f, .01f));
        } else {
          paused.set(true);
        }
      }
    }
    if (bricks.isEmpty()) nextLevel();
  }

  private void updateBalls(float delta) {
    for (ListIterator<Ball> iter = balls.listIterator(); iter.hasNext(); ) {
      Ball ball = iter.next();
      ball.updatePosition(delta);
      if (ball.getY() > 1.1f) {
        iter.remove();
        continue;
      }
      checkBallCollision(ball, delta);
      checkWallCollision(ball, delta);
      checkBrickCollision(ball, delta);
      checkPaddleCollision(ball, delta);
    }
  }

  private void updateDrops(float delta) {
    for (Iterator<Drop> iterator = drops.iterator(); iterator.hasNext(); ) {
      Drop drop = iterator.next();
      drop.updatePosition(delta);
      if (paddle.hits(drop) != null) {
        iterator.remove();
        drop.activate();
        playerStats.addPoints(POINTS_FOR_DROP);
      }
    }
  }

  private float applyGravityAndComputeDeltaMax(float trueDelta) {
    float Dmax = 1;
    for (Drop drop : drops) {
      drop.setVelY(drop.getVelY() + trueDelta *GRAVITY);
      float div = drop.getRadius();
      if (drop.getVelX() != 0)
        Dmax = Math.min( Dmax, Math.abs(div / drop.getVelX()));
      if (drop.getVelY() != 0)
        Dmax = Math.min( Dmax, Math.abs(div / drop.getVelY()));
    }
    for (Ball ball : balls) {
      ball.setVelY(ball.getVelY() + trueDelta *GRAVITY);
      float div = ball.getRadius();
      if (ball.getVelX() != 0)
        Dmax = Math.min( Dmax, Math.abs(div / ball.getVelX()));
      if (ball.getVelY() != 0)
        Dmax = Math.min( Dmax, Math.abs(div / ball.getVelY()));
    }
    if (Dmax == 0) return trueDelta;
    return Dmax*0.9f;
  }

  private void checkPaddleCollision(Ball ball, float delta) {
    Impact impact = paddle.hits(ball);
    if (impact != null) {
        updatePreciseCollision(ball, delta, impact);
    }
  }

  private void checkBrickCollision(Ball ball, float delta) {
    Impact res = null;
    for (Brick brick : bricks) {
      Impact impactLoc = brick.hits(ball);
      if (impactLoc != null) {
        if (res == null) res = impactLoc;
        else if (res.dh() > impactLoc.dh()) res = impactLoc;
      }
    }
    if (res != null) {
      if (updatePreciseCollision(ball, delta, res)) return;

      Brick brick = (Brick) res.entity();
      bricks.remove(brick);
      playerStats.addPoints(POINTS_FOR_BRICK);
      if (Math.random() < 0.1) {
        createNewDrop(brick);
      }
      ball.boostSpeed(1.02f);
    }
  }

  /**
   * Computes the precise impact of the given ball.
   * Computes the time the collision occurred, rewinds time to this point,
   * computes the new velocity and applies the remainder of the movement.
   * @param ball the ball that collides
   * @param delta the duration of the movement
   * @param impact the rough point of impact
   * @return true if the impact was spurious.
   */
  private static boolean updatePreciseCollision(Ball ball, float delta, Impact impact) {
    double penetration = ball.getRadius() - impact.dh();
    Point2D vel = new Point2D(ball.getVelX(), ball.getVelY());
    double velH = vel.dotProduct(impact.norm()); // velocity along normal to impact
    double t = penetration / velH;
    if (t > 0 || t <= -delta) {
      System.out.println("bug !!");
      return true;
    }
    ball.updatePosition((float) t);
    ball.updateVelocityAfterReflection(impact.norm(), null);
    ball.updatePosition((float) (delta +t));
    return false;
  }

  /** Computes an animation extending/shrinking the paddle to the desired width.
   * Color is interpolated at the same time.
   * Points multiplier are reset at the end.
   * @param width the desired width of the paddle
   * @param color the desired color of the paddle.
   * @return the Timeline, ready to be played
   */
  private Timeline createGrowTimeLine(float width, Color color) {
    ObjectProperty<Color> paddleColor = (ObjectProperty<Color>) paddle.colorProperty();
    var line = new Timeline(
        new KeyFrame(Duration.seconds(0.5), new KeyValue(paddle.widthProperty(), width), new KeyValue(paddleColor, color)),
        new KeyFrame(Duration.seconds(15), new KeyValue(paddle.widthProperty(), width), new KeyValue(paddleColor, color)),
        new KeyFrame(Duration.seconds(15.5), new KeyValue(paddle.widthProperty(), 0.05f), new KeyValue(paddleColor, Color.RED))
    );
    line.setCycleCount(1);
    line.setOnFinished(event -> playerStats.setMultiplier(1f));
    return line;
  }

  private void checkWallCollision(Ball ball, float delta) {
    for (Block block : walls) {
      Impact impact = block.hits(ball);
      if (impact != null) {
        updatePreciseCollision(ball, delta, impact);
      }
    }
  }

  private void checkBallCollision(Ball ball, float delta) {
    for(Ball otherBall : balls) {
      if (otherBall != ball) {
        float dx = ball.getX() - otherBall.getX();
        float dy = ball.getY() - otherBall.getY();
        float rSum = ball.getRadius() + otherBall.getRadius();
        if (dx*dx+dy*dy <= rSum*rSum) {
          float velX = ball.getVelX();
          float velY = ball.getVelY();
          ball.updateVelocityAfterReflection(dx, dy, otherBall.getVelX(), otherBall.getVelY());
          ball.updatePosition(delta);
          otherBall.updateVelocityAfterReflection(-dx, -dy, velX, velY);
          otherBall.updatePosition(delta);
        }
      }
    }
  }

}

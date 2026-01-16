package fr.unicaen.iut.tpnote2025;

import java.net.URL;
import java.util.ResourceBundle;

import fr.unicaen.iut.tpnote2025.model.GameModel;
import fr.unicaen.iut.tpnote2025.model.entities.Ball;
import fr.unicaen.iut.tpnote2025.model.entities.Block;
import fr.unicaen.iut.tpnote2025.model.entities.Brick;
import javafx.animation.AnimationTimer;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import fr.unicaen.iut.tpnote2025.model.entities.Paddle;

public class BreakerController implements Initializable{

	private @FXML Label points;
	private @FXML Label vies;
	private @FXML Label pointsText;
	private @FXML Label viesText;
	private @FXML Pane frontPane;
	private @FXML Pane centerPane;
	private @FXML Label pauseLabel;
	final GameModel gameModel = new GameModel();
	
	public void initialize(URL location, ResourceBundle resources) {
	  
	  points.textProperty().bind(gameModel.pointsProperty().asString());
	  vies.textProperty().bind(gameModel.livesProperty().asString());
	  
	  Arc arc = new Arc(gameModel.paddle.getX(),gameModel.paddle.getY(),gameModel.paddle.getHeight(),gameModel.paddle.getWidth(), gameModel.paddle.getStartAngle(), gameModel.paddle.getEndAngle());
	  
	  arc.centerXProperty().bind(gameModel.paddle.xProperty());
	  centerPane.getChildren().add(arc);
	  
	  for(Brick brick : gameModel.bricks) {
	  	javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle(brick.getX(),brick.getY(),brick.getWidth()*2,brick.getHeight()*2);
	  	centerPane.getChildren().add(rectangle);
	  }
	  
	  for(Block block : gameModel.walls) {
	  	javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle(block.getX(),block.getY(),block.getWidth()*2,block.getHeight()*2);
	  	centerPane.getChildren().add(rectangle);
	  }
	  
	  for(Ball ball : gameModel.balls) {
	  	Circle circle = new Circle(ball.getX(),ball.getY(),ball.getRadius());
	  	circle.centerXProperty().bind(ball.xProperty());
	  	circle.centerYProperty().bind(ball.yProperty());
	  	circle.radiusProperty().bind(ball.radiusProperty());
	  	centerPane.getChildren().add(circle);
	  }
	    
	  new AnimationTimer() {
	      long prev = -1;
	      @Override
	      public void handle(long now) {
	        if (prev != -1)
	          gameModel.update(now - prev);
	        prev = now;
	      }
	    }.start();
	    
	    //Style definition
	    frontPane.setStyle("-fx-background-color: blue");
	    
	    vies.setStyle("-fx-background-color: white");
	    points.setStyle("-fx-background-color: white");
	    viesText.setStyle("-fx-background-color: white");
	    pointsText.setStyle("-fx-background-color: white");
	    
	    centerPane.addEventHandler(MouseEvent.MOUSE_PRESSED, unpause -> {
	    	gameModel.paused.setValue(false);
	    });
	    
	    pauseLabel.visibleProperty().bind(gameModel.paused);
	    
	    gameModel.balls.addListener((ListChangeListener<Ball>) event -> {
	    	while (event.next()) {
		    	if (event.wasAdded()) {
			    	for (Ball ball : event.getAddedSubList()) {
			    	}
			    }
			    if (event.wasRemoved()) {
			    	for (Ball ball : event.getRemoved()) {
			    		centerPane.getChildren().remove(ball);
			    	}
		    	}
		    }
	    });
	    
  }
  
  @FXML
  private void newGame() {
	  gameModel.newGame();
  } 
}


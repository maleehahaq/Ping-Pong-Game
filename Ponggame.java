package application;
import java.util.Random ;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Ponggame extends Application{
	private static final int width = 800;
	private static final int height = 600;
	private static final int P_HEIGHT = 100;
	private static final int P_WIDTH = 20;
	private static final double BALL_RAD = 20;
	private int ballYSpeed = 1;
	private int ballXSpeed = 1;
	private double POneYPos = height / 2;                                   //vertical position
	private double PTwoYPos = height / 2;
	private double ballXPos= width/ 2 ;
	private double ballYPos= height/ 2 ;
	private int scoP1 = 0;
	private int scoP2 = 0;
	private boolean gameStarted;
	
	private int POneXPos = 0;
	private double PTwoXPos = width - P_WIDTH;
	int a=0;
	int s=0;
	Random random;
	
	
	
	public void start(Stage stage) throws Exception {
		
		Canvas canvas = new Canvas(width, height);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Timeline t = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
		t.setCycleCount(Timeline.INDEFINITE);
		Scene scene=new Scene(new StackPane(canvas));
		
		
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event)
			{
			     //System.out.println(event.getCode());
				
				  if (event.getCode() == KeyCode.UP) {
					  if(PTwoYPos>0) {
					  PTwoYPos-=50;}}
				 
	                if (event.getCode() == KeyCode.DOWN) {
	                	if(PTwoYPos<500) {
	                	PTwoYPos+=50;}
	                }
		                
	              if (event.getCode() == KeyCode.S) {
	   					  if(POneYPos<500) {
	                		 
	                		 POneYPos+=50;}}
							  
						  
			      if (event.getCode() == KeyCode.W) {
				  if(POneYPos>0)
				  {
				  POneYPos-=50;}
				  
			  }
			}   
			}
		);
		
		canvas.setOnMouseClicked(e -> 
		{
			gameStarted = true;
			s=1;	
		}
		);
		stage.setScene(scene);
		
		stage.setTitle("Pong Game");
		stage.show();
		t.play();
	}

	private void run(GraphicsContext gc) {
		gc.setFill(Color.web("#EAE7B1"));
		gc.fillRect(0 , 0 , width, height);
		gc.setFill(Color.web("#3C6255"));
		gc.setFont(Font.font(30));
		gc.strokeText("Player 1",130,53);
		gc.strokeText("Player 2",670,53);
		
		if(gameStarted) {
			ballXPos+=ballXSpeed;                             //certain amount of speed added in ball position
			ballYPos+=ballYSpeed;
			gc.fillOval(ballXPos, ballYPos, BALL_RAD, BALL_RAD);
		} else {
			if(s==0){
			gc.setStroke(Color.DARKBLUE);
			gc.setTextAlign(TextAlignment.CENTER);
			gc.strokeText("Click to Start", width / 2, height / 2);
			ballXPos = width/ 2;	//ball position
			ballYPos = height/ 6;
			POneYPos = height / 2;
			PTwoYPos = height / 2;
			}
			else {
				ballXPos = width/ 2;
				ballYPos = height/ 4;
				ballXSpeed = new Random().nextInt(2) == 0 ? 2: -1;         //if random value is zero then it will move in 2 direction otherwise in -1
				ballYSpeed = new Random().nextInt(2) == 0 ? 2: -1;
				gameStarted=true;
				}
				
			}
			
		if(ballYPos > height || ballYPos < 0) 
			ballYSpeed *=-1;                  //ball stays in the frame
		if(ballXPos < POneXPos - P_WIDTH) {                
			scoP2++;			
			gameStarted = false;		     //player 2 scores
			
		}
		if(ballXPos > PTwoXPos + P_WIDTH) {  
			scoP1++;
			gameStarted = false;		//player 1 scores
		}
		
		if( ((ballXPos + BALL_RAD > PTwoXPos) && ballYPos >= PTwoYPos && ballYPos <= PTwoYPos + P_HEIGHT) ||     //speed increase after striking
			((ballXPos < POneXPos + P_WIDTH) && ballYPos >= POneYPos && ballYPos <= POneYPos + P_HEIGHT)) {
			ballYSpeed += 1 * Math.signum(ballYSpeed);
			ballXSpeed += 1 * Math.signum(ballXSpeed);
			ballXSpeed *= -1;
			ballYSpeed *= -1;                                                                                     
		
		}
		if(scoP1==3) {
			gc.strokeText("Congratulations!! Player 1 wins",width / 2, height / 2);
			gc.setStroke(Color.DARKBLUE);
			gc.setTextAlign(TextAlignment.CENTER);
			gameStarted=false;
			
		}
		if(scoP2==3) { 
			gc.strokeText("Congratulations!! Player 2 wins",width / 2, height / 2);
			gc.setStroke(Color.DARKBLUE);
			gc.setTextAlign(TextAlignment.CENTER);
			gameStarted=false;
		}
		
	
		gc.fillText(scoP1 + "\t\t\t\t\t\t\t\t" + scoP2, width / 2, 100);
		gc.fillRect(PTwoXPos, PTwoYPos, P_WIDTH, P_HEIGHT);
		gc.fillRect(POneXPos, POneYPos, P_WIDTH, P_HEIGHT);
	}


public static void main(String[] args) {
    launch(args);  }}

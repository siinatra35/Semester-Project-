package Game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


import javax.swing.*;
import java.security.Key;
import java.util.Random;

/**
 * @author david
 */
public class game extends Application {

    private final Pane root = new Pane();
    private Rectangle[] rectangle = new Rectangle[100];
    private Rectangle apple;
    private Random rand = new Random();
    private int grow = 0;
    private boolean going = false;
    private int[] x = new int[100];
    private int[] y = new int[100];
    private int[] getFood, hitTail;
    private BorderPane borderPane = new BorderPane();
    private Button startGame;
    private int direction = 3;
    private int counter = 3;


    /**
     * this method generates the snake shape, size, and color
     * and returns it to the stage method
     *
     * @return returns snake/apple shape and color
     */
    private Rectangle generateShape() {

        //size of food/snake
        Rectangle foodAndSnake = new Rectangle(45, 45);
        //color of snake/food
        foodAndSnake.setFill(Color.BURLYWOOD);
        //outline of snake/food
        foodAndSnake.setStroke(Color.BLACK);
        //allows snake to pass border
        foodAndSnake.setVisible(false);
        return foodAndSnake;

    }

    /**
     * @param primaryStage window elements
     */
    @Override
    public void start(Stage primaryStage) {

        //button objects for moving snake
        startGame = new Button("Start");
        Button quitGame = new Button("Quit");

        hitTail = new int[]{
                x[0], y[0]
        };

        //exits game
        quitGame.setOnAction(event -> System.exit(0));

        //when pushed snake will be shown
        startGame.setOnAction(new StartButtonHandler());

        //for button layout
        HBox hBox = new HBox(10, startGame, quitGame);

        //setting HBox to window
        borderPane.setBottom(hBox);

        //position of button
        hBox.setAlignment(Pos.BOTTOM_CENTER);

        //adding borderPane to scene
        Scene scene = new Scene(borderPane);

        //setting scene
        primaryStage.setScene(scene);

        //setting wid/height of window
        primaryStage.setMinHeight(543);
        primaryStage.setMinWidth(514);
        primaryStage.setMaxHeight(543);
        primaryStage.setMaxWidth(514);

        //setting title of window
        primaryStage.setTitle("Snake Game");
        primaryStage.setOnCloseRequest(event -> going =true);


        //show window
        primaryStage.show();

    }

    class StartButtonHandler implements
            EventHandler<ActionEvent> {
        /**
         * @param event
         */
        @Override
        public void handle(ActionEvent event) {

            //disable start button after push
            // startGame.setDisable(true);
            Thread game;

            //getting Rectangle form generateShape method
            apple = generateShape();
            apple.setStroke(Color.RED); //
            apple.setVisible(true); //sets food to visible
            apple.setTranslateX(rand.nextInt(10) * 50);
            apple.setTranslateY(rand.nextInt(10) * 50);
            root.getChildren().add(apple);

            //setting snake to center of window
            borderPane.setCenter(root);

            //generating body of snake
            for (int i = 0; i < 3; i++) {
                rectangle[i] = generateShape();
                rectangle[i].setTranslateX(50 + 50 * i);
                rectangle[i].setTranslateY(50 + 50);
                rectangle[i].setVisible(true);
                root.getChildren().add(rectangle[i]);

            }
            for (int i = 0; i < 100; i++) {
                rectangle[i] = generateShape();
                rectangle[i].setTranslateY(50 + 50);
                rectangle[i].setTranslateX(50 + 50 * i);
                root.getChildren().add(rectangle[i]);

            }
            rectangle[0].setFill(Color.RED);
            game = new Thread(()-> {
                while (!going) {
                    moveSnake();
                    try {
                        Thread.sleep(200);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            });
            game.start();


                //-----------------------------------------------------------//
                // adding key input handler
                startGame.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
                    KeyCode WASD = keyEvent.getCode();
                    if (WASD == KeyCode.W) { //W key
                        System.out.println("W key was entered ");
                    } else if (WASD == KeyCode.D) {// D key
                        System.out.println("D key was entered ");
                    } else if (WASD == KeyCode.A) {// A key
                        System.out.println("A key was entered");
                    } else if (WASD == KeyCode.S) {// S key
                        System.out.println("S key was entered");
                    }
                    hitTail = new int[]{
                      x[0], y[0]
                    };
                });//end of key input handler
                //------------------------------------------------------------//


        }
    }


    /**
     * moves snake
     */
    private void moveSnake() {

        int[][] temp = {{x[1], y[1]}, {}};
        System.out.println(temp.length);
        x[1] = x[0];
        y[1] = y[0];
        for (int i = 2; i < counter; i++) {
            temp[1] = new int[]{x[i], y[i]};
            x[i] = temp[0][0];
            y[i] = temp[0][1];
            temp[0] = temp[1];
            //adding new tail
            if (grow > 0 && x[counter - 1] == getFood[0] && y[counter - 1] == getFood[1]) {
                rectangle[counter - 1].setVisible(true);
                --grow;
            }// end of if statement
        }//end of for loop

        switch (direction) {
            case 0:
                if (y[0] + 50 <= 450) y[0] += 50;
                else y[0] = 0;
                break;
            case 1:
                if (x[0] - 50 >= 0) x[0] -= 50;
                else x[0] = 450;
                break;
            case 2:
                if (y[0] - 50 >= 0) y[0] -= 50;
                else y[0] = 450;
                break;
            case 3:
                if (x[0] + 50 <= 450) x[0] += 50;
                else x[0] = 0;
                break;
        }

        //passing values to collide method
        //and checking points
        if (collide(x[0], y[0])) {
            System.err.println("Game over");
            going = true;
        } else {
            for (int i = 0; i < counter; i++) {
                rectangle[i].setTranslateX(x[i]);
                rectangle[i].setTranslateY(y[i]);
            }//end of for loop
        }//end of if statement

        if (rectangle[0].getBoundsInParent().intersects(apple.getBoundsInParent())) {
            boolean applePosition = false;

            getFood = new int[]{
                    (int) apple.getTranslateX(), (int) apple.getTranslateY()
            };

            //while loop for randomized apple positioning
            while (!applePosition) {
                //sets food in random position after being eaten
                apple.setTranslateX(rand.nextInt(10) * 50);
                apple.setTranslateY(rand.nextInt(10) * 50);

                //if snake gets the food it allows the apple to change locations
                //@FIXME fix null Pointer Exceptions FUCK ME
                if (collide(getFood[0], getFood[1]) && (int) apple.getTranslateX() != getFood[0]
                        || (int) apple.getTranslateY() != getFood[1])
                    applePosition = true;
            }//end of while loop

        }//end if statement
        x[counter] = (int) apple.getTranslateX();
        y[counter] = (int) apple.getTranslateY();
        rectangle[counter].setTranslateX(rectangle[counter - 1].getX());
        rectangle[counter].setTranslateY(rectangle[counter - 1].getY());
        ++counter;
        ++grow;
        System.out.println(counter + " ");


    }

    /**
     * @param xAxis
     * @param yAxis
     * @return
     */
    public boolean collide(int xAxis, int yAxis) {
        int intersects = 0;
        for (Rectangle rect : rectangle) {
            if (rect != rectangle[0] && intersects > 0 &&
                    rect.isVisible() && xAxis == x[intersects] && yAxis == y[intersects]) {
                System.out.println(intersects + " from the collide method");
                return true;
            }
            intersects++;

        }
        return false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        //launches applications
        launch(args);

    }
}

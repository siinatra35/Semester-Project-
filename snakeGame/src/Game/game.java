package Game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


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
    private Scene scene;
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


    @Override
    public void start(Stage primaryStage) {

        //start and stop button
        startGame = new Button("Start");
        Button quitGame = new Button("Quit");


        System.out.println(x[0] + " " + x[1] + " " + x[2] + " ");

        //exits game
        quitGame.setOnAction(event ->
                System.exit(0));

        //when pushed snake will be shown
        startGame.setOnAction(new StartButtonHandler());

        //for button layout
        HBox hBox = new HBox(10, startGame, quitGame);

        //setting HBox to window
        borderPane.setBottom(hBox);

        //position of buttons
        hBox.setAlignment(Pos.BOTTOM_CENTER);

        //adding borderPane to scene
        scene = new Scene(borderPane);

        //setting scene
        primaryStage.setScene(scene);

        //setting wid/height of window
        primaryStage.setMinHeight(543);
        primaryStage.setMinWidth(514);
        primaryStage.setMaxHeight(543);
        primaryStage.setMaxWidth(514);

        //when window is exited applications stops
        primaryStage.setOnCloseRequest(windowEvent -> going = true);

        //setting title of window
        primaryStage.setTitle("Snake Game");

        //show window
        primaryStage.show();

    }

    class StartButtonHandler implements
            EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {


            Thread game;

            hitTail = new int[]{x[0], y[0]};

            //creates food image
            apple = generateShape();
            apple.setStroke(Color.RED); //
            apple.setVisible(true); //sets food to visible
            apple.setTranslateX(rand.nextInt(10) * 50);
            apple.setTranslateY(rand.nextInt(10) * 50);
            root.getChildren().add(apple);


            //setting snake to center of window
            root.setMaxHeight(543);
            root.setMaxWidth(514);
            borderPane.setCenter(root);

            //generating body of snake
            for (int i = 0; i < 3; i++) {
                rectangle[i] = generateShape();
                rectangle[i].setTranslateX(50 + 50 * i);
                rectangle[i].setTranslateY(50 + 50);
                rectangle[i].setVisible(true);
                root.getChildren().add(rectangle[i]);

            }
            for (int i = 3; i < 100; i++) {
                rectangle[i] = generateShape();
                rectangle[i].setTranslateY(50 + 50);
                rectangle[i].setTranslateX(50 + 50 * i);
                root.getChildren().add(rectangle[i]);

            }
            rectangle[0].setFill(Color.RED);
            game = new Thread(() -> {
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
            //captures which key that is pressed
            scene.setOnKeyPressed(keyEvent -> {
                KeyCode inputKey = keyEvent.getCode();
                switch (inputKey) {
                    case D:
                        if (direction != 1 && ((direction == 2 || direction == 0)
                                && hitTail[1] != y[0])) direction = 3;
                        break;
                    case A:
                        if (direction != 3 && ((direction == 2 || direction == 0)
                                && hitTail[1] != y[0])) direction = 1;
                        break;
                    case S:
                        if (direction != 2 && ((direction == 3 || direction == 1)
                                && hitTail[1] != x[0])) direction = 0;
                        break;
                    case W:
                        if (direction != 0 && ((direction == 3 || direction == 1)
                                && hitTail[0] != x[0])) direction = 2;
                        break;
                }
                hitTail = new int[]{x[0], y[0]};
            });

        }
    }

    public void moveSnake() {

        int[][] temp = {{x[1],
                y[1]}, {

        }};
        x[1] = x[0];
        y[1] = y[0];
        try {
            for (int i = 2; i < counter; i++) {
                temp[1] = new int[]{x[i], y[i]};
                x[i] = temp[0][0];
                y[i] = temp[0][1];
                temp[0] = temp[1];

                //adding new tail
                if (grow > 0 && x[counter - 1] == getFood[0]
                        && y[counter - 1] == getFood[1]) {
                    System.out.println(grow + " test");
                    rectangle[counter - 1].setVisible(true);
                    --grow;
                }// end of if statement
            }//end of for loop
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
                //   System.out.println("testing");
                rectangle[i].setTranslateX(x[i]);
                rectangle[i].setTranslateY(y[i]);
            }//end of for loop
            //end of if statement

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
                    if (collide(getFood[0], getFood[1]) && (int) apple.getTranslateX() != getFood[0] || (int) apple.getTranslateY() != getFood[1])
                        applePosition = true;
                }//end of while loop
                x[counter] = (int) apple.getTranslateX();
                y[counter] = (int) apple.getTranslateY();
                rectangle[counter].setTranslateX(rectangle[counter - 1].getX());
                rectangle[counter].setTranslateY(rectangle[counter - 1].getY());
                ++counter;
                ++grow;
                System.out.println(counter + "counter");
            }
        }
    }

    public boolean collide(int xAxis, int yAxis) {
        int i = 0;
        for (Rectangle rect : rectangle) {
            if (rect != rectangle[0] && i > 0 && rect.isVisible() && xAxis == x[i] && yAxis == y[i]) {
                System.out.println(i);
                return true;
            }
            i++;
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

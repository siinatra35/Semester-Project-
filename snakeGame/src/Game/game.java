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
    private boolean going = false;
    private int[] x = new int[100];
    private int[] y = new int[100];
    private int[] getFood, hitTail;
    private BorderPane borderPane = new BorderPane();
    private Button startGame;
    private Thread game;
    private int direction = 3;

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

        //TODO add a way to read to directions
        //getting wasd input keys
        startGame.setOnKeyPressed(event -> {
            KeyCode WASD = event.getCode();
            if (WASD == KeyCode.W) { //W key
                System.out.println("W key was entered ");
            } else if (WASD == KeyCode.D) {// D key
                System.out.println("D key was entered ");
            } else if (WASD == KeyCode.A) {// A key
                System.out.println("A key was entered");
            } else if (WASD == KeyCode.S) {// S key
                System.out.println("S key was entered");
            }
        });


        //setting scene
        primaryStage.setScene(scene);

        //setting wid/height of window
        primaryStage.setMinHeight(543);
        primaryStage.setMinWidth(514);
        primaryStage.setMaxHeight(543);
        primaryStage.setMaxWidth(514);


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


        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //launches applications
        launch(args);

    }
}

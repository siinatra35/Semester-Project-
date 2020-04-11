package Game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
    private Rectangle food;
    private Random rand = new Random();

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
        foodAndSnake.setFill(Color.GREEN);
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

        //@TODO create a game control in a t form for better visual
        //   GridPane gridPane = new GridPane(); /

        //for setting button to and snake layout
        BorderPane borderPane = new BorderPane();

        //button objects for moving snake
        Button startGame = new Button("Start");
        Button quitGame = new Button("Quit");


        //for button layout
        HBox hBox = new HBox(10, startGame, quitGame);
        //setting HBox to window
        borderPane.setBottom(hBox);

        //position of button
        hBox.setAlignment(Pos.BOTTOM_CENTER);


        //getting Rectangle form generateShape method
        food = generateShape();
        food.setStroke(Color.RED); //
        food.setVisible(true); //sets food to visible
        food.setTranslateX(rand.nextInt(10) * 50);
        food.setTranslateY(rand.nextInt(10) * 50);
        root.getChildren().add(food);

        //setting snake to center of window
        borderPane.setCenter(root);

        //adding borderPane to scene
        Scene scene = new Scene(borderPane);

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

        //@TODO add remaining WASD keys
        //getting wasd input keys
        startGame.setOnKeyPressed(event -> {
            KeyCode inputKey = event.getCode();
            if (inputKey == KeyCode.W) {
                System.out.println("test");
            } else if (inputKey == KeyCode.D) {
                System.out.println("test2 ");
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }
}

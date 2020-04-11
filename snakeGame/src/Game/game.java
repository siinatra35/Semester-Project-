package Game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


import java.util.Random;

public class game extends Application {
    private Pane root = new Pane();
    private Rectangle[] rectangle = new Rectangle[100];
    private Rectangle food;
    private Random rand = new Random();

    private Rectangle generateShape() {
        //size of food/snake
        Rectangle foodAndSnake = new Rectangle(45, 45);
        //color of snake/food
        foodAndSnake.setFill(Color.BROWN);
        //outline of snake/food
        foodAndSnake.setStroke(Color.BLACK);
        //allows snake to pass border
        foodAndSnake.setVisible(false);
        return foodAndSnake;


    }

    /**
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {

        //@TODO create a game control in a t form for better visual
        GridPane gridPane = new GridPane();


        //button objects for moving snake
        Button up = new Button("up");
        Button down = new Button("down");
        Button left = new Button("left");
        Button right = new Button("right");

        //getting Rectangle form generateShape method
        food = generateShape();
        food.setStroke(Color.RED);
        food.setVisible(true);
        food.setTranslateX(rand.nextInt(10) * 50);
        food.setTranslateY(rand.nextInt(10) * 50);
      //  root.getChildren().add(food);



        HBox hBox = new HBox(10, food, left, up, down, right);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        Scene scene = new Scene(hBox);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(543);
        primaryStage.setMinWidth(514);
        primaryStage.setMaxHeight(543);
        primaryStage.setMaxWidth(514);
        primaryStage.show();

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);

    }
}

package testing;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class game extends Application {

    @Override
    public void start(Stage primaryStage) {

        Button startButton = new Button("Start game");
        startButton.setOnAction(event -> {
            startButton.setDisable(true);

        });
        Button exitGame = new Button("Quit Game");

        exitGame.setOnAction(event -> {
            System.exit(0);
        });
        HBox hBox = new HBox(5, startButton, exitGame);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        Scene scene = new Scene(hBox);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(543);
        primaryStage.setMinWidth(514);
        primaryStage.setMaxHeight(543);
        primaryStage.setMaxWidth(514);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }
}

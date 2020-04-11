package Game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class game extends Application {
    private Label label;

    @Override
    public void start(Stage primaryStage) {

        Button startButton = new Button("Start game");
        startButton.setOnAction(new StartButtonHandler());
        Button exitGame = new Button("Quit Game");
        Label intructions = new Label("Use WASD to move snake");

        //lamda expression to handle quiting game
        exitGame.setOnAction(event -> {
            System.exit(0);//exits game
        });
        HBox hBox = new HBox(10, startButton, exitGame);
        VBox vBox = new VBox(hBox,intructions);
      //  hBox.setAlignment(Pos.BOTTOM_CENTER);
        vBox.setAlignment(Pos.BASELINE_CENTER);
        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(543);
        primaryStage.setMinWidth(514);
        primaryStage.setMaxHeight(543);
        primaryStage.setMaxWidth(514);
        primaryStage.show();

    }

    class StartButtonHandler implements
            EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {

        }
    }

    public static void main(String[] args) {
        launch(args);

    }
}

package bohac;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Hlavni trida co vytvori okno a otevre aplikaci
 */
public class TicTacToe extends Application {
    private static Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/layout.fxml"));
        primaryStage.getIcons().add(new Image("/icon.png"));
        primaryStage.setTitle(Settings.WINDOW_LABEL);
        primaryStage.setScene(new Scene(loader.load()));
        controller = loader.getController();
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static Controller getController() {
        return controller;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

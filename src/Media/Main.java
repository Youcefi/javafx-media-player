package Media;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
   public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Media.fxml"));
        primaryStage.getIcons().add(new Image("images/open.png"));
        primaryStage.setTitle("Media Player");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click_2) {
                if(click_2.getClickCount()==2){
                    primaryStage.setFullScreen(true);
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package Media;

import com.sun.org.apache.bcel.internal.generic.Select;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


        public class Controller implements Initializable{
            private MediaPlayer mediaPlayer;
            @FXML
            private MediaView mediaView;
            private String filePath;
            @FXML
            private Slider sliderVolume;
            @FXML
            private Slider slider;
            @FXML
            private Label time;

            @FXML
            private Label totalTime;

            @FXML
            public void handleButtonAction(ActionEvent event){
                FileChooser fileChooser=new FileChooser();
                FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("Select a file","*.mp3","*.mp4","*.wav");
                fileChooser.getExtensionFilters().add(filter);
                File file =fileChooser.showOpenDialog(null);
                filePath=file.toURI().toString();

                if(filePath!=null){
                    Media media= new Media(filePath);
                    mediaPlayer=new MediaPlayer(media);
                    mediaView.setMediaPlayer(mediaPlayer);
                    mediaPlayer.play();

                    DoubleProperty width=mediaView.fitWidthProperty();
                    DoubleProperty hight=mediaView.fitHeightProperty();

                   width.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
                   hight.bind(Bindings.selectDouble(mediaView.sceneProperty(),"hight"));

                   sliderVolume.setValue(mediaPlayer.getVolume() * 100);
                   sliderVolume.valueProperty().addListener(new InvalidationListener() {
                       @Override
                       public void invalidated(Observable observable) {
                           mediaPlayer.setVolume(sliderVolume.getValue()/100 );
                       }
                   });
                    mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                        @Override
                        public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                            slider.setValue(newValue.toSeconds());
                            time.setText(String.format("%.2f",newValue.toMinutes()));
                            totalTime.setText(String.format("%.2f",mediaPlayer.totalDurationProperty().getValue().toMinutes()));
                        }
                    });
                    slider.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            mediaPlayer.seek(Duration.seconds(slider.getValue()));
                        }
                    });
                }
            }

            @FXML
            private void playVideo(ActionEvent event){
                mediaPlayer.play();
                mediaPlayer.setRate(1);
            }
            @FXML
            private void pauseVideo(ActionEvent event){
                mediaPlayer.pause();
            }
            @FXML
            private void stopVideo(ActionEvent event){
                mediaPlayer.stop();
            }
            @FXML
            private void fastVideo(ActionEvent event){
                mediaPlayer.setRate(1.5);
            }
            @FXML
            private void fasterVideo(ActionEvent event){
                mediaPlayer.setRate(2);
            }
            @FXML
            private void slowVideo(ActionEvent event){
                mediaPlayer.setRate(0.5);
            }
            @FXML
            private void slowerVideo(ActionEvent event){
                mediaPlayer.setRate(0.25);
            }
            @FXML
           /* private void ExitVideo(ActionEvent event){
                System.exit(0);
            }*/
            @Override
            public void initialize(URL location, ResourceBundle resources) {

            }


        }

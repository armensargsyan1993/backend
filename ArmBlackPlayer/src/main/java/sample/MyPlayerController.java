package sample;

import de.jensd.fx.glyphs.emojione.EmojiOneView;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class MyPlayerController implements Initializable {

    private ObservableList playListFiles = FXCollections.observableArrayList();
    private ObjectProperty<Path> selectedMedia = new SimpleObjectProperty<>();
    private MediaPlayer mediaPlayer;

    private int s = 0;

    private boolean status = false;
    private boolean ple = false;
    private boolean entry = false;
    private boolean mediaStatus = false;
    private boolean clickList = false;
    private boolean startFunc = false;
    private boolean stopListening = false;
    private boolean dragAnDropped = false;

    @FXML
    private Text timeText;
    @FXML
    private Text currTimeText;
    @FXML
    private MaterialIconView btn_repeat;
    @FXML
    private AnchorPane mediaAndListViewPane;
    @FXML
    private EmojiOneView btn_play;
    @FXML
    private Slider valueSlider;
    @FXML
    private Slider rateSlider;
    @FXML
    private Slider timeSlider;
    @FXML
    private ListView playListView;
    @FXML
    private MediaView mediaView;

    public void initialize(URL location, ResourceBundle resources) {
            playListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent click) {
                    if (click.getClickCount() == 2) {
                        System.out.println(s = playListView.getSelectionModel().getSelectedIndex());//сторка где кликают
                        if (playListView.getSelectionModel().getSelectedItem() != null) {
                            selectedMedia.setValue((Path) playListView.getSelectionModel().getSelectedItem());
                            mediaStatus = true;
                            startFunc = true;
                        }
                    }
                    try {

                        mediaPlayer.setOnEndOfMedia(new Runnable() {
                            @Override
                            public void run() {
                                forward(click);
                            }
                        });
                    } catch (NullPointerException e) {

                    }
                }
            });

            selectedMedia.addListener((observable, oldValue, newValue) -> {
                stopListening = false;
                rateSlider.setValue(10);
                if (newValue != null || mediaStatus == true) {
                    if (oldValue != null || mediaStatus == true) {
                        mediaPlayer.stop();
                    }
                    playerCreate(newValue.toString());

                    if (playListView.getItems().get(s).toString().endsWith(".mp4")){
                        playListView.setVisible(false);
                        mediaView.setVisible(true);

                    }
                    else {
                        playListView.setVisible(true);
                        mediaView.setVisible(false);
                    }

                    mediaPlayer.play();
                    clickList = true;
                }
            });

                btn_play.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (btn_play.getFill() == Color.RED){
                            mediaPlayer.play();
                            btn_play.setFill(Color.WHITE);
                            return;
                        }
                        stopListening = false;
                        if (startFunc == false ) {
                            if (clickList == true) {
                                mediaPlayer.stop();
                            }

                            if (!status) {}
                            else {
                                rateSlider.setValue(10);
                                if (ple || mediaStatus) {
                                    mediaPlayer.stop();
                                }
                                playerCreate(playListView.getItems().get(s).toString());

                                mediaPlayer.play();
                                mediaStatus = true;
                                ple = true;

//          ПЕРЕКЛЮЧАЕТ НЕ СЛЕДУЙШИЙ ТРЕК
                                mediaPlayer.setOnEndOfMedia(new Runnable() {
                                    @Override
                                    public void run() {
                                        forward(event);
                                    }
                                });
                                startFunc = true;
                            }
                        } else if (startFunc == true || !dragAnDropped) {
                            mediaPlayer.pause();
                            btn_play.setFill(Color.RED);
                        }
                        if (dragAnDropped || mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED ||dragAnDropped) {
                            mediaPlayer.play();
                            btn_play.setFill(Color.WHITE);
                            dragAnDropped = false;
                        }
                    }
                });

            btn_repeat.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (btn_repeat.getFill() == Color.WHITE) {
                        btn_repeat.setFill(Color.RED);
                        mediaPlayer.setOnEndOfMedia(new Runnable() {
                            @Override
                            public void run() {
                                mediaPlayer.seek(Duration.ZERO);
                            }
                        });

                    } else if (btn_repeat.getFill() == Color.RED) {
                        btn_repeat.setFill(Color.WHITE);
                        mediaPlayer.setOnEndOfMedia(new Runnable() {
                            @Override
                            public void run() {
                                forward(event);
                            }
                        });
                    }

                }
            });
            //НАСТРОЙКА СЛАЙДЕРА ЗВУКА
            valueSlider.setValue(10);
            valueSlider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    mediaPlayer.setVolume(valueSlider.getValue()/10);
                }
            });
    }


        @FXML
        void add (MouseEvent event){
        stopListening = false;
            AddPlayList addPlayList = new AddPlayList();
            addPlayList.add(playListFiles, playListView, entry, mediaStatus, mediaPlayer);
            status = true;
            entry = true;
            startFunc = false;
            s = 0;
        }

        @FXML
        void eye (MouseEvent event){
            if (mediaView.isVisible()) {
                mediaView.setVisible(false);
                playListView.setVisible(true);
            } else {
                mediaView.setVisible(true);
                playListView.setVisible(false);
            }
        }
        @FXML
        void forward (MouseEvent event){
            stopListening = false;
            rateSlider.setValue(10);
            mediaPlayer.stop();
            s++;
            try {
                playerCreate(playListView.getItems().get(s).toString());
            } catch (IndexOutOfBoundsException e) {
                s = 0;
                playerCreate(playListView.getItems().get(s).toString());
            }

            if (playListView.getItems().get(s).toString().endsWith(".mp4")){
                playListView.setVisible(false);
                mediaView.setVisible(true);

            }
            else {
                playListView.setVisible(true);
                mediaView.setVisible(false);
            }

            mediaPlayer.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    forward(event);
                }
            });

        }
        @FXML
        void backward (MouseEvent event){
            stopListening = false;
            rateSlider.setValue(10);
            mediaPlayer.stop();
            s--;
            try {
                playerCreate(playListView.getItems().get(s).toString());
            } catch (ArrayIndexOutOfBoundsException e) {
                s = playListView.getItems().size() - 1;
                playerCreate(playListView.getItems().get(s).toString());
            }

            if (playListView.getItems().get(s).toString().endsWith(".mp4")){
                playListView.setVisible(false);
                mediaView.setVisible(true);

            }
            else {
                playListView.setVisible(true);
                mediaView.setVisible(false);
            }

            mediaPlayer.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    forward(event);
                }
            });
        }


        public void playerCreate (String MEDIA_URL){
            try {

                String MEDIA_URL_FOR_MEDIA = URLEncoder.encode(MEDIA_URL, "UTF-8");
                MEDIA_URL_FOR_MEDIA = "file:/"
                        + (MEDIA_URL_FOR_MEDIA).replace("\\", "/").replace("+", "%20");
                Media media = new Media(MEDIA_URL_FOR_MEDIA);
                mediaPlayer = new MediaPlayer(media);
                mediaView.setMediaPlayer(mediaPlayer);
                mediaPlayer.setAutoPlay(true);
                mediaPlayer.setVolume(valueSlider.getValue()/10);
                if (btn_play.getFill() == Color.RED){
                    mediaPlayer.pause();
                }

                //КЛАСС ОТВЕЧАЮШИЙ ЗА ОТОБРАЖЕНИЯ ВИДЕО
                MediaView2 mediaView2 = new MediaView2();
                mediaView2.mediaView(mediaView, mediaAndListViewPane);

                //КЛАСС ОТВЧАЮШИЙ ЗА ДЛИТЕЛЬНОСТЬ И НАЖАТИЕ НА СЛАЙДЕР ДЛИТЕЛНОСТИ
                TimeSlider2 timeSlider2 = new TimeSlider2(stopListening);
                timeSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        timeSlider2.timeSliderClickEvent(event, mediaPlayer, timeSlider);
                    }
                });
                mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                    @Override
                    public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                        timeSlider2.timeSliderListener(observable, oldValue, newValue, mediaPlayer, timeSlider,timeText,currTimeText);

                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }

            //КЛАСС ОТВЕЧАЮШИЙ ЗА СКОРОСТЬ ПЕСНИ
            //изначально было в методе initialize (следить за вазможными побочными эфектами)
            RateSlider2 rateSlider2 = new RateSlider2(rateSlider);

            rateSlider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    rateSlider2.changeRate(rateSlider, mediaPlayer, observable, oldValue, newValue);

                }
            });
        }

        //КЛАСС ОТВЕЧАЮШИЙ ЗА ПЕРЕТАСКИВАНИЕ
        DragAndDrop2 dragAndDrop2 = new DragAndDrop2();

    public void mediaDragDropped(DragEvent event) {
        dragAndDrop2.DragDropped(event);
    }

    public void mediaDragEntered(DragEvent event) {
        dragAndDrop2.DragEntered(event,playListFiles,playListView);
    }

    public void mediaDragExited(DragEvent event) {
        status = true;
        startFunc = false;
        dragAnDropped = true;
        stopListening = false;
    }

    public void mediaDragOver(DragEvent event) {
        dragAndDrop2.dragOver(event);
    }
}




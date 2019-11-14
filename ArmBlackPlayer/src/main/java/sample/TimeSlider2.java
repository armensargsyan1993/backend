package sample;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class TimeSlider2 {
    boolean stopListening;

    public TimeSlider2(boolean stopListening) {
        this.stopListening = stopListening;
    }

    public void timeSliderClickEvent(MouseEvent event , MediaPlayer mediaPlayer,Slider timeSlider){
        double dx = event.getX();
        double dwidth = timeSlider.getWidth();
        double progression = (dx / dwidth);
        double milliseconds = progression * mediaPlayer.getTotalDuration().toMillis();
        Duration duration = new Duration(milliseconds);
        mediaPlayer.seek(duration);
    }

    public void timeSliderListener(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue, MediaPlayer mediaPlayer, Slider timeSlider, Text timeText,Text currTimeText){
        timeSlider.setMax(mediaPlayer.getTotalDuration().toSeconds());
        timeSlider.setValue(newValue.toSeconds());
        int aaa = (int) (mediaPlayer.getCurrentTime().toSeconds()%60);
        int bbb = (int) mediaPlayer.getCurrentTime().toMinutes()%60;
        int ccc = (int) mediaPlayer.getCurrentTime().toHours();
        String aac = String.valueOf(aaa);
        String abc = String.valueOf(bbb);
        String acd = String.valueOf(ccc);
        currTimeText.setText(acd+":"+abc+":"+aac);
        if (stopListening == false) {
            int a = (int) (mediaPlayer.getTotalDuration().toSeconds()%60);
            int b = (int) mediaPlayer.getTotalDuration().toMinutes()%60;
            int c = (int) mediaPlayer.getTotalDuration().toHours();
            String aa = String.valueOf(a);
            String ab = String.valueOf(b);
            String ac = String.valueOf(c);

            timeText.setText(ac +":"+ab+":"+aa);
            stopListening = true;
        }
    }
}

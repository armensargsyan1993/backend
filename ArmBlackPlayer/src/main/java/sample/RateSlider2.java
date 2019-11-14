package sample;


import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;

class RateSlider2 {
    public RateSlider2(Slider rateSlider) {
       rateSlider.setMin(0);
       rateSlider.setMax(20);
       rateSlider.setValue(10);
//       rateSlider.setShowTickLabels(true);
//       rateSlider.setShowTickMarks(true);
       rateSlider.setBlockIncrement(1);
        }

        public void changeRate (Slider rateSlider,MediaPlayer mediaPlayer, ObservableValue<? extends Number> observable,Number oldValue , Number newValue){
            switch ((int) rateSlider.getValue()){
                case 0:mediaPlayer.setRate(0.1);
                    break;
                case 1:mediaPlayer.setRate(0.10);
                    break;
                case 2:mediaPlayer.setRate(0.20);
                    break;
                case 3:mediaPlayer.setRate(0.30);
                    break;
                case 4:mediaPlayer.setRate(0.40);
                    break;
                case 5:mediaPlayer.setRate(0.50);
                    break;
                case 6:mediaPlayer.setRate(0.60);
                    break;
                case 7:mediaPlayer.setRate(0.70);
                    break;
                case 8:mediaPlayer.setRate(0.80);
                    break;
                case 9:mediaPlayer.setRate(0.90);
                    break;
                case 10:mediaPlayer.setRate(1);
                    break;
                case 11:mediaPlayer.setRate(1.10);
                    break;
                case 12:mediaPlayer.setRate(1.25);
                    break;
                case 13:mediaPlayer.setRate(1.50);
                    break;
                case 14:mediaPlayer.setRate(1.75);
                    break;
                case 15:mediaPlayer.setRate(2);
                    break;
                case 16:mediaPlayer.setRate(2.25);
                    break;
                case 17:mediaPlayer.setRate(2.50);
                    break;
                case 18:mediaPlayer.setRate(2.75);
                    break;
                case 19:mediaPlayer.setRate(3);
                    break;
                case 20:mediaPlayer.setRate(5);
                    break;
            }
        }
    }

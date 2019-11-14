package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaView;

class MediaView2 {

    void mediaView(MediaView mediaView, AnchorPane mediaAndListViewPane){
        mediaView.setPreserveRatio(false);
        mediaView.fitWidthProperty().bind(mediaAndListViewPane.widthProperty());
        mediaView.fitHeightProperty().bind(mediaAndListViewPane.heightProperty());
//        DoubleProperty width = mediaView.fitWidthProperty();
//        DoubleProperty height = mediaView.fitHeightProperty();
//
//        width.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
//        height.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));
    }
}

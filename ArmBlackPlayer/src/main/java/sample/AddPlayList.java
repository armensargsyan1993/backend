package sample;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class AddPlayList {

    public void add(ObservableList playListFiles, ListView playListView, boolean entry, boolean mediaStatus, MediaPlayer mediaPlayer) {

        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Files", readFormats()));
        List<Path> listOfFiles = new ArrayList<>();
        System.out.println(mediaStatus);
        List<File> fil = chooser.showOpenMultipleDialog(null);
        if (fil != null) {
            listOfFiles = convertListFiletoListPath(fil);
        } else {}
        if (listOfFiles != null && !entry) {
            if (mediaStatus) {
                mediaPlayer.stop();
            }
            playListFiles.addAll(listOfFiles);
            playListView.setItems(playListFiles);
            System.out.println(playListFiles.size() + " if2");

        } else if (listOfFiles != null && entry && !mediaStatus) {
            if (mediaStatus) {
                mediaPlayer.stop();
            }
            playListFiles.clear();
            playListFiles.addAll(listOfFiles);
            playListView.setItems(playListFiles);
            System.out.println(playListFiles.size() + " if3");
        } else if (listOfFiles != null && entry && mediaStatus) {
            mediaPlayer.stop();
            playListFiles.clear();
            playListFiles.addAll(listOfFiles);
            playListView.setItems(playListFiles);
            System.out.println(playListFiles.size() + " if4");
        }
    }

    static List<String> readFormats() {
        List<String> formats = null;
        try {
            InputStream in = ClassLoader.getSystemResourceAsStream("format.properties");
            Properties p = new Properties();
            p.load(in);
            formats = Arrays.asList(p.getProperty("formats").split(","));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formats;
    }

    static List<Path> convertListFiletoListPath(List<File> listOfFile) {
        return listOfFile.stream().map(File::toPath).collect(Collectors.toList());
    }
}

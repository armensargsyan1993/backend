package sample;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static sample.AddPlayList.convertListFiletoListPath;

class DragAndDrop2 {

    boolean success = false;
    List<String> validExtensions = Arrays.asList("mp4","m4a","m4v","mp3","aif","aiff","fxm","flv","m3u8","m3u8","mp3","wav");


    void DragDropped(DragEvent event) {
//        if (event.getDragboard().hasFiles()) {
//            event.acceptTransferModes(TransferMode.ANY);
//        }
        //код из примера для филтрации файлов

        if (event.getGestureSource() != event && event.getDragboard().hasFiles()) {
            event.getDragboard().getFiles().forEach(file -> System.out.println(file.getAbsolutePath()));
            success = true;
        } else {
            event.setDropCompleted(success);
            event.consume();
        }
    }

    void DragEntered(DragEvent event, ObservableList playListFiles, ListView playListView) {
        List<Path> listOfFiles;
        List<File> dragFiles = event.getDragboard().getFiles();
        listOfFiles = convertListFiletoListPath(dragFiles);
        if (!validExtensions.containsAll(
                event.getDragboard().getFiles().stream()
                        .map(file -> getExtension(file.getName()))
                        .collect(Collectors.toList()))) {
            event.consume();
        }else{
            playListFiles.addAll(listOfFiles);
            playListView.setItems(playListFiles);
        }
    }


    void dragOver(DragEvent event) {

        if (event.getGestureSource() != event && event.getDragboard().hasFiles()) {
            if (!validExtensions.containsAll(
                    event.getDragboard().getFiles().stream()
                            .map(file -> getExtension(file.getName()))
                            .collect(Collectors.toList()))) {
                event.consume();
                return;
            }
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
    }
    //метод из примера для филтрации файлов
    private String getExtension(String fileName) {
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0 && i < fileName.length() - 1) {
            return fileName.substring(i + 1).toLowerCase();
        }
        return extension;
    }


}







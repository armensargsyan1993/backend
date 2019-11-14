//package sample;
//
//import javafx.stage.FileChooser;
//
//import java.io.File;
//import java.nio.file.Path;
//import java.util.ArrayList;
//import java.util.List;
//
//import static sample.AddPlayList.convertListFiletoListPath;
//import static sample.AddPlayList.readFormats;
//
//public class ApplicationGetParam {
//
//    boolean mediaStatus;
//    boolean entry = false;
//
//    public void addPar(List<String> param){
//        MyPlayerController mp = new MyPlayerController();
//        FileChooser chooser = new FileChooser();
//        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Files", readFormats()));
//        List<Path> listOfFiles = new ArrayList<>();
////        System.out.println(mediaStatus);
//        List<File> fil = chooser.showOpenMultipleDialog(null);
//        if (fil != null) {
//            listOfFiles = convertListFiletoListPath(fil);
//        } else {}
//        if (listOfFiles != null && !entry) {
//            if (mediaStatus) {
//                mp.getMediaPlayer().stop();
//            }
//            mp.getPlayListFiles().addAll(listOfFiles);
//            mp.getPlayListView().setItems(mp.getPlayListFiles());
//            System.out.println(mp.getPlayListFiles().size() + " if2");
//            entry = true;
//        } else if (listOfFiles != null && entry && !mediaStatus) {
//            if (mediaStatus) {
//                mp.getMediaPlayer().stop();
//            }
//            mp.getPlayListFiles().clear();
//            mp.getPlayListFiles().addAll(listOfFiles);
//            mp.getPlayListView().setItems( mp.getPlayListFiles());
//            System.out.println( mp.getPlayListFiles().size() + " if3");
//        } else if (listOfFiles != null && entry && mediaStatus) {
//           mp.getMediaPlayer().stop();
//            mp.getPlayListFiles().clear();
//            mp.getPlayListFiles().addAll(listOfFiles);
//            mp.getPlayListView().setItems( mp.getPlayListFiles());
//            System.out.println( mp.getPlayListFiles().size() + " if4");
//        }
//    }
//}

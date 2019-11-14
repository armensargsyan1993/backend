package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/myPlayer.fxml"));
        AnchorPane root = loader.load();
        primaryStage.setTitle("ABP");
//      primaryStage.initStyle(StageStyle.TRANSPARENT);
//      primaryStage.setFullScreen(true);
        primaryStage.setResizable(true);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


//        Application.Parameters parameters = getParameters();
//        List<String> paramList = parameters.getUnnamed();
//        Button button = new Button("FUCK");
//        button.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                    MyPlayerController mp = loader.getController();
//                    for(String s : paramList) {
//                        mp.getPlayListFiles().addAll(s);
//                    }
//            }
//        });


//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("/myPlayer.fxml"));
//        primaryStage.setTitle("ABP");
//        Application.Parameters parameters = getParameters();
//        List<String> list = parameters.getUnnamed();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }



//        Application.Parameters par = getParameters();
//        List<String> list = par.getUnnamed();
//        ApplicationGetParam applicationGetParam = new ApplicationGetParam();
//        applicationGetParam.addPar(list);


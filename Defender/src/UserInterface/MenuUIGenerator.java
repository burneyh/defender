package UserInterface;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class MenuUIGenerator extends Application {

    public void generateHelpUI(){}
    public void generateAboutUI(){}
    public void generateHighScoreUI(){}
    public MenuUIGenerator getInstance(){return new MenuUIGenerator();}
    public void generateMainMenuUI(){}
    public void generatePauseMenuUI(){}

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        root.setPrefSize(800,600);
        Scene scene  = new Scene(root);



        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
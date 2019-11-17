package UserInterface;

import javafx.application.Application;
import javafx.stage.Stage;

public class MenuUIGenerator extends Application {

    public void generateHelpUI(){}
    public void generateAboutUI(){}
    public void generateHighScoreUI(){}
    public MenuUIGenerator getInstance(){return new MenuUIGenerator();}
    public void generateMainMenuUI(){}
    public void generatePauseMenuUI(){}

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public static void main(String[] args){
        launch(args)
    }
}
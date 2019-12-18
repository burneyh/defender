package UserInterface.Menu;

import UserInterface.MyApplication;
import UserInterface.SceneGenerator.SceneGenerator;
import javafx.animation.FadeTransition;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MenuSection extends Parent {
    public MenuSection(boolean isPause){
        VBox menu1 = new VBox(10);
        menu1.setTranslateX(260);
        menu1.setTranslateY(250);

        MenuButton resume = new MenuButton("Resume");
        resume.setOnMouseClicked(event -> {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setOnFinished(e -> setVisible(false));
            MyApplication.setScene(SceneGenerator.getInstance());
            MyApplication.ge.refresh();
        });

        MenuButton play = new MenuButton("Play");
        play.setOnMouseClicked(event -> {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setOnFinished(e -> setVisible(false));
            Username.setInstance();
            if(isPause){
                MyApplication.setScene(Username.getInstance(true));
            }
            else{
                MyApplication.setScene(Username.getInstance(false));
            }
        });

        MenuButton highScore = new MenuButton("High Score");
        highScore.setOnMouseClicked(event -> {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setOnFinished(e -> setVisible(false));
            if(isPause){
                MyApplication.setScene(HighScore.getInstance(true));
            }
            else{
                MyApplication.setScene(HighScore.getInstance(false));
            }
        });

        MenuButton help = new MenuButton("Help");
        help.setOnMouseClicked(event -> {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setOnFinished(e -> setVisible(false));
            if(isPause){
                MyApplication.setScene(Help.getInstance(true));
            }
            else {
                MyApplication.setScene(Help.getInstance(false));
            }
        });

        MenuButton about = new MenuButton("About");
        about.setOnMouseClicked(event -> {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setOnFinished(e -> setVisible(false));
            if(isPause){
                MyApplication.setScene(About.getInstance(true));
            }
            else{
                MyApplication.setScene(About.getInstance(false));
            }
        });

        MenuButton exit = new MenuButton("Exit");
        exit.setOnMouseClicked(event -> {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setOnFinished(e -> setVisible(false));
            ft.play();
            System.exit(0);
        });

        if (isPause == false)
            menu1.getChildren().addAll(play, highScore, help, about, exit);
        else
            menu1.getChildren().addAll(resume, play, highScore, help, about, exit);

        getChildren().addAll(menu1);
    }
}
package UserInterface.Menu;

import UserInterface.MyApplication;
import UserInterface.SceneGenerator.SceneGenerator;
import javafx.animation.FadeTransition;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MenuSection extends Parent {
    public MenuSection(){
        VBox menu1 = new VBox(10);
        menu1.setTranslateX(210);
        menu1.setTranslateY(150);

        MenuButton play = new MenuButton("Play");
        play.setOnMouseClicked(event -> {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setOnFinished(e -> setVisible(false));
            MyApplication.setScene(SceneGenerator.getInstance());
//            MyApplication.ge.createUniverse();
        });

        MenuButton highScore = new MenuButton("High Score");
        highScore.setOnMouseClicked(event -> {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setOnFinished(e -> setVisible(false));
            MyApplication.setScene(HighScore.getInstance());
        });

        MenuButton help = new MenuButton("Help");
        help.setOnMouseClicked(event -> {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setOnFinished(e -> setVisible(false));
            MyApplication.setScene(Help.getInstance());
        });

        MenuButton about = new MenuButton("About");
        about.setOnMouseClicked(event -> {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setOnFinished(e -> setVisible(false));
            MyApplication.setScene(About.getInstance());
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

        menu1.getChildren().addAll(play, highScore, help, about, exit);
        getChildren().addAll(menu1);
    }
}
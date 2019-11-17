package UserInterface.Menu;

import javafx.animation.FadeTransition;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MenuSection extends Parent {
    public MenuSection(){
        VBox menu1 = new VBox(10);
        menu1.setTranslateX(220);
        menu1.setTranslateY(150);

        MenuButton play = new MenuButton("Play");
        play.setOnMouseClicked(event -> {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setOnFinished(e -> setVisible(false));
            ft.play();
        });

        MenuButton highScore = new MenuButton("High Score");
        highScore.setOnMouseClicked(event -> {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setOnFinished(e -> setVisible(false));
            ft.play();
        });

        MenuButton help = new MenuButton("Help");
        help.setOnMouseClicked(event -> {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setOnFinished(e -> setVisible(false));
            ft.play();
        });

        MenuButton about = new MenuButton("About");
        about.setOnMouseClicked(event -> {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setOnFinished(e -> setVisible(false));
            ft.play();
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


        MenuButton btnResume = new MenuButton("RESUME");
        btnResume.setOnMouseClicked(event -> {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setOnFinished(e -> setVisible(false));
            ft.play();
        });

        menu1.getChildren().addAll(play, highScore, help, about, exit);
        getChildren().addAll(menu1);
    }
}

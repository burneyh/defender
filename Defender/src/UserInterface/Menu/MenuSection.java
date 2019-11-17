package UserInterface.Menu;

import UserInterface.OurApplication;
import javafx.animation.FadeTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
            Pane root = new Pane();
            Rectangle r = new Rectangle();
            r.setFill(Color.RED);
            r.setHeight(200);
            r.setWidth(200);
            root.getChildren().add(r);
            Scene s = new Scene(root);
            OurApplication.setScene(s);
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

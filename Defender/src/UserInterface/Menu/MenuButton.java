package UserInterface.Menu;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MenuButton extends StackPane {
    private Text text;

    public MenuButton(String name){
        text = new Text(name);
        text.setTranslateX(20);
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        text.setFill(Color.WHITE);

        Rectangle menuRect = new Rectangle(150, 30);
        menuRect.setStroke(Color.WHITE);
        menuRect.setStrokeWidth(2);
        menuRect.setFill(Color.rgb(38,6,71));

        setAlignment(Pos.CENTER_LEFT);
        setRotate(-0.5);
        getChildren().addAll(menuRect, text);

        setOnMouseEntered(e -> {
            menuRect.setTranslateX(10);
            text.setTranslateX(10);
            menuRect.setFill(Color.WHITE);
            text.setFill(Color.rgb(38,6,71));
        });

        setOnMouseExited(e -> {
            menuRect.setTranslateX(0);
            text.setTranslateX(20);
            menuRect.setFill(Color.rgb(38,6,71));
            text.setFill(Color.WHITE);
        });
    }
}

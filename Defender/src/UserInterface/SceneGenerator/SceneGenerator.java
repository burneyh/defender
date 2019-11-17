package UserInterface.SceneGenerator;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneGenerator extends Scene {
    static private SceneGenerator sceneGenerator = null;

    synchronized static public SceneGenerator getInstance() {
        if (sceneGenerator == null) {
                Group root = new Group();
                sceneGenerator = new SceneGenerator( root);
        }
        return sceneGenerator;
    }

    private SceneGenerator (Group root) {
        super(root);
    }

}

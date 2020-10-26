package sample.animations;

        import javafx.animation.TranslateTransition;
        import javafx.scene.Node;
        import javafx.util.Duration;

/**
 *
 * Creates by GGB 23/10/2020
 * TranslateTransition -> shaking our Object
 * Node -> button , field itc..
 * Shake Fields
 *
 */

public class Shake {

    private TranslateTransition translateTransition;

    // after incorrect logIn the fields shaking.
    public Shake(Node node){
        translateTransition = new TranslateTransition(Duration.millis(100),node);
        translateTransition.setFromX(0f);
        translateTransition.setByX(10);
        translateTransition.setCycleCount(3);
        translateTransition.setAutoReverse(true);
    }

    // play animation only.
    public void playAnimation(){
        translateTransition.playFromStart();
    }
}

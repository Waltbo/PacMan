import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class PacMan extends Pane implements Animation {
    private ImageView view = new ImageView();
    private Image image = new Image("file:spriteSheet.png");
    private ArrayList<Rectangle2D> pacManAnimation = new ArrayList();
    private double speed;
    private double direction;

    public PacMan(){
        pacManAnimation.add(new Rectangle2D(858, 58,36,36));
        pacManAnimation.add(new Rectangle2D(858, 108,36,36));
        pacManAnimation.add(new Rectangle2D(858, 158,36,36));
        view.setImage(image);
        this.setScaleY(0.5);
        this.setScaleX(0.5);

        view.setViewport(pacManAnimation.get(0));
        this.getChildren().add(view);
        setSpeed(0);
        setDirection(90);

    }



    @Override
    public void move() {
        this.setTranslateX(this.getTranslateX() + getSpeed() * Math.cos(getDirection() * (Math.PI * 2) / 360));
        this.setTranslateY(this.getTranslateY() + getSpeed() * Math.sin(getDirection() * (Math.PI * 2) / 360));
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }
}

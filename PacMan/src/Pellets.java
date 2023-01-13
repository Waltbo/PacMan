import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class Pellets extends Pane {
    private Rectangle2D pellet;
    private ImageView view = new ImageView();
    private boolean eaten;
    private int points = 10;


    public Pellets(Image image){

        setEaten(false);
        view.setImage(image);
        this.setVisible(true);
        this.setScaleY(0.69444444444);
        this.setScaleX(0.69444444444);
        view.setViewport(new Rectangle2D(511,615,18,18));
        this.getChildren().add(view);

    }

    public boolean isEaten() {
        return eaten;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
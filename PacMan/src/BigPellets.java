import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class BigPellets extends Pane {
    private ImageView view = new ImageView();
    private boolean isEaten;
    private int points = 25;


    public BigPellets(Image image){

        setEaten(false);
        view.setImage(image);
        this.setScaleY(0.69444444444);
        this.setScaleX(0.69444444444);
        view.setViewport(new Rectangle2D(532,600,31,31));
        this.getChildren().add(view);

    }

    public boolean isEaten() {
        return isEaten;
    }

    public void setEaten(boolean eaten) {
        isEaten = eaten;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
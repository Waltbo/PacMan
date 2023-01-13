import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class Ghost extends Pane implements  Animation{
    private Rectangle2D ghostName;
    private ImageView view = new ImageView();
    private Image image = new Image("file:spriteSheet.png");
    private double speed;
    private double direction;
    private boolean canBeEaten;
    private int points = 100;

    public Ghost(Rectangle2D ghostName){
        setCanBeEaten(false);
        this.ghostName = ghostName;
        view.setImage(image);
        this.setScaleY(.5);
        this.setScaleX(0.5);
        this.ghostName = ghostName;
        view.setViewport(ghostName);
        this.getChildren().add(view);
        speed = .80;

        direction = 90;

    }


    public void setGhostVulnerable(){
        view.setViewport(new Rectangle2D(6,557,32,32));
    }
    public void returnOriginal(){
        view.setViewport(ghostName);
    }

    @Override
    public void move() {
        this.setTranslateX(this.getTranslateX() + speed * Math.cos(direction * (Math.PI * 2) / 360));
        this.setTranslateY(this.getTranslateY() + speed * Math.sin(direction * (Math.PI * 2) / 360));
    }


    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public boolean isCanBeEaten() {
        return canBeEaten;
    }

    public void setCanBeEaten(boolean canBeEaten) {
        this.canBeEaten = canBeEaten;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}

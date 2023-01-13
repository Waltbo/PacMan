    import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class WallBlock extends Rectangle {
    public double getTopEdge(){
        return (this.getTranslateY() - 12.5);
    }
    public double getBottomEdge(){
        return (this.getTranslateY() + 12.5);
    }
    public double getLeftEdge(){
        return (this.getTranslateX() - 12.5);
    }
    public double getRightEdge(){
        return (this.getTranslateX()+12.5);
    }

    public WallBlock(Color color){
        this.setFill(color);
        this.setWidth(25);
        this.setHeight(25);
        this.setVisible(true);
    }
}

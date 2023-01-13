import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;


public class GamePane extends BorderPane {
    private LevelData level;
    Label currentScore;
    private Button quitButton;

    public GamePane(String levelFileName, PlayerInfo player){
        GridPane grid = new GridPane();
        BorderPane buttonHolder = new BorderPane();
        setQuitButton(new Button("Save and Exit"));
        buttonHolder.setCenter(quitButton);

        Label playerName = new Label("Current Player: "+player.getUserName());
        Label playerHighScore = new Label("Your High Score: "+player.getHighScore());

        currentScore = new Label("Current Score: "+0);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        grid.getColumnConstraints().addAll(col1,col1,col1);
        grid.addColumn(0,playerName);
        grid.addColumn(1, playerHighScore);
        grid.addColumn(2,currentScore);
        this.setTop(grid);

        setLevel(new LevelData(levelFileName));
        Timer time = new Timer();
        this.setCenter(getLevel());
        time.start();
        this.setBottom(buttonHolder);


    }

    public PacMan getPac(){
        return getLevel().getPac();
    }

    public Button getQuitButton() {
        return quitButton;
    }

    public void setQuitButton(Button quitButton) {
        this.quitButton = quitButton;
    }

    public LevelData getLevel() {
        return level;
    }

    public void setLevel(LevelData level) {
        this.level = level;
    }

    private class Timer extends AnimationTimer {

        private long lastTime = 0;

        public void handle(long now) {
            if (lastTime != 0) {
                if (now > lastTime + 1_000 ) {
                    currentScore.setText("Current Score: "+ getLevel().getCurrentScore());

                    lastTime = now;
                }
            } else {
                lastTime = now;

            }


        }
    }



}

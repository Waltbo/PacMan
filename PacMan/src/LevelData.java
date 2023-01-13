import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class LevelData extends Pane {
    private ArrayList<WallBlock> wallLocation = new ArrayList<>();
    private ArrayList<Ghost> ghosts = new ArrayList<>();
    private ArrayList<Rectangle> test = new ArrayList();
    private ArrayList<Pellets> pellets = new ArrayList<>();
    private ArrayList<BigPellets> bigPellets = new ArrayList<>();
    private Image image = new Image("file:spriteSheet.png");

    Timer timer = new Timer();

    int seconds=0;
    int ghostSeconds =0;
    private int currentScore=0;
    private PacMan pac;
    public LevelData(String levelFileName) {


        this.setMaxWidth(625);
        this.setMaxHeight(600);
        this.setStyle("-fx-background-color: black");
        try {
            Scanner inFile = new Scanner(new File(levelFileName));
            while(inFile.hasNext()){
                String[] levelData = inFile.nextLine().split(" ");
                int height = Integer.parseInt(levelData[0]);
                int width = Integer.parseInt(levelData[1]);
                for(int i=0; i<height;i++){
                    String[] lineData = inFile.nextLine().split("");
                    for(int j=0; j<width;j++) {
                        if (lineData[j].equals("1")) {
                            WallBlock wall = new WallBlock(new Color(0, 0, 1, 1));
                            this.getChildren().add(wall);
                            Rectangle rect = new Rectangle();
                            wall.setX(j*25);
                            wall.setY(i*25);
                            wallLocation.add(wall);
                        }else if(lineData[j].equals("0")){
                            Pellets pellet = new Pellets(image);
                            pellet.setTranslateX(j*25);
                            pellet.setTranslateY(i*25);
                            pellets.add(pellet);
                            this.getChildren().add(pellet);

                        }else if(lineData[j].equals("L")){
                            BigPellets pellet = new BigPellets(image);
                            pellet.setTranslateX(j*25);
                            pellet.setTranslateY(i*25);
                            this.getChildren().add(pellet);
                            bigPellets.add(pellet);
                        }
                        else if(lineData[j].equals("I")){
                            Ghost inky = new Ghost(new Rectangle2D(106,57,36,36));
                           inky.setTranslateX(j*25);
                           inky.setTranslateY(i*25);

                            ghosts.add(inky);
                            this.getChildren().add(inky);
                        }else if(lineData[j].equals("P")){
                            Ghost Pinky = new Ghost(new Rectangle2D(56,57,36,36));
                            Pinky.setTranslateX(j*25);
                            Pinky.setTranslateY(i*25);
                            ghosts.add(Pinky);
                            this.getChildren().add(Pinky);
                        }else if(lineData[j].equals("C")){
                            Ghost Clyde = new Ghost(new Rectangle2D(156,57,36,36));
                            Clyde.setTranslateX(j*25);
                            Clyde.setTranslateY(i*25);
                            ghosts.add(Clyde);
                            this.getChildren().add(Clyde);
                        }else if(lineData[j].equals("B")){
                            Ghost Blinky = new Ghost(new Rectangle2D(6,57,36,36));
                            Blinky.setTranslateX(j*25);
                            Blinky.setTranslateY(i*25);
                            ghosts.add(Blinky);
                            this.getChildren().add(Blinky);
                        }else if(lineData[j].equals("G")){
                            setPac(new PacMan());
                            getPac().setTranslateX(j*25);
                            getPac().setTranslateY(i*25);
                            this.getChildren().add(getPac());
                        }

                    }

                }


            }
            this.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if(event.getCode() == KeyCode.E){

                    }
                }
            });

            timer.start();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }



   public void ghostCollision(){
        for(int i=0; i<wallLocation.size(); i++){


            for(int j=0; j<ghosts.size();j++){
                Random random = new Random();
                int Math = random.nextInt((4-1)+1)+1;

                    if (ghosts.get(j).getBoundsInParent().intersects(wallLocation.get(i).getBoundsInParent())) {
                        if(ghosts.get(j).getDirection()==270){
                            ghosts.get(j).setTranslateY(ghosts.get(j).getTranslateY()+1.5);
                        }
                        if(ghosts.get(j).getDirection()==90){
                            ghosts.get(j).setTranslateY(ghosts.get(j).getTranslateY()-1.5);
                        }
                        if(ghosts.get(j).getDirection()==180){
                            ghosts.get(j).setTranslateX(ghosts.get(j).getTranslateX()+1.5);
                        }
                        if(ghosts.get(j).getDirection()==360){
                            ghosts.get(j).setTranslateX(ghosts.get(j).getTranslateX()-1.5);
                        }
                        ghosts.get(j).setDirection(Math*90);
                    }
            }
        }
    }
    public void randomGhostMove(){

        for(int i=0; i<ghosts.size();i++){
            Random random = new Random();
            int Math = random.nextInt((4-1)+1)+1;
            ghosts.get(i).setDirection(Math*90);
        }
    }

    public PacMan getPac() {
        return pac;
    }

    public void setPac(PacMan pac) {
        this.pac = pac;
    }

    public void pacManPelletCollision(){
        for(int i=0; i<pellets.size();i++){
            if(pellets.get(i).getBoundsInParent().intersects(pac.getBoundsInParent()) && !pellets.get(i).isEaten()){
                pellets.get(i).setVisible(false);
                setCurrentScore(getCurrentScore() + pellets.get(i).getPoints());
                pellets.get(i).setEaten(true);
            }
        }
    }

    public void pacManBigPelletCollision(){
        for(int i=0; i<bigPellets.size();i++){
            if(bigPellets.get(i).getBoundsInParent().intersects(pac.getBoundsInParent())){
                bigPellets.get(i).setVisible(false);
                setCurrentScore(getCurrentScore() + bigPellets.get(i).getPoints());
                class GhostTimer extends AnimationTimer {
                    private long lastTime = 0;
                    public void handle(long now) {
                        if(ghostSeconds==0) {
                            for (int k = 0; k < ghosts.size(); k++) {
                                ghosts.get(k).setGhostVulnerable();
                                ghosts.get(k).setCanBeEaten(true);
                            }
                        }


                        if (lastTime != 0) {
                            if (now > lastTime + 1_000_000_000 ) {
                                ghostSeconds++;
                                if(ghostSeconds==4){
                                    for(int k=0; k<ghosts.size();k++){
                                        ghosts.get(k).returnOriginal();
                                        ghosts.get(k).setCanBeEaten(false);
                                    }

                                    ghostSeconds=0;
                                    this.stop();

                                }
                                lastTime = now;
                            }
                        } else {
                            lastTime = now;

                        }

                    }
                }GhostTimer time = new GhostTimer();
                time.start();


            }
        }

    }

    public void pacManWallCollision(){
        for(int i=0; i<wallLocation.size();i++){
            if(wallLocation.get(i).getBoundsInParent().intersects(pac.getBoundsInParent())){
                if(pac.getDirection()==270){
                    pac.setTranslateY(pac.getTranslateY()+1.5);
                }
                if(pac.getDirection()==90){
                    pac.setTranslateY(pac.getTranslateY()-1.5);
                }
                if(pac.getDirection()==180){
                    pac.setTranslateX(pac.getTranslateX()+1.5);
                }
                if(pac.getDirection()==360){
                    pac.setTranslateX(pac.getTranslateX()-1.5);
                }
                pac.setSpeed(0);
            }
        }
    }

    public void getEaten(){
        for(int i=0; i<ghosts.size();i++) {
            if (pac.getBoundsInParent().intersects(ghosts.get(i).getBoundsInParent()) && ghosts.get(i).isCanBeEaten() && ghosts.get(i).isVisible()){
                setCurrentScore(getCurrentScore() + ghosts.get(i).getPoints());
                ghosts.get(i).setVisible(false);
            }else if(pac.getBoundsInParent().intersects(ghosts.get(i).getBoundsInParent()) && ghosts.get(i).isVisible()){
                gameOver();
            }
        }
    }

    public void gameOver(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Game Over", ButtonType.OK);
        timer.stop();
        alert.show();
        if(alert.getResult() == ButtonType.OK){

            try {
                alert.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }


    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }


    private class Timer extends AnimationTimer {
        private long previous = 0;
        private long lastTime = 0;
        public void handle(long now) {
            pacManWallCollision();
            pacManPelletCollision();
            getEaten();

            if (previous != 0) {
                if (now > previous + 1_000_000_0 ) {
                    for(int i=0; i<ghosts.size();i++){
                        getPac().move();

                        ghostCollision();
                        ghosts.get(i).move();

                    }
                    previous = now;
                }
            } else {
                previous = now;

            }
            if (lastTime != 0) {
                if (now > lastTime + 1_000_000_000 ) {
                    seconds++;
                    if(seconds==1){
                        seconds=0;
                        randomGhostMove();
                        pacManBigPelletCollision();

                    }
                    lastTime = now;
                }
            } else {
                lastTime = now;

            }

        }
    }


}

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Loader extends GridPane {
    ArrayList<PlayerInfo> players;


    public Loader(String playersDataInfo){
        this.setWidth(425);
        this.setHeight(75);
        TextField playerName = new TextField("Jane Doe");
        Button addPlayerButton = new Button("Create New Player");
        Button loadPlayerButton = new Button("Load Selected Player");
        ComboBox<String> listOfPlayers = new ComboBox<>();
        players = new ArrayList<>();
        loadPlayers(playersDataInfo);
        for(int i=0; i<players.size();i++){
            listOfPlayers.getItems().add(players.get(i).getUserName());
        }

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);


        loadPlayerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event1) {
                startUpGame(players.get(listOfPlayers.getSelectionModel().getSelectedIndex()));
            }

        }
        );

        addPlayerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event1) {
                startUpGame (new PlayerInfo(playerName.getText(), 0));


            }
        });

        this.getColumnConstraints().addAll(col1);
        this.addColumn(0,playerName);
        this.addColumn(1,addPlayerButton);
        this.addColumn(2,loadPlayerButton);
        this.addColumn(3,listOfPlayers);


        listOfPlayers.getSelectionModel().selectFirst();


    }

    public void startUpGame(PlayerInfo player){
        GamePane gamePane = new GamePane("levelData.txt", player);
        Scene scene = new Scene(gamePane, 625, 650);

        gamePane.getQuitButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(player.getHighScore()<gamePane.getLevel().getCurrentScore()){
                    player.setHighScore(gamePane.getLevel().getCurrentScore());
                }
                for(int i=0; i<players.size();i++){
                    if(players.get(i).getUserName().equals(player.getUserName())){
                        players.get(i).setHighScore(player.getHighScore());
                    }
                }
                boolean exist = false;
                for(int i=0; i<players.size();i++){
                    if(players.get(i).getUserName().equals(player.getUserName())){
                        exist=true;
                    }
                }
                if(exist){
                    players.add(player);
                }

                savePlayers("playerInfo.txt");

                System.exit(0);
            }
        });

        scene.addEventFilter(KeyEvent.KEY_PRESSED,
                event -> {
                    switch (event.getCode().toString()) {
                        case "UP":
                            gamePane.getPac().setDirection(270);
                            gamePane.getPac().setSpeed(.4);
                            gamePane.getPac().setTranslateY( gamePane.getPac().getTranslateY()-1.5);
                            gamePane.getPac().setRotate(270);
                            break;
                        case "DOWN":
                            gamePane.getPac().setDirection(90);
                            gamePane.getPac().setSpeed(.4);
                            gamePane.getPac().setTranslateY( gamePane.getPac().getTranslateY()+1.5);
                            gamePane.getPac().setRotate(90);
                            break;
                        case "LEFT":
                            gamePane.getPac().setDirection(180);
                            gamePane.getPac().setSpeed(.4);
                            gamePane.getPac().setTranslateX( gamePane.getPac().getTranslateX()-1.5);
                            gamePane.getPac().setRotate(180);
                            break;
                        case "RIGHT":
                            gamePane.getPac().setDirection(360);
                            gamePane.getPac().setSpeed(.4);
                            gamePane.getPac().setTranslateX( gamePane.getPac().getTranslateX()+1.5);
                            gamePane.getPac().setRotate(360);
                            break;
                    }
                });
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(scene);
        secondaryStage.show();

    }



    public void loadPlayers(String playersInfo){
        try {
            Scanner scan = new Scanner(new File(playersInfo));
            while(scan.hasNext()){
                String [] playerInfo = scan.nextLine().split(",");
                players.add(new PlayerInfo(playerInfo[0].trim(), Integer.parseInt(playerInfo[1].trim())));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
    public void savePlayers(String playersInfo){
        try {
            PrintWriter pw = new PrintWriter(playersInfo);
            String data ="";
            for(int i=0; i<players.size();i++){
                data += players.get(i).toString()+System.lineSeparator();
            }
            pw.print(data);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

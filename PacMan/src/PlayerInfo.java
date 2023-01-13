
public class PlayerInfo {
    private String userName;
    private int highScore;

    public PlayerInfo(String userName, int highScore){
        this.setUserName(userName);
        this.setHighScore(highScore);
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
    @Override
    public String toString(){
        return userName+", "+highScore;
    }
}

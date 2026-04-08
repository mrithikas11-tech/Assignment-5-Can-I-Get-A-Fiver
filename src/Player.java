public abstract class Player {

    protected String playerName;
    private int wins;
    private Sign curSign;

    /**
     * Convert int sign to the enum represetation of rock paper, scissors
     * @param intSign sign choice in terms of int
     */
    public void intToEnum(int intSign){
        if(intSign == Sign.ROCK.getValue()){
            curSign = Sign.ROCK;
        }
        else if(intSign == Sign.PAPER.getValue()){
            curSign = Sign.PAPER;
        }
        else if(intSign == Sign.SCISSORS.getValue()){
            curSign = Sign.SCISSORS;
        }
    }
    
    public Sign makeChoice(){
        return curSign;
        
    }
    public Sign getCurSign(){
        return curSign;
    }
    public void setCurSign(Sign sign){
        curSign = sign;
    }

    public void addWin(){
        wins+=1;
    }
    public int getWins(){
        return wins;
    }

    public void getintRPS(){
}

    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String name){
        this.playerName = name;
    }
    

}

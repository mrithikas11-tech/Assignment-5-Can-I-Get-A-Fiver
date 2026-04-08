public class RoundSequence {
    

    private String sequence;
    //private String currSequence = "";
    private String humanSeq;
    private String comSeq;
    Player comPlayer;
    Player humPlayer;

    public RoundSequence() {
        sequence = "";
        humanSeq = "";
        comSeq = "";
    }
    
    public RoundSequence(Player computer, Player human) {
        this.comPlayer = computer;
        this.humPlayer = human;
        sequence = "";
        humanSeq = "";
        comSeq = "";
    }
    public void updatePlayers(Player human, Player computer){
        this.comPlayer = computer;
        this.humPlayer = human;
    }
    public String getCurrSequence(int N){
        return sequence.substring(sequence.length()-N-1, N-1);
    }
    
    public void updateRoundSequence(){
        String cInitial;
        String hInitial;
         if (humPlayer.getCurSign() == Sign.ROCK) {
                hInitial = "R";
                sequence = sequence.concat(hInitial);
                humanSeq = humanSeq.concat(hInitial);
        }
        else if(humPlayer.getCurSign() == Sign.PAPER){
                hInitial = "P";
                sequence = sequence.concat(hInitial);
                humanSeq = humanSeq.concat(hInitial);
        }
        else if(humPlayer.getCurSign()== Sign.SCISSORS){
                hInitial = "S";
                sequence = sequence.concat(hInitial);
                humanSeq = humanSeq.concat(hInitial);
        }
        if (comPlayer.getCurSign()== Sign.ROCK) {
                cInitial = "R";
                sequence = sequence.concat(cInitial);
                comSeq = comSeq.concat(cInitial);
        }
        else if(comPlayer.getCurSign() == Sign.PAPER){
                cInitial = "P";
                sequence = sequence.concat(cInitial);
                comSeq = comSeq.concat(cInitial);
        }
        else if(comPlayer.getCurSign()== Sign.SCISSORS){
                cInitial = "S";
                sequence = sequence.concat(cInitial);
                comSeq = comSeq.concat(cInitial);
        }
        
    }
    public void updateRoundSequence(Player computer, Player human){
        String cInitial;
        String hInitial;

         if(null != human.getCurSign())switch (human.getCurSign()) {
            case ROCK:
                hInitial = "R";
                sequence = sequence.concat(hInitial);
                humanSeq = humanSeq.concat(hInitial);
            case PAPER:
                hInitial = "P";
                sequence = sequence.concat(hInitial);
                humanSeq = humanSeq.concat(hInitial);
            case SCISSORS:
                hInitial = "S";
                sequence = sequence.concat(hInitial);
                humanSeq = humanSeq.concat(hInitial);
            default:
                break;
        }

        if(null != computer.getCurSign())switch (computer.getCurSign()) {
            case ROCK:
                cInitial = "R";
                sequence = sequence.concat(cInitial);
                comSeq = comSeq.concat(cInitial);
            case PAPER:
                cInitial = "P";
                sequence = sequence.concat(cInitial);
                comSeq = comSeq.concat(cInitial);
            case SCISSORS:
                cInitial = "S";
                sequence = sequence.concat(cInitial);
                comSeq = comSeq.concat(cInitial);
            default:
                break;
        }
    }
    public String getRoundSequence(){
        return sequence;
    }
    public String getHumanSequence(){
        return humanSeq;
    }
    public String getComputerSequence(){
        return comSeq;
    }

    public String getNSizeSequence(int N){
        return sequence.substring(sequence.length()-N-1, sequence.length()-1);
    }

    public void print(){
        System.out.println(sequence);
        System.out.println("Computer "+ comSeq);
        System.out.println("Human "+ humanSeq);
    }

    public boolean isLengthEqualN(int N){
        return comSeq.length()>=N;
    }
    
    public boolean isLengthGreater(int N){
        return sequence.length() >= N;
    }

    public boolean isEmpty(){
        return sequence.isEmpty();
    }

    


    
}

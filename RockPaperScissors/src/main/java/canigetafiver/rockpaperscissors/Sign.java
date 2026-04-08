package canigetafiver.rockpaperscissors;
//enum that represents the three possible choices: rock, paper, and scissors.
public enum Sign { 
    ROCK(0,"R"), 
    PAPER(1, "P"),
    SCISSORS(2, "S");
    private final int value;
    private final String initial;
    /** Deprecate
    private Sign(int value) {
        this.value = value;
        this.initial = "";
    }
    */
    
    Sign(int i, String initial) {
        this.value = i;
        this.initial = initial;
        //TODO Auto-generated constructor stub
    }

    public int getValue(){
        return value;
    }
    public String getInitials(){
        return initial;
    }
    public static Sign getSignFromInt(int num){
        if(num == ROCK.getValue()){
            return ROCK;
        }
        else if(num == PAPER.getValue()){
            return PAPER;
        }
        else if (num == SCISSORS.getValue()){
            return SCISSORS;
        }
        return null;
    }
    public static Sign getSignFromString(String initial){
        if(initial.compareToIgnoreCase(ROCK.getInitial())==0){
            return ROCK;
        }
        else if(initial.compareToIgnoreCase(PAPER.getInitial())==0){
            return PAPER;
        }
        else if (initial.compareToIgnoreCase(SCISSORS.getInitial())==0) {
            return SCISSORS;
        }
        return null;
    }
    public Sign beats(Sign theSign){
        if(null != theSign)switch (theSign) {
            case ROCK -> {
                return SCISSORS;
            }
            case PAPER -> {
                return ROCK;
            }
            case SCISSORS -> {
                return PAPER;
            }
            default -> {
                break;
            }

            }
            return null;
        }
    public Sign loses(Sign theSign){
        if(null != theSign)switch (theSign) {
            case ROCK -> {
                return SCISSORS;
            }
            case PAPER -> {
                return ROCK;
            }
            case SCISSORS -> {
                return PAPER;
            }
            default -> {
            }
        }
        return null;
    }
    public Sign loseTo(){
        if(value == ROCK.getValue()){
                return PAPER;
           }
           else if(value == PAPER.getValue()){
               return SCISSORS;

           }
           else if (value == SCISSORS.getValue()) {
               return ROCK;
           }
           return null;
        
    }
    public Sign draws(){
        /* 
        if(true) switch(value){
            case ROCK.value -> {
                return ROCK;
            }
            case PAPER -> {
                return PAPER;
            }
            case SCISSORS -> {
                return PAPER;
            }
            default -> {
            }
        } 
            */
           if(value == ROCK.getValue()){
                return ROCK;
           }
           else if(value == PAPER.getValue()){
                return PAPER;

           }
           else if (value == SCISSORS.getValue()) {
               return SCISSORS;
           }
           return null;
    }
    public String getInitial(){
        if(value == ROCK.getValue()){
            return "R";
        }
        else if(value == PAPER.getValue()){
            return "P";
        }
        else if (value == SCISSORS.getValue()){
            return "S";
        }
        return "";
    }
        
        

}
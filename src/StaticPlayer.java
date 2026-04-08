
public class StaticPlayer extends Player{

    Sign bound;
    String pattern;
    int theSign;
    public StaticPlayer(Sign bound){
        this.bound = bound;
        theSign = bound.getValue();
    }
    public StaticPlayer(String pattern){
        this.pattern = pattern;
    }

    @Override
    public Sign makeChoice(){
        if(pattern != null){
            intToEnum(patternLoop().getValue());
            return super.getCurSign();
        }
        else{
            intToEnum(theSign);
            return bound;
        }
        
    }

    public Sign patternLoop(){
        String initial = pattern.substring(0, 1);
        pattern = pattern.substring(1).concat(initial);
        return Sign.getSignFromString(initial);
    }
    
}


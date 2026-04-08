package canigetafiver.rockpaperscissors;
import java.util.Scanner;

public class HumanPlayer extends Player {
    
    private final HumanInput humanInput;

    public HumanPlayer(Scanner scnr){
        super.setPlayerName("Human");
        humanInput = new HumanInput(scnr);
    }
    public HumanPlayer(){
        super.setPlayerName("Human");
        humanInput = new HumanInputGUI();
    }
    /**
     * Gets human player choice from command line and save it
     */
    @Override
    public void getintRPS(){
        int input;
        input = humanInput.inputThrow(false);
        intToEnum(input);
    }
    /**
     * Get the player's choice and return their choice
     */
    @Override
    public Sign getCurSign(){
        getintRPS();
        return super.getCurSign();
    }
    /**
     * Return the human player's choice. 
     * @return the sign human player has chosen
     */
    @Override
    public Sign makeChoice(){
        getintRPS();
        return super.getCurSign();
    }





    
}


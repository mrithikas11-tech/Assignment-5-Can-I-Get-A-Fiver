package canigetafiver.rockpaperscissors;

public class HumanPlayerGUI extends Player{

    public HumanPlayerGUI(){
        super.setPlayerName("Human");
        //humanInput = new HumanInputGUI();
    }
    /**
     * Get the player's choice and return their choice
     */
    @Override
    public Sign getCurSign(){
        return super.getCurSign();
    }
    /**
     * Return the human player's choice.
     * @return the sign human player has chosen
     */
    @Override
    public Sign makeChoice(){
        return super.getCurSign();
    }
}

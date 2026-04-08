
import java.util.Scanner;

public class HumanInput {

    private Scanner scnr;

    public HumanInput(Scanner scnr){
        this.scnr=scnr;
    }

    /**
     * Gets the human player's choice for rock, paper, or scissors in a command line from 0 to 2
     * @return the int represetnation the players choice.
     */
    public int inputThrow(boolean test){
        int throwSign;
        throwSign =-1;
        
        boolean valid=false;
        while(!valid){
            try {
                if(test == false) {
                System.out.println("Enter A Number: 0.Rock, 1.Paper, or 2.Scissors");
                }
                    
                    throwSign = scnr.nextInt();

                    if (0<=throwSign && throwSign<=2) {
                        valid = true;
                    }else {
                        System.out.println(throwSign + " is not a valid input");
                        System.out.println("Enter correct amount again");
                    }

                    } catch (Exception e) {
                        valid = false;
                        System.out.println(throwSign+"is not a valid input");
                        System.out.println("Enter correct amount again");
                        scnr.nextLine();
                    }
                    
        }
        return throwSign;
    }

}






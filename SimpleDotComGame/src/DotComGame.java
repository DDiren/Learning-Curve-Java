import java.util.Scanner;

public class DotComGame {
    public static void main(String[] args) {
        System.out.println("Game size 1x7");
        for (char c : "Sink The Krait-MK2 in 5 guesses!\n\n".toCharArray()){
            System.out.print(c);
            try
            {
                Thread.sleep(75);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
        startGame();
    }

    public static void startGame() {
        int loopit = 0;
        while (loopit < 1) {
            DotCom dot = new DotCom();

            int x = (int) (Math.random() * (5 - 1)+1) + 1;   //create random number in range of [1,7]
            int[] locations = {x, x + 1, x + 2};
            dot.setLocationCells(locations);

            for (int count = 0; count < 5; ++count) {
                //pass getGuess to checkYourself method, and assign returned value to result
                String result = dot.checkYourself(dot.getGuess());

                String testResult = "";
                if (result.equals("Kill")) {
                    testResult = "\nKrait-MK2 is destroyed. You've won!";
                    System.out.println(testResult);
                    break;
                } else if (count == 4 && !(result.equals("Kill"))) {    //4 guesses made and sink hasn't sunk, you've lost
                    testResult = "\nKrait-MK2 lives. You've lost!";
                    System.out.println(testResult);
                    break;
                }
            }
            char z = dot.end();
            if ((z != 'y') && (z != 'Y')) {
                ++loopit;
            }
            System.out.print('\n');
        }
    }
}

class DotCom {
    private int[] locationCells;
    private int numOfHits = 0;  //initially no hits are made, so this is 0

    public void setLocationCells(int[] locs) {  //Setter to set locations to locationCells
        locationCells = locs;
    }
    
    String checkYourself(String strGuess) {

        int guess = Integer.parseInt(strGuess); //Convert string to integer
        String result = "Miss";

        //Create a variable named cell Go through each element of the array locationCell
        for (int cell : locationCells) {
            if (guess == cell) {    //Check if user guess is the same as one of the array ellements
                result = "Hit";     //if a mach is made, change result from Miss to Hit
                numOfHits++;

                break;
            }
        }

        //if the amount of hits accumulated is equal to the length of the location array, all the dots are hit
        if (numOfHits == locationCells.length) {
            result = "Kill";    //if so, the ship has sunk, change result to Kill
        }
        System.out.println(result);
        return result;
    }

    public static String getGuess() {
        Scanner guess = new Scanner(System.in);
        System.out.print("Enter a guess: ");
        String userGuess = guess.nextLine();

        return userGuess;
    }

    public static char end() {
        Scanner endChoice = new Scanner(System.in);
        System.out.print("\nEnter anything to end, 'y' to restart: ");
        char choice = endChoice.next().charAt(0);

        return choice;
    }
}

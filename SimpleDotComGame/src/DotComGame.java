import java.util.Scanner;
import java.util.ArrayList;

public class DotComGame {
    public static void main(String[] args) {
        System.out.println("Game size 1x7, ship size 3");

        //for cosmetic purposes, prints each character in the string one by one with 100ms intervals
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

            int x = (int) (Math.random() * (5 - 1)+1) + 1;  //create random number in range of [1,5]

            ArrayList<String> locations = new ArrayList<String>();

            for (int ct = 0; ct < 3; ++ct){
                String y = Integer.toString(x);     //conver random number to string
                ++x;                                //add 1 to it
                locations.add(y);                   //add it to locations array list
            }   //do it 3 times since the size of the ship is 3


            dot.setLocationCells(locations);    //send it as locations

            for (int count = 0; count < 5; ++count) {

                //pass getGuess to checkYourself method, and assign returned value to result
                String result = dot.checkYourself(dot.getGuess());

                String testResult = "";

                if (result.equals("Kill")) {
                    testResult = "\nKrait-MK2 is destroyed. You've won!";
                    System.out.println(testResult);
                    System.out.println("You took " + (count + 1) + " guesses.");

                    break;
                } else if (count == 4 && !(result.equals("Kill"))) {    //4 guesses made and ship hasn't sunk, you've lost
                    testResult = "\nKrait-MK2 lives. You've lost!";
                    System.out.println(testResult);

                    break;
                } else if (result.equals("This location was hit before.")) {
                    --count;
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
    private ArrayList<String> locationCells;
    private ArrayList<String> removedDots = new ArrayList<String>();

    public void setLocationCells(ArrayList<String> locs) {  //Setter to set locations parameter to locationCells
        locationCells = locs;
    }

    String checkYourself(String strGuess) {

        String result = "Miss";

        int index = locationCells.indexOf(strGuess);    //get the index of user guess

        if (index >= 0) {   //if user guess not in the array list, -1
            removedDots.add(locationCells.get(index));  //add the hit ellement to another array
            locationCells.remove(index);    //remove that element represented by index
            result = "Hit";     //since input is in the list, might as well call it a hit;

        } else if (removedDots.contains(strGuess)) {    //if the previous location was hit, execute this
            result = "This location was hit before.";   //these will not be counted as guesses
        }

        if (locationCells.isEmpty()) {
            result = "Kill";    //if all elements are gone, then the ship is gone too
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

public class DotComGame {
    public static void main(String[] args) {
        DotCom dot = new DotCom();
        
        int[] locations = {2,3,4};
        dot.setLocationCells(locations);

        String userGuess = "3";

        //pass userGuess to checkYourself method, and assign returned value to result
        String result = dot.checkYourself(userGuess);
        
        String testResult = "Failed";
        if (result.equals("Hit")) {
            testResult = "Passed";
        }
        System.out.println(testResult);
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
}

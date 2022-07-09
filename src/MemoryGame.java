import java.util.*;
public class MemoryGame {

    private String[][] displayedArray = {{" ", "1", "2", "3", "4"},
                                         {"A", "X", "X", "X", "X"},
                                         {"B", "X", "X", "X", "X"},
    };

    private String[][] hidedArray = {{" ", "1", "2", "3", "4"},
                                     {"A", "X", "X", "X", "X"},
                                     {"B", "X", "X", "X", "X"},
    };

    private ArrayList<String> arrayFromFile;

    public MemoryGame(ArrayList<String> arrayFromFile) {
        this.arrayFromFile = arrayFromFile;

        setRandomlyHidedArray();
    }

    private void setRandomlyHidedArray(){

        //Set hided first row to random words
        for (int i = 1; i < hidedArray[1].length; i++){
            hidedArray[1][i] = arrayFromFile.get(i-1);
        }

        //Shuffle array from file
        Collections.shuffle(arrayFromFile);

        //Set hided second row to random words
        for (int i = 1; i < hidedArray[2].length; i++){
            hidedArray[2][i] = arrayFromFile.get(i-1);
        }
    }

    public void displayCurrentlyGame(String answer){

    }


}

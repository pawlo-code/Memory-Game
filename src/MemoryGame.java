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

    private int lastCoordinates[] = {0,0};

    private boolean ifFirstAnswer = true;

    private int answerCounter = 0;

    private String firstAnswer = "";
    private String secondAnswer = "";

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

    public void displayCurrentlyGame() {
        System.out.println("\n-----------------------------");
        System.out.println("\t Level: soon");
        System.out.println("\t Guess chances: soon");
        //creating grid of game
        for (int i = 0; i <= 2; i++) {
            System.out.println("\n");
            for (int j = 0; j <= 4; j++) {
                //adding tabulator before some chars
                if (displayedArray[i][j] == " " || displayedArray[i][j] == "A" || displayedArray[i][j] == "B" ){
                    System.out.printf("\t\t" + displayedArray[i][j] + " ");
                } else {
                    System.out.printf(displayedArray[i][j] + " ");
                }
            }
        }
        System.out.println("\n\n-----------------------------");

    }

    public void checkGameStatus(String answer){
        answerCounter++;
        switch (answer) {
            case "A1" -> {
                displayedArray[1][1] = hidedArray[1][1];
                checkAnswer(1, 1);
            }
            case "A2" -> {
                displayedArray[1][2] = hidedArray[1][2];
                checkAnswer(1, 2);
            }
            case "A3" -> {
                displayedArray[1][3] = hidedArray[1][3];
                checkAnswer(1, 3);
            }
            case "A4" -> {
                displayedArray[1][4] = hidedArray[1][4];
                checkAnswer(1, 4);
            }
            case "B1" -> {
                displayedArray[2][1] = hidedArray[2][1];
                checkAnswer(2,  1);
            }
            case "B2" -> {
                displayedArray[2][2] = hidedArray[2][2];
                checkAnswer(2, 2);
            }
            case "B3" -> {
                displayedArray[2][3] = hidedArray[2][3];
                checkAnswer(2, 3);
            }
            case "B4" -> {
                displayedArray[2][4] = hidedArray[2][4];
                checkAnswer(2, 4);
            }
        }
    }

    private void checkAnswer(int row, int column){
        if(ifFirstAnswer){
            firstAnswer = displayedArray[row][column];
            ifFirstAnswer = false;
            lastCoordinates[0] = row;
            lastCoordinates[1] = column;
        }else{
            secondAnswer = displayedArray[row][column];
            ifFirstAnswer = true;
        }
        //Answer check every two rounds
        if(answerCounter % 2 == 0){
            if(firstAnswer == secondAnswer){
                System.out.println("Nice you did it!");
            } else {
                displayedArray[row][column] = "X";
                displayedArray[lastCoordinates[0]][lastCoordinates[1]] = "X";
            }
        }
    }
}

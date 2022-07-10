import java.util.*;
public class MemoryGame {

    private String[][] displayedArray;
    private String[][] hidedArray;
    private String[][] displayedArrayEasy = {{" ", "1", "2", "3", "4"},
            {"A", "X", "X", "X", "X"},
            {"B", "X", "X", "X", "X"},
    };

    private String[][] hidedArrayEasy = {{" ", "1", "2", "3", "4"},
            {"A", "X", "X", "X", "X"},
            {"B", "X", "X", "X", "X"},
    };

    private String[][] displayedArrayHard = {{" ", "1", "2", "3", "4", "5", "6", "7", "8"},
            {"A", "X", "X", "X", "X", "X", "X", "X", "X"},
            {"B", "X", "X", "X", "X", "X", "X", "X", "X"},
    };

    private String[][] hidedArrayHard = {{" ", "1", "2", "3", "4", "5", "6", "7", "8"},
            {"A", "X", "X", "X", "X", "X", "X", "X", "X"},
            {"B", "X", "X", "X", "X", "X", "X", "X", "X"},
    };

    private int lastCoordinates[] = {0, 0};

    private boolean ifFirstAnswer = true;

    private String firstAnswer = "";
    private String secondAnswer = "";
    private String level;
    private ArrayList<String> arrayFromFile;
    private int roundCounter;

    public MemoryGame(ArrayList<String> arrayFromFile, String level) {
        this.arrayFromFile = arrayFromFile;
        this.level = level;

        if (level.equals("EASY")) {
            displayedArray = displayedArrayEasy;
            hidedArray = hidedArrayEasy;
            roundCounter = 10 * 2;
        } else if (level.equals("HARD")) {
            displayedArray = displayedArrayHard;
            hidedArray = hidedArrayHard;
            roundCounter = 15 * 2;
        }

        setRandomlyHidedArray();
    }

    private void setRandomlyHidedArray() {

        //Set hided first row to random words
        for (int i = 1; i < hidedArray[1].length; i++) {
            hidedArray[1][i] = arrayFromFile.get(i - 1);
        }

        //Shuffle array from file
        Collections.shuffle(arrayFromFile);

        //Set hided second row to random words
        for (int i = 1; i < hidedArray[2].length; i++) {
            hidedArray[2][i] = arrayFromFile.get(i - 1);
        }
    }

    public void displayCurrentlyGame() {
        System.out.println("\n-----------------------------");
        System.out.println("\t Level: " + level);
        System.out.println("\t Guess chances: " + Integer.toString(roundCounter / 2));
        //creating grid of game
        for (int i = 0; i <= 2; i++) {
            System.out.println("\n");
            for (int j = 0; j <= displayedArray[2].length - 1; j++) {
                //adding tabulator before some chars
                if (displayedArray[i][j] == " " || displayedArray[i][j] == "A" || displayedArray[i][j] == "B") {
                    System.out.printf("\t\t" + displayedArray[i][j] + " ");
                } else {
                    System.out.printf(displayedArray[i][j] + " ");
                }
            }
        }
        System.out.println("\n\n-----------------------------");

    }

    public void checkGameStatus(String answer) {
        roundCounter--;
        checkIfWinOrLose();
        int firstIndex = 0;
        int secondIndex = Integer.parseInt(answer.substring(1, 2));

        if (answer.charAt(0) == 'A') {
            firstIndex++;
        } else if (answer.charAt(0) == 'B') {
            firstIndex++;
            firstIndex++;
        }
        displayedArray[firstIndex][secondIndex] = hidedArray[firstIndex][secondIndex];
        checkAnswer(firstIndex, secondIndex);

    }

    private void checkAnswer(int row, int column) {
        displayCurrentlyGame();
        if (ifFirstAnswer) {
            firstAnswer = displayedArray[row][column];
            ifFirstAnswer = false;
            lastCoordinates[0] = row;
            lastCoordinates[1] = column;
        } else {
            secondAnswer = displayedArray[row][column];
            ifFirstAnswer = true;
        }
        //Answer check every two rounds
        if (roundCounter % 2 == 0) {
            if (firstAnswer == secondAnswer) {
                System.out.println("Nice you did it!");
            } else {
                displayedArray[row][column] = "X";
                displayedArray[lastCoordinates[0]][lastCoordinates[1]] = "X";
            }
        }
    }

    public enum WinOrLose {
        WIN, LOSE, NOTHING
    }

    public WinOrLose checkIfWinOrLose() {
        if (roundCounter == 0) {
            displayedArray = hidedArray;
            return WinOrLose.LOSE;
        } else if (Arrays.toString(displayedArray[2]).contains("X") == false) {
            return WinOrLose.WIN;
        } else {
            return WinOrLose.NOTHING;
        }
    }
}

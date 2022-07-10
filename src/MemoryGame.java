import java.time.Instant;
import java.util.*;
import java.time.LocalDate;

public class MemoryGame {

    private String[][] displayedArray;
    private String[][] hiddenArray;
    private String[][] displayedArrayEasy = {{" ", "1", "2", "3", "4"},
                                             {"A", "X", "X", "X", "X"},
                                             {"B", "X", "X", "X", "X"},
    };

    private String[][] hiddenArrayEasy = {{" ", "1", "2", "3", "4"},
                                          {"A", "X", "X", "X", "X"},
                                          {"B", "X", "X", "X", "X"},
    };

    private String[][] displayedArrayHard = {{" ", "1", "2", "3", "4", "5", "6", "7", "8"},
                                             {"A", "X", "X", "X", "X", "X", "X", "X", "X"},
                                             {"B", "X", "X", "X", "X", "X", "X", "X", "X"},
    };

    private String[][] hiddenArrayHard = {{" ", "1", "2", "3", "4", "5", "6", "7", "8"},
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
    private long timeInSeconds;
    public MemoryGame(ArrayList<String> arrayFromFile, String level) {
        this.arrayFromFile = arrayFromFile;
        this.level = level;
        getCurrentTime();

        if (level.equals("EASY")) {
            displayedArray = displayedArrayEasy;
            hiddenArray = hiddenArrayEasy;
            roundCounter = 10 * 2;
        } else if (level.equals("HARD")) {
            displayedArray = displayedArrayHard;
            hiddenArray = hiddenArrayHard;
            roundCounter = 15 * 2;
        }

        setRandomlyHidedArray();
    }
    private void getCurrentTime(){
        Instant currentInstant = Instant.now();
        timeInSeconds = currentInstant.getEpochSecond();
    }
    private void setRandomlyHidedArray() {

        //Set hided first row to random words
        for (int i = 1; i < hiddenArray[1].length; i++) {
            hiddenArray[1][i] = arrayFromFile.get(i - 1);
        }

        //Shuffle array from file
        Collections.shuffle(arrayFromFile);

        //Set hided second row to random words
        for (int i = 1; i < hiddenArray[2].length; i++) {
            hiddenArray[2][i] = arrayFromFile.get(i - 1);
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
            firstIndex = 1;
        } else if (answer.charAt(0) == 'B') {
            firstIndex = 2;
        }
        displayedArray[firstIndex][secondIndex] = hiddenArray[firstIndex][secondIndex];
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
    public WinOrLose checkIfWinOrLose() {
        if (roundCounter == 0) {
            displayedArray = hiddenArray;
            return WinOrLose.LOSE;
        } else if (Arrays.toString(displayedArray[2]).contains("X") == false) {
            return WinOrLose.WIN;
        } else {
            return WinOrLose.NOTHING;
        }
    }

    public void checkResult(){
        System.out.println("YOUR RESULTS!");
        if(level.equals("EASY"))
        {
            System.out.println("It takes you: "+ Integer.toString(10-roundCounter/2) + " rounds!");
        } else {
            System.out.println("It takes you: "+ Integer.toString(15-roundCounter/2) + " rounds!");
        }

        Instant currentInstant = Instant.now();
        long getSeconds = currentInstant.getEpochSecond() - timeInSeconds;
        Scanner scanner = new Scanner(System.in);

        System.out.println("It takeS " + Long.toString(getSeconds) + " seconds! Nice one!");
        System.out.println("Write your name to save your results in Leaderboard!");

        String name = scanner.nextLine();

        Leaderboard.fileWriterMethod(name+ " | Date: " + LocalDate.now() +  " | "+ "Time: " + getSeconds + "| Tries: " + Integer.toString(10-roundCounter/2));
        Leaderboard.fileReaderMethod();
    }
}

import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) {

        boolean shouldContinueGame = true;
        final String[] answersLvlEasy = {"A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4"};
        final String[] answersLvlHard = {"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8",
                                         "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8"};
        int numberWords = 4;

        ReadFile file = new ReadFile();

        Scanner scanner = new Scanner(System.in);


            do {
                System.out.println("\tMEMORY GAME");
                System.out.println("\tSelect difficulty level: easy/hard");
                String level = scanner.nextLine();

                if(level.equals("easy")){
                    numberWords = 4;

                } else if (level.equals("hard")) {
                    numberWords = 8;
                } else {
                    System.out.println("Wrong selection.. Try again!");
                    continue;
                }
                MemoryGame game = new MemoryGame(file.getRandomWords(numberWords), level);
                game.displayCurrentlyGame();

                while(shouldContinueGame) {

                    if(game.checkIfWinOrLose().equals("win")){
                        System.out.println("YOU WON! CONGRATULATIONS!");
                        break;
                    } else if (game.checkIfWinOrLose().equals("lose")){
                        System.out.println("You don't have more chances. You lose. :(");
                        break;
                    }

                    String selection = scanner.nextLine();
                    if(Arrays.toString(answersLvlEasy).contains(selection) == false && level.equals("easy")) {
                        System.out.println("You selection is wrong! Try again.");
                        continue;
                    } else if (Arrays.toString(answersLvlHard).contains(selection) == false && level.equals("hard")){
                        System.out.println("You selection is wrong! Try again.");
                        continue;
                    }

                    game.checkGameStatus(selection);
                }
                System.out.println("YOU WANT PLAY AGAIN? If yes write - 'Yes' ");
                String ifAgain = scanner.nextLine();
                if(ifAgain.equals("Yes")){
                    continue;
                } else {
                    break;
                }


            } while (shouldContinueGame);
    }
}
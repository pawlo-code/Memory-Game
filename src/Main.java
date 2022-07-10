import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) {

        final String[] answers = {"A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4"};

        ReadFile file = new ReadFile();

        MemoryGame game = new MemoryGame(file.getRandomWords(4));

        Scanner scanner = new Scanner(System.in);

        boolean shouldContinue = true;

        System.out.println("Press enter to START memory game!");
        String enter = scanner.nextLine();

        if (enter.equals("")) {

            do {
                game.displayCurrentlyGame();
                String answer = scanner.nextLine();

                if (answer.equals("quit")){
                    break;
                } else if (Arrays.toString(answers).contains(answer) == false){
                    System.out.println("You selected the wrong field! Try (A1-A4, B1-B4)");
                }

                game.checkGameStatus(answer);

            } while (shouldContinue);

        } else {
            System.out.println("Hey.. I said Enter!");
        }
    }
}
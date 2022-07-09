import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        ReadFile file = new ReadFile();

        MemoryGame game = new MemoryGame(file.getRandomWords(4));

        Scanner scanner = new Scanner(System.in);

        boolean shouldContinue = true;

//        System.out.println("Press enter to START memory game!");
//        String enter = scanner.nextLine();
//
//        System.out.println(file.getRandomWords(4));
//        if (enter.equals("")) {
//            do {
//
//            } while (shouldContinue);
//        } else {
//            System.out.println("You should just click enter...");
//        }
    }
}
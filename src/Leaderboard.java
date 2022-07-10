import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Leaderboard {
    public static void fileWriterMethod(String results){
        try {
            FileWriter writer = new FileWriter("Leaderboard.txt", true);
            writer.append("\n" + results);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void fileReaderMethod() {
        try {
            java.io.File file = new java.io.File("Leaderboard.txt");
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()){
                System.out.println(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

import java.io.*;
import java.util.*;

public class ReadFile {
    private ArrayList<String> wordsList = new ArrayList<String>();

    public ReadFile() {
        fileOpener();
    }

    private void fileOpener(){
        try {
            File file = new File("Words.txt");
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()){
                String line = scanner.nextLine();
                wordsList.add(line);
            }
        } catch (Exception e){
            System.out.println("File not found!");
        }
    }

    public ArrayList<String> getRandomWords(int count){
        ArrayList<String> randomWords = new ArrayList<String>();
        for (int i = 0; i < count; i++){
            //creating pseudo random number
            int randomNumber = (int) (Math.random()*100);
            randomWords.add(wordsList.get(randomNumber));
            wordsList.remove(randomNumber);
        }
        return randomWords;
    }

    public ArrayList<String> getFileList() {
        return wordsList;
    }

    public int getListSize() {
        return wordsList.size();
    }

}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//THIS CLASS IS NOT FINAL AND MAY HAVE QUALITY FEATURES SUCH AS CATEGORY SELECTION ADDED AT A LATER POINT

public class WordGeneration {  //This class has a method that can be called which generates a random word from the .txt file "word_library.txt"

    private static int random(int listSize){  //Method for generating random number between 0 and list length
        int randomNumber = (int) (Math.random() * (listSize+1));
        return randomNumber;
    }

    public static String generate() throws FileNotFoundException {
        File file = new File("src/word_library.txt");  //reading file with words in
        Scanner scan = new Scanner(file);
        List<String> words = new ArrayList<String>();

        for(int i = 0; scan.hasNextLine(); i++) {  //For loop to add all words in file to a list
            words.add(i, scan.nextLine());
        }

        String randomWord = words.get(WordGeneration.random(words.size())); //Using random method to get a random word from the list of words

        return randomWord;
    }

    public static char[] splitWord(String randomWord){
        char[] splitWord = randomWord.toCharArray();
        return splitWord;
    }
}
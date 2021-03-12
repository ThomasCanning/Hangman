import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class HangmanGame {
    public static void main(String[] args) throws FileNotFoundException {
        String randomWord = RandomWordGeneration.generate();
        System.out.println(randomWord);
    }
}

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.File;

public class Hangman {
    public static void main(String[] args) throws FileNotFoundException {

        // Scanner for file of words
        Scanner file = new Scanner(new File("C:\\Users\\em1li\\OneDrive\\Documents\\EnglishWordsForHangmanGame\\words_alpha.txt"));
       // Scanner for user input
        Scanner keyboard = new Scanner(System.in);

        // Create list to store words
        List<String> wordsList = new ArrayList<String>();

        // Fill words in the ArrayList
        while (file.hasNext()) {
            wordsList.add(file.nextLine());
        }

        Random rand = new Random();
        // Get a random word within the range of the size of words list, nested method calls
        String word = wordsList.get(rand.nextInt(wordsList.size()));
        System.out.println(word);

        // List for the guesses the player has made
        List<Character> playerGuesses = new ArrayList<>();
        printWordState(word, playerGuesses);

        while (true) {
            getPlayerGuesses(keyboard, playerGuesses);
            if (printWordState(word, playerGuesses)) {
                break;
            }
        }
        System.out.println("You win!");
    }

    private static void getPlayerGuesses(Scanner keyboard, List<Character> playerGuesses) {
        System.out.println("Please enter a letter");
        // read user input
        String letterGuess = keyboard.nextLine();
        // add character of user input and store in the list playerGuesses
        playerGuesses.add(letterGuess.charAt(0));
    }

    // If the player guess contains a character that is on the word, it gets replaced, else we print a dash
    private static boolean printWordState(String word, List<Character> playerGuesses) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
            System.out.print(word.charAt(i));
            correctCount++;
            }
            else {
                System.out.print("-");
            }
        }
        System.out.println();
        return (word.length() == correctCount);
    }
}
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.File;

public class Hangman {
    public static void main(String[] args) throws FileNotFoundException {

        // Scanner for user input
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Do you want 1 player or 2 players?");
        String player = keyboard.nextLine();

        String word;
        if (player.equals("1")) {
            // Scanner for file of words
            Scanner file = new Scanner(new File("C:\\Users\\em1li\\OneDrive\\Documents\\EnglishWordsForHangmanGame\\words_alpha.txt"));

            // Create list to store words
            List<String> wordsList = new ArrayList<String>();

            // Fill words in the ArrayList
            while (file.hasNext()) {
                wordsList.add(file.nextLine());
            }

            // new object of the random class
            Random rand = new Random();
            // Get a random word within the range of the size of words list, nested method calls
            word = wordsList.get(rand.nextInt(wordsList.size()));
        } else {
            System.out.println("Please enter a word");
            word = keyboard.nextLine();
            // for loop to print spaces so the user can't see the selected word.
            for (int i = 0; i < 20 ; i++) {
                System.out.println();
            }
            System.out.println("Ready for player two! Good luck.");
        }

        //Commented print out line to see what line we are trying to guess
        //System.out.println(word);

        // create list for the guesses the player has made
        List<Character> playerGuesses = new ArrayList<>();

        int wrongCount = 0;
        while (true) {
            printHangedMan(wrongCount);
            if (wrongCount >= 6) {
                System.out.println("You lose.");
                break;
            }
            printWordState(word, playerGuesses);
            if (!getPlayerGuess(keyboard, playerGuesses, word)) {
                wrongCount++;
            }
           // Win statement for the case the user fills correctly all the letters
            if (printWordState(word, playerGuesses)) {
                System.out.println("You win!");
                break;
            }
            // Win statement for the case where the user guesses the word
            System.out.println("Please enter your guess for the word:");
            if (keyboard.nextLine().equals(word)) {
                System.out.println("You win!");
                break;
            }
            else {
                System.out.println("No, try again");
            }
        }
        System.out.println();
    }

    // method that prints the hanged man, filling everytime the user guesses wrong the word or letter.
    private static void printHangedMan(int wrongCount) {
        System.out.println("-------");
        System.out.println(" |     |");
        if (wrongCount >= 1) {
            System.out.println(" O");
        }
        if (wrongCount >= 2) {
            System.out.print("\\ ");
        }
        if (wrongCount >= 3) {
            System.out.println("/");
        } else {
            System.out.println("");
        }
        if (wrongCount >= 4) {
            System.out.println(" |");
        }
        if (wrongCount >= 5) {
            System.out.print("/ ");
        }
        if (wrongCount >= 6) {
            System.out.println("\\");
        } else {
            System.out.println("");
        }
    }

    // Method to get the first character of the user's input, boolean to check if the guessed character the user makes is part of the word
    private static boolean getPlayerGuess(Scanner keyboard, List<Character> playerGuesses, String word) {
        System.out.println("Please enter a letter:");
        // read user input
        String letterGuess = keyboard.nextLine();
        // add character of user input and store in the list playerGuesses
        playerGuesses.add(letterGuess.charAt(0));

        return word.contains(letterGuess);
    }

    // Method to check if the player guess contains a character that is on the word, then it gets replaced by that character,
    // else we print a dash
    private static boolean printWordState(String word, List<Character> playerGuesses) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
            System.out.print(word.charAt(i));
            correctCount++;
            }
            else {
                System.out.print("_");
            }
        }
        System.out.println();
        return (word.length() == correctCount);
    }
}
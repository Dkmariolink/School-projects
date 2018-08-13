/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package woffortune;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.InputMismatchException;

/**
 * WofFortuneGame class Contains all logistics to run the game
 *
 * @author Justin Wright
 */
public class WofFortuneGame {

    private boolean puzzleSolved = false;

    private Wheel wheel;
    private Player players; // for a new player object
    private String phrase = "Once upon a time";
    private static ArrayList<Letter> Letters = new ArrayList<>(); // Changed from standard array to array list.
    private ArrayList<String> Phrases = new ArrayList<>(); // Arraylist that holds string objects named Phrases
    private ArrayList<Player> Players = new ArrayList<>(); // Arraylist that holds player objects named players

    /**
     * Constructor
     *
     * @param wheel Wheel
     * @throws InterruptedException
     */
    public WofFortuneGame(Wheel wheel) throws InterruptedException {
        // get the wheel
        this.wheel = wheel;

        // do all the initialization for the game
        setUpGame();

    }

    /**
     * Plays the game
     *
     * @throws InterruptedException
     */
    public void playGame() throws InterruptedException {
        // while the puzzle isn't solved, keep going
        while (!puzzleSolved) {
            // let the players take turns guessing
            int guess = 0;
            for (Player turn : Players) { // Enhanced (for each) look to cycle through players
                Players.get(guess);
                guess++;
                playTurn(turn);
                if (puzzleSolved) {
                    break; // ends once phrase is guessed  
                }
            }
        }
    }

    /**
     * Sets up all necessary information to run the game
     */
    private void setUpGame() {
        addPhrase(); // Calling method to add more phrases to the game3

        // ask how many people are going to play
        System.out.println("How many players will be participating today?");
        try {
            Scanner playerCount = new Scanner(System.in);
            int player = playerCount.nextInt();
            System.out.print(+player);
            System.out.println(" players will be playing, please enter their names"); // ask for players names
            for (int i = 0; i < player; i++) {
                Scanner playerName = new Scanner(System.in);
                String name = playerName.nextLine();

                players = new Player(name);
                Players.add(players);
            }

        } catch (InputMismatchException e) {
            System.out.println("Please enter correct input! " + e); // Prints error if incorrect input is entered
            System.out.println("The program will now close, as correct input must be entered");
            System.exit(0); // Closes program if it catches exception.
        }
        // add players to Players array list
        // print out the rules
        System.out.println("RULES!");
        System.out.println("Each player gets to spin the wheel, to get a number value");
        System.out.println("Each player then gets to guess a letter. If that letter is in the phrase, ");
        System.out.println(" the player will get the amount from the wheel for each occurence of the letter");
        System.out.println("If you have found a letter, you will also get a chance to guess at the phrase");
        System.out.println("Each player only has three guesses, once you have used up your three guesses, ");
        System.out.println("you can still guess letters, but no longer solve the puzzle.");
        System.out.println("Prizes will be shown at the end of the round! Have fun!");
        System.out.println();
        // User can enter own phrase
        System.out.println("Would you like to enter your own phrase? (Y/N)");
        try {
            Scanner customPhrase = new Scanner(System.in); // Scanner for if yes or no to create custom phrase
            char letter = customPhrase.next().charAt(0);
            if ((letter == 'Y') || (letter == 'y')) {
                System.out.println("Please enter your phrase!");
                Scanner typePhrase = new Scanner(System.in); // Scanner to type custom phrase
                String custom = typePhrase.nextLine();
                phrase = custom;
                System.out.println("Now let's play!");
            } else { // If anything other than Y, program will still work, just won't allow custom phrase.
                Random randomPhrase = new Random();
                phrase = Phrases.get(randomPhrase.nextInt(Phrases.size()));
            }
            // for each character in the phrase, create a letter and add to letters arraylist
            for (int i = 0; i < phrase.length(); i++) {
                //   letter_array[i] = new Letter(phrase.charAt(i)); stores in standard array
                Letters.add(new Letter(phrase.charAt(i))); // stores in array list
            }
        } catch (Exception a) {
            System.out.println("Incorrect input" + a);
            System.out.println("Program will now exit");
            System.exit(0);
        }
        // setup done
    }

    /**
     * Method to add a number of phrases to implement into the game. Using
     * ArrayList, one could easily add more phrases if the game becomes boring.
     */
    private void addPhrase() {
        String phrase1 = "Where the Sun Dont Shine";
        Phrases.add(phrase1);
        String phrase2 = "High Noon";
        Phrases.add(phrase2);
        String phrase3 = "Fly Me to the Moon";
        Phrases.add(phrase3);
        String phrase4 = "Im Batman";
        Phrases.add(phrase4);
        String phrase5 = "Happy Halloween";
        Phrases.add(phrase5);
        String phrase6 = "Rock and Roll";
        Phrases.add(phrase6);
        String phrase7 = "Stay in School";
        Phrases.add(phrase7);
        String phrase8 = "There is no place like home";
        Phrases.add(phrase8);
        String phrase9 = "Survival of the Fittest";
        Phrases.add(phrase9);
        String phrase10 = "No Mans Sky";
        Phrases.add(phrase10);
        String phrase11 = "Offense is the Best Defense";
        Phrases.add(phrase11);
        String phrase12 = "I Play to Win";
        Phrases.add(phrase12);
    }

    /**
     * One player's turn in the game Spin wheel, pick a letter, choose to solve
     * puzzle if letter found
     *
     * @param player
     * @throws InterruptedException
     */
    private void playTurn(Player player) throws InterruptedException {
        int money = 0;
        String Prize = wheel.getPrize();
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println(player.getName() + ", you have $" + player.getWinnings());
            System.out.println("Spin the wheel! <press enter>");
            sc.nextLine();
            System.out.println("<SPINNING>");
            Thread.sleep(200);
            Wheel.WedgeType type = wheel.spin();
            System.out.print("The wheel landed on: ");
            switch (type) {
                case MONEY:
                    money = wheel.getAmount();
                    System.out.println("$" + money);
                    break;

                case PRIZE:
                    Prize = wheel.getPrize();
                    System.out.println(" " + Prize);
                    break;

                case LOSE_TURN:
                    System.out.println("LOSE A TURN");
                    System.out.println("So sorry, you lose a turn.");
                    return; // doesn't get to guess letter

                case BANKRUPT:
                    System.out.println("BANKRUPT");
                    player.bankrupt();
                    return; // doesn't get to guess letter

                default:

            }
            System.out.println("");
            System.out.println("Here is the puzzle:");
            showPuzzle();
            System.out.println();
            System.out.println(player.getName() + ", please guess a letter.");
            //String guess = sc.next();

            char letter = sc.next().charAt(0);
            if (!Character.isAlphabetic(letter)) {
                System.out.println("Sorry, but only alphabetic characters are allowed. You lose your turn.");
            } else {
                // search for letter to see if it is in
                int numFound = 0;
                for (Letter l : Letters) {
                    if ((l.getLetter() == letter) || (l.getLetter() == Character.toUpperCase(letter))) {
                        l.setFound();
                        numFound += 1;
                    }
                }
                if (numFound == 0) {
                    System.out.println("Sorry, but there are no " + letter + "'s.");
                } else {
                    if (numFound == 1) {
                        System.out.println("Congrats! There is 1 letter " + letter + ":");
                    } else {
                        System.out.println("Congrats! There are " + numFound + " letter " + letter + "'s:");
                    }
                    System.out.println();
                    showPuzzle();
                    System.out.println();
                    player.incrementScore(numFound * money);
                    System.out.println("You earned $" + (numFound * money) + ", and you now have: $" + player.getWinnings());
                    if (Prize == Prize) {
                        player.incrementPrize(Prize);
                    }
                    System.out.println("Would you like to try to solve the puzzle? (Y/N)");
                    letter = sc.next().charAt(0);
                    System.out.println();
                    if ((letter == 'Y') || (letter == 'y')) {
                        solvePuzzleAttempt(player);
                    }
                }

            }

        } catch (InterruptedException e) {
            System.out.println("Error while playing game " + e); // prints interrupted exception error.
        }
    }

    /**
     * Logic for when user tries to solve the puzzle
     *
     * @param player
     */
    private void solvePuzzleAttempt(Player player) {

        if (player.getNumGuesses() >= 3) {
            System.out.println("Sorry, but you have used up all your guesses.");
            return;
        }

        player.incrementNumGuesses();
        System.out.println("What is your solution?");
        try {
            Scanner sc = new Scanner(System.in);
            sc.useDelimiter("\n");
            String guess = sc.next();
            if (guess.compareToIgnoreCase(phrase) == 0) {
                System.out.println("Congratulations! You guessed it!");
                puzzleSolved = true;
                // Round is over.
                System.out.println("The player who won is " + players.getName());
                System.out.println("Everyone's earnings are ");
                for (Player earnings : Players) {
                    System.out.println(earnings.getName());
                    System.out.println("$" + earnings.getWinnings());
                    System.out.println("Prizes: " + earnings.getPrize());
                }
            } else {
                System.out.println("Sorry, but that is not correct.");
            }
        } catch (Exception e) {
            System.out.println("Error occured " + e); // Catch any possible errors when guessing word
        }
    }

    /**
     * Display the puzzle on the console
     */
    private void showPuzzle() {
        System.out.print("\t\t");
        for (Letter l : Letters) {
            if (l.isSpace()) {
                System.out.print("   ");
            } else if (l.isFound()) {
                System.out.print(Character.toUpperCase(l.getLetter()) + " ");
            } else {
                System.out.print(" _ ");
            }
        }
        System.out.println();

    }

    /**
     * For a new game reset player's number of guesses to 0
     */
    public void reset() {
        players.reset();
    }

}

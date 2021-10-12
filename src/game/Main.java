package game;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        //introduction

        System.out.println("Welcome to the HiLo game! \n" +
                "Try to guess the number, from 1 to 100!\n" +
                "The Only hint you'll get is \"Higher\" or \"Lower\"\n");

        //set up

        int current = new Random().nextInt(99)+1;
        Scanner scanner = new Scanner(System.in);

        //ensure that the values entered by the user are either only numeric, or equal "Quit" whilst ignoring case

        Pattern quit = Pattern.compile("QUIT",Pattern.CASE_INSENSITIVE);
        Pattern containsText = Pattern.compile("([^0-9]+)");
        String result = "";


        //looping the game, user will have the amount of attempts set in "tries"


        for(int tries = 6; tries>0 ; tries--) {
            System.out.println("you have " + tries + " Tries left. Your guess: ");
            String currentLine = scanner.nextLine();
            Matcher matcher = quit.matcher(currentLine);

        //check to see if the user enters nothing

            if(currentLine.length() > 0) {

                //check to see if the user quits

                if (matcher.find()) {
                    System.out.println("Thank you for playing");
                    break;
                }

                //if the user does not quit, continue with the game

                matcher = containsText.matcher(currentLine);

                //check to see if the user enters anything other than a number

                if (matcher.find()) {
                    System.out.println("Please use a number only! no letters, no spaces...");
                    tries++;
                    continue;
                }

                //check the users input against the current value

                result = (evaluate(current, Integer.parseInt(currentLine)));
                System.out.println(result);

                //if successful, user wins and game ends

                if (result == "A WINNER IS YOU!") {
                    break;
                }
            } else {
                System.out.println("C'mon man, you have to enter something...");
                tries++;
            }
        }
        System.out.println("game over man... the answer was " + current);


    }

    public static String evaluate(int current, int guess) {

        if(current != guess) {
            return (current > guess ? "Higher" : "lower");
        }

        return "A WINNER IS YOU!";
    }
}

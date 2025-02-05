//Jag har valt att lägga mina try/catch i min Main-metod. Detta för att det blir tydligt att det är i inläsning från
//consolen som det kan gå fel.
//Det negativa: Jag kan inte skriva testfall på dem, eftersom att mina tester testar logiklassen.

import java.util.Scanner;

public class MorseCodeReaderMain {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        boolean isRunning = true;
        MorseCodeReader myText = new MorseCodeReader();

        System.out.println("Hello, and welcome to the Morse Code Reader!");
        while (isRunning) {
            System.out.println("Please choose what you want to do:");
            System.out.println("1. Translate English to Morse" +
                    "\n2. Translate Morse to English" +
                    "\n3. Print out the Morse alphabet" +
                    "\n4. Quit program");
            //Här vill jag fånga upp alla inmatningar som inte är siffror (eftersom siffrorna fångas upp i min default).
            //Därför kastar jag ett NumberFormatException. Även om jag inte kan se att något annat skulle kunna gå fel,
            //så kastar jag ett allmänt exception.
            try {
                int choice = Integer.parseInt(scan.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("Write the text you want to translate to morse. (Only English letters!)");
                        while (true) {
                            //Jag valde ett IllegalArgumentException, eftersom att mitt program skriver ut ett frågetecken
                            //för alla tecken som det inte kan översätta. Det enda "fel" min try/catch behöver fånga är
                            //om strängen är tom.
                            try {
                                System.out.println(myText.englishToMorse(scan.nextLine()));
                                break;
                            } catch (IllegalArgumentException e) {
                                System.out.println("Empty String, please try again!");
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Write the morse code message you want to translate to English!");
                        while (true) {
                            try {
                                System.out.println(myText.morseToEnglish(scan.nextLine()));
                                break;
                            } catch (IllegalArgumentException e) {
                                System.out.println("Empty String, please try again!");
                            }
                        }
                        break;
                    case 3:
                        System.out.println(myText.printMorseAlphabet());
                        break;
                    case 4:
                        System.out.println("Program closes down");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice, please enter a number between 1 and 4!");

                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number!");
            } catch (Exception e) {
                System.out.println("Something went wrong, please try again!");
            }
        }
    }
}

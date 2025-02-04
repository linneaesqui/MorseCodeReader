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
            try {
                int choice = Integer.parseInt(scan.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("Write the text you want to translate to morse. (Only English letters!)");
                        while (true) {
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
                        myText.getMorseAlphabet();
                        break;
                    case 4:
                        System.out.println("Program closes down");
                        isRunning = false;
                        break;

                }
            } catch (NumberFormatException e) {
                System.out.println("Ogiltigt val, välj en siffra mellan 1 och 4!");
            }
        }
    }
}

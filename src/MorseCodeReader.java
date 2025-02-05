//Saker som jag lagt till/tolkat i kraven:
//Jag vill översätta mellanslag korrekt åt båda hållen (ett mellanslag mellan bokstäver och tre mellan ord i morse-kod).
//Jag vill att specialtecken och tecken utanför det engelska alfabetet ska översättas med ett frågetecken,
//så att användaren lätt ska kunna se vilket tecken som inte gick att översätta.
//Ovannämnda lösning gör också att eventuella felinmatningar undviks, vilket ger programmet mer resiliens.
//Tomma rader ska ge ett felmeddelande om att raden är tom, användaren får fortsätta tills korrekt inmatning skett.
//Ogiltigt menyval ska ge ett felmeddelande om ogiltigt val, användaren får fortsätta tills korrekt inmatning skett.
//Jag vill ha en metod för att kunna skriva ut hela morse-alfabetet från menyn i min Main.
import java.util.HashMap;

public class MorseCodeReader {

    private static HashMap<String, Character> morseToLetter = new HashMap<>();
    private static HashMap<Character, String> letterToMorse = new HashMap<>();

//Jag läste mig till att man kan göra ett static{}-block, när man har en kod som ska köras en gång
//(när klassen skapas).
//Och att detta kan vara praktiskt när det är något som ska vara statiskt för ALLA objekt av en klass.
//Praktiskt pga: använder mindre prestanda än kod som ligger i konstruktorn, eftersom denna körs
//varje gång ett objekt skapas.
    static {
        //Jag vill splita mellan varje tecken, men vid användning av split(""), blir det en tom plats i början på min Array.
        //För att undvika detta fann jag ett annat sätt att skriva split.
        String[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("(?!^)");
        String[] morse = (".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-." +
                " ... - ..- ...- .-- -..- -.-- --..").split(" ");
        for(int i = 0; i < letters.length; i++) {
            char letter = letters[i].charAt(0);
            morseToLetter.put(morse[i], letter);
            letterToMorse.put(letter, morse[i]);
        }
    }


    public String morseToEnglish(String input){
        //Om input är tom, kastar vi ett IllegalArgumentException. Detta fångas upp i en try/catch i Main-metoden
        if (input.isEmpty()) {
            throw new IllegalArgumentException();
        }
        //Det behövs en räknare som håller reda på mellanslagen, så att vi kan få en korrekt utskrift av mellanslag i översättningen.
        int spaceCount = 0;
        //Efter att ha läst på om StringBuilder, så valde jag att jobba med det istället för String.
        //Detta pga att vi behöver skapa en ny String varje gång vi vill lägga till nånting, vilket slösar prestanda.
        StringBuilder result = new StringBuilder();
        String[] morseLetters = input.toUpperCase().split(" ");
        for (String letter : morseLetters) {
            //Utan mellanslag blir texten svårläst, och det är inte så krångligt att fixa.
            //När texten delas upp med en split kommer fler mellanslag än ett att skapa tomma platser i min array.
            //Två tomma platser betyder att vi har läst in tre mellanslag, vilket ger ett mellanslag i utskriften.
            //Färre än 2 hoppas över med continue.
            //Så fort vi hittar ett tecken nollställs spaceCount.
            if (letter.isEmpty()) {
                spaceCount++;
                if (spaceCount == 2) {
                    result.append(" ");
                } continue;
            } else {
                spaceCount = 0;
            }
            //Om vi inte hittar motsvarande tecken i vår HashMap, kommer värdet att bli ?.
            result.append(morseToLetter.getOrDefault(letter, '?'));
        }
        return result.toString().trim();
    }

    public String englishToMorse(String input){

        if (input.isEmpty()) {
            throw new IllegalArgumentException();
        }

        String [] words = input.toUpperCase().split(" ");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }
            char [] letters = word.toCharArray();
            for (char c : letters) {
                //Vi lägger alltid till ett mellanslag efter morse-bokstäverna...
                result.append(letterToMorse.getOrDefault(c, "?")).append(" ");
                //...därför behöver vi bara lägga till två mellanslag mellan varje ord...
            } result.append("  ");
            //...och vi behöver trimma result, för att få bort det sista mellanslaget.
        } return result.toString().trim();
    }

//    public String printMorseAlphabet() {
//        String alphabetToMorse = "";
//        for (Character key : letterToMorse.keySet()) {
//            alphabetToMorse = (key + " = " + letterToMorse.get(key));
//        } return alphabetToMorse;
//    }
    public String printMorseAlphabet() {
        StringBuilder result = new StringBuilder();
        for (Character key : letterToMorse.keySet()) {
            result.append(key).append(" = ").append(letterToMorse.get(key)).append("\n");
        }
        return result.toString().trim();
    }
}

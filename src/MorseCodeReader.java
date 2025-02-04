import java.util.HashMap;
// Skriv ett program som gör om morsekod till engelska
//och engelska till morsekod
//❖ Skriv programmet med minst en logikklass samt en
//klass som läser in text och skriver ut text (med en
//main-metod). Utveckla programmet i TDD och skriv
//minst 5 JUnit-testfall
//❖ Logik-klass
//❖ Main-klass
//❖ Test-klass
public class MorseCodeReader {

    private static HashMap<String, Character> morseToLetter = new HashMap<>();
    private static HashMap<Character, String> letterToMorse = new HashMap<>();

//Jag läste mig till att man kan göra ett static{}-block, när man har en kod som ska köras en gång
// (när klassen skapas).
//Och att detta kan vara praktiskt när det är något som ska vara statiskt för ALLA objekt av en klass.
//Praktiskt pga: använder mindre prestanda än kod som ligger i konstruktorn, eftersom denna körs
//varje gång ett objekt skapas.
    static {
        String[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("(?!^)");//fick ju problem med split "
        String[] morse = ".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --..".split(" ");
        for(int i = 0; i < letters.length; i++) {
            char letter = letters[i].charAt(0);
            morseToLetter.put(morse[i], letter);
            letterToMorse.put(letter, morse[i]);
        }
    }


    public String morseToEnglish(String input){
        //Jag behövde en räknare som håller redan på mellanslagen, så att jag kan få en korrekt utskrift av mellanslag i översättningen.
        int spaceCount = 0;
        //Efter att ha läst på om StringBuilder, så valde jag att jobba med det istället för String.
        //Detta pga att en String körs om varje gång man ska lägga till nånting, vilket slösar prestanda.
        StringBuilder result = new StringBuilder();
        String[] morseLetters = input.toUpperCase().trim().split(" ");
        for (String letter : morseLetters) {
            //Här tar jag höjd för att de skrivit ut morsekoden på traditionellt vis. I våra krav skulle vi hoppa över mellanslag,
            //men jag tyckte inte det blev ett bra program. Texten blir svårläst, och det är inte så krångligt att lösa.
            //När det delas upp med en split kommer fler mellanslag än ett att skapa tomma platser i min array.
            //Två tomma platser betyder att vi läste in tre mellanslag, vilket ger ett mellanslag i utskriften. Färre än 2 hoppas över med continue.
            //Så fort vi hittar ett tecken nollställs spaceCount.
            if (letter.isEmpty()) {
                spaceCount++;
                if (spaceCount == 2) {
                    result.append(" ");
                } continue;
            } else {
                spaceCount = 0;
            }
            result.append(morseToLetter.getOrDefault(letter, '?'));
        }
        return result.toString().trim();
    }

    public String englishToMorse(String input){
        //String inputNoSpecialChar = removeSpecialChar(input.toUpperCase());
        String [] words = input.toUpperCase().trim().split(" ");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }
            char [] letters = word.toCharArray();
            for (char c : letters) {
                result.append(letterToMorse.getOrDefault(c, "?") + " ");
            } result.append("  ");
        } return result.toString().trim();
    }

    //public String removeSpecialChar(String text) {
    //    return text.toUpperCase().replaceAll("[^A-Z0-9 ]", "");
    //}


    public void getMorseAlphabet() {
        for (Character key : letterToMorse.keySet()) {
            System.out.println(key + " = " + letterToMorse.get(key));
        }
    }
}

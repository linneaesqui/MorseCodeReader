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
//och att detta kan vara praktiskt när det är något som ska vara statiskt för ALLA objekt av en klass.
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
        int spaceCount = 0;
        String result = "";
        String[] morseLetters = input.toUpperCase().trim().split(" ");
        for (String letter : morseLetters) {
            //Här tar jag höjd för att de skrivit ut morsekoden på traditionellt vis. I våra krav skulle vi hoppa över mellanslag,
            //men risken finns att de skriver mellanslagen mellan orden som tre mellanslag. Alltså behöver vi ignorera både mellanslagen
            //mellan bokstäverna och de eventuella tomma platserna pga tre mellanslag på rad vid vår split.
            if (letter.isEmpty()) {
                spaceCount++;
                if (spaceCount == 2) {
                    result += " ";
                } continue;
            } else {
                spaceCount = 0;
            }
            result += morseToLetter.getOrDefault(letter, '?');
        }
        return result.trim();
    }

    public String englishToMorse(String input){
        String inputNoSpecialChar = removeSpecialChar(input.toUpperCase());
        String [] words = inputNoSpecialChar.trim().split(" ");
        String result = "";
        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }
            char [] letters = word.toCharArray();
            for (char c : letters) {
                result += letterToMorse.getOrDefault(c, "?") + " ";
            } result += "  ";
        } return result.trim();
    }

    public String removeSpecialChar(String text) {
        return text.toUpperCase().replaceAll("[^A-Z0-9 ]", "");
    }


    public void getMorseAlphabet() {
        for (Character key : letterToMorse.keySet()) {
            System.out.println(key + " = " + letterToMorse.get(key));
        }
    }
}

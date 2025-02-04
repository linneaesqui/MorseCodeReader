import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MorseCodeReaderTest {


    @Test
    void testMorseToEnglish() {
        MorseCodeReader newReader = new MorseCodeReader();
        newReader.morseToEnglish("...");
        assertEquals("S", newReader.morseToEnglish("..."));
    }

    @Test
    void testEnglishToMorse() {
        MorseCodeReader newReader = new MorseCodeReader();
        assertEquals("... --- ...", newReader.englishToMorse("SOS"));
    }

    @Test
    void testEnglishToMorseSpecialChar() {
        MorseCodeReader newReader = new MorseCodeReader();
        assertEquals("? ? ? ?", newReader.englishToMorse("!!!!"));
    }

    @Test
    void testSpaceMorseToEnglish() {
        MorseCodeReader newReader = new MorseCodeReader();
        assertEquals("HEJ HEJ", newReader.morseToEnglish(".... . .---   .... . .---"));
    }

    @Test
    void testSpaceEnglishToMorse() {
        MorseCodeReader newReader = new MorseCodeReader();
        assertEquals(".... . .---   .... . .---", newReader.englishToMorse("hej hej"));
    }
}

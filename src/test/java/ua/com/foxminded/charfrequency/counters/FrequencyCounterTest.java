package ua.com.foxminded.charfrequency.counters;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class FrequencyCounterTest {
    private static FrequencyCounter freqCounter;
    
    @BeforeAll
    public static void initialise() {
        freqCounter = new FrequencyCounter();
    }
    
 
    @Test
    void testCache() {
        String[] inputs = { "kukushka", "tort", "tort", "nosok", "nosok", "nout", "ryba", "nout" };
        String[] expectedOutputs = { "\"k\" - 3\n" + "\"u\" - 2\n" + "\"s\" - 1\n" + "\"h\" - 1\n" + "\"a\" - 1\n",
            "\"t\" - 2\n" + "\"o\" - 1\n" + "\"r\" - 1\n", 
            "\"t\" - 2\n" + "\"o\" - 1\n" + "\"r\" - 1\n",
            "\"n\" - 1\n" + "\"o\" - 2\n" + "\"s\" - 1\n" + "\"k\" - 1\n",
            "\"n\" - 1\n" + "\"o\" - 2\n" + "\"s\" - 1\n" + "\"k\" - 1\n",
            "\"n\" - 1\n" + "\"o\" - 1\n" + "\"u\" - 1\n" + "\"t\" - 1\n",
            "\"r\" - 1\n" + "\"y\" - 1\n" + "\"b\" - 1\n" + "\"a\" - 1\n",
            "\"n\" - 1\n" + "\"o\" - 1\n" + "\"u\" - 1\n" + "\"t\" - 1\n" };
        
        FrequencyCounter testFreqCounter = new FrequencyCounter();
        Map<String, String> expectedCache = new LinkedHashMap<>();

        assertEquals(testFreqCounter.cache.size(), 0);

        for (int i = 0; i < inputs.length; i++) {
            testFreqCounter.getFrequencies(inputs[i]);
            Map<String, String> actualCache = testFreqCounter.cache;
            expectedCache.put(inputs[i], expectedOutputs[i]);

            assertTrue(actualCache.keySet().containsAll(expectedCache.keySet()));
            expectedCache.keySet().stream()
                    .forEach((key) -> assertEquals(expectedCache.get(key), actualCache.get(key)));
        }
    }

    @Test
    void test_getFrequencies_inputEmptyString() {
        String input = "";
        String actualFreqs = freqCounter.getFrequencies(input);
        String expectedFreqs = "";
        assertEquals(expectedFreqs, actualFreqs);
    }

    @Test
    void test_getFrequencies_inputJustSpaces() {
        String input = "         ";
        String actualFreqs = freqCounter.getFrequencies(input);
        String expectedFreqs = "\" \" - 9\n";
        assertEquals(expectedFreqs, actualFreqs);
    }

    @Test
    void test_getFrequencies_inputJustNumbers() {
        String input = "345662111";
        String actualFreqs = freqCounter.getFrequencies(input);
        String expectedFreqs = 
                "\"3\" - 1\n" + 
                "\"4\" - 1\n" + 
                "\"5\" - 1\n" + 
                "\"6\" - 2\n" + 
                "\"2\" - 1\n" + 
                "\"1\" - 3\n";
        assertEquals(expectedFreqs, actualFreqs);
    }
    
    @Test
    void test_getFrequencies_inputSameNumber() {
        String input = "1111111";
        String actualFreqs = freqCounter.getFrequencies(input);
        String expectedFreqs = "\"1\" - 7\n";
        assertEquals(expectedFreqs, actualFreqs);
    }
    
    @Test
    void test_getFrequencies_inputJustLetters() {
        String input = "karandash";
        String actualFreqs = freqCounter.getFrequencies(input);
        String expectedFreqs = 
                "\"k\" - 1\n" + 
                "\"a\" - 3\n" + 
                "\"r\" - 1\n" + 
                "\"n\" - 1\n" + 
                "\"d\" - 1\n" + 
                "\"s\" - 1\n" + 
                "\"h\" - 1\n";
        assertEquals(expectedFreqs, actualFreqs);
    }
    
    @Test
    void test_getFrequencies_inputWithSpaces() {
        String input = "i td i tp";
        String actualFreqs = freqCounter.getFrequencies(input);
        String expectedFreqs = 
                "\"i\" - 2\n" + 
                "\" \" - 3\n" + 
                "\"t\" - 2\n" + 
                "\"d\" - 1\n" + 
                "\"p\" - 1\n";
        assertEquals(expectedFreqs, actualFreqs);
    }
    
    @Test
    void test_getFrequencies_inputWithNonLetters() {
        String input = "#%&*&&^-k *087-ku!";
        String actualFreqs = freqCounter.getFrequencies(input);
        String expectedFreqs = 
                "\"#\" - 1\n" + 
                "\"%\" - 1\n" + 
                "\"&\" - 3\n" + 
                "\"*\" - 2\n" + 
                "\"^\" - 1\n" + 
                "\"-\" - 2\n" + 
                "\"k\" - 2\n" + 
                "\" \" - 1\n" + 
                "\"0\" - 1\n" + 
                "\"8\" - 1\n" + 
                "\"7\" - 1\n" + 
                "\"u\" - 1\n" + 
                "\"!\" - 1\n";
        assertEquals(expectedFreqs, actualFreqs);
    }
}

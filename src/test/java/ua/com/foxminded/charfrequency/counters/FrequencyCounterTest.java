package ua.com.foxminded.charfrequency.counters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class FrequencyCounterTest {

    @Test
    void test_getFrequencies_inputJustLetters() {
        String input = "karandash";
        
        FrequencyCounter freqCounter = new FrequencyCounter();
        Map<Character, Integer> actualMap = freqCounter.getFrequencies(input);
        
        Map<Character, Integer> expectedMap =  new HashMap<>();
        expectedMap.put('k', 1);
        expectedMap.put('a', 3);
        expectedMap.put('r', 1);
        expectedMap.put('n', 1);
        expectedMap.put('d', 1); 
        expectedMap.put('s', 1);
        expectedMap.put('h', 1);
        
        assertTrue(actualMap.keySet().containsAll(expectedMap.keySet()));
        expectedMap.keySet().stream().forEach((key) -> assertEquals(expectedMap.get(key), actualMap.get(key)));
    }
    
    @Test
    void test_getFrequencies_inputWithSpaces() {
        String input = "i td i tp";
        
        FrequencyCounter freqCounter = new FrequencyCounter();
        Map<Character, Integer> actualMap = freqCounter.getFrequencies(input);
        
        Map<Character, Integer> expectedMap =  new HashMap<>();
        expectedMap.put('i', 2);
        expectedMap.put(' ', 3);
        expectedMap.put('t', 2);
        expectedMap.put('d', 1);
        expectedMap.put('p', 1); 
        
        assertTrue(actualMap.keySet().containsAll(expectedMap.keySet()));
        expectedMap.keySet().stream().forEach((key) -> assertEquals(expectedMap.get(key), actualMap.get(key)));
    }
    
    @Test
    void test_getFrequencies_inputWithNonLetters() {
        String input = "#%&*&&^-k *087-ku!";
        
        FrequencyCounter freqCounter = new FrequencyCounter();
        Map<Character, Integer> actualMap = freqCounter.getFrequencies(input);
        
        Map<Character, Integer> expectedMap =  new HashMap<>();
        expectedMap.put('#', 1);
        expectedMap.put('%', 1);
        expectedMap.put('&', 3);
        expectedMap.put('*', 2);
        expectedMap.put('^', 1);
        expectedMap.put('-', 2);
        expectedMap.put('k', 2); 
        expectedMap.put(' ', 1);
        expectedMap.put('0', 1); 
        expectedMap.put('8', 1);
        expectedMap.put('7', 1);
        expectedMap.put('u', 1);
        expectedMap.put('!', 1); 
        
        assertTrue(actualMap.keySet().containsAll(expectedMap.keySet()));
        expectedMap.keySet().stream().forEach((key) -> assertEquals(expectedMap.get(key), actualMap.get(key)));
    }
}

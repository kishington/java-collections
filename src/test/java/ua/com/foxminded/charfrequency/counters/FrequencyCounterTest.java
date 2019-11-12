package ua.com.foxminded.charfrequency.counters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class FrequencyCounterTest {
    
    @Test
    void testCache() {
        String[] inputs = {"kukushka", "tort", "tort", "nosok", "nosok", "nout", "ryba", "nout"};
        
        FrequencyCounter freqCounter = new FrequencyCounter();
        FrequencyCounter testFreqCounter = new FrequencyCounter();
        
        Map<String, Map<Character, Integer>> expectedCache = new HashMap<>();
        //Map<String, Map<Character, Integer>> actualCache = testFreqCounter.cache;
        
        assertEquals(testFreqCounter.cache.size(), 0);
        
        for(int i = 0; i < inputs.length; i++) {
            testFreqCounter.getFrequencies(inputs[i]);
            Map<String, Map<Character, Integer>> actualCache = testFreqCounter.cache;
            expectedCache.put(inputs[i], freqCounter.getFrequencies(inputs[i]));
            
            assertTrue(actualCache.keySet().containsAll(expectedCache.keySet()));
            expectedCache.keySet().stream().forEach((key) -> assertEquals(expectedCache.get(key), actualCache.get(key)));
        }
    }

    @Test
    void test_getFrequencies_inputEmptyString() {
        String input = "";
        
        FrequencyCounter freqCounter = new FrequencyCounter();
        Map<Character, Integer> actualMap = freqCounter.getFrequencies(input);
        Map<Character, Integer> expectedMap =  new HashMap<>();
        
        assertTrue(actualMap.keySet().containsAll(expectedMap.keySet()));
        expectedMap.keySet().stream().forEach((key) -> assertEquals(expectedMap.get(key), actualMap.get(key)));
    }
    
    @Test
    void test_getFrequencies_inputJustSpaces() {
        String input = "         ";
        
        FrequencyCounter freqCounter = new FrequencyCounter();
        Map<Character, Integer> actualMap = freqCounter.getFrequencies(input);
        
        Map<Character, Integer> expectedMap =  new HashMap<>();
        expectedMap.put(' ', 9);
        
        assertTrue(actualMap.keySet().containsAll(expectedMap.keySet()));
        expectedMap.keySet().stream().forEach((key) -> assertEquals(expectedMap.get(key), actualMap.get(key)));
    }
    
    @Test
    void test_getFrequencies_inputJustNumbers() {
        String input = "345662111";
        
        FrequencyCounter freqCounter = new FrequencyCounter();
        Map<Character, Integer> actualMap = freqCounter.getFrequencies(input);
        
        Map<Character, Integer> expectedMap =  new HashMap<>();
        expectedMap.put('3', 1);
        expectedMap.put('4', 1);
        expectedMap.put('5', 1);
        expectedMap.put('6', 2);
        expectedMap.put('2', 1); 
        expectedMap.put('1', 3);
        
        assertTrue(actualMap.keySet().containsAll(expectedMap.keySet()));
        expectedMap.keySet().stream().forEach((key) -> assertEquals(expectedMap.get(key), actualMap.get(key)));
    }
    
    @Test
    void test_getFrequencies_inputSameNumber() {
        String input = "1111111";
        
        FrequencyCounter freqCounter = new FrequencyCounter();
        Map<Character, Integer> actualMap = freqCounter.getFrequencies(input);
        
        Map<Character, Integer> expectedMap =  new HashMap<>(); 
        expectedMap.put('1', 7);
        
        assertTrue(actualMap.keySet().containsAll(expectedMap.keySet()));
        expectedMap.keySet().stream().forEach((key) -> assertEquals(expectedMap.get(key), actualMap.get(key)));
    }
    
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

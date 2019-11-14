package ua.com.foxminded.charfrequency.counters;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.Instant;

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
        FrequencyCounter testFreqCounter = new FrequencyCounter();
        String veryLongString = "a;jg;dfurqrg;askjd;sgq;weruqwe;wdfjs sdf adfjas;dfj kja;jfj a;;ajfuigeheprg7"
                + "l;llj;lLJ:JKJdfsfasdfgasdfasdfasdfehrujreir634:JK:JJ;j;jfjdf;asfwuefoiu092039u02eji;jfsadf"
                + "a;jfka;dfkjas;dfjas;dfdjs;jdf;asjdfk;a;fasfjk;dsfgsdfgdsfgsdfgsdfgsdfgsdfsdgsfgsfhtuqwueps"
                + ";asfhas;dfu;adf;asdf;as;dfjwepqwurfqwifj;askjfupgupwgwquradfasdfwerqwghfdsfhsdrewqedfsawur"
                + ";afsadfsdhdshhhhhhhhhhufeuwroqruqw;rq;refjsa;dasfewqrqewrrrrrrrrrrwqqqqqqqqdddddddddddfja;"
                + "8432p3 2223r wera9fasd;fujsvj;j;U)*UJOidj2#@#######@sd--98908945343243324343dfadaaaf*()#@E"
                + "a;jg;dfurqrg;askjd;sgq;weruqwe;wdfjs sdf adfjas;dfj kja;jfj a;;ajfuigehepruyllllllllllllg7"
                + "l;llj;lLJ:JKJdfsfasdfgasdfasdfasdfehrujreir634:JK:JJ;j;jfjdf;asfwuefoiu092039u02eji;jfsadf"
                + "a;jfka;dfkjas;dfjas;dfdjs;jdf;asjdfk;a;fasfjk;dsfgsdfgdsfgsdfgsdfgsdfgsdfsdgsfgsfhtuqwueps"
                + ";asfhas;dfu;adf;asdf;as;dfjwepqwurfqwifj;askjfupgupwgwquradfasdfwerqwghfdsfhsdrewqedfsawur"
                + ";afsadfsdhdshhhhhhhhhhufeuwroqruqw;rq;refjsa;dasfewqrqewrrrrrrrrrrwqqqqqqqqdddddddddddfja;"
                + "8432p3 2223r wera9fasd;fujsvj;j;U)*UJOidj2#@#######@sd--98908945343243324343dfadaaaf*()#@E";

        Instant momentBeforeExecution = Instant.now();
        testFreqCounter.getFrequencies(veryLongString);
        Instant momentAfterExecution = Instant.now();
        long calculationTime = Duration.between(momentBeforeExecution, momentAfterExecution).toNanos();

        momentBeforeExecution = Instant.now();
        testFreqCounter.getFrequencies(veryLongString);
        momentAfterExecution = Instant.now();
        long readingFromCacheTime = Duration.between(momentBeforeExecution, momentAfterExecution).toNanos();

        assertTrue(calculationTime >= readingFromCacheTime);
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

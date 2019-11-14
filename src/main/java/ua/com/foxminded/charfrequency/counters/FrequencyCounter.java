package ua.com.foxminded.charfrequency.counters;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.joining;

public class FrequencyCounter {

    Map<String, String> cache = new LinkedHashMap<>();

    public String getFrequencies(String input) {
        String result = cache.computeIfAbsent(input, k -> {
            Map<Character, Integer> frequencies = new LinkedHashMap<>();
            input.chars()
                 .mapToObj(c -> (char) c)
                 .collect(toList())
                 .forEach(c -> frequencies.merge(c, 1, Integer::sum));
            
            String freqsToCache = 
                    frequencies.keySet()
                               .stream()
                               .map(key -> "\"" + key + "\" - " + frequencies.get(key) + "\n")
                               .collect(joining());
            return freqsToCache;
        });
        return result;
    }
}

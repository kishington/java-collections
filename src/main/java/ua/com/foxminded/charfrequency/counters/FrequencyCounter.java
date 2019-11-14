package ua.com.foxminded.charfrequency.counters;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FrequencyCounter {

    Map<String, String> cache = new LinkedHashMap<>();

    public String getFrequencies(String input) {
        String result = cache.computeIfAbsent(input, k -> {
            Map<Character, Integer> frequencies = new LinkedHashMap<>();
            input.chars().mapToObj(c -> (char) c).collect(Collectors.toList()).forEach(c -> frequencies.merge(c, 1, Integer::sum));
            String freqsToCache = frequencies.keySet().stream().map(key -> "\"" + key + "\" - " + frequencies.get(key) + "\n")
                    .collect(Collectors.joining());
            return freqsToCache;
        });
        return result;
    }
}

package ua.com.foxminded.charfrequency.counters;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FrequencyCounter {

    Map<String, String> cache = new LinkedHashMap<>();

    public String getFrequencies(String input) {
        cache.computeIfAbsent(input, k -> {
            List<Character> chars = input.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

            Map<Character, Integer> frequencies = new LinkedHashMap<>();
            chars.forEach(c -> frequencies.merge(c, 1, Integer::sum));

            return frequencies.keySet().stream().map(key -> "\"" + key + "\" - " + frequencies.get(key) + "\n")
                    .collect(Collectors.joining());
        });

        return cache.get(input);
    }
}

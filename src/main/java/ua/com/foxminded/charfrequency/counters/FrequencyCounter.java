package ua.com.foxminded.charfrequency.counters;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FrequencyCounter {

    Map<String, Map<Character, Integer>> cache = new HashMap<>();

    public void showFrequencies(String input) {
        Map<Character, Integer> frequencies = getFrequencies(input);
        frequencies.entrySet().forEach(entry -> System.out.println("\"" + entry.getKey() + "\" - " + entry.getValue()));
    }

    Map<Character, Integer> getFrequencies(String input) {

        Map<Character, Integer> frequencies;
        if (cache.containsKey(input)) {
            frequencies = cache.get(input);
        } else {
            frequencies = input.chars().boxed()
                    .collect(Collectors.toMap(k -> Character.valueOf((char) k.intValue()), v -> 1, Integer::sum));
            //input.chars().boxed().forEach(ch -> frequencies.merge((Character) ch, 1, (prev, one) -> prev  + one));
            cache.put(input, frequencies);
        }
        return frequencies;
    }
}

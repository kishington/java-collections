package ua.com.foxminded.charfrequency;

import java.util.Scanner;
import ua.com.foxminded.charfrequency.counters.*;

public class FrequencyDisplayer {

    static final int NUMBER_OF_INPUT_STRINGS = 5;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        FrequencyCounter freqCounter = new FrequencyCounter();

        for (int i = 0; i < NUMBER_OF_INPUT_STRINGS; i++) {
            System.out.println("Enter string #" + (i + 1) + ":");
            String input = scanner.nextLine();
            freqCounter.showFrequencies(input);
        }
        scanner.close();
    }
}

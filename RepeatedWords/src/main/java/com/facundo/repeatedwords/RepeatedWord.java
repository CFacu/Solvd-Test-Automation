package com.facundo.repeatedwords;

import org.apache.commons.lang3.StringUtils;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.apache.commons.io.FileUtils.writeStringToFile;
import static org.apache.commons.lang3.StringUtils.split;
import static org.apache.commons.lang3.time.DateUtils.parseDate;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class RepeatedWord {

    public static void counter() throws IOException {
        File file = new File("src//main//resources//textIn.txt");
        String text = readFileToString(file, "utf-8").toLowerCase();
        String[] delimiters = {",", ".", ";", ":", "\t", "\n", "\r"};
        Map<String, Integer> wordsRepeated = new HashMap<>();

        for (String delimiter : delimiters) {
            while (text.indexOf(delimiter) != -1) {
                text = StringUtils.replace(text, delimiter, " ");
            }
        }

        String[] words = split(text, " ");

        for (String word : words) {

            try {
                word = parseDate(word, "MM-dd-YYYY", "MM/dd/YYYY").toString();
            } catch (ParseException e){}

            if (wordsRepeated.containsKey(word)) {
                wordsRepeated.replace(word, wordsRepeated.get(word), wordsRepeated.get(word) + 1);
            } else {
                wordsRepeated.put(word, 1);
            }
        }

        writeStringToFile(new File("src//main//resources//textOut.txt"), StringUtils.join(wordsRepeated), "utf-8");
    }
}

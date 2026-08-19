import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordIndexSystem {
    public static void main(String[] args) {
        String[] sentences = {
            "Java is a programming language.",
            "Java is widely used, and Java is fun!",
            "programming in Java is great."
        };

        Map<String, Integer> wordCount = new HashMap<>();
        Set<String> uniqueWords = new HashSet<>();

        for (String sentence : sentences) {
            if (sentence != null) {
                String cleanSentence = sentence.replaceAll("[,\\.!]", "").toLowerCase();
                String[] words = cleanSentence.split("\\s+");
                
                for (String word : words) {
                    if (!word.trim().isEmpty()) {
                        uniqueWords.add(word);
                        wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                    }
                }
            }
        }

        System.out.println("--- 出現至少兩次的單字 ---");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            if (entry.getValue() >= 2) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " 次");
            }
        }
    }
}
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordIndexSystem {
    public static void main(String[] args) {

        String[] sentences = {
            "Java is easy, Java is useful.",
            "Java collections are useful.",
            "Collections make Java easier."
        };

        Map<String, Integer> wordCounts = new HashMap<>();
        Set<String> words = new HashSet<>();

        for (String sentence : sentences) {

            sentence = sentence.toLowerCase();
            sentence = sentence.replace(".", "");
            sentence = sentence.replace(",", "");

            String[] list = sentence.split(" ");

            for (String word : list) {
                words.add(word);

                wordCounts.put(
                    word,
                    wordCounts.getOrDefault(word, 0) + 1
                );
            }
        }

        System.out.println("不重複單字：");
        System.out.println(words);

        System.out.println();

        System.out.println("單字次數：");
        for (String word : wordCounts.keySet()) {
            System.out.println(word + "：" + wordCounts.get(word));
        }

        System.out.println();

        System.out.println("出現至少兩次的單字：");

        for (String word : wordCounts.keySet()) {
            if (wordCounts.get(word) >= 2) {
                System.out.println(word);
            }
        }
    }
}
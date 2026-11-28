// TextProcessor.java — پاسخ پروژهٔ جلسه ۴۶
import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
interface TextProcessor {
    String process(String input);
}

public class TextProcessorDemo {

    public static void main(String[] args) {
        String input = "Hello World";

        // lambda برای uppercase
        TextProcessor uppercase = s -> s.toUpperCase();
        System.out.println("Uppercase: " + uppercase.process(input));

        // lambda برای reverse
        TextProcessor reverse = s -> new StringBuilder(s).reverse().toString();
        System.out.println("Reversed: " + reverse.process(input));

        // lambda برای word count
        TextProcessor wordCount = s -> String.valueOf(s.split("\\s+").length);
        System.out.println("Word count: " + wordCount.process(input));

        // lambda برای remove vowels
        TextProcessor noVowels = s -> {
            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                if (!"aeiouAEIOU".contains(String.valueOf(c))) {
                    sb.append(c);
                }
            }
            return sb.toString();
        };
        System.out.println("No vowels: " + noVowels.process(input));

        // ترکیب چند processor
        List<TextProcessor> processors = new ArrayList<>();
        processors.add(uppercase);
        processors.add(reverse);
        processors.add(noVowels);

        String result = input;
        for (TextProcessor p : processors) {
            result = p.process(result);
        }
        System.out.println("All processors: " + result);
    }
}

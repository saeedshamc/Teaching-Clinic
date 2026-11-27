// WordCounter.java — پاسخ پروژهٔ جلسه ۰۹
public class WordCounter {
    public static void main(String[] args) {
        String text = "Ali Rezaei is a student";
        String[] words = text.split(" ");

        System.out.println("Words: " + words.length);
        for (int i = 0; i < words.length; i++) {
            System.out.println("Word " + (i + 1) + ": " + words[i] + " (" + words[i].length() + ")");
        }

        // کلمات را برعکس با StringBuilder
        StringBuilder rev = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            rev.append(words[i]).append(" ");
        }
        System.out.println("Reversed: " + rev.toString().trim());
    }
}

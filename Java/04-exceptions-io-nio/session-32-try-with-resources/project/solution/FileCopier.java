// FileCopier.java — پاسخ پروژهٔ جلسه ۳۲
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCopier {

    public static void main(String[] args) {
        String source = "input.txt";
        String dest = "output.txt";

        int lineCount = 0, wordCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(source));
             BufferedWriter bw = new BufferedWriter(new FileWriter(dest))) {

            String line;
            int lineNum = 1;
            while ((line = br.readLine()) != null) {
                // شماره‌گذاری خط
                bw.write(lineNum++ + ": " + line + "\n");
                lineCount++;
                // شمارش کلمات
                wordCount += line.split("\\s+").length;
            }
            System.out.println("Copied " + lineCount + " lines, " + wordCount + " words");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

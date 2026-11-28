// FileManagerFull.java — پاسخ کامل پروژهٔ جلسه ۳۷
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class TextFile {
    Path path;
    String name;
    long size;
    TextFile(Path path) throws IOException {
        this.path = path; this.name = path.getFileName().toString(); this.size = Files.size(path);
    }
}

public class FileManagerFull {
    static Path dir = Paths.get(".");
    static List<TextFile> files = new ArrayList<>();

    static void scan() throws IOException {
        files.clear();
        try (Stream<Path> s = Files.walk(dir)) {
            s.filter(Files::isRegularFile).filter(p -> p.toString().endsWith(".txt"))
             .forEach(p -> { try { files.add(new TextFile(p)); } catch (IOException e) {} });
        }
    }

    static void list() {
        System.out.println("\n=== Files ===");
        for (TextFile f : files) System.out.printf("%-20s %8d bytes%n", f.name, f.size);
    }

    static void read(String name) {
        try (BufferedReader br = Files.newBufferedReader(dir.resolve(name), StandardCharsets.UTF_8)) {
            String line; int n = 1;
            while ((line = br.readLine()) != null) System.out.println(n++ + ": " + line);
        } catch (IOException e) { System.out.println("Error: " + e.getMessage()); }
    }

    static void append(String name, String content) {
        try (BufferedWriter bw = Files.newBufferedWriter(dir.resolve(name), StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            bw.write(content); bw.newLine();
            System.out.println("Appended");
        } catch (IOException e) { System.out.println("Error: " + e.getMessage()); }
    }

    static void search(String query) {
        System.out.println("\n=== Search: " + query + " ===");
        for (TextFile f : files) {
            try (BufferedReader br = Files.newBufferedReader(f.path, StandardCharsets.UTF_8)) {
                String line; int n = 1;
                while ((line = br.readLine()) != null) {
                    if (line.toLowerCase().contains(query.toLowerCase()))
                        System.out.println(f.name + ":" + n + ": " + line);
                    n++;
                }
            } catch (IOException e) {}
        }
    }

    static void stats(String name) {
        int lines = 0, words = 0, chars = 0;
        try (BufferedReader br = Files.newBufferedReader(dir.resolve(name), StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                lines++; words += line.split("\\s+").length; chars += line.length();
            }
        } catch (IOException e) { System.out.println("Error: " + e.getMessage()); return; }
        System.out.printf("%s: %d lines, %d words, %d chars%n", name, lines, words, chars);
    }

    public static void main(String[] args) throws IOException {
        // ساخت فایل‌های تست
        Files.write(dir.resolve("test1.txt"), List.of("Hello world", "Java is fun"));
        Files.write(dir.resolve("test2.txt"), List.of("Python is great", "Hello again"));
        Files.write(dir.resolve("test3.txt"), List.of("Search me", "Find this line"));

        scan();
        list();
        search("hello");
        stats("test1.txt");
        append("test1.txt", "New line added");
        read("test1.txt");

        // cleanup
        Files.deleteIfExists(dir.resolve("test1.txt"));
        Files.deleteIfExists(dir.resolve("test2.txt"));
        Files.deleteIfExists(dir.resolve("test3.txt"));
    }
}

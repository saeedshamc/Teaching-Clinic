// TextFileManager.java — مینی‌پروژهٔ جمع‌بندی فاز ۳ (بخش IO)
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
    long lastModified;

    TextFile(Path path) throws IOException {
        this.path = path;
        this.name = path.getFileName().toString();
        this.size = Files.size(path);
        this.lastModified = Files.getLastModifiedTime(path).toMillis();
    }

    void display() {
        System.out.printf("%-20s %8d bytes%n", name, size);
    }
}

class FileManager {
    Path directory;
    List<TextFile> files;

    FileManager(String dir) throws IOException {
        this.directory = Paths.get(dir);
        this.files = new ArrayList<>();
        scanDirectory();
    }

    void scanDirectory() throws IOException {
        files.clear();
        try (Stream<Path> stream = Files.walk(directory)) {
            stream.filter(Files::isRegularFile)
                  .filter(p -> p.toString().endsWith(".txt"))
                  .forEach(p -> {
                      try { files.add(new TextFile(p)); }
                      catch (IOException e) { System.out.println("Error: " + p); }
                  });
        }
    }

    void displayFiles() {
        System.out.println("\n=== Text Files ===");
        for (TextFile f : files) f.display();
    }

    void readFile(String filename) {
        Path path = directory.resolve(filename);
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            int lineNum = 1;
            while ((line = br.readLine()) != null) {
                System.out.println(lineNum++ + ": " + line);
            }
        } catch (IOException e) {
            System.out.println("Error reading " + filename + ": " + e.getMessage());
        }
    }

    void appendToFile(String filename, String content) {
        Path path = directory.resolve(filename);
        try (BufferedWriter bw = Files.newBufferedWriter(path,
                StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            bw.write(content);
            bw.newLine();
            System.out.println("Appended to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing " + filename + ": " + e.getMessage());
        }
    }

    void searchInFiles(String query) {
        System.out.println("\n=== Search: " + query + " ===");
        for (TextFile f : files) {
            try (BufferedReader br = Files.newBufferedReader(f.path, StandardCharsets.UTF_8)) {
                String line;
                int lineNum = 1;
                while ((line = br.readLine()) != null) {
                    if (line.toLowerCase().contains(query.toLowerCase())) {
                        System.out.println(f.name + ":" + lineNum + ": " + line);
                    }
                    lineNum++;
                }
            } catch (IOException e) {
                System.out.println("Error searching " + f.name);
            }
        }
    }

    void fileStats(String filename) {
        Path path = directory.resolve(filename);
        int lines = 0, words = 0, chars = 0;
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                lines++;
                words += line.split("\\s+").length;
                chars += line.length();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
        System.out.printf("%s: %d lines, %d words, %d chars%n", filename, lines, words, chars);
    }
}

public class TextFileManager {
    public static void main(String[] args) {
        try {
            FileManager mgr = new FileManager(".");
            mgr.displayFiles();
            mgr.searchInFiles("java");
            mgr.fileStats("README.md");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

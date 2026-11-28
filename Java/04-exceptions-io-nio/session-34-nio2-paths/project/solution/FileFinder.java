// FileFinder.java — پاسخ پروژهٔ جلسه ۳۴
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class FileFinder {

    public static void main(String[] args) throws IOException {
        Path dir = Paths.get(".");
        List<Path> javaFiles = new ArrayList<>();
        Path largest = null;
        long largestSize = 0;

        try (Stream<Path> stream = Files.walk(dir)) {
            stream.filter(Files::isRegularFile)
                  .filter(p -> p.toString().endsWith(".java"))
                  .forEach(javaFiles::add);
        }

        System.out.println("Found " + javaFiles.size() + " .java files:");
        for (Path p : javaFiles) {
            long size = Files.size(p);
            System.out.printf("- %s (%.1f KB)%n", p.getFileName(), size / 1024.0);
            if (size > largestSize) {
                largestSize = size;
                largest = p;
            }
        }

        if (largest != null) {
            System.out.println("\nLargest: " + largest.getFileName() + " (" + largestSize + " bytes)");
        }
    }
}

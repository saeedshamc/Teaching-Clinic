// NioPathsDemo.java — Path operations، Files.read/write، metadata
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class NioPathsDemo {

    public static void main(String[] args) throws IOException {
        // ---------- Path basics ----------
        Path path = Paths.get("src", "main", "file.txt");
        System.out.println("path=" + path);
        System.out.println("fileName=" + path.getFileName());
        System.out.println("parent=" + path.getParent());
        System.out.println("nameCount=" + path.getNameCount());
        System.out.println("name(0)=" + path.getName(0));

        // ---------- path operations ----------
        Path base = Paths.get("/home/user");
        Path file = Paths.get("docs", "readme.txt");
        Path resolved = base.resolve(file);
        System.out.println("resolved=" + resolved);

        Path normalized = Paths.get("/a/b/../c").normalize();
        System.out.println("normalized=" + normalized);   // /a/c

        Path rel = Paths.get("/a/b").relativize(Paths.get("/a/c"));
        System.out.println("relativized=" + rel);         // ../c

        System.out.println("startsWith(src)=" + path.startsWith("src"));
        System.out.println("endsWith(.txt)=" + path.endsWith(".txt"));
        System.out.println("isAbsolute=" + path.isAbsolute());

        // ---------- Files.write ----------
        Path dataFile = Paths.get("nio_test.txt");
        Files.write(dataFile, "Hello World\nLine2\nLine3\n".getBytes(StandardCharsets.UTF_8));
        System.out.println("Written to " + dataFile);

        // ---------- Files.readAllBytes ----------
        byte[] bytes = Files.readAllBytes(dataFile);
        String content = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("Content:\n" + content);

        // ---------- Files.readAllLines ----------
        List<String> lines = Files.readAllLines(dataFile, StandardCharsets.UTF_8);
        System.out.println("Lines count=" + lines.size());
        for (int i = 0; i < lines.size(); i++) {
            System.out.println((i + 1) + ": " + lines.get(i));
        }

        // ---------- Files.exists و metadata ----------
        System.out.println("exists=" + Files.exists(dataFile));
        System.out.println("isRegularFile=" + Files.isRegularFile(dataFile));
        System.out.println("isDirectory=" + Files.isDirectory(dataFile));
        System.out.println("size=" + Files.size(dataFile));

        // ---------- Files.createDirectories ----------
        Path dir = Paths.get("nio_test_dir", "subdir");
        Files.createDirectories(dir);
        System.out.println("Created: " + dir);

        // ---------- cleanup ----------
        Files.delete(dataFile);
        Files.delete(dir.resolve("subdir"));
        Files.delete(dir.getParent());
    }
}

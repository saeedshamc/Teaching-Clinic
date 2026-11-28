// TryWithResourcesDemo.java — try-with-resources، چند resource، suppressed exception
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TryWithResourcesDemo {

    public static void main(String[] args) {
        // ---------- try-with-resources — خواندن فایل ----------
        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
            String line;
            int lineNum = 1;
            while ((line = br.readLine()) != null) {
                System.out.println(lineNum++ + ": " + line);
            }
        } catch (IOException e) {
            System.out.println("Read error: " + e.getMessage());
        }

        // ---------- چند resource در یک try ----------
        try (FileWriter fw = new FileWriter("output.txt");
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write("Hello World\n");
            bw.write("Second line\n");
            System.out.println("Written to output.txt");
        } catch (IOException e) {
            System.out.println("Write error: " + e.getMessage());
        }

        // ---------- suppressed exception ----------
        try (BufferedReader br = new BufferedReader(new FileReader("nonexistent.txt"))) {
            br.readLine();
        } catch (IOException e) {
            System.out.println("Main exception: " + e.getMessage());
            for (Throwable suppressed : e.getSuppressed()) {
                System.out.println("  Suppressed: " + suppressed.getMessage());
            }
        }

        // ---------- متد که resource را برمی‌گرداند ----------
        try (BufferedReader br = readFile("test.txt")) {
            String content = br.readLine();
            System.out.println("First line: " + content);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // متد که resource می‌سازد و برمی‌گرداند — caller باید close کند
    static BufferedReader readFile(String filename) throws IOException {
        return new BufferedReader(new FileReader(filename));
    }
}

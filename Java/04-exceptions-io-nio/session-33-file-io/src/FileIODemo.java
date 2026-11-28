// FileIODemo.java — File metadata، FileReader/Writer، Buffered، PrintWriter
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileIODemo {

    public static void main(String[] args) throws IOException {
        // ---------- File metadata ----------
        File f = new File("demo.txt");
        System.out.println("exists=" + f.exists());
        System.out.println("isFile=" + f.isFile());
        System.out.println("isDirectory=" + f.isDirectory());
        System.out.println("length=" + f.length());
        System.out.println("name=" + f.getName());
        System.out.println("absolutePath=" + f.getAbsolutePath());

        // ساخت فایل خالی اگر وجود ندارد
        if (!f.exists()) {
            f.createNewFile();
            System.out.println("Created: " + f.getName());
        }

        // ---------- FileWriter — نوشتن ----------
        try (FileWriter fw = new FileWriter("demo.txt")) {
            fw.write("Hello World\n");
            fw.write("Second line\n");
            fw.write("Third line\n");
        }
        System.out.println("Written to demo.txt");

        // ---------- BufferedReader — خواندن خط به خط ----------
        try (BufferedReader br = new BufferedReader(new FileReader("demo.txt"))) {
            String line;
            int lineNum = 1;
            while ((line = br.readLine()) != null) {
                System.out.println(lineNum++ + ": " + line);
            }
        }

        // ---------- BufferedWriter — نوشتن خط به خط ----------
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("demo2.txt"))) {
            bw.write("Line 1");
            bw.newLine();   // خط جدید platform-independent
            bw.write("Line 2");
            bw.newLine();
            bw.write("Line 3");
        }
        System.out.println("Written to demo2.txt");

        // ---------- PrintWriter — ساده‌ترین ----------
        try (PrintWriter pw = new PrintWriter("demo3.txt")) {
            pw.println("Hello from PrintWriter");
            pw.printf("%s is %d years old%n", "Ali", 20);
            pw.print("No newline at end");
        }
        System.out.println("Written to demo3.txt");

        // ---------- directory ----------
        File dir = new File("testdir");
        if (!dir.exists()) {
            dir.mkdir();
            System.out.println("Created dir: " + dir.getName());
        }
        File[] files = dir.listFiles();
        if (files != null) {
            System.out.println("Files in testdir: " + files.length);
        }

        // cleanup
        f.delete();
        new File("demo2.txt").delete();
        new File("demo3.txt").delete();
        dir.delete();
    }
}

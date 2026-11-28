// NioFilesDemo.java — newBufferedReader/Writer، FileChannel، Files.copy
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class NioFilesDemo {

    public static void main(String[] args) throws IOException {
        Path source = Paths.get("nio_source.txt");
        Path target = Paths.get("nio_target.txt");

        // ---------- Files.write ----------
        Files.write(source, List.of("Line 1", "Line 2", "Line 3"), StandardCharsets.UTF_8);
        System.out.println("Written: " + source);

        // ---------- Files.newBufferedReader ----------
        try (BufferedReader br = Files.newBufferedReader(source, StandardCharsets.UTF_8)) {
            String line;
            int n = 1;
            while ((line = br.readLine()) != null) {
                System.out.println(n++ + ": " + line);
            }
        }

        // ---------- Files.newBufferedWriter ----------
        try (BufferedWriter bw = Files.newBufferedWriter(target)) {
            bw.write("Copied content\n");
            bw.write("From source file\n");
        }
        System.out.println("Written: " + target);

        // ---------- Files.copy با options ----------
        Path copy = Paths.get("nio_copy.txt");
        Files.copy(source, copy, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Copied to: " + copy);

        // ---------- FileChannel — خواندن/نوشتن ----------
        Path channelFile = Paths.get("channel_test.bin");
        try (FileChannel channel = FileChannel.open(channelFile,
                StandardOpenOption.CREATE, StandardOpenOption.READ, StandardOpenOption.WRITE)) {
            // نوشتن
            ByteBuffer writeBuf = ByteBuffer.allocate(100);
            writeBuf.put("Hello from channel".getBytes(StandardCharsets.UTF_8));
            writeBuf.flip();   // برای نوشتن باید flip شود
            channel.write(writeBuf);
            channel.force(true);   // flush به دیسک

            // خواندن
            channel.position(0);   // به اول برگرد
            ByteBuffer readBuf = ByteBuffer.allocate(100);
            channel.read(readBuf);
            readBuf.flip();
            byte[] data = new byte[readBuf.remaining()];
            readBuf.get(data);
            System.out.println("Channel read: " + new String(data, StandardCharsets.UTF_8));

            System.out.println("Channel size: " + channel.size());
        }

        // ---------- cleanup ----------
        Files.deleteIfExists(source);
        Files.deleteIfExists(target);
        Files.deleteIfExists(copy);
        Files.deleteIfExists(channelFile);
    }
}

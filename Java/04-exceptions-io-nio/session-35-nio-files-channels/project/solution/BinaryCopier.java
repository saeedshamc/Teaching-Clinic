// BinaryCopier.java — پاسخ پروژهٔ جلسه ۳۵
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BinaryCopier {

    public static void main(String[] args) throws IOException {
        Path source = Paths.get("source.bin");
        Path target = Paths.get("target.bin");

        // ساخت فایل تست اگر وجود ندارد
        if (!Files.exists(source)) {
            Files.write(source, new byte[1024]);   // 1KB صفر
        }

        long sourceSize = Files.size(source);
        System.out.println("Source size: " + sourceSize + " bytes");

        // کپی با FileChannel و ByteBuffer
        try (FileChannel srcChannel = FileChannel.open(source, StandardOpenOption.READ);
             FileChannel dstChannel = FileChannel.open(target,
                     StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {

            ByteBuffer buffer = ByteBuffer.allocate(8192);   // 8KB chunk
            long totalRead = 0;

            while (srcChannel.read(buffer) != -1) {
                buffer.flip();
                dstChannel.write(buffer);
                buffer.clear();
            }
            dstChannel.force(true);   // flush به دیسک
            totalRead = dstChannel.size();

            System.out.println("Copied: " + totalRead + " bytes");
        }

        long targetSize = Files.size(target);
        System.out.println("Target size: " + targetSize + " bytes");
        System.out.println("Match: " + (sourceSize == targetSize));

        // cleanup
        Files.deleteIfExists(source);
        Files.deleteIfExists(target);
    }
}

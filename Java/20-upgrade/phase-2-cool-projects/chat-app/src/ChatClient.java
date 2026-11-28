// ChatClient.java — کلاینت چت با ترمینال
// قابل کامپایل و اجرا با javac — بدون dependency
// اجرا: java ChatClient localhost 5000
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) throws IOException {
        String host = args.length > 0 ? args[0] : "localhost";
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 5000;

        Socket socket = new Socket(host, port);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);

        // ترد برای خواندن پیام‌های سرور
        Thread reader = new Thread(() -> {
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("اتصال قطع شد.");
            }
        });
        reader.setDaemon(true);
        reader.start();

        // خواندن نام
        System.out.print("نام شما: ");
        String name = scanner.nextLine();
        out.println(name);

        System.out.println("دستورها: /quit برای خروج، /list برای تعداد کاربران");
        while (true) {
            String msg = scanner.nextLine();
            if (msg.equalsIgnoreCase("/quit")) break;
            out.println(msg);
        }
        socket.close();
        System.out.println("خداحافظ!");
    }
}

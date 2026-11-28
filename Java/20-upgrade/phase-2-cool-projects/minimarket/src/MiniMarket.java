// MiniMarket.java — سامانهٔ فروشگاهی (POS) کنسولی کامل
// قابل کامپایل و اجرا با javac — بدون dependency
// اجرا: java MiniMarket
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class MiniMarket {

    // ---------- مدل‌ها ----------
    static class Product {
        String code, name;
        double price;
        int stock;
        Product(String code, String name, double price, int stock) {
            this.code = code; this.name = name; this.price = price; this.stock = stock;
        }
    }

    static class InvoiceItem {
        Product product;
        int qty;
        InvoiceItem(Product p, int q) { product = p; qty = q; }
        double subtotal() { return product.price * qty; }
    }

    static class Invoice {
        long id;
        List<InvoiceItem> items = new ArrayList<>();
        double total;
        String date;
        Invoice(long id) {
            this.id = id;
            this.date = java.time.LocalDateTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
        void add(Product p, int qty) {
            items.add(new InvoiceItem(p, qty));
            total += p.price * qty;
        }
    }

    // ---------- داده ----------
    static final List<Product> catalog = new ArrayList<>();
    static final List<Invoice> invoices = new ArrayList<>();
    static long nextInvoiceId = 1;
    static final Scanner sc = new Scanner(System.in);
    static final String DB_FILE = "minimarket_data.txt";

    public static void main(String[] args) {
        loadData();
        if (catalog.isEmpty()) seedCatalog();
        menu();
    }

    static void menu() {
        while (true) {
            System.out.println("\n===== فروشگاه =====");
            System.out.println("۱) فروش جدید    ۲) موجودی کالا    ۳) تاریخچه فاکتور");
            System.out.println("۴) گزارش فروش    ۵) افزودن کالا    ۰) خروج");
            System.out.print("انتخاب: ");
            String c = sc.nextLine().trim();
            switch (c) {
                case "1" -> newSale();
                case "2" -> showStock();
                case "3" -> showInvoices();
                case "4" -> showReport();
                case "5" -> addProduct();
                case "0" -> { saveData(); System.out.println("خداحافظ!"); return; }
                default -> System.out.println("انتخاب نامعتبر");
            }
        }
    }

    static void seedCatalog() {
        catalog.add(new Product("P001", "برنج ۵ کیلویی", 850000, 20));
        catalog.add(new Product("P002", "روغن آفتابگردان", 320000, 15));
        catalog.add(new Product("P003", "چای ۵۰۰ گرمی", 180000, 30));
        catalog.add(new Product("P004", "شیر ۱ لیتری", 45000, 50));
        catalog.add(new Product("P005", "ماکارونی", 25000, 100));
    }

    // ---------- فروش ----------
    static void newSale() {
        Invoice inv = new Invoice(nextInvoiceId++);
        while (true) {
            System.out.print("کد کالا (یا 'x' برای پایان): ");
            String code = sc.nextLine().trim();
            if (code.equalsIgnoreCase("x")) break;
            Product p = findProduct(code);
            if (p == null) { System.out.println("کالا پیدا نشد!"); continue; }
            System.out.print("تعداد: ");
            int qty;
            try { qty = Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("عدد نامعتبر"); continue; }
            if (qty <= 0 || qty > p.stock) {
                System.out.println("موجودی کافی نیست (موجود: " + p.stock + ")");
                continue;
            }
            p.stock -= qty;
            inv.add(p, qty);
            System.out.printf("  + %s × %d = %.0f%n", p.name, qty, p.price * qty);
        }
        if (inv.items.isEmpty()) { System.out.println("فاکتور خالی — لغو شد"); return; }
        invoices.add(inv);
        System.out.println("\n--- فاکتور " + inv.id + " (" + inv.date + ") ---");
        for (InvoiceItem i : inv.items)
            System.out.printf("  %-25s ×%d  %.0f%n", i.product.name, i.qty, i.subtotal());
        System.out.printf("جمع کل: %.0f%n", inv.total);
        System.out.println("فروش ثبت شد.\n");
    }

    // ---------- گزارش ----------
    static void showReport() {
        if (invoices.isEmpty()) { System.out.println("فاکتوری نیست"); return; }
        double totalSales = invoices.stream().mapToDouble(i -> i.total).sum();
        long count = invoices.size();
        Map<String, Integer> topProducts = new HashMap<>();
        for (Invoice inv : invoices)
            for (InvoiceItem i : inv.items)
                topProducts.merge(i.product.name, i.qty, Integer::sum);

        System.out.println("\n--- گزارش فروش ---");
        System.out.printf("تعداد فاکتور: %d%nمجموع فروش: %.0f%nمیانگین هر فاکتور: %.0f%n",
            count, totalSales, totalSales / count);
        System.out.println("پرفروش‌ترین کالاها:");
        topProducts.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(5)
            .forEach(e -> System.out.printf("  %-25s %d عدد%n", e.getKey(), e.getValue()));
    }

    // ---------- نمایش‌ها ----------
    static void showStock() {
        System.out.println("\n--- موجودی ---");
        System.out.printf("%-7s %-25s %12s %8s%n", "کد", "نام", "قیمت", "موجودی");
        for (Product p : catalog)
            System.out.printf("%-7s %-25s %12.0f %8d%n", p.code, p.name, p.price, p.stock);
        long low = catalog.stream().filter(p -> p.stock < 5).count();
        if (low > 0) System.out.println("⚠ " + low + " کالا موجودی کم دارد!");
    }

    static void showInvoices() {
        System.out.println("\n--- تاریخچه فاکتور ---");
        for (Invoice inv : invoices) {
            System.out.printf("فاکتور #%d | %s | %d قلم | %.0f%n",
                inv.id, inv.date, inv.items.size(), inv.total);
        }
    }

    static void addProduct() {
        System.out.print("کد: "); String code = sc.nextLine().trim();
        System.out.print("نام: "); String name = sc.nextLine().trim();
        System.out.print("قیمت: "); double price = Double.parseDouble(sc.nextLine().trim());
        System.out.print("موجودی: "); int stock = Integer.parseInt(sc.nextLine().trim());
        catalog.add(new Product(code, name, price, stock));
        System.out.println("کالا اضافه شد.");
    }

    // ---------- ابزارها ----------
    static Product findProduct(String code) {
        return catalog.stream().filter(p -> p.code.equalsIgnoreCase(code)).findFirst().orElse(null);
    }

    // ---------- ذخیره/بازیابی ----------
    static void saveData() {
        try (PrintWriter w = new PrintWriter(Files.newBufferedWriter(Paths.get(DB_FILE)))) {
            for (Product p : catalog)
                w.printf("P|%s|%s|%.0f|%d%n", p.code, p.name, p.price, p.stock);
            for (Invoice inv : invoices) {
                w.printf("I|%d|%.0f|%s%n", inv.id, inv.total, inv.date);
                for (InvoiceItem i : inv.items)
                    w.printf("  i|%s|%d%n", i.product.code, i.qty);
            }
            w.printf("N|%d%n", nextInvoiceId);
        } catch (IOException e) { System.err.println("خطا در ذخیره: " + e.getMessage()); }
    }

    static void loadData() {
        Path path = Paths.get(DB_FILE);
        if (!Files.exists(path)) return;
        try (BufferedReader r = Files.newBufferedReader(path)) {
            Invoice current = null;
            String line;
            while ((line = r.readLine()) != null) {
                String[] parts = line.split("\\|");
                switch (parts[0]) {
                    case "P" -> catalog.add(new Product(parts[1], parts[2],
                        Double.parseDouble(parts[3]), Integer.parseInt(parts[4])));
                    case "I" -> { current = new Invoice(Long.parseLong(parts[1]));
                        current.total = Double.parseDouble(parts[2]); current.date = parts[3];
                        invoices.add(current); }
                    case "i" -> { if (current != null) {
                        Product p = findProduct(parts[1]);
                        if (p != null) current.items.add(new InvoiceItem(p, Integer.parseInt(parts[2]))); } }
                    case "N" -> nextInvoiceId = Long.parseLong(parts[1]);
                }
            }
        } catch (IOException e) { System.err.println("خطا در بارگذاری: " + e.getMessage()); }
    }
}

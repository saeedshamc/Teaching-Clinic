// SalesAnalyzer.java — پاسخ پروژهٔ جلسه ۴۸
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Sale {
    String product;
    int quantity;
    double price;
    Sale(String product, int quantity, double price) {
        this.product = product; this.quantity = quantity; this.price = price;
    }
}

public class SalesAnalyzer {

    public static void main(String[] args) {
        List<Sale> sales = List.of(
            new Sale("Laptop", 2, 5000),
            new Sale("Phone", 3, 2000),
            new Sale("Tablet", 1, 3000),
            new Sale("Laptop", 1, 5000),
            new Sale("Phone", 2, 2000)
        );

        // مجموع فروش
        double totalSales = sales.stream()
            .mapToDouble(s -> s.quantity * s.price)
            .sum();
        System.out.println("Total sales: " + totalSales);

        // بیشترین فروش بر اساس محصول
        Map<String, Double> salesByProduct = sales.stream()
            .collect(Collectors.groupingBy(
                s -> s.product,
                Collectors.summingDouble(s -> s.quantity * s.price)
            ));
        System.out.println("Sales by product: " + salesByProduct);

        // میانگین قیمت
        double avgPrice = sales.stream()
            .mapToDouble(s -> s.price)
            .average()
            .orElse(0);
        System.out.println("Average price: " + avgPrice);

        // بیشترین مقدار فروش
        Sale topSale = sales.stream()
            .max((a, b) -> Integer.compare(a.quantity * a.price > 0 ? a.quantity : 0,
                                         b.quantity * b.price > 0 ? b.quantity : 0))
            .orElse(null);
        if (topSale != null) {
            System.out.println("Top sale: " + topSale.product + " qty=" + topSale.quantity);
        }

        // فیلتر — فروش بالای 10000
        List<Sale> highSales = sales.stream()
            .filter(s -> s.quantity * s.price > 10000)
            .collect(Collectors.toList());
        System.out.println("High sales: " + highSales.size());
    }
}

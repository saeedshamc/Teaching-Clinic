// DataConverter.java — پاسخ پروژهٔ جلسه ۴۷
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@FunctionalInterface
interface Converter<T, R> {
    R convert(T input);
}

public class DataConverter {

    public static void main(String[] args) {
        // method reference برای تبدیل String → Integer
        Converter<String, Integer> toInt = Integer::valueOf;
        System.out.println("\"42\" → " + toInt.convert("42"));

        // method reference برای تبدیل String → Double
        Converter<String, Double> toDouble = Double::valueOf;
        System.out.println("\"3.14\" → " + toDouble.convert("3.14"));

        // method reference برای تبدیل Integer → String
        Converter<Integer, String> intToString = String::valueOf;
        System.out.println("42 → \"" + intToString.convert(42) + "\"");

        // lambda برای تبدیل سفارشی
        Converter<String, String> reverse = s -> new StringBuilder(s).reverse().toString();
        System.out.println("\"hello\" → " + reverse.convert("hello"));

        // ترکیب چند converter
        List<Function<String, String>> pipeline = new ArrayList<>();
        pipeline.add(String::toUpperCase);
        pipeline.add(s -> s + "!");
        pipeline.add(String::trim);

        String result = "  hello  ";
        for (Function<String, String> f : pipeline) {
            result = f.apply(result);
        }
        System.out.println("Pipeline: \"" + result + "\"");
    }
}

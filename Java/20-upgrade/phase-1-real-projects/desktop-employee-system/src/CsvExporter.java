// CsvExporter.java — خروجی CSV از لیست کارکنان
import java.io.*;
import java.util.List;

public class CsvExporter {

    private static final String HEADER =
        "id,firstName,lastName,email,phone,department,salary,hireDate,active";

    public static void export(List<Employee> employees, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(HEADER);
            writer.newLine();
            for (Employee e : employees) {
                writer.write(String.join(",", e.toRow()));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("خطا در نوشتن CSV: " + e.getMessage(), e);
        }
    }
}

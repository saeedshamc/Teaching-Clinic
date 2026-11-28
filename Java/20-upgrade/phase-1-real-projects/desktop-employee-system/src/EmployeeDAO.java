// EmployeeDAO.java — لایهٔ دسترسی به داده با ذخیره‌سازی فایل
// قابل کامپایل با javac — بدون dependency خارجی
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class EmployeeDAO {

    private static final String FILE_NAME = "employees.csv";
    private static final String HEADER =
        "id,firstName,lastName,email,phone,department,salary,hireDate,active";

    // ---------- خواندن ----------
    public List<Employee> loadAll() {
        List<Employee> employees = new ArrayList<>();
        Path path = Paths.get(FILE_NAME);
        if (!Files.exists(path)) {
            return employees;   // فایل هنوز ساخته نشده
        }
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();   // header — رد می‌شود
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    employees.add(Employee.fromRow(line.split(",")));
                }
            }
        } catch (IOException e) {
            System.err.println("خطا در خواندن فایل: " + e.getMessage());
        }
        return employees;
    }

    // ---------- ذخیرهٔ کامل (هر بار همه را می‌نویسد — ساده و قابل‌اعتماد) ----------
    public void saveAll(List<Employee> employees) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_NAME))) {
            writer.write(HEADER);
            writer.newLine();
            for (Employee e : employees) {
                writer.write(String.join(",", e.toRow()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("خطا در نوشتن فایل: " + e.getMessage());
        }
    }

    // ---------- آمار ساده ----------
    public Map<String, Integer> countByDepartment(List<Employee> employees) {
        Map<String, Integer> counts = new TreeMap<>();
        for (Employee e : employees) {
            if (e.isActive()) {
                counts.merge(e.getDepartment(), 1, Integer::sum);
            }
        }
        return counts;
    }
}

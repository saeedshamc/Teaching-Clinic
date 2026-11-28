// LegacyOverviewDemo.java — مقایسهٔ legacy و modern
import java.util.*;

public class LegacyOverviewDemo {

    public static void main(String[] args) {
        // ---------- Vector vs ArrayList ----------
        System.out.println("=== Vector vs ArrayList ===");
        Vector<String> vector = new Vector<>();
        ArrayList<String> arrayList = new ArrayList<>();
        vector.add("item1");
        arrayList.add("item1");
        System.out.println("Vector size: " + vector.size());
        System.out.println("ArrayList size: " + arrayList.size());
        // Vector synchronized است — کندتر برای single-thread
        // ArrayList سریع‌تر — برای most cases بهتر

        // ---------- Hashtable vs HashMap ----------
        System.out.println("\n=== Hashtable vs HashMap ===");
        Hashtable<String, Integer> hashtable = new Hashtable<>();
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashtable.put("key", 1);
        hashMap.put("key", 1);
        System.out.println("Hashtable: " + hashtable.get("key"));
        System.out.println("HashMap: " + hashMap.get("key"));
        // Hashtable synchronized — کندتر
        // HashMap سریع‌تر — برای most cases بهتر

        // ---------- Enumeration vs Iterator ----------
        System.out.println("\n=== Enumeration vs Iterator ===");
        Vector<String> vec = new Vector<>();
        vec.add("a"); vec.add("b"); vec.add("c");

        // Enumeration — legacy
        Enumeration<String> e = vec.elements();
        while (e.hasMoreElements()) {
            System.out.println("Enum: " + e.nextElement());
        }

        // Iterator — modern
        Iterator<String> it = vec.iterator();
        while (it.hasNext()) {
            System.out.println("Iter: " + it.next());
        }

        // ---------- Date vs java.time ----------
        System.out.println("\n=== Date vs java.time ===");
        @SuppressWarnings("deprecation")
        Date oldDate = new Date();
        System.out.println("Date: " + oldDate);

        java.time.LocalDateTime newDate = java.time.LocalDateTime.now();
        System.out.println("LocalDateTime: " + newDate);

        // ---------- StringBuffer vs StringBuilder ----------
        System.out.println("\n=== StringBuffer vs StringBuilder ===");
        StringBuffer sb = new StringBuffer();
        StringBuilder sb2 = new StringBuilder();
        sb.append("Hello");
        sb2.append("Hello");
        System.out.println("StringBuffer: " + sb);
        System.out.println("StringBuilder: " + sb2);
        // StringBuffer synchronized — کندتر
        // StringBuilder سریع‌تر — برای most cases بهتر
    }
}

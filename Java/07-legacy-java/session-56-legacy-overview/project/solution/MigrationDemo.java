// MigrationDemo.java — پاسخ پروژهٔ جلسه ۵۶
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MigrationDemo {

    public static void main(String[] args) {
        // ---------- Vector → ArrayList ----------
        Vector<String> oldVec = new Vector<>();
        oldVec.add("Ali"); oldVec.add("Sara"); oldVec.add("Reza");

        ArrayList<String> newList = new ArrayList<>();
        newList.addAll(oldVec);
        System.out.println("Vector → ArrayList: " + newList);

        // ---------- Hashtable → HashMap ----------
        Hashtable<String, Integer> oldHt = new Hashtable<>();
        oldHt.put("Ali", 20); oldHt.put("Sara", 25);

        HashMap<String, Integer> newMap = new HashMap<>(oldHt);
        System.out.println("Hashtable → HashMap: " + newMap);

        // ---------- Enumeration → Iterator ----------
        Enumeration<String> oldEnum = oldVec.elements();
        System.out.println("Enumeration:");
        while (oldEnum.hasMoreElements()) {
            System.out.println("  " + oldEnum.nextElement());
        }

        Iterator<String> newIter = newList.iterator();
        System.out.println("Iterator:");
        while (newIter.hasNext()) {
            System.out.println("  " + newIter.next());
        }

        // ---------- Date → LocalDateTime ----------
        @SuppressWarnings("deprecation")
        Date oldDate = new Date();
        LocalDateTime newDate = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("\nDate: " + oldDate);
        System.out.println("LocalDateTime: " + newDate.format(fmt));

        // ---------- StringBuffer → StringBuilder ----------
        StringBuffer oldSb = new StringBuffer();
        oldSb.append("Hello ").append("World");

        StringBuilder newSb = new StringBuilder();
        newSb.append("Hello ").append("World");
        System.out.println("\nStringBuffer: " + oldSb);
        System.out.println("StringBuilder: " + newSb);

        System.out.println("\nMigration complete!");
    }
}

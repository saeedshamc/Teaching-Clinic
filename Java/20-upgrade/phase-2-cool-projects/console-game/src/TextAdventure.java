// TextAdventure.java — بازی ماجراجویی متنی چندمرحله‌ای
// قابل کامپایل و اجرا با javac — بدون dependency
// اجرا: java TextAdventure
import java.util.*;

public class TextAdventure {

    // ---------- مدل جهان ----------
    static class Room {
        String name, description;
        Map<String, String> exits = new HashMap<>();   // جهت → اتاق
        Room(String name, String desc) { this.name = name; this.description = desc; }
    }

    static final Map<String, Room> rooms = new HashMap<>();
    static Room current;
    static int health = 100;
    static int gold = 0;
    static final Set<String> inventory = new HashSet<>();
    static boolean hasSword = false, hasKey = false, bossDefeated = false;
    static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        buildWorld();
        intro();
        gameLoop();
    }

    static void buildWorld() {
        Room start = room("start", "دهکدهٔ کوچک — نقطهٔ شروع ماجراجویی",
            "خانه‌ها، چاه، و جاده‌ای که به شمال می‌رود");
        Room forest = room("forest", "جنگل تیره — درختان بلند و صدای پرندگان",
            "مسیرهای پیچ‌درپیچ و صدای زوزیدن مار");
        Room cave = room("cave", "غار مرموز — هوای سرد و صدای قطره‌های آب",
            "تونل‌های تاریک و نقاشی‌های باستانی");
        Room castle = room("castle", "قلعهٔwizard — بوی جادو و قدیمی",
            "تالار بزرگ با تخت پادشاه و دروازهٔ شرقی");
        Room dungeon = room("dungeon", "زیرزمین شرور — محل پادشاه جادوگر",
            "اتاق‌های سنگی و شمع‌های سبز");

        link("start", "north", "forest");
        link("forest", "south", "start");
        link("forest", "east", "cave");
        link("forest", "north", "castle");
        link("cave", "west", "forest");
        link("castle", "south", "forest");
        link("castle", "east", "dungeon");
        link("dungeon", "west", "castle");

        current = start;
    }

    static Room room(String id, String name, String desc) {
        Room r = new Room(name, desc);
        rooms.put(id, r);
        r.name = name;
        return r;
    }

    // برای لینک باید id داشته باشیم — نگاشت roomId→Room لازم است
    static final Map<String, String> roomIds = new HashMap<>();
    static Room roomById(String id) { return rooms.get(id); }

    static void link(String fromId, String dir, String toId) {
        roomById(fromId).exits.put(dir, toId);
    }

    static void intro() {
        System.out.println("""
        ╔══════════════════════════════════════╗
        ║     ماجراجویی جاوا — سرزمین جادو     ║
        ╚══════════════════════════════════════╝
        """);
        System.out.println("هدف: غار را پیدا کن، کلید بگیر، به قلعه برو و جادوگر را شکست بده!");
        System.out.println("دستورها: north/south/east/west، look، take، inventory، stats، quit");
    }

    static void gameLoop() {
        while (true) {
            printStatus();
            System.out.print("> ");
            String cmd = sc.nextLine().trim().toLowerCase();
            if (cmd.equals("quit")) { System.out.println("بازی تمام شد. خداحافظ!"); return; }
            handleCommand(cmd);
            if (bossDefeated) { victory(); return; }
            if (health <= 0) { defeat(); return; }
        }
    }

    static void printStatus() {
        System.out.println("\n■ " + current.name);
        System.out.println("  " + current.description);
        System.out.println("  راه‌ها: " + String.join(", ", current.exits.keySet()));
        System.out.printf("  ❤ %d | 💰 %d | 🗡 %s | 🔑 %s%n",
            health, gold, hasSword ? "دارد" : "ندارد", hasKey ? "دارد" : "ندارد");
    }

    static void handleCommand(String cmd) {
        String[] parts = cmd.split(" ");
        switch (parts[0]) {
            case "north", "south", "east", "west" -> {
                String dest = current.exits.get(parts[0]);
                if (dest == null) System.out.println("از این سمت راهی نیست!");
                else {
                    current = roomById(dest);
                    onEnterRoom(dest);
                }
            }
            case "look" -> System.out.println(current.description);
            case "take" -> takeItem(parts.length > 1 ? parts[1] : "");
            case "inventory" -> System.out.println(
                inventory.isEmpty() ? "کوله‌پشتی خالی است" : "کوله‌پشتی: " + inventory);
            case "stats" -> printStatus();
            case "attack" -> attack();
            default -> System.out.println("دستور ناشناخته — north/south/east/west، look، take، attack");
        }
    }

    static void onEnterRoom(String roomId) {
        switch (roomId) {
            case "cave" -> {
                if (!inventory.contains("gem")) {
                    inventory.add("gem");
                    gold += 50;
                    System.out.println("✨ یک گوهر درخشان پیدا کردی! (+50 طلا)");
                }
                if (!inventory.contains("key") && !hasKey) {
                    // کلید در غار پنهان است
                }
            }
            case "dungeon" -> {
                if (!hasKey) {
                    health -= 20;
                    System.out.println("🚪 دروازه قفل است! کلید نداری. (-20 جان)");
                } else {
                    System.out.println("🚪 وارد اتاق جادوگر شدی! با 'attack' حمله کن.");
                }
            }
            case "forest" -> {
                if (!hasSword && Math.random() < 0.5) {
                    health -= 15;
                    System.out.println("🐍 یک مار به تو حمله کرد! (-15 جان)");
                }
            }
        }
    }

    static void takeItem(String item) {
        if (item.equals("sword") && current.name.contains("جنگل") && !hasSword) {
            hasSword = true; inventory.add("sword");
            System.out.println("🗡 شمشیر پیدا کردی!");
        } else if (item.equals("key") && current.name.contains("غار") && !hasKey) {
            hasKey = true; inventory.add("key");
            System.out.println("🔑 کلید طلایی پیدا کردی!");
        } else {
            System.out.println("چیزی برای برداشتن اینجا نیست.");
        }
    }

    static void attack() {
        if (!current.name.contains("زیرزمین")) {
            System.out.println("اینجا چیزی برای حمله نیست.");
            return;
        }
        if (!hasKey) { System.out.println("اول باید وارد اتاق شوی — کلید می‌خواهی."); return; }
        if (!hasSword) { health -= 30; System.out.println("بدون شمشیر به جادوگر حمله کردی! (-30 جان)"); return; }

        int damage = 30 + new Random().nextInt(20);
        System.out.println("⚔ به جادوگر حمله کردی! (-" + damage + " جان جادوگر)");
        if (Math.random() < 0.6) {
            bossDefeated = true;
            System.out.println("👑 جادوگر شکست خورد! پادشاهی نجات یافت!");
        } else {
            health -= 15;
            System.out.println("جادوگر جادو کرد! (-15 جان تو)");
        }
    }

    static void victory() {
        System.out.println("""
        ╔══════════════════════════════════════╗
        ║   🎉 پیروزی! ماجراجویی تمام شد       ║
        ║   جان باقی‌مانده: """ + health + "  طلا: " + gold + """
        ╚══════════════════════════════════════╝
        """);
    }

    static void defeat() {
        System.out.println("💀 جانت تمام شد... Game Over.");
    }
}

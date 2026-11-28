// GameSave.java — پاسخ پروژهٔ جلسه ۳۶
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class GameSave implements Serializable {
    private static final long serialVersionUID = 1L;
    String playerName;
    int level;
    int score;
    List<String> inventory;
    transient String sessionToken;   // serialize نمی‌شود

    GameSave(String playerName, int level, int score) {
        this.playerName = playerName;
        this.level = level;
        this.score = score;
        this.inventory = new ArrayList<>();
        this.sessionToken = "token-" + System.currentTimeMillis();
    }
}

public class GameSaveSystem {

    static final String SAVE_FILE = "save.dat";

    static void save(GameSave save) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(save);
        }
    }

    static GameSave load() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            return (GameSave) ois.readObject();
        }
    }

    public static void main(String[] args) {
        try {
            // ساخت save
            GameSave save = new GameSave("Ali", 5, 1000);
            save.inventory.add("Sword");
            save.inventory.add("Shield");
            save.inventory.add("Potion");

            System.out.println("Saving: " + save.playerName + " level=" + save.level + " score=" + save.score);
            save(save);

            // بارگذاری
            GameSave loaded = load();
            System.out.println("Loaded: " + loaded.playerName + " level=" + loaded.level + " score=" + loaded.score);
            System.out.println("Inventory: " + loaded.inventory);
            System.out.println("sessionToken (transient): " + loaded.sessionToken);   // null!

            new java.io.File(SAVE_FILE).delete();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

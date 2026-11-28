// AppStructure.java — architecture overview برای full Android app
// نیازمند Android SDK
package com.example.myapp;

// ---------- Data Layer ----------
// Room Entity
// @Entity(tableName = "items")
// public class Item { ... }

// Room DAO
// @Dao
// public interface ItemDao {
//     @Query("SELECT * FROM items") LiveData<List<Item>> getAllItems();
//     @Insert void insert(Item item);
// }

// Room Database
// @Database(entities = {Item.class}, version = 1)
// public abstract class AppDatabase extends RoomDatabase { ... }

// Retrofit API
// public interface ApiService {
//     @GET("items") Call<List<Item>> getItems();
// }

// Repository
// public class ItemRepository {
//     private ItemDao dao;
//     private ApiService api;
//     public LiveData<List<Item>> getAllItems() { return dao.getAllItems(); }
// }

// ---------- Presentation Layer ----------
// ViewModel
// public class ItemViewModel extends ViewModel {
//     private ItemRepository repository;
//     private LiveData<List<Item>> items;
// }

// Activity
// @AndroidEntryPoint
// public class MainActivity extends AppCompatActivity {
//     @Inject ItemViewModel viewModel;
// }

// ---------- DI ----------
// @Module
// @InstallIn(SingletonComponent.class)
// public class AppModule {
//     @Provides @Singleton
//     public ItemDao provideItemDao(AppDatabase db) { return db.itemDao(); }
// }

// ---------- Background ----------
// WorkManager Worker
// public class SyncWorker extends Worker { ... }

// Service
// public class MyService extends Service { ... }

// BroadcastReceiver
// public class MyReceiver extends BroadcastReceiver { ... }

// ---------- UI ----------
// Fragment
// public class ItemFragment extends Fragment { ... }

// Navigation
// nav_graph.xml for navigation

// ---------- Utils ----------
// NotificationHelper
// SettingsRepository (DataStore)

public class AppStructure {
    public static void main(String[] args) {
        System.out.println("=== Android App Architecture ===");
        System.out.println("Data Layer: Room + Retrofit + Repository");
        System.out.println("Presentation: ViewModel + LiveData + Activity/Fragment");
        System.out.println("DI: Hilt");
        System.out.println("Background: WorkManager + Service + BroadcastReceiver");
        System.out.println("UI: Navigation + RecyclerView + DataBinding");
        System.out.println("Utils: NotificationHelper + SettingsRepository");
    }
}

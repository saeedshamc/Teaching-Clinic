# جلسه ۷۳ — JavaFX Capstone: سیستم مدیریت مخاطبین

## اهداف یادگیری
- همهٔ مباحث JavaFX را ترکیب کنید
- یک برنامهٔ کامل و کاربردی بسازید
- FXML + Controller + ObservableList + TableView + Charts را با هم به‌کار ببرید

## پیش‌نیازها
- جلسات ۶۷–۷۲

## مدت زمان پیشنهادی
۹۰ دقیقه

## درس

### پروژه: Contact Manager JavaFX

یک سیستم مدیریت مخاطبین بسازید که:

1. **Model**: `Contact` — name, phone, email, category
2. **UI**:
   - `BorderPane` برای ساختار اصلی
   - `HBox` برای toolbar (add, delete, search)
   - `TableView` برای نمایش مخاطبین
   - `ComboBox` برای category filter
   - `PieChart` برای category distribution
   - `TextField`/`TextArea` برای add/edit
3. **Concurrency**: `Task` برای load/save simulation
4. **CSS**: styling برای professional look

### معماری

```java
public class Contact {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty phone = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty category = new SimpleStringProperty();
}

public class ContactController {
    @FXML private TableView<Contact> tableView;
    @FXML private TextField nameField;
    @FXML private ComboBox<String> categoryCombo;
    private ObservableList<Contact> contacts;
}
```

## کد مهم

`src/ContactManagerFX.java` — پیاده‌سازی کامل.

## جمع‌بندی فاز ۵ (JavaFX)
- Setup, Controls, Layout/FXML, Events/CSS, Tables/Charts, Concurrency ✅
- **بعدی: SWT — Eclipse's native GUI toolkit**

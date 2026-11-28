# جلسه ۸۷ — Navigation Component

## اهداف یادگیری
- `Navigation Component` را برای single-activity architecture بشناسید
- `nav_graph.xml` برای navigation graph را بفهمید
- `NavHostFragment` و `NavController` را به‌کار ببرید
- `navigate`, `popBackStack`, `safeArgs` را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۸۶ (WorkManager)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Navigation Component Setup

```gradle
dependencies {
    implementation 'androidx.navigation:navigation-fragment:2.6.0'
    implementation 'androidx.navigation:navigation-ui:2.6.0'
}
```

### nav_graph.xml — navigation graph

```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/nav_graph"
    app:startDestination="@id/homeFragment">

    <fragment
        android:id="@+id/homeFragment"
        android:name="com.example.myapp.HomeFragment"
        android:label="Home">
        <action
            android:id="@+id/action_home_to_detail"
            app:destination="@id/detailFragment" />
    </fragment>

    <fragment
        android:id="@+id/detailFragment"
        android:name="com.example.myapp.DetailFragment"
        android:label="Detail">
        <argument
            android:name="itemId"
            app:argType="integer" />
    </fragment>
</navigation>
```

### NavHostFragment — container

```xml
<fragment
    android:id="@+id/nav_host_fragment"
    android:name="androidx.navigation.fragment.NavHostFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:defaultNavHost="true"
    app:navGraph="@navigation/nav_graph" />
```

### NavController — navigation

```java
NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

// Navigate to fragment
navController.navigate(R.id.detailFragment, bundle);

// Navigate with action
navController.navigate(R.id.action_home_to_detail);

// Pop back stack
navController.popBackStack();
```

### Safe Args — type-safe arguments

```java
// In fragment
Bundle args = new Bundle();
args.putInt("itemId", 42);
navController.navigate(R.id.detailFragment, args);

// In DetailFragment
Bundle args = getArguments();
int itemId = args.getInt("itemId");
```

## کد مهم

`src/nav_graph.xml` — navigation graph.
`src/HomeFragment.java` — home fragment.
`src/DetailFragment.java` — detail fragment.
`src/MainActivity.java` — NavHostFragment setup.

## خطاهای رایج
- `nav_graph.xml` را در `res/navigation` فراموش کردن → `Resource not found`.
- `NavHostFragment` را بدون `app:navGraph` استفاده کردن → navigation کار نمی‌کند.
- `navigate` را بدون `NavController` صدا زدن → NPE.
- `argument` را بدون `app:argType` تعریف کردن → type mismatch.

## جمع‌بندی
- Navigation Component برای single-activity architecture.
- `nav_graph.xml` برای navigation graph، `NavHostFragment` برای container.
- `NavController` برای navigation، `safeArgs` برای type-safe arguments.

**جلسهٔ بعد:** Coroutines و Flow.

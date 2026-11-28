# جلسه ۸۵ — Retrofit: REST API Client

## اهداف یادگیری
- `Retrofit` را برای REST API calls بشناسید
- `@GET`, `@POST`, `@PUT`, `@DELETE` annotations را بفهمید
- `Call`, `Callback` برای async requests را به‌کار ببرید
- `Gson` برای JSON serialization را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۸۴ (Room)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Retrofit Setup

```gradle
dependencies {
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:4.11.0'
}
```

### API Interface

```java
public interface ApiService {
    @GET("users")
    Call<List<User>> getUsers();

    @GET("users/{id}")
    Call<User> getUser(@Path("id") int id);

    @POST("users")
    Call<User> createUser(@Body User user);

    @PUT("users/{id}")
    Call<User> updateUser(@Path("id") int id, @Body User user);

    @DELETE("users/{id}")
    Call<Void> deleteUser(@Path("id") int id);
}
```

### Model — User

```java
public class User {
    private int id;
    private String name;
    private String email;
    private String role;

    // getters/setters
}
```

### Retrofit Instance

```java
Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("https://api.example.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build();

ApiService api = retrofit.create(ApiService.class);
```

### Async Call — Callback

```java
api.getUsers().enqueue(new Callback<List<User>>() {
    @Override
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        if (response.isSuccessful()) {
            List<User> users = response.body();
            // handle success
        } else {
            // handle error
        }
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {
        // handle failure
    }
});
```

### Synchronous Call

```java
// on background thread
Call<List<User>> call = api.getUsers();
Response<List<User>> response = call.execute();
if (response.isSuccessful()) {
    List<User> users = response.body();
}
```

## کد مهم

`src/ApiService.java` — API interface.
`src/User.java` — model.
`src/RetrofitClient.java` — Retrofit instance.
`src/ApiActivity.java` — API calls.

## خطاهای رایج
- `baseUrl` را بدون trailing slash استفاده کردن → `IllegalArgumentException`.
- `enqueue` را روی main thread استفاده کردن → `NetworkOnMainThreadException` — background thread لازم است.
- `response.body()` را بدون null check استفاده کردن → NPE.
- `GsonConverterFactory` را فراموش کردن → JSON parse error.

## جمع‌بندی
- Retrofit برای REST API — type-safe HTTP client.
- `@GET`, `@POST`, `@PUT`, `@DELETE` برای endpoints.
- `Call.enqueue` برای async، `Call.execute` برای sync.
- `Gson` برای JSON serialization.

**جلسهٔ بعد:** WorkManager — background tasks.

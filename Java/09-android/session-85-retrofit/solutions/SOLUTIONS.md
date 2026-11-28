# پاسخ تمرین‌های جلسه ۸۵

## آسان

**۱ و ۲.**
```java
public interface ApiService {
    @GET("users") Call<List<User>> getUsers();
    @POST("users") Call<User> createUser(@Body User user);
}

Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("https://api.example.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build();
```

## متوسط

**۳ و ۴.**
```java
api.getUsers().enqueue(new Callback<List<User>>() {
    @Override public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        if (response.isSuccessful()) { /* handle success */ }
    }
    @Override public void onFailure(Call<List<User>> call, Throwable t) { /* handle failure */ }
});
```

## سخت

**۵ و ۶.**
```java
@GET("users/{id}") Call<User> getUser(@Path("id") int id);
@PUT("users/{id}") Call<User> updateUser(@Path("id") int id, @Body User user);

// synchronous call on background thread
Response<List<User>> response = api.getUsers().execute();
```

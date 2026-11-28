# پاسخ تمرین‌های جلسه ۱۱۴

## آسان

**۱ و ۲.**
```java
@Component
public class UserDao { ... }

@Service
public class UserService { ... }
```

## متوسط

**۳ و ۴.**
```java
@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
}

@Configuration
public class AppConfig {
    @Bean
    public UserDao userDao() { return new UserDao(); }
}
```

## سخت

**۵ و ۶.**
```java
@Configuration
@ComponentScan("com.example")
public class AppConfig { }

AnnotationConfigApplicationContext context =
    new AnnotationConfigApplicationContext(AppConfig.class);
context.close();
```

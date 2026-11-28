# جلسه ۱۱۳ — Jakarta EE: Enterprise Java

## اهداف یادگیری
- `Jakarta EE` را به‌عنوان successor Java EE بشناسید
- `Jakarta Servlet`, `Jakarta REST`, `Jakarta Persistence` را بفهمید
- `Application Server` (WildFly, TomEE) را بشناسید
- `CDI` (Contexts and Dependency Injection) را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۱۱۲ (JSP)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Jakarta EE چیست؟

`Jakarta EE` — نسخهٔ مدرن Java EE (که Oracle به Eclipse Foundation داد). namespace از `javax.*` به `jakarta.*` تغییر کرد (Jakarta EE 9+).

### Jakarta Specifications

| Spec | Package | Usage |
|---|---|---|
| Servlet | `jakarta.servlet` | HTTP handling |
| REST | `jakarta.ws.rs` | REST APIs |
| Persistence | `jakarta.persistence` | JPA |
| CDI | `jakarta.inject` | Dependency injection |
| JSON-B | `jakarta.json.bind` | JSON binding |

### Application Server

- **WildFly** — Red Hat، open-source
- **TomEE** — Apache، Tomcat + Java EE
- **Payara** — GlassFish fork
- **OpenLiberty** — IBM، cloud-native

### CDI — Dependency Injection

```java
@ApplicationScoped
public class UserService {
    @Inject
    UserDao userDao;   // auto-injected
}

@Path("/users")
public class UserResource {
    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAll() {
        return userService.findAll();
    }
}
```

### JAX-RS — REST در Jakarta EE

```java
@Path("/users")
public class UserResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAll() { ... }

    @GET
    @Path("/{id}")
    public User getById(@PathParam("id") int id) { ... }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(User user) { ... }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") int id, User user) { ... }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) { ... }
}
```

## کد مهم

`src/UserResource.java` — JAX-RS REST resource.
`src/UserService.java` — CDI service.
`src/pom.xml` — Jakarta EE dependencies.

## خطاهای رایج
- `javax.*` vs `jakarta.*` — Jakarta EE 9+ فقط `jakarta` را support می‌کند.
- `@Inject` را بدون CDI container استفاده کردن → NPE.
- `@Path` را بدون leading slash تعریف کردن → `IllegalArgumentException`.
- Application Server را بدون deployment استفاده کردن → servlet container لازم است.

## جمع‌بندی
- Jakarta EE = Java EE مدرن — `jakarta.*` namespace.
- JAX-RS برای REST، CDI برای DI، JPA برای persistence.
- Application Server برای deployment.
- WildFly/TomEE برای open-source servers.

**جلسهٔ بعد:** Spring Core — dependency injection framework.

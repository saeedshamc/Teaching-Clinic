# پاسخ تمرین‌های جلسه ۱۱۳

## آسان

**۱ و ۲.**
```java
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    @GET
    public List<User> getAll() { ... }
}

@ApplicationScoped
public class UserService { ... }
```

## متوسط

**۳ و ۴.**
```java
@POST
public Response create(User user) { ... }

@GET
@Path("/{id}")
public Response getById(@PathParam("id") int id) { ... }
```

## سخت

**۵ و ۶.**
```java
@Inject
UserService userService;

return Response.status(Response.Status.CREATED).entity(user).build();
```

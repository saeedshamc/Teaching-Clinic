// UserResource.java — JAX-RS REST resource برای Jakarta EE
// نیازمند: jakarta.ws.rs-api jar برای کامپایل
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.*;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private static final List<Map<String, Object>> users = new ArrayList<>();
    private static int nextId = 1;

    static {
        users.add(Map.of("id", 1, "name", "Ali", "email", "ali@mail.com"));
        users.add(Map.of("id", 2, "name", "Sara", "email", "sara@mail.com"));
        nextId = 3;
    }

    @GET
    public List<Map<String, Object>> getAll() {
        return users;
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") int id) {
        return users.stream()
            .filter(u -> (int) u.get("id") == id)
            .findFirst()
            .map(u -> Response.ok(u).build())
            .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response create(Map<String, Object> userData) {
        Map<String, Object> user = new HashMap<>(userData);
        user.put("id", nextId++);
        users.add(user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") int id, Map<String, Object> userData) {
        return users.stream()
            .filter(u -> (int) u.get("id") == id)
            .findFirst()
            .map(u -> {
                u.putAll(userData);
                return Response.ok(u).build();
            })
            .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        boolean removed = users.removeIf(u -> (int) u.get("id") == id);
        return removed ? Response.noContent().build()
            : Response.status(Response.Status.NOT_FOUND).build();
    }
}

// FullUserResource.java — پاسخ کامل پروژهٔ جلسه ۱۱۳
// نیازمند: jakarta.ws.rs-api + jakarta.inject-api jars برای کامپایل
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.*;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FullUserResource {

    @Inject
    UserService userService;

    @GET
    public List<Map<String, Object>> getAll() {
        return userService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") int id) {
        return userService.findById(id)
            .map(u -> Response.ok(u).build())
            .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response create(Map<String, Object> userData) {
        Map<String, Object> user = userService.create(
            (String) userData.get("name"),
            (String) userData.get("email"));
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        return userService.delete(id)
            ? Response.noContent().build()
            : Response.status(Response.Status.NOT_FOUND).build();
    }
}

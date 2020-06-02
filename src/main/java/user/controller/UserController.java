package user.controller;

import com.google.inject.Inject;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import user.dto.CreateUserDto;
import user.dto.UserDto;
import user.model.RegisterResponse;
import user.model.User;
import user.service.UserService;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@RequestScoped
@Path("/api/v1/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PermitAll
public class UserController {

    public UserController(UserService userService){
        this.userService = userService;
    }

    @Inject
    private UserService userService;


    @GET
    public Response findAll(){
        System.out.println(userService != null);

            List<User> response = userService.findAll();
            return Response.ok()
                    .entity(response)
                    .build();

    }


    @GET
    @Path("/id/{id}")
    public Response findById(@PathParam("id") String id){
        User response = userService.findUserById(id);
        return Response.ok()
                .entity(response)
                .build();
    }

    @GET
    @Path("/email/{email}")
    public Response findByEmail(@PathParam("email") String email){
        User response = userService.findUserByEmail(email);
        return Response.ok()
                .entity(response)
                .build();
    }


    @POST
    public Response save(@Valid CreateUserDto createUserDto, @Context UriInfo uriInfo){
        RegisterResponse response = new RegisterResponse("usuario registrado com sucesso!");
        String id = userService.save(createUserDto);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        URI uri = builder.path(id).build();
        return Response.created(uri)
                .entity(response)
                .build();
    }

    @PATCH
    @Path("/{id}")
    public Response update(@Valid @RequestBody UserDto userDto, @PathParam("id") String id){
        userService.update(userDto);
        return Response.noContent()
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id){
        userService.delete(id);
        return Response.noContent()
                .build();
    }


}

package user.controller;

import com.google.inject.Inject;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import response.RegisterResponse;
import user.dto.CreateUserDto;
import user.dto.UserDto;
import user.facade.UserFacade;
import user.model.User;
import user.service.UserService;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/api/v1/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PermitAll
public class UserController {

    public UserController(UserService userService,
                          UserFacade userFacade
    ){
        this.userService = userService;
        this.userFacade = userFacade;
    }

    @Inject
    private UserService userService;

    @Inject
    private UserFacade userFacade;


    @GET
    @Path("/principal")
    @PermitAll
    public String getPrincipal(@Context SecurityContext securityContext) {
        System.out.println(securityContext.getUserPrincipal());
        return "testando principal";
    }

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
    public Response findById(@PathParam("id") final int id){
        User response = userService.findUserById(id);
        return Response.ok()
                .entity(response)
                .build();
    }


    @GET
    @Path("/email/{email}")
    public Response findByEmail(@PathParam("email") final String email){
        User response = userService.findUserByEmail(email);
        return Response.ok()
                .entity(response)
                .build();
    }


    @POST
    public Response save(@Valid final CreateUserDto createUserDto, @Context final UriInfo uriInfo){
        RegisterResponse response = new RegisterResponse("usuario registrado com sucesso!");
        int id = userFacade.saveWithMember(createUserDto);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        URI uri = builder.path(String.valueOf(id)).build();
        return Response.created(uri)
                .entity(response)
                .build();
    }

    @PATCH
    @Path("/{id}")
    @Authenticated
    //@RolesAllowed("ADMIN")
    public Response update(@PathParam("id") final int id, @Valid @RequestBody final UserDto userDto){
        userService.update(userDto);
        return Response.noContent()
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Authenticated
    //@RolesAllowed("ADMIN")
    public Response delete(@PathParam("id") final int id){
        userService.delete(id);
        return Response.noContent()
                .build();
    }

}

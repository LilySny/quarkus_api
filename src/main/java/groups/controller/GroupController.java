package groups.controller;

import com.google.inject.Inject;
import groups.dto.CreateGroupDto;
import groups.dto.GroupDto;
import groups.model.Group;
import groups.service.GroupService;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/api/v1/groups")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PermitAll
public class GroupController {

    public GroupController(GroupService groupService){
        this.groupService = groupService;
    }

    @Inject
    GroupService groupService;

    @GET
    public Response findAll(){
        List<Group> response = groupService.findAll();
        return Response.ok()
                .entity(response)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") int id){
        Group response = groupService.findById(id);
        return Response.ok()
                .entity(response)
                .build();
    }

    @POST
    public Response save(@Valid CreateGroupDto createGroupDto, @Context UriInfo uriInfo){
        int response = groupService.save(createGroupDto);
        String id = String.valueOf(response);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path(id).build();
        return Response.created(uri)
                .entity(response)
                .build();
    }

    @PATCH
    @Path("/{id}")
    public Response update(@RequestBody GroupDto groupDto, @PathParam("id") int id) {
        groupService.update(groupDto);
        return Response.noContent()
                .build();
    }

    @DELETE
    @Path(("/{id}"))
    public Response delete(@PathParam("id") int id){
        groupService.delete(id);
        return Response.noContent()
                .build();
    }

}

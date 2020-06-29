package members.controller;

import com.google.inject.Inject;
import members.dto.CreateMemberDto;
import members.dto.MemberDto;
import members.model.Member;
import members.service.MemberService;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/api/v1/members")
@PermitAll
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MemberController {
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @Inject
    private MemberService memberService;

    @GET
    public Response findAll(){
        List<Member> response = memberService.findAll();
        return Response.ok()
                .entity(response)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") int id){
        Member response = memberService.findById(id);
        return Response.ok()
                .entity(response)
                .build();
    }

    @POST
    public Response save(CreateMemberDto memberDto, UriInfo uriInfo){
        int response = memberService.save(memberDto);
        String id = String.valueOf(response);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        URI uri = builder.path(id).build();
        return Response.created(uri)
                .entity(response)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@RequestBody MemberDto memberDto, @PathParam("id") int id){
        memberService.update(memberDto);
        return Response.noContent().build();

    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id){
        memberService.delete(id);
        return Response.noContent().build();
    }
}




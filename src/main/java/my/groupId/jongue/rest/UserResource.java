package my.groupId.jongue.rest;


import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import my.groupId.jongue.rest.dto.CreateUserRequest;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import my.groupId.jongue.rest.dto.ResponseError;
import my.groupId.quarkustest.domain.user.User;
import my.groupId.quarkustest.repository.UserRepository;


import java.util.Set;


@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class UserResource {

    private UserRepository repository;
    private Validator validator;

    @Inject
    public UserResource(UserRepository repository, Validator validator ){
        this.repository = repository;
        this.validator = validator;
    }

    @POST
    @Transactional
    public Response createUser(CreateUserRequest userRequest ){

        Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(userRequest);
        if(!violations.isEmpty()){

            ResponseError responseError = ResponseError.createFromValidation(violations);

            return Response.status(400).entity(responseError).build();
        }

        User user = new User();
        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());

        repository.persist(user);
        return Response.ok(user).build();
    }
    @GET
    public Response listAllUsers(){
        PanacheQuery<User> query = repository.findAll();
        return Response.ok(query.list()).build();
    }
    
    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteUser(@PathParam("id") Long id){
        User user = repository.findById(id);

        if(user != null){
            repository.delete(user);
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @PUT
    @Transactional
    @Path("{id}")
    public Response updateUser(@PathParam("id") Long id, CreateUserRequest userData ){
        User user = repository.findById(id);
        if(user != null){
            user.setName(userData.getName());
            user.setAge(userData.getAge());
            return Response.ok().build();
        }


        return Response.ok().build();
    }
    
}


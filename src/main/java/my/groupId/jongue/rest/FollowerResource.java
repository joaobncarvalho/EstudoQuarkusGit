package my.groupId.jongue.rest;


import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import my.groupId.jongue.rest.dto.FollowerRequest;
import my.groupId.quarkustest.domain.user.Follower;
import my.groupId.quarkustest.repository.FollowerRepository;
import my.groupId.quarkustest.repository.UserRepository;

@Path("/users/{userId}/followers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FollowerResource {

    private FollowerRepository repository;
    private UserRepository userRepository;

    @Inject
    public FollowerResource(FollowerRepository repository, UserRepository userRepository){

        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Transactional
    @PUT
    public Response followUser(@PathParam("userId") Long userId, FollowerRequest request){
        var user = userRepository.findById(userId);
        if (user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        var follower = userRepository.findById(request.getFollowerId());

        boolean followers = repository.followers(follower, user);

        if(!followers){
            var entity = new Follower();
            entity.setUser(user);
            entity.setFollower(follower);
        }


        var entity = new Follower();
        entity.setUser(user);
        entity.setFollower(follower);

        repository.persist(entity);

        return Response.status(Response.Status.NO_CONTENT).build();

    }
}

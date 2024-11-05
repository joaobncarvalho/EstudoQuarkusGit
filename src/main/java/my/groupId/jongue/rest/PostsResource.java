package my.groupId.jongue.rest;


import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import my.groupId.jongue.rest.dto.CreatePostRequest;
import my.groupId.jongue.rest.dto.PostResponse;
import my.groupId.quarkustest.domain.user.Posts;
import my.groupId.quarkustest.domain.user.User;
import my.groupId.quarkustest.repository.PostRepository;
import my.groupId.quarkustest.repository.UserRepository;
import org.jboss.logging.annotations.Pos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Path("/users/{userId}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostsResource {

    private UserRepository userRepository;
    private PostRepository repository;

    @Inject
    public PostsResource(
            UserRepository userRepository,
            PostRepository repository){
        this.userRepository = userRepository;
        this.repository = repository;
    }
    @POST
    @Transactional
    public Response savePost(
            @PathParam("userId") Long userId
            , CreatePostRequest request){
        User user = userRepository.findById(userId);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Posts post = new Posts();
        post.setText(request.getText());
        post.setUser(user);

        repository.persist(post);

        return Response.status(Response.Status.CREATED).build();

    }

    @GET
    public Response listPost( @PathParam("userId") Long userId ){
        User user = userRepository.findById(userId);
        if (user == null){
            return Response.status(Response.Status.NOT_FOUND).build();

        }

        var query = repository.find("user", Sort.by("dateTime", Sort.Direction.Descending), user);
        var list = query.list();

        var postResponseList = list.stream()
                .map(PostResponse::fromEntity)
                .collect(Collectors.toList());

    return Response.ok(list).build();

    }

}

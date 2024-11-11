package my.groupId.quarkustest.repository;
//comment
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import my.groupId.quarkustest.domain.user.Follower;
import my.groupId.quarkustest.domain.user.User;

import java.util.Optional;

@ApplicationScoped
public class FollowerRepository implements PanacheRepository<Follower> {

    public boolean followers(User follower, User user){

        var params = Parameters.with("follower", follower)
                .and("user", user).map();

        PanacheQuery<Follower> query = find("follower =:follower and user =:user", params);
        Optional<Follower> result = query.firstResultOptional();

        return result.isPresent();

    }

}

package my.groupId.quarkustest.repository;
//comment
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import my.groupId.quarkustest.domain.user.Follower;

@ApplicationScoped
public class FollowerRepository implements PanacheRepository<Follower> {

}

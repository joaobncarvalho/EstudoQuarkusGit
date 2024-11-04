package my.groupId.quarkustest.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import my.groupId.quarkustest.domain.user.User;


@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
}

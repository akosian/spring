package sweater.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import sweater.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

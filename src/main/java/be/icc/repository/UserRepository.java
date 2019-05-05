package be.icc.repository;

import be.icc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Scohier Dorian on 09-12-18.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    User findByEmail(String email);
}

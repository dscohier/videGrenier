package be.icc.repository;

import be.icc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Student on 09-12-18.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}

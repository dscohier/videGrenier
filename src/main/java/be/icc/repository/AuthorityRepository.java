package be.icc.repository;

import be.icc.entity.Authority;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Scohier Dorian on 09-12-18.
 */
public interface AuthorityRepository extends CrudRepository<Authority,Long> {

    Authority findByAuthority(String authority);

}

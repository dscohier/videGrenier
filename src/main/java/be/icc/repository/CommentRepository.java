package be.icc.repository;

import be.icc.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Scohier Dorian on 27-06-19.
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {


}

package be.icc.service.imp;

import be.icc.dto.CommentDto;
import be.icc.repository.CommentRepository;
import be.icc.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Scohier Dorian on 27-06-19.
 */
@Service
@Transactional
public class CommentServiceImp implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public CommentDto add(CommentDto comment) {
        return commentRepository.save(comment.toEntity()).toDto();
    }

}

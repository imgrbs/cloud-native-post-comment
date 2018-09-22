package th.ac.kmutt.sit.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;


    public List<Comment> getAllComment() {
        return this.commentRepository.findAll();
    }

    public Comment save(Comment comment) {
        return this.commentRepository.save(comment);
    }
    public Optional<Comment> findById(long commentId) {
        return this.commentRepository.findById(commentId);
    }

    public void delete(Comment comment) {
        this.commentRepository.delete(comment);
    }

}

package th.ac.kmutt.sit.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPost() {
        return this.postRepository.findAll();
    }

    public Post save(Post post) {
        return this.postRepository.save(post);
    }

    public Optional<Post> findById(long postId) {
        return this.postRepository.findById(postId);
    }

    public void delete(Post post) {
        this.postRepository.delete(post);
    }

    public boolean existsById(long postId) {
        return this.postRepository.existsById(postId);
    }

}

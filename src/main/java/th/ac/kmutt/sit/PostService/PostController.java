package th.ac.kmutt.sit.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import th.ac.kmutt.sit.Exception.ResourceNotFoundException;
import th.ac.kmutt.sit.UserService.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @RequestMapping(
            value = "/posts",
            method = RequestMethod.GET
    )
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = this.postService.getAllPost();
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/post/{id:[\\d]}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Optional<Post> > getPost(@PathVariable("id") long postId) {
        Optional<Post> post = this.postService.findById(postId);
        return new ResponseEntity<Optional<Post> >(post, HttpStatus.OK);
    }


    @PostMapping("/users/{userId}/posts")
    public Post createPost(@PathVariable (value = "userId") Long userId,@Valid @RequestBody Post post) {
        return this.userService.findById(userId).map(user -> {
            post.setUser(user);
            return this.postService.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }


    @PutMapping("/posts/{postId}")
    public Post updatePost(@PathVariable Long postId, @Valid @RequestBody Post postBody) {
        return this.postService.findById(postId).map(post -> {
            post.setTitle(postBody.getTitle());
            post.setDescription(postBody.getDescription());
            return postService.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }


    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        return this.postService.findById(postId).map(post -> {
            this.postService.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }
}
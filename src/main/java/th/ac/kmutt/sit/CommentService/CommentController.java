package th.ac.kmutt.sit.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import th.ac.kmutt.sit.Exception.ResourceNotFoundException;
import th.ac.kmutt.sit.PostService.PostService;
import th.ac.kmutt.sit.UserService.User;
import th.ac.kmutt.sit.UserService.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @RequestMapping(
            value = "/comments",
            method = RequestMethod.GET
    )
    public ResponseEntity<List<Comment>> getComments() {
        List<Comment> comments = this.commentService.getAllComment();
        return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
    }

    @PostMapping("/posts/{postId}/comments")
    public Comment createComment(@PathVariable (value = "postId") long postId, @Valid @RequestBody Comment comment) {
        Optional<User> user = this.userService.findById(1);
        return this.postService.findById(postId).map(post -> {
            comment.setPost(post);
            comment.setUser(user.get());
            return this.commentService.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable (value = "postId") Long postId,
                                 @PathVariable (value = "commentId") Long commentId,
                                 @Valid @RequestBody Comment commentReq) {
        if(!postService.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }

        return commentService.findById(commentId).map(comment -> {
            comment.setComment(commentReq.getComment());
            return commentService.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "postId") Long postId,
                                           @PathVariable (value = "commentId") Long commentId) {
        if(!postService.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }

        return commentService.findById(commentId).map(comment -> {
            commentService.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + " not found"));
    }
}
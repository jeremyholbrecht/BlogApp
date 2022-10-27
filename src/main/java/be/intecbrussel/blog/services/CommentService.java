package be.intecbrussel.blog.services;
import be.intecbrussel.blog.data.Comment;
import be.intecbrussel.blog.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {
    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    //add Comment
    Comment addComment(Comment comment) {
        return this.commentRepository.save(comment);
    }

    //get blogpost under comment
    public List<Comment> getBlogPostComment(long id) {
        List<Comment> comments  = commentRepository.findAllByPost(id);
        return  comments;
    }
    public void deleteComment(Comment comment){
        this.commentRepository.delete(comment);
    }
    public Comment editComment(Comment comment) {
        /* if we want to know the change
         if(!comment.getModified()) {
            comment.setModified(true);
        }
        */
        return this.commentRepository.save(comment);
    }
    public Comment getComment(long id) {
        return this.commentRepository.findById(id);
    }

}


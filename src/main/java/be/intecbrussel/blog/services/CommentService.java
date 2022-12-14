package be.intecbrussel.blog.services;
import be.intecbrussel.blog.data.BlogPost;
import be.intecbrussel.blog.data.Comment;
import be.intecbrussel.blog.repositories.BlogPostRepository;
import be.intecbrussel.blog.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private BlogPostRepository blogPostRepository;

    @Autowired

    public CommentService(CommentRepository commentRepository, BlogPostRepository blogPostRepository) {
        this.commentRepository = commentRepository;
        this.blogPostRepository = blogPostRepository;
    }

    //add Comment
     public Comment addComment(Comment comment) {
        return this.commentRepository.save(comment);
    }

    public Comment getCommentById(long id){
        Comment commentO = new Comment();
        Optional  comment  = commentRepository.findById(commentO.getId());
        if(comment.isPresent()){
            return commentO;
        }
        return null;
    }

    //get blogpost under comment
    public List<Comment> getCommentsForBlogPost(Long id) {
        List<Comment> comments = new ArrayList<>();
        Optional<BlogPost> blogPost=blogPostRepository.findById(id);
        commentRepository.findAllByPost(blogPost)
                .forEach(comments::add);
        return comments;
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
    /*public Comment getComment(long id) {
        return this.commentRepository.findById(id);
    }
    */
    /*
    public void createComment(String postURL, Comment comment) {
      BlogPostRepository blogPostRepository1 = (BlogPostRepository) blogPostRepository.findBlogPostByTitle(postURL).get();
      //still can't why to string converting needed
      comment.setComment(blogPostRepository1.toString());
      commentRepository.save(comment);
    }
   */

}
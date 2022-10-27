package be.intecbrussel.blog.services;
import be.intecbrussel.blog.data.BlogPost;
import be.intecbrussel.blog.data.Comment;
import be.intecbrussel.blog.repositories.BlogPostRepository;
import be.intecbrussel.blog.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private BlogPostRepository blogPostRepository;
    private BlogPostService BlogPostService;

    public CommentService(CommentRepository commentRepository, BlogPostRepository blogPostRepository, be.intecbrussel.blog.services.BlogPostService blogPostService) {
        this.commentRepository = commentRepository;
        this.blogPostRepository = blogPostRepository;
        BlogPostService = blogPostService;
    }

    public CommentService(CommentRepository commentRepository, BlogPostRepository blogPostRepository) {
        this.commentRepository = commentRepository;
        this.blogPostRepository = blogPostRepository;
    }

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    //add Comment
    public Comment addComment(Comment comment) {
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



    /*
    public void createComment(String postURL, Comment comment) {
      BlogPostRepository blogPostRepository1 = (BlogPostRepository) blogPostRepository.findBlogPostByTitle(postURL).get();
      //still can't why to string converting needed
      comment.setComment(blogPostRepository1.toString());
      commentRepository.save(comment);
    }

*/
}
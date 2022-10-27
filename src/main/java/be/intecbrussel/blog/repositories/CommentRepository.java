package be.intecbrussel.blog.repositories;

import be.intecbrussel.blog.data.BlogPost;
import be.intecbrussel.blog.data.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
   List<Comment> findAllByPost(Optional<BlogPost> blogPost);
   Optional findById(long id);
   void delete(Comment comment);
   Comment save(Comment comment);
}

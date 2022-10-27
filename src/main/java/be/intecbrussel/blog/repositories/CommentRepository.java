package be.intecbrussel.blog.repositories;

import be.intecbrussel.blog.data.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
   List<Comment> findAllByPost(long blogPostId);
   Optional findById(long id);
   void delete(Comment commernt);
   Comment save(Comment comment);
}

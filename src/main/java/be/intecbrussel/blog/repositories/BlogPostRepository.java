package be.intecbrussel.blog.repositories;

import be.intecbrussel.blog.data.BlogPost;
import be.intecbrussel.blog.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    List<BlogPost> findByUser(long userId);
    Optional<BlogPost> findBlogPostByTitle(String title);

}

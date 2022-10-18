package be.intecbrussel.blog.repositories;

import be.intecbrussel.blog.data.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, User> {
    List<BlogPost> findAllByUser(User user);

}

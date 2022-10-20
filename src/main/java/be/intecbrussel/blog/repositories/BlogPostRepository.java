package be.intecbrussel.blog.repositories;

import be.intecbrussel.blog.data.BlogPost;
import be.intecbrussel.blog.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    List<BlogPost> findByUser(long userId);
    List<BlogPost> findByUserName(String userName);

}

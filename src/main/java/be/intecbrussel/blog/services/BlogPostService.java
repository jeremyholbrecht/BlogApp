package be.intecbrussel.blog.services;

import be.intecbrussel.blog.data.BlogPost;
import be.intecbrussel.blog.data.User;
import be.intecbrussel.blog.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {
    private BlogPostRepository blogPostRepository;

    @Autowired
    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public BlogPost createBlogPost(BlogPost blogPost){
        return blogPostRepository.save(blogPost);
    }

    public List<BlogPost> getAllBlogPostsByAuthor(User user){
        List<BlogPost> blogPosts = new ArrayList<>();
        blogPostRepository.findByUser(user.getId())
                .forEach(blogPosts::add);
        return blogPosts;
    }

    public BlogPost getOneById(long id){
        BlogPost bp = new BlogPost();
        Optional blogpost = blogPostRepository.findById(bp.getId());
          if(blogpost.isPresent()){
              return bp;
            }return null;
    }

    public BlogPost getOneByTitle(String title){
        Optional blogPost = blogPostRepository.findBlogPostByTitle(title);
        if(blogPost.isPresent()){
            return (BlogPost) blogPost.get();
        }
        return null;
    }

    public void deleteBlogPost(BlogPost blogPost){
        blogPostRepository.delete(blogPost);
    }

    public void editBlogPost(BlogPost blogPost){
        blogPostRepository.save(blogPost);

    }




}

package be.intecbrussel.blog.services;

import be.intecbrussel.blog.data.BlogPost;
import be.intecbrussel.blog.data.User;
import be.intecbrussel.blog.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class BlogPostService {
    private BlogPostRepository blogPostRepository;

    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public BlogPost createBlogPost(BlogPost blogPost){
        return blogPostRepository.save(blogPost);
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

    public List<BlogPost> getAllBlogPosts(){
        return blogPostRepository.findAll();
    }

    public List<BlogPost> getAllBlogPostsByAuthor(User user){
        List<BlogPost> blogPosts = new ArrayList<>();
        blogPostRepository.findByUser(user.getId())
                .forEach(blogPosts::add);
        return blogPosts;
    }

    public List<BlogPost> getAllByNewest(List<BlogPost> blogPosts){
        blogPosts = blogPostRepository.findAll();
        blogPosts.sort(Comparator.comparing(BlogPost::getTimeOfPost).reversed());
        return blogPosts;
    }

    public List<BlogPost> getAllByOldest(List<BlogPost> blogPosts){
        blogPosts = blogPostRepository.findAll();
        blogPosts.sort(Comparator.comparing(BlogPost::getTimeOfPost));
        return blogPosts;
    }
//// TODO: Not sure how to test this one OR link it to front end.
// TODO: Servlet created in BlogPost Class to count views, but not sure if this is correct.
    public List<BlogPost> getAllByPopular(List<BlogPost> blogPosts){
        blogPosts = blogPostRepository.findAll();
        Collections.sort(blogPosts, new Comparator<BlogPost>() {
            @Override
            public int compare(BlogPost o1, BlogPost o2) {
                return Integer.valueOf(o2.getHitCounter()).compareTo(o1.getHitCounter());
            }
        });
          return blogPosts;
        //listMostPopular.sort(Comparator.comparingInt(BlogPost.getHitCounter()));
    }

    public List<BlogPost> getAllByAuthorByNewest(User user){
        List<BlogPost> blogPosts = new ArrayList<>();
        blogPostRepository.findByUser(user.getId())
                .forEach(blogPosts::add);
        blogPosts.sort(Comparator.comparing(BlogPost::getTimeOfPost).reversed());
        return blogPosts;
    }

    public List<BlogPost> getAllByAuthorByOldest(User user){
        List<BlogPost> blogPosts = new ArrayList<>();
        blogPostRepository.findByUser(user.getId())
                .forEach(blogPosts::add);
        blogPosts.sort(Comparator.comparing(BlogPost::getTimeOfPost));
        return blogPosts;
    }

    //TODO: see above re: getAllByPopular
    public List<BlogPost> getAllByUserByPopular(User user){
        List<BlogPost> blogPosts = new ArrayList<>();
        blogPostRepository.findByUser(user.getId())
                .forEach(blogPosts::add);
        Collections.sort(blogPosts, new Comparator<BlogPost>() {
            @Override
            public int compare(BlogPost o1, BlogPost o2) {
                return Integer.valueOf(o2.getHitCounter()).compareTo(o1.getHitCounter());
            }
        });
        return blogPosts;
    }




}

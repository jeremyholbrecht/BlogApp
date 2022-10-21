package ServiceTests;

import be.intecbrussel.blog.data.BlogPost;
import be.intecbrussel.blog.data.User;
import be.intecbrussel.blog.repositories.BlogPostRepository;
import be.intecbrussel.blog.services.BlogPostService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class BlogPostServiceTest {
    private static BlogPost blogpost1;
    private static BlogPost blogpost2;
    private static BlogPost blogpost3;
    private static BlogPost blogPost4;
    private static User author1;
    private static User author2;

    @Mock
    private BlogPostRepository blogPostRepository;
    @InjectMocks
    private BlogPostService blogPostService = new BlogPostService(blogPostRepository);

    @BeforeClass
    public static void createTestBlogPosts(){
        author1 = new User();
        author2 = new User();
        blogpost1 = new BlogPost(1, "abcdefg", author1);
        blogpost2 = new BlogPost(2, "hijk", author2);
        blogpost3 = new BlogPost(3, "lmnop", author1);
        blogPost4 = new BlogPost(4, "qrstu", author2);
        author1.setUserName("Sam");
        author1.setId(Long.valueOf(3));
        author2.setUserName("Iam");
        author2.setId(Long.valueOf(2));

    }

    @Test
    public void testIfYouCanGetAllPostsByAuthor(){
        List<BlogPost> allBlogPostsByAuthor1 = new ArrayList<>();
        allBlogPostsByAuthor1.add(blogpost1);
        allBlogPostsByAuthor1.add(blogpost3);
        when(blogPostRepository.findByUser(author1.getId())).thenReturn(allBlogPostsByAuthor1);
        List<BlogPost> foundAllBlogsByAuthor1 = blogPostService.getAllBlogPostsByAuthor(author1);
        Assert.assertTrue(foundAllBlogsByAuthor1.contains(blogpost1));
        Assert.assertTrue(foundAllBlogsByAuthor1.contains(blogpost3));
        Assert.assertFalse(foundAllBlogsByAuthor1.contains(blogpost2));
        Assert.assertFalse(foundAllBlogsByAuthor1.contains(blogPost4));
        Assert.assertEquals(2, foundAllBlogsByAuthor1.size());
    }
    @Test
    public void testIfYouCanGetOneByTitle(){
        String title = blogpost1.getTitle();
        String title2 = blogpost2.getTitle();
        when(blogPostRepository.findBlogPostByTitle(title)).thenReturn(Optional.ofNullable(blogpost1));
        Assert.assertEquals(blogpost1, blogPostService.getOneByTitle(title));
        Assert.assertNotEquals(blogpost2, blogPostService.getOneByTitle(title));
}
    @Test
    public void testIfSortableByNewest(){
        blogpost1.setTimeOfPost(LocalDateTime.of(2022, Month.JANUARY, 5, 13, 45));
        blogpost2.setTimeOfPost(LocalDateTime.of(2021, Month.JANUARY, 23, 4, 55));
        blogpost3.setTimeOfPost(LocalDateTime.of(2022, Month.JUNE, 17, 12, 14));
        blogPost4.setTimeOfPost(LocalDateTime.of(2021, Month.JANUARY, 23, 5, 05));
        List<BlogPost> allBlogPosts = new ArrayList<>();
        allBlogPosts.add(blogpost1);
        allBlogPosts.add(blogpost2);
        allBlogPosts.add(blogpost3);
        allBlogPosts.add(blogPost4);
        when(blogPostRepository.findAll()).thenReturn(allBlogPosts);
        List<BlogPost> sortedByNew = blogPostService.getAllByNewest();
        Assert.assertEquals(0, sortedByNew.indexOf(blogpost3));
        Assert.assertEquals(1, sortedByNew.indexOf(blogpost1));
        Assert.assertEquals(2, sortedByNew.indexOf(blogPost4));
        Assert.assertEquals(3, sortedByNew.indexOf(blogpost2));
    }

    @Test
    public void testIfSortableByOldest(){
        blogpost1.setTimeOfPost(LocalDateTime.of(2022, Month.JANUARY, 5, 13, 45));
        blogpost2.setTimeOfPost(LocalDateTime.of(2021, Month.JANUARY, 23, 4, 55));
        blogpost3.setTimeOfPost(LocalDateTime.of(2022, Month.JUNE, 17, 12, 14));
        blogPost4.setTimeOfPost(LocalDateTime.of(2021, Month.JANUARY, 23, 5, 05));
        List<BlogPost> allBlogPosts = new ArrayList<>();
        allBlogPosts.add(blogpost1);
        allBlogPosts.add(blogpost2);
        allBlogPosts.add(blogpost3);
        allBlogPosts.add(blogPost4);
        when(blogPostRepository.findAll()).thenReturn(allBlogPosts);
        List<BlogPost> sortedByOld = blogPostService.getAllByOldest(allBlogPosts);
        Assert.assertEquals(3, sortedByOld.indexOf(blogpost3));
        Assert.assertEquals(2, sortedByOld.indexOf(blogpost1));
        Assert.assertEquals(1, sortedByOld.indexOf(blogPost4));
        Assert.assertEquals(0, sortedByOld.indexOf(blogpost2));
    }

    @Test
    public void testIfSortableByNewestByAuthor(){
        author1.setId(Long.valueOf(1));
        author2.setId(Long.valueOf(2));
        blogpost1.setTimeOfPost(LocalDateTime.of(2022, Month.JANUARY, 5, 13, 45));
        blogpost2.setTimeOfPost(LocalDateTime.of(2021, Month.JANUARY, 23, 4, 55));
        blogpost3.setTimeOfPost(LocalDateTime.of(2022, Month.JUNE, 17, 12, 14));
        blogPost4.setTimeOfPost(LocalDateTime.of(2021, Month.JANUARY, 23, 5, 05));
        List<BlogPost> author1blogposts = new ArrayList<>();
        List<BlogPost> author2blogposts = new ArrayList<>();
        author1blogposts.add(blogpost1);
        author2blogposts.add(blogpost2);
        author1blogposts.add(blogpost3);
        author2blogposts.add(blogPost4);
//        when(blogPostRepository.findByUser(author1.getId())).thenReturn(allBlogPostsByAuthor1);
//        List<BlogPost> foundAllBlogsByAuthor1 = blogPostService.getAllBlogPostsByAuthor(author1);
        when(blogPostRepository.findByUser(author1.getId())).thenReturn(author1blogposts);
        when(blogPostRepository.findByUser(author2.getId())).thenReturn(author2blogposts);
        List<BlogPost> author1sortedByNew = blogPostService.getAllByAuthorByNewest(author1);
        List<BlogPost> author2sortedByNew = blogPostService.getAllByAuthorByNewest(author2);
        Assert.assertEquals(2, author1sortedByNew.size());
        Assert.assertEquals(0, author1sortedByNew.indexOf(blogpost3));
        Assert.assertEquals(2, author2sortedByNew.size());
        Assert.assertEquals(1, author2sortedByNew.indexOf(blogpost2));
    }

    @Test
    public void testIfSortableByOldestByAuthor(){
        author1.setId(Long.valueOf(1));
        author2.setId(Long.valueOf(2));
        blogpost1.setTimeOfPost(LocalDateTime.of(2022, Month.JANUARY, 5, 13, 45));
        blogpost2.setTimeOfPost(LocalDateTime.of(2021, Month.JANUARY, 23, 4, 55));
        blogpost3.setTimeOfPost(LocalDateTime.of(2022, Month.JUNE, 17, 12, 14));
        blogPost4.setTimeOfPost(LocalDateTime.of(2021, Month.JANUARY, 23, 5, 05));
        List<BlogPost> author1blogposts = new ArrayList<>();
        List<BlogPost> author2blogposts = new ArrayList<>();
        author1blogposts.add(blogpost1);
        author2blogposts.add(blogpost2);
        author1blogposts.add(blogpost3);
        author2blogposts.add(blogPost4);
//        when(blogPostRepository.findByUser(author1.getId())).thenReturn(allBlogPostsByAuthor1);
//        List<BlogPost> foundAllBlogsByAuthor1 = blogPostService.getAllBlogPostsByAuthor(author1);
        when(blogPostRepository.findByUser(author1.getId())).thenReturn(author1blogposts);
        when(blogPostRepository.findByUser(author2.getId())).thenReturn(author2blogposts);
        List<BlogPost> author1byoldest = blogPostService.getAllByAuthorByOldest(author1);
        List<BlogPost> author2byoldest = blogPostService.getAllByAuthorByOldest(author2);
        Assert.assertEquals(2, author1byoldest.size());
        Assert.assertEquals(1, author1byoldest.indexOf(blogpost3));
        Assert.assertEquals(2, author1byoldest.size());
        Assert.assertEquals(0, author2byoldest.indexOf(blogpost2));
    }
}

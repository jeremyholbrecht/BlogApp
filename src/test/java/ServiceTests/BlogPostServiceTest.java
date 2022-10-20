package ServiceTests;

import be.intecbrussel.blog.data.BlogPost;
import be.intecbrussel.blog.data.User;
import be.intecbrussel.blog.repositories.BlogPostRepository;
import be.intecbrussel.blog.services.BlogPostService;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

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
        blogpost1 = new BlogPost("abcdefg", author1, LocalDateTime.of(2021, Month.JANUARY, 5, 12, 00));
        blogpost2 = new BlogPost("hijk", author2, LocalDateTime.of(2021, Month.JANUARY, 6, 12, 00));
        blogpost3 = new BlogPost("lmnop", author1, LocalDateTime.of(2021, Month.JANUARY, 7, 12, 00));
        blogPost4 = new BlogPost("qrstu", author2, LocalDateTime.of(2021, Month.JANUARY, 8, 12, 00));
        author1.setUserName("Sam");
        author2.setUserName("Iam");
    }

//    public void testIfYouCanGetAllPostsByAuthor(){
//        List<BlogPost> blogPosts
//    }
}

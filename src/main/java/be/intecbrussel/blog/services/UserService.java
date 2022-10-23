package be.intecbrussel.blog.services;

import be.intecbrussel.blog.data.BlogPost;
import be.intecbrussel.blog.data.Comment;
import be.intecbrussel.blog.data.User;
import be.intecbrussel.blog.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public User createUser(User user) {
        List<BlogPost> posts = new ArrayList<>();
        if(user.isAuthor()) {
            user.setPosts(posts);
            return userRepository.save(user);
        }
        return userRepository.save(user);
    }



    public User getCurrentUser(String username) {
        Optional user = userRepository.findUserByUserName(username);
        if (user.isPresent()) {
            return (User) user.get();
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}


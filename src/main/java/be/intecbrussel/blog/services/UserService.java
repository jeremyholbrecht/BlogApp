package be.intecbrussel.blog.services;

import be.intecbrussel.blog.data.User;
import be.intecbrussel.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public User createUser(User user) {
        if(user.isPasswordsEqual()) {
            return userRepository.save(user);
        } else {
            throw new RuntimeException("Different password entered!");

        }
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


    public void update(User updateUser) {
        userRepository.findById(updateUser.getId())
                .ifPresent(user1 -> {
                    user1.setName(updateUser.getName());
                    user1.setLastName(updateUser.getLastName());
                    user1.setUserName(updateUser.getUserName());
                    user1.setEmail(updateUser.getEmail());
                    user1.setStreet(updateUser.getStreet());
                    user1.setHouseN(updateUser.getHouseN());
                    user1.setCity(updateUser.getCity());
                    user1.setZip(updateUser.getZip());
                    user1.setPassword(updateUser.getPassword());
                    user1.setRePassword(updateUser.getRePassword());

                    userRepository.save(user1);
        });


    }

    public User autenticate(String userName, String password) {
        return userRepository.findByUserNameAndPassword(userName, password).orElse(null);
    }



    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }


    public User getUserbyId(Long userId) {
        User user = userRepository.findById(userId).get();
        return user;
    }
}


package be.intecbrussel.blog.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
 import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
 import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
   @NotBlank(message = "Name should not be empty")
   @Size(min = 2, max = 30, message = "Name should be entre 2 and 30 characters")
    private String name;
    @Column(unique = true, updatable = false)
   @NotEmpty(message = "Username should not be empty")
    @Size(min = 2, max = 30, message = "Username should be entre 2 and 30 characters")
    private String userName;
    @Column(nullable = false)
    @NotEmpty(message = "Lastname should not be empty")
    @Size(min = 2, max = 30, message = "Lastname should be entre 2 and 30 characters")
    private String lastName;
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "email should not be empty")
    @Email(message = "email should be valid")
    private String email;
    @Column(nullable = false, length = 2500)
    @NotEmpty(message = "password should not be empty")
    private String password;
    @Column(nullable = false, length = 2500)
    @NotEmpty(message = "Repassword should not be empty")
    private String rePassword;
    private String street;
    private Integer houseN;
    private String city;
    private String zip;
    private boolean isAuthor;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<BlogPost> posts = new ArrayList<>();
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @PrePersist
    protected void oneCreate() {
        this.createdDate = LocalDateTime.now();
    }

    public User() {

    }

    public User(String name, String userName, String lastName, String email,
                String password, String rePassword, String street, Integer houseN,
                String city, String zip, boolean isAuthor, List<BlogPost> posts,
                LocalDateTime createdDate) {
        this.name = name;
        this.userName = userName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.rePassword = rePassword;
        this.street = street;
        this.houseN = houseN;
        this.city = city;
        this.zip = zip;
        this.isAuthor = isAuthor;
        this.posts = posts;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouseN() {
        return houseN;
    }

    public void setHouseN(Integer houseN) {
        this.houseN = houseN;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public boolean isAuthor() {
        return isAuthor;
    }

    public void setAuthor(boolean author) {
        isAuthor = author;
    }

    public List<BlogPost> getPosts() {
        return posts;
    }

    public void setPosts(List<BlogPost> posts) {
        this.posts = posts;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    @Override
    public String toString() {
        return "User{"+
                "id="+id+
                ", name='"+name+'\''+
                ", userName='"+userName+'\''+
                ", lastName='"+lastName+'\''+
                ", email='"+email+'\''+
                ", password='"+password+'\''+
                ", rePassword='"+rePassword+'\''+
                ", street='"+street+'\''+
                ", houseN="+houseN+
                ", city='"+city+'\''+
                ", zip='"+zip+'\''+
                ", isAuthor="+isAuthor+
                ", posts="+posts+
                ", createdDate="+createdDate+
                '}';
    }
}
















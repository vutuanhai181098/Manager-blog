package com.example.managerblog;

import com.example.managerblog.entities.*;
import com.example.managerblog.repositories.*;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@SpringBootTest
class ManagerBlogApplicationTests {
@Autowired
    private  CategoryRepository categoryRepository;
    @Autowired
    private  RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    void save_roles() {
        List<Role> roles = List.of(
                new Role("ADMIN"),
                new Role("USER"),
                new Role("AUTHOR")
        );
        roleRepository.saveAll(roles);
    }

    @Test
    void save_users() {
        Faker faker = new Faker();
        Role userRole = roleRepository.findByName("USER").orElse(null);
        Role adminRole = roleRepository.findByName("ADMIN").orElse(null);
        Role authorRole = roleRepository.findByName("AUTHOR").orElse(null);

        List<User> users = List.of(
                new User(faker.name().lastName(), faker.internet().emailAddress(), faker.avatar().image(), faker.internet().password(), List.of(adminRole)),
                new User(faker.name().lastName(), faker.internet().emailAddress(), faker.avatar().image(), faker.internet().password(), List.of(authorRole, userRole)),
                new User(faker.name().lastName(), faker.internet().emailAddress(), faker.avatar().image(), faker.internet().password(), List.of(authorRole, userRole)),
                new User(faker.name().lastName(), faker.internet().emailAddress(), faker.avatar().image(), faker.internet().password(), List.of(authorRole, userRole)),
                new User(faker.name().lastName(), faker.internet().emailAddress(), faker.avatar().image(), faker.internet().password(), List.of(authorRole, userRole))
        );
        userRepository.saveAll(users);

        for (int i = 0; i < 20; i++) {
            User user = new User(
                    faker.name().lastName(),
                    faker.internet().emailAddress(),
                    faker.avatar().image(),
                    faker.internet().password(),
                    List.of(userRole));
            userRepository.save(user);
        }
    }

    @Test
    void save_categories() {
        List<Category> categories = List.of(
                new Category("Backend", new ArrayList<>()),
                new Category("Frontend", new ArrayList<>()),
                new Category("Devops", new ArrayList<>()),
                new Category("Database", new ArrayList<>()),
                new Category("Mobile", new ArrayList<>()),
                new Category("Javascript", new ArrayList<>()),
                new Category("Java", new ArrayList<>()),
                new Category("Game", new ArrayList<>())
        );
        categoryRepository.saveAll(categories);
    }

    @Test
    void save_blogs() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        Random rd = new Random();

        List<User> userList = userRepository.findByRoles_NameIn(List.of("ADMIN", "AUTHOR"));
        List<Category> categoryList = categoryRepository.findAll();

        for (int i = 1; i <= 25; i++) {
            User rdUser = userList.get(rd.nextInt(userList.size()));

            List<Category> rdList = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Category rdCategory = categoryList.get(rd.nextInt(categoryList.size()));
                if(!rdList.contains(rdCategory)) {
                    rdList.add(rdCategory);
                }
            }

            Blog blog = Blog.builder()
                    .title("Blog " + (i + 25))
                    .slug(slugify.slugify("Blog " + (i + 25)))
                    .description("description " + (i + 25))
                    .content(faker.lorem().paragraph())
                    .status(rd.nextInt(2) == 0)
                    .user(rdUser)
                    .categories(rdList)
                    .build();

            blogRepository.save(blog);
        }
    }

    @Test
    void save_blog() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        Random rd = new Random();

        List<User> userList = userRepository.findByRoles_NameIn(List.of("ADMIN", "AUTHOR"));
        List<Category> categoryList = categoryRepository.findAll();
        User rdUser = userList.get(rd.nextInt(userList.size()));

        List<Category> rdList = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            Category rdCategory = categoryList.get(rd.nextInt(categoryList.size()));
            if(!rdList.contains(rdCategory)) {
                rdList.add(rdCategory);
            }
        }

        Blog blog = Blog.builder()
                .title("New Blog")
                .slug(slugify.slugify("New Blog"))
                .description("New description")
                .content(faker.lorem().sentence())
                .status(true)
                .user(rdUser)
                .categories(rdList)
                .build();

        blogRepository.save(blog);
    }

    @Test
    void save_comments() {
        Random rd = new Random();
        List<User> userList = userRepository.findAll();
        List<Blog> blogList = blogRepository.findAll();
        List<String> contentList = List.of("Quá tuyệt vời", "Rất bổ ích", "Nice bro!");
        for (int i = 0; i < 100; i++) {
            User rdUser = userList.get(rd.nextInt(userList.size()));
            Blog rdBlog = blogList.get(rd.nextInt(blogList.size()));
            Comment comment = Comment.builder()
                    .content(contentList.get(rd.nextInt(contentList.size())))
                    .user(rdUser)
                    .blog(rdBlog)
                    .build();
            commentRepository.save(comment);
        }
    }

    @Test
    void delete_blog(){
        Blog blog = blogRepository.findById(Long.valueOf(27)).orElse(null);
        assert blog != null;
        blog.getCategories().forEach(System.out::println);
        blog.getCategories().forEach(category -> {
            category.getBlogs().remove(blog);
        });
        blog.setCategories(null);
        blogRepository.delete(blog);
    }
}

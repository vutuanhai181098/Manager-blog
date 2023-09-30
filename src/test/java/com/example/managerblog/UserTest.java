package com.example.managerblog;

import com.example.managerblog.entities.User;
import com.example.managerblog.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void set_avatar(){
        userRepository.updateAvatarById(26L, "https://i0.wp.com/thatnhucuocsong.com.vn/wp-content/uploads/2023/02/Hinh-anh-avatar-Facebook.jpg?ssl=1");
        User user = userRepository.findById(26L).orElse(null);
        System.out.println(user.getAvatar());
    }
}

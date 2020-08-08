package com.example.demoApp;

import com.example.demoApp.models.Community;
import com.example.demoApp.models.User;
import com.example.demoApp.repository.CommunityRepository;
import com.example.demoApp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(UserRepository userRepository, CommunityRepository communityRepository) {
        return (args -> {

            Community community1 = new Community("community1");
            communityRepository.save(community1);

            User admin = new User("admin", "admin123", community1);
            admin.setSuperuser(true);
            userRepository.save(admin);

//			User guest = new User("test", "admin123");
//			userRepository.save(guest);

        });

    }

}

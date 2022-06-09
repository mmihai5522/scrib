package com.scrib.scrib.user;

import com.scrib.scrib.userRole.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class UserConfig {



    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){

        return args -> {
                User andrei= new User(
                        "Potec",
                        "Andrei",
                        LocalDate.of(1993, Month.SEPTEMBER,13),
                        "poteca@yahoo.com",
                        "freelancer developer since 1990, after that stacker",
                        List.of()
                );

                User attila= new User(
                        "Tuzson",
                        "Attila",
                        LocalDate.of(1970, Month.MAY,25),
                        "poteca@yahoo.com",
                        "freelancer developer since 1990, after that stacker",
                        List.of()
                );

                repository.saveAll(
                    List.of(andrei,attila)
            );
        };
    }
}

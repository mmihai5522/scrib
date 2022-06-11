package com.scrib.scrib.user;

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
                        "fgdfgdfg",
                        "dfgdfgdfg",
                        LocalDate.of(1993, Month.SEPTEMBER,13),
                        "sdfsdfsdfsdf@yahoo.com",
                        "lorem ipsum.............",
                        List.of()
                );

                User atesz= new User(
                        "Tuzson",
                        "Attila",
                        LocalDate.of(1970, Month.MAY,25),
                        "jiouty@yahoo.com",
                        "since 1990....",
                        List.of()
                );

                repository.saveAll(
                    List.of(andrei,atesz)
            );
        };
    }
}

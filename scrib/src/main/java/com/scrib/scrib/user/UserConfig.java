//package com.scrib.scrib.user;
//
//import com.scrib.scrib.userRole.UserRole;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.List;
//
//@Configuration
//public class UserConfig {
//
//
//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository repository){
//
//        UserRole userRole=new UserRole(1L);
//        return args -> {
//                User andrei= new User(
//                        "fgdfgdfg2",
//                        "dfgdfgdfg2",
//                        LocalDate.of(1993, Month.SEPTEMBER,13),
//                        "sdfsdfsdfsdf@yahoo.com2",
//                        "lorem ipsum.............2",
//                        List.of()
//                );
//
//                User atesz= new User(
//                        "Tuzson2",
//                        "Attila2",
//                        LocalDate.of(1970, Month.MAY,25),
//                        "jiouty1@yahoo.com2",
//                        "since 1990....2",
//                        List.of()
//                );
//
//                repository.saveAll(
//                    List.of(andrei,atesz)
//            );
//        };
//    }
//}

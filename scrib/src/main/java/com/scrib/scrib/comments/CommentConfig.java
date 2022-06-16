//package com.scrib.scrib.comments;
//
//import com.scrib.scrib.article.Article;
//import com.scrib.scrib.user.User;
//import com.scrib.scrib.userRole.UserRole;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.*;
//import java.util.List;
//
//@Configuration
//public class CommentConfig {
//
//
//
//    @Bean
//    CommandLineRunner commandLineRunner(CommentRepository commentRepository){
//
//        return args -> {
//
//            UserRole userRole=new UserRole(1L);
//
//            User admin=new User(
//                    "alibaba9",
//                    "alibaba9",
//                    LocalDate.now(),
//                    "saaaa9@yahoo.com",
//                    "bla bla9",
//                    List.of(userRole)
//            );
//
//            Article theArticle=new Article("Proba 9!"
//                    ,"Memento mori (Latin for 'remember that you [have to] die'[2])."
//                    ,LocalDate.now()
//                    ,List.of("https://www.youtube.com/"));
//
//                Comment opinion=new Comment(
//                    "foarte tare9"
//                        , LocalDate.now()
//                        , admin
//                        ,theArticle);
//
//            commentRepository.save(opinion);
//        };
//    }
//
//}

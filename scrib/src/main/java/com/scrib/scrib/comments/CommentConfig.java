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
//        UserRole userRole=new UserRole(1L);
//
//        User Adminul=new User(
//                "alibaba6",
//                "alibaba6",
//                LocalDate.of(1998, Month.SEPTEMBER,13),
//                "saaaa6@yahoo.com",
//                "bla bla5",
//                List.of(userRole)
//        );
//
//        Article theArticle=new Article("Proba 6!"
//                ,"Memento mori (Latin for 'remember that you [have to] die'[2])."
//                ,LocalDate.now()
//                ,List.of("https://www.youtube.com/"));
//
//        return args -> {
//
//                Comment opinion=new Comment(
//                    "foarte tare6"
//                        , LocalDate.now()
//                        , Adminul
//                        ,theArticle);
//
//            commentRepository.save(opinion);
//        };
//    }
//
//}

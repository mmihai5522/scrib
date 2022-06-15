//package com.scrib.scrib.article;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.time.Month;
//import java.time.Year;
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class ArticleConfig {
//
//    @Bean
//    CommandLineRunner commandLineRunner(ArticleRepository articleRepository){
//        return args -> {
//            Article momentoMori=new Article(
//                        "Momento Mori!2"
//                    ,"Memento mori (Latin for 'remember that you [have to] die'[2]) is an artistic or symbolic trope acting a...2"
//                    , LocalDate.of(2021, Month.JULY,23)
//                    ,List.of("https://en.wikipedia.org/wiki/Memento_mori")
//            );
//            articleRepository.save(momentoMori);
//
//        };
//    }
//}
//
//

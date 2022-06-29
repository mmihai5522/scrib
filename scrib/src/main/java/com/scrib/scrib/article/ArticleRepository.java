//package com.scrib.scrib.article;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface ArticleRepository
//        extends JpaRepository<Article,Long> {
//
//
//       @Query("SELECT a FROM Article a WHERE a.title=?1")
//       Optional<Article>  findByTitle(String title);
//
//}

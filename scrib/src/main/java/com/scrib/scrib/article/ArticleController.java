//package com.scrib.scrib.article;
//
//import com.scrib.scrib.exception.ApiRequestException;
////import com.scrib.scrib.user.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(path = "api/v1/article")
//public class ArticleController {
//
//    private final ArticleService articleService;
//
//    @Autowired
//    public ArticleController(ArticleService articleService) {
//        this.articleService = articleService;
//    }
//
//    @GetMapping
//    public List<Article> getArticles() {
//
//        List<Article> allArticles=articleService.getArticle();;
//
//        if (allArticles.isEmpty()) {
//            throw new ApiRequestException("No articles registred/ fetched!");
//        }
//        return allArticles;
//    }
//
//    @PostMapping(path = "registration")
//    public void registerNewArticle(
//            @RequestBody Article article){
//        articleService.addNewArticle(article);
//    }
//
//    @DeleteMapping(path = "{articleId}")
//    public void deleteArticleById(@PathVariable("articleId") Long articleId){
//        articleService.deleteArticle(articleId);
//    }
//
//    @PutMapping(path = "{articleId}")
//    public void updateArticleInfo(
//            @PathVariable("articleId") Long articleId
//            ,@RequestParam(required= false) String title
//            ,@RequestParam(required = false) List bibliography){
//
//        articleService.modifyArticleInformation(articleId,title,bibliography);
//    }
//}

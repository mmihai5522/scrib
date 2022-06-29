//package com.scrib.scrib.article;
//
////import com.scrib.scrib.user.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//@Service
//public class ArticleService {
//
//    private final ArticleRepository articleRepository;
//
//    @Autowired
//    public ArticleService(ArticleRepository articleRepository) {
//        this.articleRepository = articleRepository;
//    }
//
//
//    public List<Article> getArticle() {
//        return articleRepository.findAll();
//    }
//
//    public void addNewArticle(Article article) {
//        articleRepository.save(article);
//    }
//
//    public void deleteArticle(Long articleId) {
//        boolean isPresent= articleRepository.existsById(articleId);
//
//        if (!isPresent){
//            throw new IllegalStateException("User with id: " + articleId + " does not exists!");
//        }else {
//            articleRepository.deleteById(articleId);
//        }
//    }
//
//    @Transactional
//    public void modifyArticleInformation(Long articleId
//            , String title
//            , List bibliography) {
//
//        Article fetchedArticle= articleRepository.findById(articleId)
//                .orElseThrow(()->
//                        new IllegalStateException("Article with id: "+ articleId +" does not exist to modify it"));
//
//        if (title !=null && title.length() > 0 &&
//                !Objects.equals(fetchedArticle.getTitle(),title)){
//            Optional<Article> articleFoundByTitle=articleRepository.findByTitle(title);
//            if (articleFoundByTitle.isPresent()){
//                throw new IllegalStateException(
//                        "Article with id: "+ articleId+" does not exist to modify" );
//            }
//            fetchedArticle.setTitle(title);
//        }
//
//        if (bibliography !=null && bibliography.size() >0
//        && !Objects.equals(fetchedArticle.getBibliography(),bibliography)){
//            fetchedArticle.setBibliography(bibliography);
//        }
//
//    }
//}

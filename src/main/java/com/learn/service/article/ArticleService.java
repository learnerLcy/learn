package com.learn.service.article;

import com.learn.PoJo.article.Article;
import com.learn.service.BaseService;

import java.util.List;

/**
 * @ClassName:ArticleService
 * @Description:
 * @Author:lvchunyang
 * @Date:19:42
 **/
public interface ArticleService extends BaseService<Article,String> {
    List<Article> select_Artificial(Article article);
}

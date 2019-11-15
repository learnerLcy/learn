package com.learn.service.article.impl;

import com.learn.PoJo.article.Article;
import com.learn.mapper.article.ArticleMapper;
import com.learn.mapper.BaseMapper;
import com.learn.service.BaseServiceImpl;
import com.learn.service.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName:ArticleServiceImpl
 * @Description:
 * @Author:lvchunyang
 * @Date:19:45
 **/
@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article,String> implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public BaseMapper<Article, String> getMappser() {
        return articleMapper;
    }

    @Override
    public List<Article> select_Artificial(Article article) {
        return articleMapper.select_Artificial(article);
    }
}

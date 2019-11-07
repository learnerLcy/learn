package com.learn.service.system.impl.article;

import com.learn.PoJo.article.Article;
import com.learn.mapper.article.ArticleMapper;
import com.learn.mapper.system.BaseMapper;
import com.learn.service.BaseServiceImpl;
import com.learn.service.system.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

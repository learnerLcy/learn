package com.learn.service.system.impl.article;

import com.learn.PoJo.article.ArticleCategory;
import com.learn.mapper.article.ArticleCategoryMapper;
import com.learn.mapper.system.BaseMapper;
import com.learn.service.BaseServiceImpl;
import com.learn.service.system.article.ArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName:ArticleTypesServiceImpl
 * @Description:
 * @Author:lvchunyang
 * @Date:14:40
 **/
@Service
public class ArticleCategoryServiceImpl extends BaseServiceImpl<ArticleCategory,String> implements ArticleCategoryService {

    @Autowired
    private ArticleCategoryMapper article_typesMapper;

    @Override
    public BaseMapper<ArticleCategory, String> getMappser() {
        return article_typesMapper;
    }
}

package com.learn.service.article.impl;

import com.learn.PoJo.article.ArticleCategory;
import com.learn.mapper.article.ArticleCategoryMapper;
import com.learn.mapper.BaseMapper;
import com.learn.service.BaseServiceImpl;
import com.learn.service.article.ArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName:ArticleTypesServiceImpl
 * @Description:
 * @Author:lvchunyang
 * @Date:14:40
 **/
@Service
public class ArticleCategoryServiceImpl extends BaseServiceImpl<ArticleCategory,String> implements ArticleCategoryService {

    @Autowired
    private ArticleCategoryMapper articleCategoryMapper;

    @Override
    public BaseMapper<ArticleCategory, String> getMappser() {
        return articleCategoryMapper;
    }

    @Override
    public List<ArticleCategory> select_Artificial(ArticleCategory articleCategory) {
        return articleCategoryMapper.select_Artificial(articleCategory);
    }
}

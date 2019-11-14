package com.learn.service.system.article;

import com.learn.PoJo.article.ArticleCategory;
import com.learn.service.BaseService;

import java.util.List;


/**
 * @ClassName:ArticleTypesService
 * @Description: 文章类型service
 * @Author:lvchunyang
 * @Date:14:38
 **/
public interface ArticleCategoryService extends BaseService<ArticleCategory,String> {
    //通用mapepr不提供联表查询，自己联表查询
    List<ArticleCategory> select_Artificial(ArticleCategory articleCategory);
}

package com.learn.mapper.article;

import com.learn.PoJo.article.ArticleCategory;
import com.learn.mapper.system.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleCategoryMapper extends BaseMapper<ArticleCategory,String> {

    @Select("SELECT a.id id," +
            "a.category category," +
            "a.description description," +
            "a.category_pid category_pid," +
            "a.flag flag, " +
            "b.category categoryPName " +
            "FROM article_category a "+
            "left join article_category b on a.category_pid=b.id "+
            "where 1=1 "
    )
    List<ArticleCategory> select_Artificial(ArticleCategory articleCategory);
}
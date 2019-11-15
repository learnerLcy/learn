package com.learn.mapper.article;

import com.learn.PoJo.article.Article;
import com.learn.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article,String> {
    @Select("SELECT a.id id," +
            "a.article_type_id articleTypeId," +
            "a.title title," +
            "a.content content," +
            "a.text text, " +
            "a.html html, " +
            "a.modifytime modifytime, " +
            "a.user_id user_id, " +
            "b.category articleTypeName " +
            "FROM article a "+
            "left join article_category b on b.id=a.article_type_id "+
            "where 1=1 "
           // "and user_id={article.user_id} "
    )
    List<Article> select_Artificial(Article article);
}
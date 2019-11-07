package com.learn.mapper.article;

import com.learn.PoJo.article.Article;
import com.learn.mapper.system.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapper<Article,String> {
}
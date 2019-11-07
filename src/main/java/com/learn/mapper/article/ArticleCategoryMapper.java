package com.learn.mapper.article;

import com.learn.PoJo.article.ArticleCategory;
import com.learn.mapper.system.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleCategoryMapper extends BaseMapper<ArticleCategory,String> {
}
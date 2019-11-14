package com.learn.PoJo.article;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "article_category")
@Data
public class ArticleCategory {
    @Id
    private String id;

    private String category;

    private String description;

    private String category_pid;

    private String flag;

    //父类型
    @Transient
    private String categoryPName;
}
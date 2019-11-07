package com.learn.PoJo.article;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Table(name = "article")
@Data
public class Article implements Serializable {

    @Id
    private String id;

    private String articleTypeId;

    private String title;

    private Date modifytime;

    private String userId;

    private byte[] content;

    @Transient
    private String contentStr;

    private String text;

    private String html;

}
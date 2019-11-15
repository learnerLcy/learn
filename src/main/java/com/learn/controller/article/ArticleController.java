package com.learn.controller.article;

import com.learn.PoJo.PageData;
import com.learn.PoJo.Result;
import com.learn.PoJo.article.Article;
import com.learn.service.article.ArticleCategoryService;
import com.learn.service.article.ArticleService;
import com.learn.utils.CommonConstant;
import com.learn.utils.CommonUtils;
import com.learn.utils.ConventUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @ClassName:ArticleCategory
 * @Description:
 * @Author:lvchunyang
 * @Date:14:54
 **/
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleCategoryService articleCategoryService;

    /**
    *@Author:lvchunyang
    *@Description: 文章分类列表页面
    *@Date:14:55 2019/7/20
    *@Para:[mv]
    *@Return:org.springframework.web.servlet.ModelAndView
    **/
    @RequestMapping("/showArticlePage")
    public ModelAndView showMenuPage(ModelAndView mv){
        mv.setViewName("/article/article/showArticlePage");
        return mv;
    }
    /**
    *@Author:lvchunyang
    *@Description: 文章分类列表数据
    *@Date:15:04 2019/7/20
    *@Para:[articleCategory, pageData]
    *@Return:com.learn.PoJo.Result
    **/
    @RequestMapping("/getArticleList")
    @ResponseBody
    public Result getCategoryList(Article article, PageData pageData){
        return articleService.show(articleService.select_Artificial(article),pageData.getPage(), pageData.getLimit());
    }
    /**
    *@Author:lvchunyang
    *@Description: 文章分类增加/编辑页面
    *@Date:15:06 2019/7/20
    *@Para:[mv]
    *@Return:org.springframework.web.servlet.ModelAndView
    **/
    @RequestMapping("/addArticlePage")
    public ModelAndView addCategoryPage(ModelAndView mv){
        mv.setViewName("/article/article/addArticlePage");
        return mv;
    }
    @RequestMapping("/editArticlePage")
    public ModelAndView editMenuPage(ModelAndView mv,Article article){
        Article articleInfo = articleService.selectByPrimaryKey(article.getId());
        if(StringUtils.isNotBlank(articleInfo.getArticleTypeId())){
            articleInfo.setArticleTypeName(articleCategoryService.selectByPrimaryKey(articleInfo.getArticleTypeId()).getCategory());
        }
        articleInfo.setContentStr(new String(articleInfo.getContent()));
        mv.addObject("articleInfo",articleInfo);
        mv.setViewName("/article/article/editArticlePage");
        return mv;
    }
    /**
    *@Author:lvchunyang
    *@Description: 增加/修改实现
    *@Date:15:25 2019/7/20
    *@Para:[articleCategory]
    *@Return:com.learn.PoJo.Result
    **/
    @RequestMapping("/addArticle")
    @ResponseBody
    public Result addCategory(Article article){
        article.setId(CommonUtils.get32Uuid());
        article.setText(ConventUtil.html2Text(article.getHtml()));
        article.setModifytime(new Date());
        return articleService.defaultOperate(articleService.insert(article), CommonConstant.ADD_CH);
    }
    @RequestMapping("/editArticle")
    @ResponseBody
    public Result editCategory(Article article){
        article.setText(ConventUtil.html2Text(article.getHtml()));
        article.setModifytime(new Date());
        return articleService.defaultOperate(articleService.updateByPrimaryKey(article), CommonConstant.UPDATE_CH);
    }
    @RequestMapping("/deleteArticle")
    @ResponseBody
    public Result deleteArticle(Article article){
        return articleService.defaultOperate(articleService.deleteByPrimaryKey(article.getId()), CommonConstant.DELETE_CH);
    }
}

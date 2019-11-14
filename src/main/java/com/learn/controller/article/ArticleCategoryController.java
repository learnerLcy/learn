package com.learn.controller.article;

import com.learn.Constants.Constants;
import com.learn.PoJo.PageData;
import com.learn.PoJo.Result;
import com.learn.PoJo.article.ArticleCategory;
import com.learn.service.system.article.ArticleCategoryService;
import com.learn.utils.CommonConstant;
import com.learn.utils.CommonUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName:ArticleCategory
 * @Description:
 * @Author:lvchunyang
 * @Date:14:54
 **/
@Controller
@RequestMapping("/article/category")
public class ArticleCategoryController {

    @Autowired
    private ArticleCategoryService articleCategoryService;

    /**
    *@Author:lvchunyang
    *@Description: 文章分类列表页面
    *@Date:14:55 2019/7/20
    *@Para:[mv]
    *@Return:org.springframework.web.servlet.ModelAndView
    **/
    @RequestMapping("/showCategoryPage")
    public ModelAndView showMenuPage(ModelAndView mv){
        mv.setViewName("/article/category/showCategoryPage");
        return mv;
    }
    /**
    *@Author:lvchunyang
    *@Description: 文章分类列表数据
    *@Date:15:04 2019/7/20
    *@Para:[articleCategory, pageData]
    *@Return:com.learn.PoJo.Result
    **/
    @RequestMapping("/getCategoryList")
    @ResponseBody
    public Object getCategoryList(ArticleCategory articleCategory, PageData pageData){
        return articleCategoryService.show(articleCategoryService.select_Artificial(articleCategory),pageData.getPage(), pageData.getLimit());
    }
    /**
    *@Author:lvchunyang
    *@Description: 文章分类增加/编辑页面
    *@Date:15:06 2019/7/20
    *@Para:[mv]
    *@Return:org.springframework.web.servlet.ModelAndView
    **/
    @RequestMapping("/addCategoryPage")
    public ModelAndView addCategoryPage(ModelAndView mv){
        mv.setViewName("/article/category/addCategoryPage");
        return mv;
    }
    @RequestMapping("/editCategoryPage")
    public ModelAndView editMenuPage(ModelAndView mv,ArticleCategory articleCategory){
        ArticleCategory articleCategoryInfo = articleCategoryService.selectByPrimaryKey(articleCategory.getId());
        if(StringUtils.isNotBlank(articleCategoryInfo.getCategory_pid()) && !articleCategoryInfo.getCategory_pid().equals(Constants.ROOT)){
            articleCategoryInfo.setCategoryPName(articleCategoryService.selectByPrimaryKey(articleCategoryInfo.getCategory_pid()).getCategory());
        }
        mv.addObject("articleCategoryInfo",articleCategoryInfo);
        mv.setViewName("/article/category/editCategoryPage");
        return mv;
    }
    /**
    *@Author:lvchunyang
    *@Description: 增加/修改实现
    *@Date:15:25 2019/7/20
    *@Para:[articleCategory]
    *@Return:com.learn.PoJo.Result
    **/
    @RequestMapping("/addCategory")
    @ResponseBody
    public Result addCategory(ArticleCategory articleCategory){
        articleCategory.setId(CommonUtils.get32Uuid());
        return articleCategoryService.defaultOperate(articleCategoryService.insert(articleCategory), CommonConstant.ADD_CH);
    }
    @RequestMapping("/editCategory")
    @ResponseBody
    public Result editCategory(ArticleCategory articleCategory){
        return articleCategoryService.defaultOperate(articleCategoryService.updateByPrimaryKey(articleCategory), CommonConstant.UPDATE_CH);
    }
    @RequestMapping("/deleteCategory")
    @ResponseBody
    public Result deleteCategory(ArticleCategory articleCategory){
        return articleCategoryService.defaultOperate(articleCategoryService.deleteByPrimaryKey(articleCategory.getId()), CommonConstant.DELETE_CH);
    }
    /**
    *@Author:lvchunyang
    *@Description: 跳转选择类型页面
    *@Date:13:20 2019/10/29
    *@Para:
    *@Return:
    **/
    @RequestMapping("/selectCategoryPage")
    public ModelAndView selectCategoryPage(ModelAndView mv,String articleTypeId){
        mv.addObject("articleTypeId",articleTypeId);
        mv.setViewName("/article/category/selectCategoryPage");
        return mv;
    }
}

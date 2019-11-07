package com.learn.controller.file;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @ClassName:FileCOntroller
 * @Description: 文件
 * @Author:lvchunyang
 * @Date:11:51
 **/
@Controller
@RequestMapping("/file")
public class FileController {
    /**
     * editormd的图片上传
     * @param file
     * @param request
     * @param response
     * @return
     * @throws Exception
     * 期望的返回值
     * {success : 0 | 1, //0表示上传失败;1表示上传成功
     *     message : "提示的信息",
     *     url     : "图片地址" //上传成功时才返回}
     */
    @RequestMapping("/fileUpload_Md")
    @ResponseBody
    public JSONObject editormdPic (@RequestParam(value = "editormd-image-file", required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception{

        String fold = request.getParameter("fold");

        String trueFileName = file.getOriginalFilename();

        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));

        String fileName = System.currentTimeMillis()+suffix;

        String path = request.getSession().getServletContext().getRealPath(fold);
        System.out.println(path);

        File targetFile = new File(path);
        File uploadfile = new File(path+fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        /*uploadfile.createNewFile();*/
        //保存
        try {
            /*file.transferTo(uploadfile);*/
            FileUtils.copyInputStreamToFile(file.getInputStream(), uploadfile);
        } catch (Exception e) {
            e.printStackTrace();
        }


        JSONObject res = new JSONObject();
        res.put("url", fold+fileName);
        res.put("success", 1);
        res.put("message", "upload success!");
        return res;
    }

    /**
     * 描述：下载外部案件导入模板
     * @throws Exception
     */
    @RequestMapping(value = "/downloadExcel")
    @ResponseBody
    public void downloadExcel(HttpServletResponse res, HttpServletRequest req,String fileName,String filePath) throws Exception {
        ServletOutputStream out;
        res.setContentType("multipart/form-data");
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/html");
        String realfilePath = getClass().getResource(filePath).getPath();
        String userAgent = req.getHeader("User-Agent");
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
             fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else {
             // 非IE浏览器的处理：
             fileName = new String((fileName).getBytes("UTF-8"), "ISO-8859-1");
        }
        realfilePath = URLDecoder.decode(realfilePath, "UTF-8");
        res.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        FileInputStream inputStream = new FileInputStream(realfilePath);
        out = res.getOutputStream();
        int b = 0;
        byte[] buffer = new byte[1024];
        while ((b = inputStream.read(buffer)) != -1) {
            // 4.写到输出流(out)中
            out.write(buffer, 0, b);
        }
        inputStream.close();
        if (out != null) {
            out.flush();
            out.close();
        }
    }
}

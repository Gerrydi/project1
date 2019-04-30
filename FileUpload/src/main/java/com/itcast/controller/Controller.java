package com.itcast.controller;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@org.springframework.stereotype.Controller
@RequestMapping(path = ("/user"))
public class Controller {
    @RequestMapping("/test1")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.sendRedirect("/upload.jsp");


    }

    @RequestMapping("/uploadFile")
    public String uploadFile(HttpServletRequest request, MultipartFile upload) throws IOException {
        //使用springmvc框架编写上传文件代码
        //第一步:写入上传的位置

        /*
        String path = request.getSession().getServletContext().getRealPath（"/upload/"）
        */
        String path = request.getSession().getServletContext().getRealPath("/upload");
        File file = new File(path);
        //第二步:判断这个路径是否存在

            if(!file.exists()){
                //如果不存在,那么在生成一个
                file.mkdirs();
            }
        //第三步:获取上传文件的名称
        String filename = upload.getOriginalFilename();
        //把文件的名称设置成唯一值
        String s = UUID.randomUUID().toString().replace("-", "");
        filename = s +"_"+ filename;
        //完成上传文件
        upload.transferTo(new File(path,filename));
        return "sucess";
    }

    /**
     * 传统方式上传文件
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/uploadFile2")
    public String uploadFile2(HttpServletRequest request) throws Exception {
        //使用springmvc框架编写上传文件代码
        //第一步:写入上传的位置

        /*
        String path = request.getSession().getServletContext().getRealPath（"/upload/"）
        */
        String path = request.getSession().getServletContext().getRealPath("/upload/");
        File file = new File(path);
        //第二步:判断这个路径是否存在
        if(!file.exists()){
            //如果不存在,那么在生成一个
            file.mkdirs();
        }
        //解析request请求
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(request);
        //遍历结果集
        for (FileItem item : items) {
            //判断item是否是当前上传文件项
            //item.isFormileld是否是普通属性
            if(item.isFormField()){
            //他不是普通属性,在else中操作
        }else{
                //获取上传文件名称
                String filename = item.getName();
                //把文件的名称设置成唯一值
                String s = UUID.randomUUID().toString().replace("-", "");
                filename = s +"_"+filename;
                //完成上传文件
                item.write(new File(path,filename));
            }
        }
        return "sucess";
    }
}

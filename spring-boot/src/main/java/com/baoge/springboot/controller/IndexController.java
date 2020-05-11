package com.baoge.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.baoge.springboot.untils.ImageUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * @Author shaoxubao
 * @Date 2020/5/11 16:26
 */

@RestController
public class IndexController {

    /**
     * 文件图片上传
     */
    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadLogo(@RequestParam("file") MultipartFile file) {
        if (file  != null) {
            if (!this.validFile(file)) {
                return "wrong file format";
            }
            JSONObject jo =  uploadFile(file);
            if("1".equals(jo.get("code"))){
               return "上传失败";
            }
        }

        return "right file format";
    }
    /**
     * 校验文件格式
     */
    private Boolean validFile(MultipartFile file){
        final String fileName = file.getOriginalFilename();
        String fileSuffix = FilenameUtils.getExtension(fileName).toLowerCase();
        List<String> validFileSuffix = Arrays.asList("js", "php", "html");
        return !validFileSuffix.contains(fileSuffix);
    }

    /**
     * 文件上传
     */
    private JSONObject uploadFile(MultipartFile uploadFilePic) {
        JSONObject map = new JSONObject();
        map.put("fileName", uploadFilePic.getOriginalFilename());
        map.put("code", "success");
        String url = "";
        try {
            url = ImageUtils.upload(uploadFilePic);
        } catch (Exception e) {
            System.out.println(e);
            map.put("code", "fail");
        }
        map.put("url", url);
        return map;
    }

}

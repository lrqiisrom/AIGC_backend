package com.example.aigc_backend.controller;

import com.example.aigc_backend.pojo.Files;
import com.example.aigc_backend.service.FilesService;
import com.example.aigc_backend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/files")
public class FilesController {
    @Autowired
    private FilesService filesService;
    @GetMapping("/list")
    public Result<List<Files>> filesList() {
        List<Files> filesList = filesService.getFiles();
        return Result.success(filesList);
    }

    @GetMapping("/getContent/{id}")
    public Result<String> getContentById(@PathVariable(value = "id") Integer id) {
        String res = filesService.getContentById(id);
        if(res != null) {
            return Result.success(res);
        }
        else {
            return Result.error("查看失败");
        }
    }

    @PostMapping("/upload")
    public Result uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            System.out.println(file);
            byte[] bytes = file.getBytes();
            String fileContent = new String(bytes);
            String fileName = file.getOriginalFilename();
            Integer textnum = fileContent.length();
            if(filesService.addFiles(fileName,fileContent,textnum)) {
                return Result.success();
            }
            else {
                return Result.error("未知错误");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("文件上传失败");
        }
    }
}

package com.example.aigc_backend.service;


import com.example.aigc_backend.pojo.Files;

import java.util.List;

public interface FilesService {
    Boolean addFiles(String name, String content, Integer textnum);
    List<Files> getFiles();
    Boolean deleteById(Integer id);
    String getContentById(Integer id);
}

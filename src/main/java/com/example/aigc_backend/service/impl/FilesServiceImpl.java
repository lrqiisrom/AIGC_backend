package com.example.aigc_backend.service.impl;

import com.example.aigc_backend.pojo.Files;
import com.example.aigc_backend.mapper.FilesMapper;
import com.example.aigc_backend.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilesServiceImpl implements FilesService {
    @Autowired
    private FilesMapper filesMapper;
    @Override
    public Boolean addFiles(String name, String content, Integer textnum) {
        try {
            filesMapper.insertFile(name,content,textnum);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Files> getFiles() {
        return filesMapper.selectFiles();
    }

    @Override
    public Boolean deleteById(Integer id) {
        try {
            filesMapper.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getContentById(Integer id) {
        try {
            return filesMapper.getContentById(id);
        } catch (Exception e) {
            return null;
        }
    }
}

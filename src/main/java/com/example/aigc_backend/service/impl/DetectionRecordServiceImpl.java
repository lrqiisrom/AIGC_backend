package com.example.aigc_backend.service.impl;

import com.example.aigc_backend.mapper.DetectionRecordMapper;
import com.example.aigc_backend.mapper.FilesMapper;
import com.example.aigc_backend.pojo.DetectionRecord;
import com.example.aigc_backend.pojo.Files;
import com.example.aigc_backend.service.DetectionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetectionRecordServiceImpl implements DetectionRecordService {
    @Autowired
    private DetectionRecordMapper detectionRecordMapper;
    @Autowired
    private FilesMapper filesMapper;
    @Override
    public Boolean addDetectionRecord(Integer fileId, Double result) {
        Files file = filesMapper.getById(fileId);
        try {
            detectionRecordMapper.insertRecord(file.getName(), file.getContent(), result);
            filesMapper.deleteById(fileId);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<DetectionRecord> getDetectionRecordList() {
        return detectionRecordMapper.selectDetectionRecord();
    }

    @Override
    public String getContentById(Integer id) {
        return detectionRecordMapper.getContentById(id);
    }

    @Override
    public String getContentByIdFromFileList(Integer id) {
        return filesMapper.getContentById(id);
    }
}

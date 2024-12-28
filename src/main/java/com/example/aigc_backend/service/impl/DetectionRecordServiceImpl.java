package com.example.aigc_backend.service.impl;

import com.example.aigc_backend.mapper.DetectionRecordMapper;
import com.example.aigc_backend.pojo.DetectionRecord;
import com.example.aigc_backend.service.DetectionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetectionRecordServiceImpl implements DetectionRecordService {
    @Autowired
    private DetectionRecordMapper detectionRecordMapper;
    @Override
    public Boolean addDetectionRecord(String name, String content, Double result) {
        try {
            detectionRecordMapper.insertRecord(name,content,result);
            return true;
        } catch (Exception e) {
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
}

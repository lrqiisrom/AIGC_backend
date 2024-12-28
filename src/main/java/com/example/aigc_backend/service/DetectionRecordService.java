package com.example.aigc_backend.service;

import com.example.aigc_backend.pojo.DetectionRecord;
import com.example.aigc_backend.pojo.Files;

import java.util.List;

public interface DetectionRecordService {
    Boolean addDetectionRecord(Integer fileId, Double result);
    List<DetectionRecord> getDetectionRecordList();
    String getContentById(Integer id);
    String getContentByIdFromFileList(Integer id);
}

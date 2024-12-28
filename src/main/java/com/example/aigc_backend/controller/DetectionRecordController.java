package com.example.aigc_backend.controller;

import com.example.aigc_backend.pojo.DetectionRecord;
import com.example.aigc_backend.service.DetectionRecordService;
import com.example.aigc_backend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/record")
public class DetectionRecordController {
    @Autowired
    private DetectionRecordService detectionRecordService;
    @GetMapping("/list")
    public Result<List<DetectionRecord>> filesList() {
        List<DetectionRecord> filesList = detectionRecordService.getDetectionRecordList();
        return Result.success(filesList);
    }
    @PostMapping("/addFiles")
    public Result addRecord(@RequestBody Map<String, String> filesMap) {
        String name = filesMap.get("name");
        String content = filesMap.get("content");
        Double result = 0.2;
        if(detectionRecordService.addDetectionRecord(name,content,result)) {
            return Result.success();
        }else {
            return Result.error("未知错误");
        }
    }
}

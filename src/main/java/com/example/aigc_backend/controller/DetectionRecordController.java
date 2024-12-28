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
    @GetMapping("/getContent/{id}")
    public Result<String> getContentById(@PathVariable(value = "id") Integer id) {
        String res = detectionRecordService.getContentById(id);
        if(res != null) {
            return Result.success(res);
        }
        else {
            return Result.error("查看失败");
        }
    }
    @PostMapping("/addRecords")
    public Result addRecord(@RequestBody Map<String, List<Integer>> recordMap) {
        List<Integer> fileIdList = recordMap.get("fileIds");
        System.out.println(fileIdList);
        Double result = 0.2;
        for (Integer fileId : fileIdList) {
            if(!detectionRecordService.addDetectionRecord(fileId,result)) return Result.error("检测出现错误");
        }
        return Result.success();
    }
}

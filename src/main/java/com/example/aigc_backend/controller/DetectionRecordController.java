package com.example.aigc_backend.controller;

import com.example.aigc_backend.pojo.DetectionRecord;
import com.example.aigc_backend.service.DetectionRecordService;
import com.example.aigc_backend.utils.Result;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
//import org.springframework.ai.moonshot.MoonshotChatModel;
import org.springframework.ai.zhipuai.ZhiPuAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/record")
public class DetectionRecordController {
    @Autowired
    private DetectionRecordService detectionRecordService;

//    private final MoonshotChatModel chatModel;
    private final ZhiPuAiChatModel chatModel;


    @Autowired
    public DetectionRecordController(ZhiPuAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    //测试接口
    @CrossOrigin
    @GetMapping("/ai/generate")
    public Map generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        message = "我会给你个文本，你需要帮我判断这些文本是否是AIGC的，给我一个表示内容是AIGC的概率。注意：只用给出概率（范围为（0，1）开区间），其他什么也别说。文本如下：\n i love xjtu";
        return Map.of("generation", this.chatModel.call(message));
    }

    //测试接口
    @GetMapping("/ai/generateStream")
    public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        var prompt = new Prompt(new UserMessage(message));
        return this.chatModel.stream(prompt);
    }
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
    public Result addRecord(@RequestBody Map<String, List<Integer>> recordMap) throws InterruptedException {
        List<Integer> fileIdList = recordMap.get("fileIds");
        //System.out.println(fileIdList);
        for (Integer fileId : fileIdList) {
            String msg = detectionRecordService.getContentByIdFromFileList(fileId);
            String aiRes = this.chatModel.call("我会给你个文本，你需要帮我判断这些文本是否是AIGC的，给我一个表示文本内容是AIGC的概率（类似于论文的AIGC率）。注意：只用给出概率（小数），并且不能太绝对，其他什么也别说。文本如下：\n" + msg);
            Double result = Double.parseDouble(aiRes);
            if(!detectionRecordService.addDetectionRecord(fileId,result)) return Result.error("检测出现错误");
        }
        return Result.success();
    }
}

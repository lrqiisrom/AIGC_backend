package com.example.aigc_backend.mapper;

import com.example.aigc_backend.pojo.DetectionRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DetectionRecordMapper {
    @Select("select * from detection_record")
    List<DetectionRecord> selectDetectionRecord();
    @Select("select content from detection_record where id = #{id}")
    String getContentById(Integer id);
    @Insert("insert into detection_record(name,content,result) values (#{name},#{content},#{result})")
    void insertRecord(@Param("name") String name, @Param("content") String content, @Param("result") Double result);
}

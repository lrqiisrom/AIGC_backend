package com.example.aigc_backend.mapper;

import com.example.aigc_backend.pojo.Files;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FilesMapper {
    @Select("select * from files")
    List<Files> selectFiles();
    @Select("select content from files where id = #{id}")
    String getContentById(Integer id);
    @Select("select * from files where id = #{id}")
    Files getById(Integer id);
    @Insert("insert into files(name,content,textnum) values (#{name},#{content},#{textnum})")
    void insertFile(@Param("name") String name, @Param("content") String content, @Param("textnum") Integer textnum);
    @Delete("delete from files where id = #{id}")
    void deleteById(@Param("id") Integer id);
}

package com.zclu.interviewtips.mapper;

import com.zclu.interviewtips.entity.Tips;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper  // MyBatis 的 Mapper 注解
public interface TipsMapper {

    // 通过ID查询
    @Select("SELECT * FROM tips WHERE id = #{id}")
    Tips findById(Integer id);

    // 查询所有
    @Select("SELECT * FROM tips")
    List<Tips> findAll();

    // 插入新的
    @Insert("INSERT INTO tips(text, source, title) VALUES(#{text}, #{source}, #{title})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Tips tips); // 插入返回影响行数

    // 根据ID删除
    @Delete("DELETE FROM tips WHERE id = #{id}")
    int deleteById(Integer id); // 删除返回影响行数

    // 更新
    @Update("UPDATE tips SET text = #{text}, source = #{source}, title = #{title} WHERE id = #{id}")
    int update(Tips tips); // 更新返回影响行数
}

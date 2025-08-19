package com.zclu.interviewtips.mapper;


import com.zclu.interviewtips.entity.Problems;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProblemsMapper {
    @Select("SELECT * FROM problems WHERE id = #{id}")
    Problems findById(Integer id);

    @Select("SELECT * FROM problems")
    List<Problems> findAll();

    @Insert("INSERT INTO problems(question, source) VALUES(#{question}, #{source})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Problems problems);

    //    Search
    @Select(
            "SELECT * FROM problems " +
                    "WHERE (#{question} IS NULL OR question LIKE CONCAT('%', #{question}, '%'))" +
                    "AND (#{tag} IS NULL OR tag LIKE CONCAT('%', #{tag}, '%'))" +
                    "LIMIT #{offset}, #{pagesize}"
    )
    List<Problems> searchByQuestion(
            @Param("question") String kw,
            @Param("tag") String tag,
            @Param("offset") Integer offset,
            @Param("pagesize") Integer pagesize
    );

    @Select("SELECT COUNT(*) FROM problems " +
            "WHERE (#{question} IS NULL OR question LIKE CONCAT('%', #{question}, '%'))" +
            "AND (#{tag} IS NULL OR tag LIKE CONCAT('%', #{tag}, '%'))")
    int countProblems(
            @Param("question") String kw,
            @Param("tag") String tag
    );

    // Update Tag
    @Update("UPDATE problems SET tag = #{tag} WHERE id = #{id}")
    int updateTag(@Param("id") Integer id, @Param("tag") String tag);

    // Random
    @Select("SELECT * FROM problems ORDER BY RAND() LIMIT 1")
    Problems getRandomProblem();
}

package com.zclu.interviewtips.mapper;

import com.zclu.interviewtips.entity.AdministrativeTest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdministrativeTestMapper {
    @Select("SELECT * FROM administrative_test ORDER BY id DESC LIMIT 1")
    AdministrativeTest findLatest();

    @Select("SELECT * FROM administrative_test")
    List<AdministrativeTest> findAll();

    @Insert("INSERT INTO administrative_test(question) VALUES(#{question})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(AdministrativeTest administrativeTest);

}
